public class ChessBoard {
    public ChessPiece[][] board;
    private boolean whiteKingMoved = false;
    private boolean blackKingMoved = false;
    private boolean whiteRook0Moved = false;
    private boolean whiteRook7Moved = false;
    private boolean blackRook0Moved = false;
    private boolean blackRook7Moved = false;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        // Инициализация доски
    }

    public boolean moveToPosition(int fromLine, int fromColumn, int toLine, int toColumn) {
        if (board[fromLine][fromColumn] != null && board[fromLine][fromColumn].canMove(fromLine, fromColumn, toLine, toColumn, board)) {
            // Фиксация первого хода короля или ладьи для рокировки
            if (board[fromLine][fromColumn] instanceof King) {
                if (board[fromLine][fromColumn].getColor().equals("White")) {
                    whiteKingMoved = true;
                } else {
                    blackKingMoved = true;
                }
            }

            if (board[fromLine][fromColumn] instanceof Rook) {
                if (board[fromLine][fromColumn].getColor().equals("White")) {
                    if (fromColumn == 0) {
                        whiteRook0Moved = true;
                    } else if (fromColumn == 7) {
                        whiteRook7Moved = true;
                    }
                } else {
                    if (fromColumn == 0) {
                        blackRook0Moved = true;
                    } else if (fromColumn == 7) {
                        blackRook7Moved = true;
                    }
                }
            }

            board[toLine][toColumn] = board[fromLine][fromColumn];
            board[fromLine][fromColumn] = null;
            return true;
        }
        return false;
    }

    public boolean castling0() {
        if (whiteKingMoved || whiteRook0Moved) {
            return false; // Белый король или ладья уже ходили
        }

        if (board[0][4] instanceof King && board[0][0] instanceof Rook &&
                board[0][4].getColor().equals("White") && board[0][0].getColor().equals("White") &&
                board[0][1] == null && board[0][2] == null && board[0][3] == null) {

            board[0][2] = board[0][4]; // Переместить короля
            board[0][4] = null;
            board[0][3] = board[0][0]; // Переместить ладью
            board[0][0] = null;
            return true;
        }
        return false;
    }

    public boolean castling7() {
        if (whiteKingMoved || whiteRook7Moved) {
            return false; // Белый король или ладья уже ходили
        }

        if (board[0][4] instanceof King && board[0][7] instanceof Rook &&
                board[0][4].getColor().equals("White") && board[0][7].getColor().equals("White") &&
                board[0][5] == null && board[0][6] == null) {

            board[0][6] = board[0][4]; // Переместить короля
            board[0][4] = null;
            board[0][5] = board[0][7]; // Переместить ладью
            board[0][7] = null;
            return true;
        }
        return false;
    }

    public boolean blackCastling0() {
        if (blackKingMoved || blackRook0Moved) {
            return false; // Черный король или ладья уже ходили
        }

        if (board[7][4] instanceof King && board[7][0] instanceof Rook &&
                board[7][4].getColor().equals("Black") && board[7][0].getColor().equals("Black") &&
                board[7][1] == null && board[7][2] == null && board[7][3] == null) {

            board[7][2] = board[7][4]; // Переместить короля
            board[7][4] = null;
            board[7][3] = board[7][0]; // Переместить ладью
            board[7][0] = null;
            return true;
        }
        return false;
    }

    public boolean blackCastling7() {
        if (blackKingMoved || blackRook7Moved) {
            return false; // Черный король или ладья уже ходили
        }

        if (board[7][4] instanceof King && board[7][7] instanceof Rook &&
                board[7][4].getColor().equals("Black") && board[7][7].getColor().equals("Black") &&
                board[7][5] == null && board[7][6] == null) {

            board[7][6] = board[7][4]; // Переместить короля
            board[7][4] = null;
            board[7][5] = board[7][7]; // Переместить ладью
            board[7][7] = null;
            return true;
        }
        return false;
    }

    public void printBoard() {
        // Доработанная доска, чтобы было удобно ходить
        System.out.println("    a  b  c  d  e  f  g  h");
        System.out.println("  +------------------------+");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " | ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("-  ");
                } else {
                    System.out.print(board[i][j].getClass().getSimpleName().charAt(0) + "  ");
                }
            }
            System.out.println("| " + (8 - i));
        }
        System.out.println("  +------------------------+");
        System.out.println("    a  b  c  d  e  f  g  h");
    }
}