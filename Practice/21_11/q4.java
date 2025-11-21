// package Practice.21_11;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
public class q4 {
    public static void main(String[] args) {
        List<Integer>list=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer>even=list.stream().filter(e->e%2==0).collect(Collectors.toList());
        List<Integer>odd=list.stream().filter(e->e%2!=0).collect(Collectors.toList());
        System.out.println(even);
        System.out.println(odd);
    }
}
