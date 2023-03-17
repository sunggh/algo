package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 트리의 지름 풀이방식(= 1647)
// 차이점은 부모와 자식 관계를 알려줘 이진트리를 구성한다.
// 이 방법은 방문체크가 필요없다.
public class BOJ_1967 {

    static int N, ans;

    static ArrayList<Node>[] list;
    static int dfs(int n) {
        int maxVal = 0;
        int second = 0;
        for(Node node : list[n]) {
            int val = node.v + dfs(node.n);
            if(val >= maxVal) {
                second = maxVal;
                maxVal = val;
            } else {
                if(val > second) {
                    second = val;
                }
            }
        }
        ans = Math.max(ans,maxVal + second);
        return maxVal;
    }
    public static void main(String[] args) {
        FastIo sc = new FastIo();
        N = sc.nextInt();
        list = new ArrayList[N+1];
        for(int i = 1; i<=N; i++) {
            list[i] = new ArrayList();
        }
        for(int i = 1; i<N; i++) {
            int p = sc.nextInt();
            int c = sc.nextInt();
            int v = sc.nextInt();

            list[p].add(new Node(c,v));
        }
        dfs(1);
        System.out.println(ans);
    }

    static class Node {
        int n;
        int v;

        public Node(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }

    static class FastIo {
        BufferedReader br;
        StringTokenizer st;

        FastIo() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
