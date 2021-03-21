stream-playground
=================

Playground for playing with [streams](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/package-summary.html) in Java.

Data is taken from the [REST Countries](https://restcountries.eu/) API from [here](https://restcountries.eu/rest/v2/all?fields=alpha2Code;name;capital;region;area;population;translations;timezones).

Usage:
```console
$ mvn compile jshell:run
jshell> import countries.*;
jshell> List<Country> countries = new CountryRepository().getAll(); // The list of all countries
```

Building and running the project requires JDK 15.

## Implementation Notes

The `RegionSerializer` and `RegionDeserializer` classes are required for technical reasons. In the JSON data, the lack of a country region is denoted by the empty string. Country regions can be easily deserialized as constants of the enum class `Region` as follows:
```java
// Region.java:
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Region {

    @JsonProperty("Africa") AFRICA,
    @JsonProperty("Americas") AMERICAS,
    @JsonProperty("Asia") ASIA,
    @JsonProperty("Europe") EUROPE,
    @JsonProperty("Oceania") OCEANIA,
    @JsonProperty("Polar") POLAR;

}

``` 
In the `CountryRepository` class the empty string can be handled as follows:
```java
private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

OBJECT_MAPPER.coercionConfigFor(Region.class).setCoercion(CoercionInputShape.EmptyString, CoercionAction.AsNull);
```
With this settings the empty string as a region will be deserialized as `null`. However, a `null` region causes problems (i.e., `NullPointerException`s) when grouping is made by region. To avoid these problems a special enum constant, e.g., `Region.UNSPECIFIED` should be used instead of a `null` reference. The following seems to be a straightforward solution:
```
public enum Region {

    @JsonProperty("Africa") AFRICA,
    @JsonProperty("Americas") AMERICAS,
    @JsonProperty("Asia") ASIA,
    @JsonProperty("Europe") EUROPE,
    @JsonProperty("Oceania") OCEANIA,
    @JsonProperty("Polar") POLAR,
    @JsonProperty("") UNSPECIFIED;

}
```
Unfortunately, this will not work, since `""` is not a valid value as a property name for the `@JsonProperty` annotation.

The `RegionSerializer` and `RegionDeserializer` classes use the enum constant `Region.UNSPECIFIED` to represent the empty string.
