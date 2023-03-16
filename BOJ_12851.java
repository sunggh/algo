import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_12851 {
    static int N,K;
    static int[] v;
    static int ans;
    static int cnt;

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        int[] dx = new int[] {-1,1,2};
        q.add(N);
        v[N] = 0;
        if(N == K) {
            ans = 0;
            cnt = 1;
            return;
        }
        while (!q.isEmpty()) {
            int tmp = q.poll();
            dx[2] = tmp;
            for (int i = 0; i<3;i++) {
                int lx = tmp + dx[i];
                if(lx<0||lx >100000 ||v[lx] < v[tmp] + 1) continue;
                if(lx == K) {
                    if(v[K] < ans) {
                        v[K] = v[tmp] + 1;
                        ans = v[K];
                        cnt = 0;
                    } else if (v[K] > ans) {
                        continue;
                    }
                    cnt++;
                } else {
                    v[lx] = v[tmp] + 1;
                    q.add(lx);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        v = new int[100001];
        Arrays.fill(v,100001);
        ans = 987654321;
        cnt = 0;
        bfs();
        System.out.println(ans);
        System.out.println(cnt);
    }
}
