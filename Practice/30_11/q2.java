import java.util.*;

public class q2 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // Map -> double every number
        List<Integer> doubled = list.stream()
                .map(n -> n * 2)
                .toList();

        // Filter -> even numbers
        List<Integer> evens = list.stream()
                .filter(n -> n % 2 == 0)
                .toList();

        // Reduce -> sum
        int sum = list.stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println("Doubled : " + doubled);
        System.out.println("Evens : " + evens);
        System.out.println("Sum : " + sum);
    }
}
