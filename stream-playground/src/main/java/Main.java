import countries.Country;
import countries.CountryRepository;
import countries.Region;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZoneId;
import java.util.*;
import java.util.function.ToLongFunction;

public class Main {
    public static void main(String[] args) {
        List<Country> countries = new CountryRepository().getAll();
        //feladat7(countries);
        //System.out.println(feladat8(countries));
        //feladat9(countries);
        //feladat10(countries);
        //System.out.println(feladat11(countries));
        //feladat12(countries);
        //feladat13(countries);
        //System.out.println(feladat14(countries));
        //System.out.println(feladat17(countries));
        //System.out.println(feladat19(countries));
        //System.out.println(feladat20(countries));
        //System.out.println(feladat3_1(countries));
        //feladat3_2(countries);
        //System.out.println(feladat3_3(countries));
        //System.out.println(feladat3_4(countries));
        //System.out.println(feladat3_4_2(countries));
        //System.out.println(feladat3_6(countries));
        //System.out.println(feladat2_14(countries));
        feladat2_16(countries);
    }

    public static boolean feladat2_1(List<Country> countries){
        return countries.stream()
                .anyMatch(country -> country.getName().toLowerCase().contains("island"));
    }

    public static Optional<String> feladat2_2(List<Country> countries){
        return countries.stream()
                .filter(country -> country.getName().toLowerCase().contains("island"))
                .map(country -> country.getName())
                .findFirst();
    }

    public static void feladat2_3(List<Country> countries){
        countries.stream()
                .filter(country -> country.getName().toLowerCase().charAt(0) == country.getName().toLowerCase().charAt(country.getName().length()-1))
                .map(country -> country.getName())
                .forEach(System.out::println);
    }

    public static void feladat2_4(List<Country> countries){
        countries.stream()
                .mapToLong(Country::getPopulation)
                .sorted()
                .limit(10)
                .forEachOrdered(System.out::println);
    }

    public static void feladat2_5(List<Country> countries){
        countries.stream()
                .mapToLong(country -> country.getPopulation())
                .sorted()
                .limit(10)
                .forEach(System.out::println);
    }

    public static IntSummaryStatistics feladat2_6(List<Country> countries) {
        return countries.stream()
                .mapToInt(country -> country.getTranslations().size())
                .summaryStatistics();
    }

    public static void feladat2_7(List<Country> countries){
        countries.stream()
                .flatMap(country -> country.getTimezones().stream().distinct())
                .sorted()
                .forEach(System.out::println);
    }

    public static void feladat2_8(List<Country> countries){
        countries.stream()
                .map(country -> country.getTimezones())
                .sorted()
                .forEachOrdered(System.out::println);
    }

    public static long feladat2_9(List<Country> countries){
        return countries.stream()
                .filter(country -> country.getTranslations().containsKey("es") == false)
                .count();
    }

    public static void feladat2_10(List<Country> countries){
        countries.stream()
                .filter(country -> country.getArea() == null)
                .map(country -> country.getName())
                .forEachOrdered(System.out::println);
    }

    public static void feladat2_11(List<Country> countries){
        countries.stream()
                .flatMap(country -> country.getTranslations().keySet().stream())
                .sorted()
                .distinct()
                .forEach(System.out::println);
    }

    public static double feladat2_12(List<Country> countries){
        return countries.stream()
                .mapToDouble(country -> country.getName().length())
                .average()
                .getAsDouble();

    }

    public static void feladat2_13(List<Country> countries){
        countries.stream()
                .filter(country -> country.getArea() == null)
                .map(country -> country.getRegion())
                .distinct()
                .forEach(System.out::println);
    }

    public static Optional<Country> feladat2_14(List<Country> countries){
        return Optional.of(countries.stream()
                .filter(country -> country.getArea() != null)
                .max(Comparator.comparing(Country::getArea))
                .get());
    }

