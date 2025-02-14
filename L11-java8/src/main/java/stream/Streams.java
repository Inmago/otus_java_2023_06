package stream;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings({"java:S106", "java:S125", "java:S3864", "java:S1130"})
public class Streams {

    private static final List<Student> students =
            Arrays.asList(
                    new Student("Alex", 22, 5, 4.5),
                    new Student("Maria", 22, 5, 3.5),
                    new Student("John", 12, 4, 4.7),
                    new Student("Bob", 22, 5, 4.8),
                    new Student("Anna", 20, 3, 4.5));

    public static void main(String[] args) throws IOException {
        // creating();
        // stringsJoiner();
        // filterMapReduce();
        // System.out.println(getAvgMark());
        // groupBy();

        // streamConsumers();

        // flatMap();

        streamNotStarted();
    }

    public static void creating() {
        Stream<String> empty = Stream.empty();
        empty.forEach(System.out::println);
        var single = Stream.of(10);
        single.forEach(System.out::println);
        var array = Stream.of(1, 2, 3);
        array.forEach(System.out::print);
        var range = IntStream.range(1, 5);
        range.forEach(System.out::print);
    }

    public static void stringsJoiner() {
        String[] arrayOfString = {"A", "B", "C", "D"};
        var result = Arrays.stream(arrayOfString).collect(Collectors.joining(","));
        System.out.println(result);

        String result2 = String.join(",", arrayOfString);
        System.out.println(result2);
    }

    public static void filterMapReduce() {
        System.out.println("filterMapReduce");
        var list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int result =
                list.stream()
                        .filter(val -> val % 2 > 0)
                        .map(val -> val * 10)
                        .reduce(0, Integer::sum);
        System.out.println("result:" + result);
    }

    public static double getAvgMark() {
        return students.stream()
                .filter(st -> st.course() == 5)
                .mapToDouble(Student::avgMark)
                .average()
                .orElseThrow(() -> new RuntimeException("Can't calculate average"));
    }

    public static void groupBy() {
        var map = students.stream().collect(Collectors.groupingBy(Student::course));
        System.out.println(map);
    }

    public static void streamConsumers() {
        var intStream = List.of(1, 2, 3, 4, 5).stream();
        consume(intStream);
        consume(intStream);
    }

    public static void consume(Stream<Integer> stream) {
        stream.forEach(System.out::println);
    }

    public static void flatMap() {
        var data =
                List.of(List.of(1, 2, 3, 4), List.of(10, 20, 30, 40), List.of(100, 200, 300, 400));

        var dataFlat = data.stream().flatMap(Collection::stream).toList();
        System.out.println(dataFlat);
    }

    public static void streamNotStarted() {
        var dataStream =
                List.of(1, 2, 3, 4, 5).stream().map(val -> val * 10).peek(System.out::println);
        // .forEach(System.out::println);

        dataStream.forEach(System.out::println);
    }
}
