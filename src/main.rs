use tokio::net::{TcpListener, TcpStream};
use tokio::io::AsyncReadExt;
use dotenv::dotenv;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    dotenv().ok();

    let host = std::env::var("HOST").expect("Unable to find host in environment variables.");
    let port = std::env::var("PORT").expect("Unable to find port in environment variables.");

    let address = format!("{}:{}", host, port);
    let listener = TcpListener::bind(address).await?;

    println!("Server listening on host {} and port {}...", host, port);

    loop {
        let (stream, _) = listener.accept().await?;
        println!("New connection with address: {:?}", stream.peer_addr());

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