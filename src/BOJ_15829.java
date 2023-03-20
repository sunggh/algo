package src;

import java.util.Scanner;

public class BOJ_15829 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String str = sc.next();
        long EXPONENT = 1;
        long hash = 0;
        for(int i = 0; i< N; i++) {
            int num = str.charAt(i) - 'a' + 1;
            hash = ((EXPONENT * num)% 1234567891 + hash % 1234567891) % 1234567891 ;
            EXPONENT = (EXPONENT * 31) % 1234567891;
        }
        System.out.println(hash % 1234567891);
    }
}
