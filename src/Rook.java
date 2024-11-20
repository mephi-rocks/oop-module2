public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMove(int fromLine, int fromColumn, int toLine, int toColumn, ChessPiece[][] board) {
        if (fromLine == toLine || fromColumn == toColumn) {
            if (fromLine == toLine) {
                int stepColumn = (toColumn - fromColumn) / Math.abs(toColumn - fromColumn);
                for (int i = 1; i < Math.abs(toColumn - fromColumn); i++) {
                    if (board[fromLine][fromColumn + i * stepColumn] != null) {
                        return false;
                    }
                }
            } else {
                int stepLine = (toLine - fromLine) / Math.abs(toLine - fromLine);
                for (int i = 1; i < Math.abs(toLine - fromLine); i++) {
                    if (board[fromLine + i * stepLine][fromColumn] != null) {
                        return false;
                    }
                }
            }

            ChessPiece targetPiece = board[toLine][toColumn];
            return targetPiece == null || !targetPiece.getColor().equals(this.color);
        }
        return false;
    }
}