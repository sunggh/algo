package src;

import java.util.Scanner;

public class B_19 {
    // 라빈 카프 알고리즘
    // 매직넘버 5831 사용
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t<=T; t++) {
            String origin = sc.next();
            String target = sc.next();
            int targetHash = 0;
            int originHash = 0;
            int EXPONENT = 1;
            int cnt = 0;
            for(int i = target.length() - 1; i >=0; i--) {
                int num = target.charAt(i) - 'a' + 1;
                targetHash = EXPONENT * num + targetHash;
                if(i != 0)
                    EXPONENT *= 5831;
            }
            EXPONENT = 1;
            for (int i = 0; i<=origin.length()-target.length(); i++) {
                if(i == 0) {
                   for(int j = target.length() - 1; j >=0;j--) {
                       int num = origin.charAt(j) - 'a' + 1;
                       originHash = EXPONENT * num + originHash;
                       if(j != 0)
                           EXPONENT *= 5831;
                   }
                } else {
                    int num = origin.charAt(i -1) - 'a' + 1;
                    int next =  origin.charAt(i + target.length() -1) - 'a' + 1;
                    originHash = (originHash - EXPONENT * num) * 5831 + next;
                }
                if(originHash == targetHash) {
                    cnt++;
                }
            }
            System.out.printf("#%d %d\n",t,cnt);
        }
    }
}
