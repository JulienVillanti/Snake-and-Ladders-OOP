import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayLadderAndSnake {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        int attempts = 0;
        int numOfPlayers = 0;
        int totalNumOfAttempts = 3;

        System.out.println("Welcome to the Snake and Ladders game !!");

        //Asking the user to select how many players will play the game
        while (attempts < 4) {
            try {
                System.out.println("Please enter the number of players (between 2 and 4): ");
                numOfPlayers = kb.nextInt();

                if (numOfPlayers >= 2 && numOfPlayers <= 4) {
                    System.out.println("The number of players is " + numOfPlayers);
                    break;
                } else {
                    System.out.println("Wrong number of players, number must be between 2 and 4.(Remaining attempts: " + (totalNumOfAttempts - attempts) + ")");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, please enter a valid number. You have " + (totalNumOfAttempts - attempts) + " attempts remaining.");
                kb.nextLine();
            }
            attempts++;
        }
        if (attempts == 4) {
            System.out.println("You have reached the maximum number of attempts, this program will now terminate.");
        } else {
            LadderAndSnake game = new LadderAndSnake(numOfPlayers);
            game.play();
        }
        kb.close();
    }

}


