import java.util.*;

// import q5.student;
public class sorting {
    public static void selectionSort(int arr[]){
        int n=arr.length;
        for(int i=0;i<=n-2;i++){
            int mini=i;
            for(int j=i+1;j<=n-1;j++){
                if(arr[j]<arr[mini])mini=j;
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
    public static void insertionSort(int arr[]){
        int n=arr.length;
        for(int i=0;i<n;i++){
            int j=i;
            while(j>0 && arr[j-1]>arr[j]){
                int temp=arr[j-1];
                arr[j-1]=arr[j];
                arr[j]=temp;
                j--;
            }
        }
    }
    public static void merge(int arr[],int slow,int mid,int end){
        int i=slow;
        int j=mid+1;
        ArrayList<Integer>res=new ArrayList<>();
        while(i<=mid && j<=end){
            if(arr[i]<=arr[j]){
                res.add(arr[i]);
                i++;
            }
            else{
                res.add(arr[j]);
                j++;
            }
        }
        while(i<=mid){
            res.add(arr[i]);
            i++;           
        }
        while(j<=end){
            res.add(arr[j]);
            j++;
        }
        for(i=0;i<res.size();i++){
            arr[i+slow]=res.get(i);
        }
    }
    public static void mergeSort(int arr[],int s,int e){
        if(s>=e)return;
        int mid=s+(e-s)/2;
        mergeSort(arr, s, mid);
        mergeSort(arr, mid+1, e);
        merge(arr,s,mid,e);
    }
    public static void print(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
    public static void qs(int arr[],int s,int e){
        if(s>=e)return;
        int pIndex=partion(arr,s,e);
        qs(arr,s,pIndex-1);
        qs(arr,pIndex+1,e);
    }
    public static int partion(int arr[],int s,int e){
        int idx=s-1;
        int pivot=arr[e];
        for(int i=s;i<e;i++){
            if(arr[i]<=pivot){
                idx++;
                int temp=arr[idx];
                arr[idx]=arr[i];
                arr[i]=temp;
            }
        }
        idx++;
        int temp=arr[e];
        arr[e]=arr[idx];
        arr[idx]=temp;
        return idx;
    }
    public static void main(String[] args) {
        int arr[]={13,46,24,52,20,9};
        // selectionSort(arr);
        // bubbleSort(arr);
        // insertionSort(arr);
        // mergeSort(arr, 0, 5);
        qs(arr, 0, 5);
        print(arr);
    }
}
