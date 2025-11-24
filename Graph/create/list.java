package Graph.create;

import java.util.ArrayList;
import java.util.Scanner;

public class list {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int V=sc.nextInt(); // number of vertices
        int e=sc.nextInt(); // number of edges
        ArrayList<ArrayList<Integer>>arr=new ArrayList<>();
        for(int i=0;i<=V;i++)arr.add(new ArrayList<>());
        for(int i=0;i<e;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            arr.get(u).add(v);
            arr.get(v).add(u);
        }
    }
}
