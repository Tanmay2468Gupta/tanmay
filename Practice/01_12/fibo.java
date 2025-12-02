import java.util.Scanner;
public class fibo {
    public static int fibonacchi(int n,Integer dp[]){
        if(n==0)return 0;
        if(n==1)return 1;
        if(dp[n]!=null)return dp[n];
        return dp[n]=fibonacchi(n-1,dp)+fibonacchi(n-2,dp);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Integer dp[]=new Integer[n+1];
        System.out.println(fibonacchi(n,dp));


        int prev2=0;
        int prev=1;
        for(int i=2;i<=n;i++){
            int curr=prev2+prev;
            prev2=prev;
            prev=curr;
        }
        System.out.println(prev);
    }
}
