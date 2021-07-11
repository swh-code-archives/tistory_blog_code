package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준온라인저지 13460번 구슬탈출2 문제풀이
public class BOJ_13460 {

    static int[] moveRow = {-1, 1, 0, 0};
    static int[] moveColumn = {0, 0, -1, 1};
    static boolean[][][][] visited = new boolean[10][10][10][10];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Board board = new Board(N, M);

        for (int i = 0; i < board.map.length; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < board.map[i].length; j++) {
                if (temp[j].equals("R")) {
                    board.redBeadRow = i;
                    board.redBeadColumn = j;
                    board.map[i][j] = ".";
                } else if (temp[j].equals("B")) {
                    board.blueBeadRow = i;
                    board.blueBeadColumn = j;
                    board.map[i][j] = ".";
                } else {
                    board.map[i][j] = temp[j];
                }
            }
        }

        Queue<Board> bfs = new LinkedList<>();

        bfs.offer(board);
        visited[board.redBeadRow][board.redBeadColumn][board.blueBeadRow][board.blueBeadColumn] = true;

        int result = -1;

        // 여기부터 시작
        while (!bfs.isEmpty()) {
            Board pollBoard = bfs.poll();

            if (pollBoard.count >= 10) {
                System.out.println(result);
                return;
            }

            for (int i = 0; i < 4; i++) {
                Board tempBoard = new Board(N, M);

                tempBoard.redBeadRow = pollBoard.redBeadRow;
                tempBoard.redBeadColumn = pollBoard.redBeadColumn;
                tempBoard.blueBeadRow = pollBoard.blueBeadRow;
                tempBoard.blueBeadColumn = pollBoard.blueBeadColumn;
                tempBoard.count = pollBoard.count;
                tempBoard.map = pollBoard.map;

                tempBoard.count++;
                int tempResult = 0;

                switch (i) {
                    case 0: //상

                        if (tempBoard.redBeadRow <= tempBoard.blueBeadRow) {
                            tempResult = firstRedSecondBlue(tempBoard, i);
                        } else {
                            tempResult = firstBlueSecondRed(tempBoard, i);
                        }

                        if (tempResult == 0) {
                            if (!visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]) {
                                bfs.offer(tempBoard);
                                visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]= true;
                            }
                        } else {
                            result = Math.max(result, tempResult);
                        }

                        break;
                    case 1: // 하

                        if (tempBoard.redBeadRow >= tempBoard.blueBeadRow) {
                            tempResult = firstRedSecondBlue(tempBoard, i);
                        } else {
                            tempResult = firstBlueSecondRed(tempBoard, i);
                        }

                        if (tempResult == 0) {
                            if (!visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]) {
                                bfs.offer(tempBoard);
                                visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]= true;
                            }
                        } else {
                            result = Math.max(result, tempResult);
                        }

                        break;
                    case 2: // 좌

                        if (tempBoard.redBeadColumn <= tempBoard.blueBeadColumn) {
                            tempResult = firstRedSecondBlue(tempBoard, i);
                        } else {
                            tempResult = firstBlueSecondRed(tempBoard, i);
                        }

                        if (tempResult == 0) {
                            if (!visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]) {
                                bfs.offer(tempBoard);
                                visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]= true;
                            }
                        } else {
                            result = Math.max(result, tempResult);
                        }

                        break;
                    case 3: // 우

                        if (tempBoard.redBeadColumn >= tempBoard.blueBeadColumn) {
                            tempResult = firstRedSecondBlue(tempBoard, i);
                        } else {
                            tempResult = firstBlueSecondRed(tempBoard, i);
                        }

                        if (tempResult == 0) {
                            if (!visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]) {
                                bfs.offer(tempBoard);
                                visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]= true;
                            }
                        } else {
                            result = Math.max(result, tempResult);
                        }

                        break;
                }
            }

            if (result > 0) {
                System.out.println(result);
                return;
            }
        }

        System.out.println(result);
    }

    static class Board {

        String[][] map;
        int redBeadRow;
        int redBeadColumn;
        int blueBeadRow;
        int blueBeadColumn;
        int count;

        public Board(int N, int M) {
            this.map = new String[N][M];
        }

    }

    static int firstRedSecondBlue(Board tempBoard, int i) {
        int moveR = tempBoard.redBeadRow + moveRow[i];
        int moveC = tempBoard.redBeadColumn + moveColumn[i];
        int result = 0;
        boolean redEscChk = false;

        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
        }

        if (tempBoard.map[moveR][moveC].equals("#")) {
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            tempBoard.redBeadRow = moveR;
            tempBoard.redBeadColumn = moveC;
        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            redEscChk = true;
            result = tempBoard.count;
        }

        moveR = tempBoard.blueBeadRow + moveRow[i];
        moveC = tempBoard.blueBeadColumn + moveColumn[i];

        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
        }

        if (tempBoard.map[moveR][moveC].equals("#")) {
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            if (moveR == tempBoard.redBeadRow && moveC == tempBoard.redBeadColumn) {
                moveR = moveR - moveRow[i];
                moveC = moveC - moveColumn[i];
            }
            tempBoard.blueBeadRow = moveR;
            tempBoard.blueBeadColumn = moveC;
        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            return -1;
        }

        if (redEscChk) {
            return result;
        } else {
            return 0;
        }
    }

    static int firstBlueSecondRed(Board tempBoard, int i) {
        int moveR = tempBoard.blueBeadRow + moveRow[i];
        int moveC = tempBoard.blueBeadColumn + moveColumn[i];

        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
        }

        if (tempBoard.map[moveR][moveC].equals("#")) {
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            tempBoard.blueBeadRow = moveR;
            tempBoard.blueBeadColumn = moveC;
        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            return -1;
        }

        moveR = tempBoard.redBeadRow + moveRow[i];
        moveC = tempBoard.redBeadColumn + moveColumn[i];

        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
        }

        if (tempBoard.map[moveR][moveC].equals("#")) {
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            if (moveR == tempBoard.blueBeadRow && moveC == tempBoard.blueBeadColumn) {
                moveR = moveR - moveRow[i];
                moveC = moveC - moveColumn[i];
            }
            tempBoard.redBeadRow = moveR;
            tempBoard.redBeadColumn = moveC;
        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            return tempBoard.count;
        }

        return 0;
    }

}
