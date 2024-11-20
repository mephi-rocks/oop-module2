public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMove(int fromLine, int fromColumn, int toLine, int toColumn, ChessPiece[][] board) {
        return new Rook(this.color).canMove(fromLine, fromColumn, toLine, toColumn, board) ||
                new Bishop(this.color).canMove(fromLine, fromColumn, toLine, toColumn, board);
    }
}