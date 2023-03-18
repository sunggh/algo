package src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 숨바꼭질 4
public class BOJ_13913 {

    static int N,K, ans;
    static boolean[] v;
    static int[] parent;

    static StringBuilder sb;

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        int[] dx = new int[] {1,-1,2};
        v[N] = true;
        parent[N] = -1;
        q.add(N);
        while (!q.isEmpty()) {
            int tmp = q.poll();
            dx[2] = tmp;
            for(int i = 0; i<3; i++) {
                int nx = tmp + dx[i];
                if(nx<0 || nx>100000|| v[nx]) continue;
                q.add(nx);
                parent[nx] = tmp;
                v[nx] = true;
                if(nx == K) return;
            }
        }
    }

    static void dfs(int n) {
        if(parent[n] == -1) {
            sb.append(n).append(" ");
            return;
        }
        dfs(parent[n]);
        sb.append(n).append(" ");
        ans++;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        sb = new StringBuilder();
        v = new boolean[100001];
        parent = new int[100001];
        if(N < K) {
            bfs();
            dfs(K);
        } else {
            ans = N-K;
            for(int i = 0; i <= N-K;i++) {
                sb.append(N-i).append(" ");
            }
        }

        System.out.println(ans);
        System.out.println(sb);


    }
}
