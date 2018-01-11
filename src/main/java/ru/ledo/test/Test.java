package ru.ledo.test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test {

    public static void main (String[] args) {
        /**
        Formula testFormula = new FormulaImpl();

        testFormula.calculate(1);
        testFormula.sqrt(100);
         */

        Formula test = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        test.calculate(1);
        test.sqrt(100);


        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (o1, o2) -> o1.compareTo(o2));

        //Converter<String, Integer> convertor = (from) -> Integer.valueOf(from);

        Converter<String, Integer> convertor = Integer::valueOf;
        Integer result = convertor.conver("123");
        System.out.println("result = " + result);

        PersonFactory<Person> testFactory = Person::new;
        testFactory.createPerson("Peter", "Jackson");

        int num = 10;
        Converter<Integer, String> secondConverter = (from) -> String.valueOf(num + from);
        secondConverter.conver(2);

        new Lambda4().testScopes();

        //Formula formula = (a) -> sqrt(a * 10);

        Predicate<String> predicate = (s) -> s.length() > 0;
        predicate.test("foo");
        predicate.negate().test("foo");

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");


        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();

        Consumer<Person> greeter = (p) -> System.out.println("Hello" + p.firstName);
        greeter.accept(new Person());

        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

        comparator.compare(p1, p2);
        comparator.reversed().compare(p1, p2);

        Optional<String> optional = Optional.of("bam");

        optional.isPresent();
        optional.get();
        optional.orElse("falBack");
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));

        List<String> stringCollection = new ArrayList<String>(8);
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("bbb");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection
                .parallelStream()
                .sorted()
                .filter((s) -> s.startsWith("b"))
                .map(String::toUpperCase)
                .forEach(System.out::println);

        boolean anyStartsWithA =
                stringCollection
                .stream()
                .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);

        boolean allStartsWithA =
                stringCollection
                .stream()
                .allMatch((s) -> s.startsWith("A"));

        System.out.println(allStartsWithA);

        boolean nonStartsWithZ =
                stringCollection
                .stream()
                .noneMatch((s) -> s.startsWith("z"));

        System.out.println(nonStartsWithZ);

        long startsWithB =
                stringCollection
                .stream()
                .filter((s) -> s.startsWith("b"))
                .count();

        System.out.println(startsWithB);

        Optional<String> reduced =
                stringCollection
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);

        System.out.println("========");

        int max = 1000000;
        List<String> values = new ArrayList<String>(max);
        for (int i =0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

        Map<Integer, String> map = new HashMap<Integer, String>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        map.computeIfPresent(3, (id, val) -> val + id);
        System.out.println(map.get(3));

        map.computeIfPresent(9, (id, val) -> null);
        System.out.println(map.get(9));

        map.computeIfAbsent(23, id -> "val" + id);
        System.out.println(map.get(23));
        System.out.println(map.containsKey(23));

        map.computeIfAbsent(3, id -> "bam");
        System.out.println(map.get(3));

        map.remove(3, "val3");
        map.get(3);

        map.remove(3, "val33");
        System.out.println(map.get(3));

        map.getOrDefault(42, "not found");

        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));

        Clock clock = Clock.systemDefaultZone();
        long testMillis = clock.millis();

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);

        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");

        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        System.out.println(now1.isBefore(now2));

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);
        System.out.println(minutesBetween);

        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);

        LocalDate today = LocalDate.now();
        LocalDate tommorow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tommorow.minusDays(2);

        LocalDate independenceDay =  LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);

        //LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        //System.out.println(xmas);

        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

        DayOfWeek dayOfWeek2 = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek2);

        Month month = sylvester.getMonth();
        System.out.println(month);

        long minuteOfDay = sylvester.getLong(ChronoField.MICRO_OF_DAY);
        System.out.println(minuteOfDay);

        Instant instant2 = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();
        Date legacyDate2 = Date.from(instant2);
        System.out.println(legacyDate2);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM dd, yyyy - YY:mm");

        //LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2015 - 07:13", formatter);
        //String ttt = formatter.format(parsed);
        //System.out.println(ttt);

        //Person testPerson2 =  new PersonFactory().createPerson("AAA", "BBB");
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint);

        Hints hints1 = Person.class.getAnnotation(Hints.class);
        //System.out.println(hints1.value().length);

        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);

        System.out.println("\nfinish");
    }
}
