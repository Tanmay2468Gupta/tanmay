// package Practice.2_11;
import java.util.*;
public class q1 {
    public static void main(String[] args) {

        //  Flip bits 


        Scanner sc=new Scanner(System.in);
        // brute force

        int n=sc.nextInt();
        int q=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<q;i++){
            int l=sc.nextInt();
            int r=sc.nextInt();
            for(int j=l-1;j<=r-1;j++){
                arr[j]=1-arr[j];
            }
        }
        
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }


        // optimal Approach

        int n1=sc.nextInt();
        int q1=sc.nextInt();
        int diff[]=new int[n1+2];
        for(int i=0;i<q1;i++){
            int l=sc.nextInt();
            int r=sc.nextInt();
            diff[l]^=1;
            if(r+1<=n)diff[r+1]^=1;
        }
        int filp=0;
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n1;i++){
            filp^=diff[i];
            sb.append(filp).append(" ");
        }
        System.out.println(sb);

    }
}
