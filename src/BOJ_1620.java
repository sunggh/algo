package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1620 {
    public static void main(String[] args) {
        FastIo sc = new FastIo();
        int N = sc.nextInt();
        int M = sc.nextInt();
        String[] arr = new String[N+1];
        Map<String, Integer> map = new HashMap<>();
        for(int i = 1; i<=N; i++) {
            arr[i] = sc.next();
            map.put(arr[i],i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< M; i++) {
            String str = sc.next();
            if(str.charAt(0) >= '0' && str.charAt(0) <='9') {
                int num = Integer.parseInt(str);
                sb.append(arr[num]).append("\n");
            } else {
                sb.append(map.get(str)).append("\n");
            }
        }
        System.out.println(sb);
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
