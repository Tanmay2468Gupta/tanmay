import java.util.ArrayList;
import java.util.Scanner;

public class q1{

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        int n=str.length();
        ArrayList<String>arr=new ArrayList<>();
        for(int i=0;i<Math.pow(2,n);i++){
            for(int j=i;j<n;j++){
                String ans=str.substring(i, j+1);
                arr.add(ans);
            }
        }
        for(int i=0;i<arr.size();i++){
            System.out.println(arr.get(i)+ " ");
        }

    }
}