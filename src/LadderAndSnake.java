import java.util.Random;

public class LadderAndSnake {
    private int boardSize = 10;
    private int[][] board = new int[boardSize][boardSize];
    private int numOfPlayers;
    private int[] playerPositions;
    private int[][]snakeAndLadderPositions;
    private int count;



    public int flipDice(){
        return new Random().nextInt(6) + 1;
    }

    public void play(){

    }

}
