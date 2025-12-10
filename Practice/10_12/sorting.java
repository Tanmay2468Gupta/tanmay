public class sorting {
    public static void print(int arr[]){
        int n=arr.length;
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void selectionSort(int arr[]){
        int n=arr.length;
        for(int i=0;i<=n-2;i++){
            int mini=i;
            for(int j=i+1;j<=n-1;j++){
                if(arr[j]<arr[mini]){
                    mini=j;
                }
            }
            int temp=arr[i];
            arr[i]=arr[mini];
            arr[mini]=temp;
        }
    }
    public static void bubbleSort(int arr[]){
        int n=arr.length;
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<=i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int arr[]={13,46,24,52,20,9};
        // selectionSort(arr);
        // bubbleSort(arr);
        print(arr);
    }
}
