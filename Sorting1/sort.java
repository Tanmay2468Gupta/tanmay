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

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        // selectionSort(arr,n);
        // bubbleSort(arr, n);
        insertionSort(arr, n);
        System.out.print("Answer is : -> ");
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
