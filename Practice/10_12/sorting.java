import java.util.ArrayList;

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
    public static void insertion(int arr[]){
        int n=arr.length;
        for(int i=0;i<=n-1;i++){
            int j=i;
            while(j>0 && arr[j-1]>arr[j]){
                int temp=arr[j-1];
                arr[j-1]=arr[j];
                arr[j]=temp;
                j--;
            }
        }
    }
    public static void merge(int arr[],int st,int mid,int e){
        int i=st;
        int j=mid+1;
        ArrayList<Integer>ans=new ArrayList<>();
        while(i<=mid && j<=e){
            if(arr[i]<arr[j]){
                ans.add(arr[i++]);
            }
            else{
                ans.add(arr[j++]);
            }
        }
        while(i<=mid)ans.add(arr[i++]);
        while(j<=e)ans.add(arr[j++]);
        for(i=0;i<ans.size();i++){
            arr[i+st]=ans.get(i);
        }
    }
    public static void mergeSort(int arr[],int s,int e){
        if(s>=e)return;
        int mid=s+(e-s)/2;
        mergeSort(arr, s, mid);
        mergeSort(arr,mid+1,e);
        merge(arr,s,mid,e);
    }
    public static int partion(int arr[],int s,int e){
        int idx=s-1;
        int pivot=arr[e];
        for(int i=s;i<e;i++){
            if(arr[i]<=pivot){
                idx++;
                int temp=arr[i];
                arr[i]=arr[idx];
                arr[idx]=temp;
            }
        }
        idx++;
        int temp=arr[idx];
        arr[idx]=arr[e];
        arr[e]=temp;
        return idx;
    }
    public static void quickSort(int arr[],int s,int e){
        if(s>=e)return;
        int index=partion(arr,s,e);
        quickSort(arr, s, index-1);
        quickSort(arr,index+1,e);
    }
    public static void main(String[] args) {
        int arr[]={13,46,24,52,20,9};
        // selectionSort(arr);
        // bubbleSort(arr);
        // insertion(arr);
        // mergeSort(arr, 0, 5);
        quickSort(arr, 0, 5);
        print(arr);
    }
}
