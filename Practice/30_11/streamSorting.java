import java.util.*;

public class streamSorting {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(5, 1, 3, 9, 2,4,7);
        List<Integer> sorted = nums.stream()
            .sorted()
            .toList();
        System.out.println(sorted); 
    }
}
