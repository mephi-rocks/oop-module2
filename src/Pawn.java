public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMove(int fromLine, int fromColumn, int toLine, int toColumn, ChessPiece[][] board) {
        // Логика для обычного хода пешкой
        // Логика для атаки пешкой
        // Логика для продвижения пешки в ферзи
        if (toLine == 0 || toLine == 7) {
            promote(toLine, toColumn, board);
            return true; // Предполагаем, что любое продвижение на противоположный край доски успешно
        }
        return true; // Замена на актуальную логику проверки
    }

    private void promote(int toLine, int toColumn, ChessPiece[][] board) {
        board[toLine][toColumn] = new Queen(this.color);
    }
}