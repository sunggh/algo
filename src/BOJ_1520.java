package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1520 {
    static int N,M;
    static int[][] map;
    static int[][] v;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int dfs(int x, int y) {
        int cnt = 0;
        if(x == N-1 && y == M -1) {
            return 1;
        }
        if(v[x][y] != -1) {
            return v[x][y];
        }
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0||nx>=N||ny<0||ny>=M||map[x][y]<=map[nx][ny]) {
                continue;
            }
            if(v[nx][ny] != -1) {
                cnt += v[nx][ny];
                continue;
            }
            int tmp = dfs(nx,ny);
            if(tmp != 0) {
                v[nx][ny] = tmp;
                cnt += tmp;
            }
        }
        if(v[x][y] == -1) {
            v[x][y] = 0;
        }
        return cnt;
    }

    public static void main(String[] args) {
        FastIo sc = new FastIo();
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        v = new int[N][M];
        for(int i = 0 ; i<N;i++) {
            for(int j = 0 ; j<M;j++) {
                map[i][j] = sc.nextInt();
                v[i][j] = -1;
            }
        }
        System.out.println(dfs(0,0));
    }
    static class FastIo {
        BufferedReader br;
        StringTokenizer st;

        public FastIo() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st==null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
