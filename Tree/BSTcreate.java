package Tree;
import java.util.*;
class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){
        this.val=val;
        this.left=null;
        this.right=null;
    }
}
public class BSTcreate {
    public static TreeNode create(int arr[],int left,int right){
        if(left>right)return null;
        int mid=left+(right-left)/2;
        TreeNode head=new TreeNode(arr[mid]);
        head.left=create(arr,left,mid-1);
        head.right=create(arr,mid+1,right);
        return head;
    }
    public static TreeNode createBst(int arr[]){
        Arrays.sort(arr);
        return create(arr,0,arr.length-1);
    }
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }
    public static void main(String[] args) {
        int arr[]={8,4,2,6,7,1,3};
        TreeNode ans=createBst(arr);
        inorder(ans);
    }
}
