package countries;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.DeserializationContext;

public class RegionDeserializer extends StdDeserializer<Region> {

    public RegionDeserializer() {
        super(Region.class);
    }

    @Override
    public Region deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String s = p.getValueAsString();
        return switch (s) {
            case "Africa" -> Region.AFRICA;
            case "Americas" -> Region.AMERICAS;
            case "Asia" -> Region.ASIA;
            case "Europe" -> Region.EUROPE;
            case "Oceania" -> Region.OCEANIA;
            case "Polar" -> Region.POLAR;
            case "" -> Region.UNSPECIFIED;
            default -> throw ctxt.weirdStringException(s, Region.class, "Invalid region name");
        };
    }

}