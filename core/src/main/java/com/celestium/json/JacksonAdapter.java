package com.celestium.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class JacksonAdapter {

    public final ObjectMapper factory = new ObjectMapper();

    /**
     * Transforms JSON String into Class.
     *
     * @param value JSON String
     * @param <T> Class that the JSON String will be transformed into
     *
     * @return Object from JSON String
     */
    public <T> T fromJson(final String value, final Class<T> clazz) {
        try {
            return factory.readValue(value, clazz);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Transforms the class into JSON string.
     *
     * @param <T> Class to be transformed
     * @return String JSON String
     */
    public <T> String toJson(final T clazz) {
        try {
            return factory.writeValueAsString(clazz);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /*
    Creates a empty ObjectNode
     */
    public ObjectNode createNode() {
        return factory.createObjectNode();
    }

    /**
     * Creates a ObjectNode by a JSON String.
     *
     * @param context JSON String
     * @return ObjectNode
     */
    public ObjectNode getNode(final String context)  {
        try {
            return factory.readValue(context, ObjectNode.class);
        } catch (JsonProcessingException exception) {
            System.out.println("Error while returning node, " + exception.getMessage());
        }

        return null;
    }

    /**
     * Adds a ArrayList formatted to a node.
     *
     * @param node ObjectNode
     * @param list ArrayList
     * @param value String
     *
     * @return ObjectNode
     */
    public ObjectNode addArrayToNode(final ObjectNode node, final ArrayList<?> list, final String value)  {
        final ArrayNode arrayNode = factory.valueToTree(list);
        node.putArray(value).addAll(arrayNode);

        return node;
    }

    /**
     * Gets ArrayList from Node.
     *
     * @param node ObjectNode
     * @param value String
     *
     * @return ArrayList<T>
     */
    public ArrayList<?> getArrayFromNode(final ObjectNode node, final String value)  {
        return factory.convertValue(node.get(value), ArrayList.class);
    }

    public JsonNode get(final ObjectNode node, final String value) {
        return node.get(value);
    }

    public String getString(final ObjectNode node, final String value) {
        return get(node, value).textValue();
    }

    public int getInt(final ObjectNode node, final String value) {
        return get(node, value).intValue();
    }

    public boolean getBoolean(final ObjectNode node, final String value) {
        return get(node, value).booleanValue();
    }

    public Double getDouble(final ObjectNode node, final String value) {
        return get(node, value).doubleValue();
    }

    public float getFloat(final ObjectNode node, final String value) {
        return get(node, value).floatValue();
    }

}
