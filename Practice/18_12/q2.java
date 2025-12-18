import java.util.Scanner;

public class q2 {
    public static void subsequence(String str,int idx,String curr){
        if(str.length()==idx){
            System.out.println(curr);
            return;
        }
        subsequence(str, idx+1, curr+str.charAt(idx));
        subsequence(str, idx+1, curr);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        subsequence(str,0,"");
    }  
}
