package com.springpracticesdemo.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.springpracticesdemo.enums.Origin;

public class DoubleToIntDeserializer extends JsonDeserializer<Origin> {

    @Override
    public Origin deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return Origin.getByValue(p.getValueAsInt());
    }
}
