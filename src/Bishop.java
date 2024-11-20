public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMove(int fromLine, int fromColumn, int toLine, int toColumn, ChessPiece[][] board) {
        if (Math.abs(fromLine - toLine) == Math.abs(fromColumn - toColumn)) {
            int stepLine = (toLine - fromLine) / Math.abs(toLine - fromLine);
            int stepColumn = (toColumn - fromColumn) / Math.abs(toColumn - fromColumn);

            for (int i = 1; i < Math.abs(toLine - fromLine); i++) {
                if (board[fromLine + i * stepLine][fromColumn + i * stepColumn] != null) {
                    return false;
                }
            }

            ChessPiece targetPiece = board[toLine][toColumn];
            return targetPiece == null || !targetPiece.getColor().equals(this.color);
        }
        return false;
    }
}