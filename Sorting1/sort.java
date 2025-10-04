import java.util.*;
public class sort {
    public static void selectionSort(int arr[],int n){
        for(int i=0;i<=n-2;i++){
            int mini=i;
            for(int j=i+1;j<=n-1;j++){
                if(arr[mini]>arr[j])mini=j;
            }
            int temp=arr[i];
            arr[i]=arr[mini];
            arr[mini]=temp;

            //print 
            System.out.print("Answer is After "+(i+1)+" round : -> ");
            for(int z=0;z<n;z++)System.out.print(arr[z]+" ");
            System.out.println();
        }
    }

    public static void bubbleSort(int arr[],int n){
        for(int i=n-1;i>=0;i--){
            boolean swap=false;
            for(int j=0;j<=i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    swap=true;
                }
            }
            if(swap==false)break;
            System.out.print("Answer is After "+(n-i)+" round : -> ");
            for(int z=0;z<n;z++)System.out.print(arr[z]+" ");
            System.out.println();
        }
    }
    
    public static void insertionSort(int arr[],int n){
        for(int i=0;i<=n-1;i++){
            int j=i;
            while(j>0 && arr[j-1]>arr[j]){
                int temp=arr[j];
                arr[j]=arr[j-1];
                arr[j-1]=temp;
                j--;
            }
            System.out.print("Answer is After "+(i+1)+" round : -> ");
            for(int z=0;z<n;z++)System.out.print(arr[z]+" ");
            System.out.println();
        }
    }

    public static void merge(int arr[],int st,int mid,int e){
        int i=st;
        int j=mid+1;
        ArrayList<Integer>ans=new ArrayList<>();
        while(i<=mid && j<=e){
            if(arr[i]<=arr[j]){
                ans.add(arr[i]);
                i++;
            }
            else{
                ans.add(arr[j]);
                j++;
            }
        }
        while(i<=mid){
            ans.add(arr[i]);
            i++;
        }
        while(j<=e){
            ans.add(arr[j]);
            j++;
        }
        for(i=0;i<ans.size();i++){
            arr[i+st]=ans.get(i);
        }
    }
    public static void mergeSort(int arr[],int s,int e){
        if(s>=e)return ;
        int mid=s+(e-s)/2;
        mergeSort(arr, s, mid);
        mergeSort(arr, mid+1, e);
        merge(arr,s,mid,e);
    }
    
    public static int partition(int arr[],int st,int e){
        int idx=st-1;
        int pivot=arr[e];
        for(int i=st;i<e;i++){
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
    public static void qs(int arr[],int st,int e){
        if(st>=e)return ;
        int pIndex=partition(arr,st,e);
        qs(arr,st,pIndex-1);
        qs(arr,pIndex+1,e);
    }
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        // selectionSort(arr,n);
        // bubbleSort(arr, n);
        // insertionSort(arr, n);
        // mergeSort(arr, 0, n-1);
        qs(arr, 0, n-1);
        
        System.out.print("Answer is : -> ");
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