    public static void feladat2_15(List<Country> countries){
        countries.stream()
                .filter(country -> country.getArea() != null && country.getArea().compareTo(BigDecimal.ONE) < 0)
                .map(country -> country.getName())
                .forEach(System.out::println);
    }

    public static void feladat2_16(List<Country> countries){
        countries.stream()
                .filter(country -> country.getRegion() == Region.EUROPE || country.getRegion() == Region.ASIA)
                .flatMap(country -> country.getTimezones().stream())
                .distinct()
                .forEach(System.out::println);
    }

    public static void feladat7(List<Country> countries) {
        countries.stream()
                .filter(country -> country.getRegion() == Region.EUROPE)
                .map(country -> country.getName())
                .forEach(System.out::println);
    }

    public static long feladat8(List<Country> countries) {
        return countries.stream()
                .filter(country -> country.getRegion() == Region.EUROPE)
                .count();
    }

    public static void feladat9(List<Country> countries) {
        countries.stream()
                .filter(country -> country.getPopulation() < 100)
                .forEach(System.out::println);
    }
    public static void feladat10(List<Country> countries) {
        countries.stream()
                .filter(country -> country.getPopulation() < 100)
                .map(country -> country.getName())
                .forEach(System.out::println);

    }
    public static long feladat11(List<Country> countries) {
        return countries
                .stream()
                .filter(c->c.getRegion().equals(Region.EUROPE))
                .mapToLong(Country::getPopulation)
                .sum();
    }
    public static void feladat12(List<Country> countries) {
        countries.stream()
                .filter(c->c.getRegion().equals(Region.EUROPE))
                .mapToLong(Country::getPopulation)
                .sorted()
                .forEachOrdered(System.out::println);
    }
    public static void feladat13(List<Country> countries) {
        countries.stream()
                .filter(c->c.getRegion().equals(Region.EUROPE))
                .sorted(Comparator.comparingLong(Country::getPopulation).reversed())
                .map(Country::getPopulation)
                .forEachOrdered(System.out::println);
    }
    public static Country feladat14(List<Country> countries) {
        return countries.stream()
                .filter(c->c.getRegion().equals(Region.EUROPE))
                .max(Comparator.comparingLong(Country::getPopulation))
                .get();
    }
    public static boolean feladat17(List<Country> countries){
        return countries.stream()
                .anyMatch(country -> country.getPopulation() == 0);
    }
    public static Country feladat19(List<Country> countries) {
        return countries.stream()
                .filter(country -> country.getName().startsWith("H"))
                .findFirst()
                .get();
    }
    public static long feladat20(List<Country> countries) {
        return countries.stream()
                .flatMap(country -> country.getTimezones().stream())
                .distinct()
                .count();
    }

    public static Country feladat3_1(List<Country> countries) {
        return countries.stream()
                .filter(country -> country.getArea() != null)
                .max(Comparator.comparing(Country::getArea))
                .get();
    }
    public static void feladat3_2(List<Country> countries) {
        countries.stream()
                .filter(country -> country.getArea() == null)
                .map(Country::getName)
                .forEach(System.out::println);
    }
    public static DoubleSummaryStatistics feladat3_3(List<Country> countries){
        return countries.stream()
                .mapToDouble(c->c.getArea() == null ? 0 : c.getArea().doubleValue())
                .summaryStatistics();

    }
    public static double feladat3_4(List<Country> countries){
        return countries.stream()
                .mapToDouble(c->c.getArea() == null ? 0 : c.getArea().doubleValue())
                .sum();
    }
    public static BigDecimal feladat3_4_2(List<Country> countries) {
        return countries.stream()
                .filter(c->c.getArea()!=null)
                .map(c->c.getArea())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public static Map<String, Country> feladat3_6(List<Country> countries) {
        return countries.stream()
                .collect(HashMap::new, (map, country) -> map.put(country.getCode(), country), HashMap::putAll);
    }
}
