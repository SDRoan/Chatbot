import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;


public class SimpleChatBot {
    private static final String[] CHOICES = {"rock", "paper", "scissors"};
    private static final int BOARD_SIZE = 8;
    private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean endChat = false;

        System.out.println("Chatbot: Hello! My name is Ruhu. I am a chatbot. What is your name?");
        System.out.print("You: ");
        String name = scanner.nextLine(); 

        System.out.println("Chatbot: Nice to meet you " + name + ". How can I help you today? or type 'bye' if you want to end the conversation.");

        while (!endChat) {
            System.out.print(name + ": ");
            input = scanner.nextLine().toLowerCase();

            if (input.matches(".*\\d+.*")) {
                calculate(input);
            } else {
                switch (input) {
                    case "hello":
                    case "hi":
                        System.out.println("ChatBot: Hello there! How can I help you?");
                        break;
                    case "play":
                        playRockPaperScissors(scanner);
                        break;
                    case "chess":
                        playChess(scanner);
                        break;
                    case "bye":
                        System.out.println("Chatbot: Goodbye, " + name + "! Have a great day!");
                        endChat = true;
                        break;
                    default:
                        System.out.println("ChatBot: Sorry, I didn't understand that.");
                        break;
                    case "battleship":
                        BattleshipGame battleshipGame = new BattleshipGame();
                        battleshipGame.startGame(scanner);
                        break;
                    case "generate password":
                        System.out.print("Enter desired password length: ");
                        int length = scanner.nextInt();
                        scanner.nextLine();  // Consume the remaining newline
                        String password = PasswordGenerator.generatePassword(length);
                        System.out.println("Generated Password: " + password);
                        break;
                    case "soccer":
                        SoccerGame soccerGame = new SoccerGame();
                        soccerGame.startGame(scanner);
                        break;
                    case "date time":
                        String currentDateTime = DateTimeResponder.getCurrentDateTime();
                        System.out.println("Chatbot: The current date and time is " + currentDateTime);
                        break;
                    case "check password strength":
                        System.out.println("Enter the password to check:");
                        String password1 = scanner.nextLine();
                        boolean isStrong = PasswordStrengthChecker.isStrongPassword(password1);
                        if (isStrong) {
                            System.out.println("This is a strong password.");
                        } else {
                            System.out.println("This is not a strong password.");
                        }
                        break;

                }
            }
        }
    }

    private static void playRockPaperScissors(Scanner scanner) {
        System.out.println("ChatBot: Let's play Rock, Paper, Scissors! Type your choice.");
        String userChoice = scanner.nextLine().toLowerCase();

        if (!userChoice.equals("rock") && !userChoice.equals("paper") && !userChoice.equals("scissors")) {
            System.out.println("ChatBot: That's not a valid choice. Let's play again sometime.");
            return;
        }

        String computerChoice = CHOICES[random.nextInt(CHOICES.length)];
        System.out.println("ChatBot: I choose " + computerChoice);

        if (userChoice.equals(computerChoice)) {
            System.out.println("ChatBot: It's a tie!");
        } else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                   (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                   (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            System.out.println("ChatBot: You win!");
        } else {
            System.out.println("ChatBot: I win!Suiii");
        }
    }

    private static void playChess(Scanner scanner) {
        initializeBoard();
        boolean gameRunning = true;
        while (gameRunning) {
            printBoard();
            System.out.println("Enter your move (e.g., 'e2 e4') or 'quit' to exit:");
            String move = scanner.nextLine();
            if ("quit".equalsIgnoreCase(move)) {
                gameRunning = false;
            } else {
                processMove(move);
                chatBotMove();
            }
        }
    }
    private static void calculate(String input) {
        try {
            String[] tokens = input.split(" ");
            double num1 = Double.parseDouble(tokens[0]);
            String operator = tokens[1];
            double num2 = Double.parseDouble(tokens[2]);
            double result = performCalculation(num1, operator, num2);

            System.out.println("ChatBot: The result is " + result);
        } catch (NumberFormatException e) {
            System.out.println("ChatBot: Invalid number format. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println("ChatBot: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ChatBot: An error occurred. Please enter a valid arithmetic expression.");
        }
    }

    private static double performCalculation(double num1, String operator, double num2) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator. Use +, -, *, or /.");
        }
    }


    private static void initializeBoard() {

        board[0] = new char[]{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};
        board[1] = new char[]{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'};
        for (int i = 2; i <= 5; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '.';
            }
        }
        board[6] = new char[]{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'};
        board[7] = new char[]{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'};
    }

    private static void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void processMove(String move) {
        String[] parts = move.split(" ");
        if (parts.length == 2) {
            int[] start = parsePosition(parts[0]);
            int[] end = parsePosition(parts[1]);
            if (start != null && end != null && board[start[0]][start[1]] != '.') {
                makeMove(start, end);
            }
        }
    }

    private static void chatBotMove() {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (Character.isLowerCase(board[i][j])) { // ChatBot's pieces
                    addValidMoves(i, j, moves);
                }
            }
        }

        if (!moves.isEmpty()) {
            int[] selectedMove = moves.get(random.nextInt(moves.size()));
            makeMove(new int[]{selectedMove[0], selectedMove[1]}, new int[]{selectedMove[2], selectedMove[3]});
        }
    }

    private static void addValidMoves(int row, int col, List<int[]> moves) {
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < BOARD_SIZE && newCol >= 0 && newCol < BOARD_SIZE) {
                if (board[newRow][newCol] == '.') {
                    moves.add(new int[]{row, col, newRow, newCol});
                }
            }
        }
    }

    private static int[] parsePosition(String position) {
        if (position.length() == 2) {
            int col = position.charAt(0) - 'a';
            int row = 8 - (position.charAt(1) - '0');
            if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
                return new int[]{row, col};
            }
        }
        return null;
    }

    private static void makeMove(int[] start, int[] end) {
        char piece = board[start[0]][start[1]];
        board[end[0]][end[1]] = piece;
        board[start[0]][start[1]] = '.';
    }


}
