public class WrongNumberOfPlayers extends Exception {

    public WrongNumberOfPlayers() {
        super("You entered a wrong amount of players. Please enter a number between 2 and 4 players");
    }
    public WrongNumberOfPlayers(String s) {
        super(s);
    }
    public String getMessage() {
        return super.getMessage();
    }
}
