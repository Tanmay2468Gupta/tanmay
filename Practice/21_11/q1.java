// package Practice.21_11;
import java.util.*;
public class q1 {
    public static void main(String[] args) {
        List<Integer>list=Arrays.asList(1,4,5,6,7,8,3,4,7,9,0,2);
        Optional<Integer>sum=list.stream().reduce((a,b) -> a+b);
        System.out.println("Sum is :"+sum.get());
    }
}
