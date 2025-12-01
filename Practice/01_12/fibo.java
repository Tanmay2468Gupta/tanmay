public class fibo {
    public static int fibonacchi(int n,Integer dp[]){
        if(n==0)return 0;
        if(n==1)return 1;
        if(dp[n]!=null)return dp[n];
        return dp[n]=fibonacchi(n-1,dp)+fibonacchi(n-2,dp);
    }
    public static void main(String[] args) {
        Integer dp[]=new Integer[6];
        System.out.println(fibonacchi(5,dp));
    }
}
