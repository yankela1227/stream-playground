package countries;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class RegionSerializer extends StdSerializer<Region> {

    public RegionSerializer() {
        super(Region.class);
    }

    @Override
    public void serialize(Region value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(
            switch (value) {
                case AFRICA -> "Africa";
                case AMERICAS -> "Americas";
                case ASIA -> "Asia";
                case EUROPE -> "Europe";
                case OCEANIA -> "Oceania";
                case POLAR -> "Polar";
                case UNSPECIFIED -> "";
            }
        );
    }

}