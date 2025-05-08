public class Board {
    private char[][] board;
    private int moveCount;

    public Board() {
        board = new char[3][3];
        moveCount = 0;
    }

    public boolean makeMove(int row, int col, char player) {
        if (board[row][col] == '\0') {
            board[row][col] = player;
            moveCount++;
            return true;
        }
        return false;
    }

    public boolean checkWin(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }

    public boolean isFull() {
        return moveCount == 9;
    }

    public void reset() {
        board = new char[3][3];
        moveCount = 0;
    }
}
