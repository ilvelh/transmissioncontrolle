package ca.teyssedre.android.transmissioncontrol.model.item;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ElementStatusDeserializer extends JsonDeserializer<TransmissionElementStatus> {

    public ElementStatusDeserializer() {
    }

    @Override
    public TransmissionElementStatus deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return TransmissionElementStatus.parse(p.getIntValue());
    }
}