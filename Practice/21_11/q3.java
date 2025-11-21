import java.util.*;

public class q3 {
    public static void main(String[] args) {
        List<Integer>list=Arrays.asList(1,10,20,30,15);
        // 1. square : 1,100,400,900,225
        // 2.filter : >100 : 400,900,225
        // sum : 400+900+225=1525
        // avg : 1525/3
        double avg=list.stream()
                        .map(e->e*e)
                            .filter(e->e>100)
                                .mapToInt(e->e)
                                    .average()
                                        .getAsDouble();
        

        System.out.println("Value is : "+ avg);
    }
}
