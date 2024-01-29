import java.util.Random;

public class LadderAndSnake {
    private int boardSize = 10;
    private int[][] board = new int[boardSize][boardSize];
    private int numOfPlayers;
    private int[] playerPositions;
    private int[][] snakeAndLadderPositions;
    private int count;
    private Random random = new Random();


    public LadderAndSnake(int numOfPlayers) {


        this.numOfPlayers = numOfPlayers;
        this.playerPositions = new int[numOfPlayers];
        this.snakeAndLadderPositions = new int[100][2];
        this.count = 0;

        boardInitialize();

    }

    private void boardInitialize() {
        int counter = 1;
        for (int i = boardSize - 1; i >= 0; i--) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = counter++;
            }
        }
        setSnakeAndLadderPositions(1, 38);//ladder
        setSnakeAndLadderPositions(4, 14);//ladder
        setSnakeAndLadderPositions(16, 6);//snake
        setSnakeAndLadderPositions(9, 31);//ladder
        setSnakeAndLadderPositions(62, 19);//snake
        setSnakeAndLadderPositions(21, 42);//ladder
        setSnakeAndLadderPositions(95, 24);//snake
        setSnakeAndLadderPositions(28, 84);//ladder
        setSnakeAndLadderPositions(48, 30);//snake
        setSnakeAndLadderPositions(36, 44);//ladder
        setSnakeAndLadderPositions(51, 67);//ladder
        setSnakeAndLadderPositions(64, 60);//snake
        setSnakeAndLadderPositions(93, 68);//snake
        setSnakeAndLadderPositions(71, 91);//ladder
        setSnakeAndLadderPositions(97, 76);//snake
        setSnakeAndLadderPositions(98, 78);//snake
        setSnakeAndLadderPositions(80, 100);//ladder
    }

    public void setSnakeAndLadderPositions(int start, int end) {
        snakeAndLadderPositions[count][0] = start;
        snakeAndLadderPositions[count][1] = end;
        count++;
    }

    public int[] determinePlayerOrder() {
        int[] order = new int[numOfPlayers];
        int[] diceThrows = new int[numOfPlayers];

        // Each player throws the dice
        for (int i = 0; i < numOfPlayers; i++) {
            diceThrows[i] = flipDice();
            System.out.println("Player " + (i + 1) + " got a dice value of " + diceThrows[i]);
        }

        // Initialize order array with player indices
        for (int i = 0; i < numOfPlayers; i++) {
            order[i] = i + 1;
        }

        // Sort the order based on dice throws in descending order
        for (int i = 0; i < numOfPlayers - 1; i++) {
            for (int j = i + 1; j < numOfPlayers; j++) {
                if (diceThrows[j] > diceThrows[i]) {
                    // Swap the dice throws and corresponding player indices
                    int tempThrow = diceThrows[i];
                    diceThrows[i] = diceThrows[j];
                    diceThrows[j] = tempThrow;

                    int tempPlayer = order[i];
                    order[i] = order[j];
                    order[j] = tempPlayer;
                }
            }
        }

        for (int i = 0; i < numOfPlayers - 1; i++) {
            for (int j = i + 1; j < numOfPlayers; j++) {
                while (diceThrows[i] == diceThrows[j]) {
                    System.out.println("Tie between Player " + order[i] + " and Player " + order[j] +
                            ". Attempting to break the tie.");
                    diceThrows[i] = flipDice();
                    diceThrows[j] = flipDice();
                    System.out.println("Player " + order[i] + " got a new dice value of " + diceThrows[i]);
                    System.out.println("Player " + order[j] + " got a new dice value of " + diceThrows[j]);
                }
            }
        }

        // Display the final order
        System.out.print("Reached final decision on order of playing: ");
        for (int player : order) {
            System.out.print("Player " + player + " ");
        }
        System.out.println();

        return order;
    }

    public int flipDice() {
        return random.nextInt(6) + 1;
    }

    public void play() {

        System.out.println("Game is played by " + numOfPlayers + " players.");

        // Determine the order of playing
        int[] finalOrder = determinePlayerOrder();
//        System.out.print("Reached final decision on order of playing: ");
        for (int player : finalOrder) {
            System.out.print("Player " + player + " ");
        }
        System.out.println();

        // Start playing the game
        int currentPlayer = 0;
        while (true) {
            int diceValue = flipDice();
            int currentPosition = playerPositions[currentPlayer];

            System.out.println("Player " + (currentPlayer + 1) + " got a dice value of " + diceValue + "; now in square " + currentPosition);

            // Update player position
            int newPosition = currentPosition + diceValue;
            for (int i = 0; i < count; i++) {
                if (snakeAndLadderPositions[i][0] == newPosition) {
                    newPosition = snakeAndLadderPositions[i][1];
                    break;
                }
            }

            if (newPosition > (boardSize * boardSize) - 1) {
                int excessMoves = newPosition - (boardSize * boardSize);
                newPosition = (boardSize * boardSize) - excessMoves;
            }

            playerPositions[currentPlayer] = newPosition;

            // Check if the game is over
            if (newPosition == (boardSize * boardSize)) {
                System.out.println("Player " + (currentPlayer + 1) + " wins!");
                break;
            }

            // Move to the next player
            currentPlayer = (currentPlayer + 1) % numOfPlayers;
        }
    }
}


