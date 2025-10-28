public class transpose {
    public static void main(String[] args) {
        int arr[][]={{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("At first : -> ");
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("After Transpose :-> ");
        for(int i=0;i<3;i++){// transpose without taking extra array[][]
            for(int j=i;j<3;j++){
                int temp=arr[i][j];
                arr[i][j]=arr[j][i];
                arr[j][i]=temp;
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Rederse Every row :-> ");
        for(int i=0;i<3;i++){// reverse every row
            for(int j=0;j<3/2;j++){
                int temp=arr[i][j];
                arr[i][j]=arr[i][2-j];
                arr[i][2-j]=temp;
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
