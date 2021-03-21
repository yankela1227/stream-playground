package countries;

import com.fasterxml.jackson.annotation.JsonProperty;

@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = RegionSerializer.class)
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = RegionDeserializer.class)
public enum Region {

    AFRICA,
    AMERICAS,
    ASIA,
    EUROPE,
    OCEANIA,
    POLAR,
    UNSPECIFIED;

}