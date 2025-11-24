package Graph.create;

import java.util.Scanner;

public class mat {
    public static void main(String[] args) {    
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();  // number of nodes
        int m=sc.nextInt();  // number of edges
        int adj[][]=new int[n+1][n+1];
        for(int i=0;i<m;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            adj[u][v]=1;
            adj[v][u]=1;
        }
    }
}
