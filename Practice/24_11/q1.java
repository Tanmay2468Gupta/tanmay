// package 24_11;
/*The King’s Minimum Gold Tax
In the ancient kingdom of Numeria, the king had a strange rule.
Every family must store gold in increasing order of family rank.
	Family 1 must have ≤ Family 2
	Family 2 must have ≤ Family 3
	… and so on.
But…
When the King inspected the gold vaults, he found that the amounts were not in increasing order.
To avoid punishment, the families can only add gold to their vaults (they cannot remove gold).
The king tells his minister:
“Make this sequence non-decreasing with MINIMUM extra gold added.
Tell me the total amount of gold to be added.”
You, as the Royal Programmer, must help him. */

import java.util.*;
import java.io.*;
public class q1 {
    public static void main(String[] subham) throws Exception {
        // Scanner sc=new Scanner(System.in);
        // int n=sc.nextInt();
        // int arr[]=new int [n];
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        System.out.println("Enter the size of family ");
        int n=Integer.parseInt(br.readLine().trim());
        System.out.println("Enter the gold coin with family ");
        long arr[]=new long[n];
        st=new StringTokenizer(br.readLine());
        int idx=0;
        while(st.hasMoreTokens() && idx>n){
            arr[idx++]=Long.parseLong(st.nextToken());
        }
        while(idx<n){
            st=new StringTokenizer(br.readLine());
            while(st.hasMoreTokens() && idx<n){
                arr[idx++]=Long.parseLong(st.nextToken());
            }
            long totalAdded=0L;
            for(int i=1;i<n;i++){
                if(arr[i]<arr[i-1]){
                    long need=arr[i-1]-arr[i];
                    totalAdded+=need;
                    arr[i]=arr[i-1];
                }
            }
        }
    }
}
