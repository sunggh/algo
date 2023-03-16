package src;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_12851 {
    static int N,K;
    static int[] v; // 방문 체크 정수형 이유 : 중복해서 들어오는데 더 짧은 경로가 생길 수 있기 때문
    static int ans; // 정답 출력
    static int cnt; // 정답에 방문한 수 출력

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        int[] dx = new int[] {-1,1,2}; // dx의 2번째는 큐 poll 값 들어갈거임
        q.add(N);
        v[N] = 0;
        if(N == K) { // 같을시 방문하지 않음
            ans = 0;
            cnt = 1;
            return;
        }
        while (!q.isEmpty()) {
            int tmp = q.poll();
            dx[2] = tmp; // *2 와 같은 효과
            for (int i = 0; i<3;i++) {
                int lx = tmp + dx[i];
                if(lx<0||lx >100000 ||v[lx] < v[tmp] + 1) continue; // 새로 추가될 값이 기존에 값보다 클때 제외
                if(lx == K) { // K랑 같으면
                    if(v[K] < ans) { // 정답보다 방문한게 작으면
                        v[K] = v[tmp] + 1; // 값 수정
                        ans = v[K]; // 값수정
                        cnt = 0; // 0으로 초기화
                    } else if (v[K] > ans) {
                        continue;
                    }
                    cnt++;
                } else { // 다르면
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
        Arrays.fill(v,100001); // 1~100000 까지 +1 해도 10만
        ans = 987654321; // 임의 값 (최대)
        cnt = 0;
        bfs();
        System.out.println(ans);
        System.out.println(cnt);
    }
}
