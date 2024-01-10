import java.util.Random;
import java.util.Scanner;

public class SoccerGame {
    private static final int MAX_ATTEMPTS = 5;
    private int playerScore = 0;
    private int botScore = 0;
    private Random random = new Random();

    public void startGame(Scanner scanner) {
        int attempt = 0;
        while (attempt < MAX_ATTEMPTS) {
            System.out.println("Attempt " + (attempt + 1) + " of " + MAX_ATTEMPTS);

            // Player's turn to shoot
            System.out.println("Type 'shoot' to take your shot:");
            String playerInput = scanner.nextLine();
            if ("shoot".equalsIgnoreCase(playerInput)) {
                if (shoot()) {
                    playerScore++;
                    System.out.println("Goal! You scored!");
                } else {
                    System.out.println("No goal! Bot saved it!");
                }
            }

            // Bot's turn to shoot
            System.out.println("Type 'save' to try to save the bot's shot:");
            String botInput = scanner.nextLine();
            if ("save".equalsIgnoreCase(botInput)) {
                if (!save(attempt)) {
                    botScore++;
                    System.out.println("Bot scored!");
                } else {
                    System.out.println("You saved the shot!");
                }
            }

            attempt++;
        }

        System.out.println("Final Score: Player " + playerScore + ", Bot " + botScore);
        if (playerScore > botScore) {
            System.out.println("You win!");
        } else if (playerScore < botScore) {
            System.out.println("Bot wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    private boolean shoot() {

        return random.nextInt(10) < 5;
    }

    private boolean save(int attempt) {

        return random.nextInt(10) < 5 || (attempt % 2 == 0);
    }
}
