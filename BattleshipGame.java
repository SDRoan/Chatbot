import java.util.Random;
import java.util.Scanner;

public class BattleshipGame {
    private static final int BOARD_SIZE = 10;
    private static final char SHIP = 'S';
    private static final char HIT = 'X';
    private static final char MISS = 'O';
    private static final char EMPTY = '.';
    private char[][] playerBoard;
    private char[][] botBoard;
    private Random random = new Random();

    public BattleshipGame() {
        playerBoard = new char[BOARD_SIZE][BOARD_SIZE];
        botBoard = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard(playerBoard);
        initializeBoard(botBoard);
        placeShips(botBoard); // Place ships for bot
    }

    private void initializeBoard(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void placeShips(char[][] board) {

        int row = random.nextInt(BOARD_SIZE);
        int col = random.nextInt(BOARD_SIZE);
        board[row][col] = SHIP;
    }

    public void startGame(Scanner scanner) {
        System.out.println("Starting Battleship game...");
        boolean gameRunning = true;
        while (gameRunning) {
            System.out.println("Player's Board:");
            printBoard(playerBoard);
            System.out.println("Enter your move (e.g., A5) or type 'quit' to exit:");
            String move = scanner.nextLine();
            if ("quit".equalsIgnoreCase(move)) {
                gameRunning = false;
            } else {
                processPlayerMove(move);
                processBotMove();
            }
        }
    }

    private void processPlayerMove(String move) {
        if (!move.matches("[A-Ja-j](10|[1-9])")) {
            System.out.println("Invalid move. Please enter a move like 'A5'.");
            return;
        }

        int col = Character.toUpperCase(move.charAt(0)) - 'A';
        int row = move.length() == 3 ? 9 : move.charAt(1) - '1';

        if (playerBoard[row][col] != EMPTY) {
            System.out.println("You already guessed this position.");
            return;
        }

        if (botBoard[row][col] == SHIP) {
            System.out.println("Hit!");
            playerBoard[row][col] = HIT;
        } else {
            System.out.println("Miss.");
            playerBoard[row][col] = MISS;
        }
    }

    private void processBotMove() {
        int row, col;
        do {
            row = random.nextInt(BOARD_SIZE);
            col = random.nextInt(BOARD_SIZE);
        } while (playerBoard[row][col] != EMPTY);


        if (playerBoard[row][col] == SHIP) {
            System.out.println("Bot hit your ship at " + (char)('A' + col) + (row + 1) + "!");
            playerBoard[row][col] = HIT;
        } else {
            System.out.println("Bot missed at " + (char)('A' + col) + (row + 1) + ".");
            playerBoard[row][col] = MISS;
        }
    }

    private void printBoard(char[][] board) {
        System.out.print("  ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((char)('A' + i) + " ");
        }
        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((i < 9 ? " " : "") + (i + 1) + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BattleshipGame game = new BattleshipGame();
        game.startGame(scanner);
    }
}
