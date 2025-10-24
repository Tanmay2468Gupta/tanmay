// package Practice.24_10;

import java.util.ArrayList;

public class sorting {
    public static void selectionsort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i <= n - 2; i++) {
            int mini = i;
            for (int j = i; j <= n - 1; j++) {
                if (arr[j] < arr[mini])
                    mini = j;
            }
            int temp = arr[i];
            arr[i] = arr[mini];
            arr[mini] = temp;
        }
    }

    public static void bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            boolean flag = false;
            for (int j = 0; j <= i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (flag == false)
                break;
        }
    }

    public static void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }
    }

    public static void merge(int arr[], int s, int mid, int e) { // merging logic
        int i = s;
        int j = mid + 1;
        ArrayList<Integer> ans = new ArrayList<>();

        while (i <= mid && j <= e) {
            if (arr[i] < arr[j])
                ans.add(arr[i++]);
            else
                ans.add(arr[j++]);
        }
        while (i <= mid)
            ans.add(arr[i++]);
        while (j <= e)
            ans.add(arr[j++]);
        for (i = 0; i < ans.size(); i++)
            arr[s + i] = ans.get(i);
    }

    public static void mergeSort(int arr[], int s, int e) {
        if (s >= e)
            return;
        int mid = s + (e - s) / 2;
        mergeSort(arr, s, mid);
        mergeSort(arr, mid + 1, e);
        merge(arr, s, mid, e);
    }

    public static void main(String[] args) {
        int arr[] = { 13, 46, 24, 52, 20, 9 };
        // selectionsort(arr);
        // bubbleSort(arr);
        // insertionSort(arr);
        mergeSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
