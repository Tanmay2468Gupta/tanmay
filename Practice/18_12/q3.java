// package Practice.18_12;

import java.util.Scanner;

public class q3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        int n=str.length();
        for(int i=1;i<Math.pow(2,n);i++){
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<n;j++){
                if((i&(1<<j))!=0){
                    sb.append(str.charAt(j));
                }
            }
            System.out.println(sb.toString());
        }
    }
}
