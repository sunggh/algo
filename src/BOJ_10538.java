package src;

import java.util.Scanner;

public class BOJ_10538 {
    final static int HASH_SIZE = (int) Math.pow(2, 30) - 1; // 해시 사이즈 설정

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int W = sc.nextInt();
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] targetArr = new int[H][W];
        int[][] originArr = new int[N][M];
        for (int i = 0; i < H; i++) { // o랑 x만 있으니 1과 0으로 매핑
            String str = sc.next();
            for (int j = 0; j < W; j++) {
                targetArr[i][j] = str.charAt(j) == 'o' ? 1 : 0;
            }
        }
        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                originArr[i][j] = str.charAt(j) == 'o' ? 1 : 0;
            }
        }
        System.out.printf("%d", solution(targetArr, originArr));


    }

    static int solution(int[][] targetArr, int[][] originArr) {
        int H = targetArr.length;
        int W = targetArr[0].length;
        int N = originArr.length;
        int M = originArr[0].length;
        int hashVal = getHashArr(targetArr, H, W)[0][0]; // targetArr 부터 해싱
        int[][] hashArr = getHashArr(originArr, H, W);
        int cnt = 0;
        for (int i = 0; i <= N - H; i++) {
            for (int j = 0; j <= M - W; j++) {
                cnt = hashArr[i][j] == hashVal ? cnt + 1 : cnt;
            }
        }
        return cnt;
    }

    static int[][] getHashArr(int[][] matrix, int height, int width) {
        int H = matrix.length;
        int W = matrix[0].length;
        int[][] horizonHashArr = new int[H][W - width + 1];
        int rowMaxP = getMaxPower(height, 5); // row의 최대 곱 구하기
        int colMaxP = getMaxPower(width, 4); // col의 최대 곱 구하기
        for (int i = 0; i < H; i++) {// 한칸씩 이동하면서 볼거야
            int hash = getHorizonHash(matrix, width, i, 0);
            horizonHashArr[i][0] = hash;
            for (int j = 1; j <= W - width; j++) { // 왼쪽에서부터 차례차례 해쉬 크기 재기
                horizonHashArr[i][j] = getNext(hash, matrix[i][j - 1], colMaxP, matrix[i][j - 1 + width], 4);
                hash = horizonHashArr[i][j];
            }
        }

        int[][] verticalHashArr = new int[H - height + 1][W - width + 1];
        for (int j = 0; j <= W - width; j++) {
            int hash = getVerticalHash(horizonHashArr, height, 0, j);
            verticalHashArr[0][j] = hash;
            for (int i = 1; i <= H - height; i++) {
                verticalHashArr[i][j] = getNext(hash, horizonHashArr[i - 1][j], rowMaxP, horizonHashArr[i - 1 + height][j], 5);
                hash = verticalHashArr[i][j];
            }
        }
        return verticalHashArr;
    }

    static int getMaxPower(int len, int shift) {
        long result = 1;
        for (int i = 0; i < len - 1; i++) {
            result = (result << shift) + result;
        }
        return (int) (result & HASH_SIZE);
    }

    static int getHorizonHash(int[][] matrix, int len, int row, int col) {
        long result = 0;
        for (int i = 0; i < len; i++) {
            result = (result << 4) + result + matrix[row][col + i];
        }
        return (int) (result & HASH_SIZE);
    }

    static int getVerticalHash(int[][] matrix, int len, int row, int col) {
        long result = 0;
        for (int i = 0; i < len; i++) {
            result = (result << 5) + result + matrix[row + i][col];
        }
        return (int) (result & HASH_SIZE);
    }

    static int getNext(int prev, int del, int maxPower, int add, int shift) {
        long result = prev - (del * maxPower);
        result = (result << shift) + result + add;
        return (int) (result & HASH_SIZE);
    }
}