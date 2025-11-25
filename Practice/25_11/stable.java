// package 25_11;

public class stable {
    public static int stableTube(int arr[]){
        long sum=0;
        for(int num:arr){
            sum+=num;
        }
        long left=0;
        for(int i=0;i<arr.length;i++){
            long right=sum-left-arr[i];
            if(left==right){
                return i;
            }
            left+=arr[i];
        }
        return -1;
    }
    public static void main(String[] args) {
        int testtube[]={1,2,3};
        System.out.println(stableTube(testtube));

    }
}
