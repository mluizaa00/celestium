use tokio::net::{TcpListener, TcpStream};
use tokio::io::AsyncReadExt;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let listener = TcpListener::bind("127.0.0.1:8080").await?;
    println!("Server listening on port 8080...");

    loop {
        let (stream, _) = listener.accept().await?;
        println!("New connection: {:?}", stream.peer_addr());

        tokio::spawn(async move {
            handle_client(stream).await;
        });
    }
}

async fn handle_client(mut stream: TcpStream) {
    let mut buffer = [0; 1024];
    loop {
        match stream.read(&mut buffer).await {
            Ok(bytes_read) => {
                if bytes_read == 0 {
                    println!("Connection closed by client.");
                    break;
                }

                let message = String::from_utf8_lossy(&buffer[..bytes_read]);
                println!("Received message: {}", message);
            }
            Err(e) => {
                eprintln!("Error reading from stream: {}", e);
                break;
            }
        }
    }
}