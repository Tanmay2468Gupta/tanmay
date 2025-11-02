// package Practice.2_11;
import java.util.*;
public class q2 {
    public static int kadane(int arr[]){
        int n=arr.length;
        int max=0;
        int curr=0;
        for(int i=0;i<n;i++){
            curr+=arr[i];
            max=Math.max(max,curr);
            if(curr<0)curr=0;
        }
        return max;

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++)arr[i]=sc.nextInt();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++){
            int ori=arr[i];
            arr[i]=m;
            int max=kadane(arr);
            sb.append(max).append(" ");
            arr[i]=ori;
        }
        System.out.println(sb);
    }
}
