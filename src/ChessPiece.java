public abstract class ChessPiece {
    protected String color;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean canMove(int fromLine, int fromColumn, int toLine, int toColumn, ChessPiece[][] board);
}