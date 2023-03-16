package src;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16953 {

    static int A,B;
    static boolean[] v;
    static int ans = -1;
    static void bfs() {
        Queue<Pair> q = new LinkedList<>(); // Pair first : 위치 second : cnt
        q.add(new Pair(A,1)); // 초기값 세팅
        v[A] =  true;
        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            // 첫번째 조건 ( * 2)
            if(tmp.first * 2 <= B && !v[tmp.first * 2]) {
                if(tmp.first * 2 == B) { // 정답이면 종료
                    ans = tmp.second + 1;
                    break;
                }
                q.add(new Pair(tmp.first * 2, tmp.second + 1));
                v[tmp.first * 2] = true;
            }
            // 두번째 조건 ( * 10 +1)
            if((long)tmp.first * 10 +1 <= B && !v[tmp.first* 10 +1]) {
                if(tmp.first * 10 +1 == B) { // 정답이면 종료
                    ans = tmp.second + 1;
                    break;
                }
                q.add(new Pair(tmp.first * 10 +1, tmp.second + 1));
                v[tmp.first* 10 +1] = true;
            }

        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        v = new boolean[B+1];
        if(A==B) {
            System.out.println(1);
            return;
        }
        bfs();
        System.out.println(ans);
    }
    static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
