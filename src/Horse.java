public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMove(int fromLine, int fromColumn, int toLine, int toColumn, ChessPiece[][] board) {
        int lineDifference = Math.abs(fromLine - toLine);
        int columnDifference = Math.abs(fromColumn - toColumn);
        return lineDifference * columnDifference == 2;
    }
}