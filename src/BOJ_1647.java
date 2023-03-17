package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 트리의 지름
// lca로 풀면 시간 복잡도 잡힌다.
// 서브트리간의 가중치를 계산하고 첫번째로 큰 값과 두번째로 큰 값을 구한다
// 구한 값들을 더한것으로 ans 변수와 max를 하여 큰 값을 ans에 저장한다.

public class BOJ_1647 {

    static int N;
    static int ans = 0;

    //이중 구조라 방문체크 필수
    static boolean[] v;
    //트리 저장
    static List<Node>[] list;

    public static void main(String[] args) {
        FastIo sc = new FastIo();
        N = sc.nextInt();
        v = new boolean[N+1];
        list = new ArrayList[N+1];
        for (int i = 1; i<=N;i++) {
            int x = sc.nextInt();
            list[x] = new ArrayList<>(); // 1부터 순서대로 들어오는게 아니다. (트릭)
            while (true) {
                int a = sc.nextInt();
                if(a==-1) {
                    break;
                }
                int b = sc.nextInt();
                list[x].add(new Node(a,b));
            }
        }
        v[1] = true;
        dfs(1);
        System.out.println(ans);
    }

    // 모른 방면에서 오는 제일 큰값을 리턴으로 넘긴다.
    // 중복으로 들어갈수 있으니깐 방문체크를 꼭 한다.
    static int dfs(int n) {
        int maxVal = 0; // 서브트리의 제일 큰 값
        int secondVal = 0; // 서브트리의 두번째로 큰 값

        for (Node node : list[n]) { // 자식노드 탐색
            if(!v[node.n]) { // 방문한곳은 이중구조
                v[node.n] = true;
                int val = dfs(node.n) + node.v; // 해당 노드의 서브트리의 제일 큰값 가져오기
                if(val >= maxVal) { // 그것이 maxVal보다 크면
                    secondVal = maxVal; // maxVal은 두번째로 큰것이 되고
                    maxVal = val; // 치환
                } else {
                    if(val > secondVal) { // 두번째로 큰값도 똑같이 적용
                        secondVal = val;
                    }
                }
            }
        }
        ans = Math.max(maxVal + secondVal, ans); // 연산
        return maxVal;
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
