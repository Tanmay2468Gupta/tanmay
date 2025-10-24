// package Practice.24_10;

public class sorting {
    public static void bubblesort(int arr[]){
        int n=arr.length;
        for(int i=0;i<=n-2;i++){
            int mini=i;
            for(int j=i;j<=n-1;j++){
                if(arr[j]<arr[mini])mini=j;
            }
            int temp=arr[i];
            arr[i]=arr[mini];
            arr[mini]=temp;
        }
    }
    public static void main(String[] args) {
        int arr[]={13,46,24,52,20,9};
        bubblesort(arr);
        for(int i=0;i<6;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
