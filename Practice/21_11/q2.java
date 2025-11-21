// package Practice.21_11;
import java.util.*;
public class q2 {
    public static void main(String[] args) {
        List<Integer>list=Arrays.asList(1,4,5,6,7,8,3,4,7,9,2);
        double avg=list.stream().mapToInt(e->e).average().getAsDouble();
        System.out.println("Average is :"+avg);
    }
}
