public class q1 {
    public static int minimumWork(int[] arr) {
        int  balance = 0;
        int work = 0;
        for (int i = 0; i < arr.length; i++) {
            balance += arr[i];    
            work += Math.abs(balance); 
        }
        return work;
    }
    public static void main(String[] args) {
        int arr[]={5,-4,1,-3,1};
        System.out.println(minimumWork(arr));
    }
}