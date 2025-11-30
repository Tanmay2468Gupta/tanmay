import java.util.*;


public class sortingChar {
    public static void main(String[] args) {
        Character ch[]={'q','w','e','r','t','y','u','i','o','p'};
        List<Character>list=Arrays.asList(ch);
        List<Character>sort=list.stream().sorted().toList();
        System.out.println(sort);
    }
}
