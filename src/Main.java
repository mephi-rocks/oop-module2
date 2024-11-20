import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        String currentPlayer = "White"; // Начинаем с Белых
        System.out.println("""
                Инструкция:
                'exit' - для выхода
                'replay' - для перезапуска игры
                'castling0' или 'castling7' - для рокировки по соответствующей линии
                'move e2 e4' - для передвижения фигуры с позиции e2 на e4
                """);
        System.out.println();
        board.printBoard();
        while (true) {
            System.out.println("Ход " + (currentPlayer.equals("White") ? "белых" : "черных"));
            String s = scanner.nextLine();
            if (s.equals("exit")) break;
            else if (s.equals("replay")) {
                System.out.println("Заново");
                board = buildBoard();
                board.printBoard();
                currentPlayer = "White";
            } else {
                if (s.contains("castling0") || s.contains("castling7")) {
                    boolean success = false;

                    if (currentPlayer.equals("White")) {
                        if (s.equals("castling0")) {
                            success = board.castling0();
                        } else if (s.equals("castling7")) {
                            success = board.castling7();
                        }
                    } else {
                        if (s.equals("castling0")) {
                            success = board.blackCastling0();
                        } else if (s.equals("castling7")) {
                            success = board.blackCastling7();
                        }
                    }

                    if (success) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                        currentPlayer = currentPlayer.equals("White") ? "Black" : "White";
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.startsWith("move")) {
                    String[] parts = s.split(" ");
                    if (parts.length == 3) {
                        String from = parts[1];
                        String to = parts[2];
                        try {
                            int fromColumn = from.charAt(0) - 'a';
                            int fromLine = 8 - Character.getNumericValue(from.charAt(1));
                            int toColumn = to.charAt(0) - 'a';
                            int toLine = 8 - Character.getNumericValue(to.charAt(1));

                            ChessPiece piece = board.board[fromLine][fromColumn];
                            if (piece != null && piece.getColor().equals(currentPlayer)) {
                                if (board.moveToPosition(fromLine, fromColumn, toLine, toColumn)) {
                                    System.out.println("Передвижение успешно");
                                    board.printBoard();
                                    // Меняем ход
                                    currentPlayer = currentPlayer.equals("White") ? "Black" : "White";
                                } else {
                                    System.out.println("Передвижение не удалось");
                                }
                            } else {
                                System.out.println("Неправильный выбор фигуры. Сначала выберите " + (currentPlayer.equals("White") ? "белую" : "черную") + " фигуру.");
                            }
                        } catch (Exception e) {
                            System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                        }
                    } else {
                        System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                    }
                }
            }
        }
        scanner.close();
    }

    private static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard();
        // Инициализация черных фигур
        board.board[7][0] = new Rook("Black");
        board.board[7][1] = new Horse("Black");
        board.board[7][2] = new Bishop("Black");
        board.board[7][3] = new Queen("Black");
        board.board[7][4] = new King("Black");
        board.board[7][5] = new Bishop("Black");
        board.board[7][6] = new Horse("Black");
        board.board[7][7] = new Rook("Black");
        for (int i = 0; i < 8; i++) {
            board.board[6][i] = new Pawn("Black");
        }
        // Инициализация белых фигур
        board.board[0][0] = new Rook("White");
        board.board[0][1] = new Horse("White");
        board.board[0][2] = new Bishop("White");
        board.board[0][3] = new Queen("White");
        board.board[0][4] = new King("White");
        board.board[0][5] = new Bishop("White");
        board.board[0][6] = new Horse("White");
        board.board[0][7] = new Rook("White");
        for (int i = 0; i < 8; i++) {
            board.board[1][i] = new Pawn("White");
        }
        return board;
    }
}