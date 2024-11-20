public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMove(int fromLine, int fromColumn, int toLine, int toColumn, ChessPiece[][] board) {
        return Math.abs(fromLine - toLine) <= 1 && Math.abs(fromColumn - toColumn) <= 1;
    }
}