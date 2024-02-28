import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    Snake snake;
    Ladder ladder;
    int boardSize;

    int totalPlayers;
    int currentIndex;
    LinkedList<Player> players;
    LinkedList<Player> activePlayers;

    ArrayList<Box> board;

    boolean gameOver;
   

    public Game() {
        gameOver = false;
        currentIndex = 0;
        players = new LinkedList<>();
        activePlayers = new LinkedList<>();
    }

    public void generateBoard() {
        board = new ArrayList<>();

        for (int i = 0; i <= boardSize; i++) {
            Box box = new Box();
            board.add(box);
        }

        for (int key : snake.headTail.keySet()) {
            board.get(key).isSnakeHead = true;
        }

        for (int key : ladder.startEnd.keySet()) {
            board.get(key).isLadderStart = true;
        }
    }

    public void play() {

        activePlayers.addAll(players);

        while (!gameOver) {
            //Player currentPlayer = activePlayers.get((int)(turn % (activePlayers.size() - 1)));
            Player currentPlayer = activePlayers.get(currentIndex); 
            int dieSum = rollDice(1);
            updatePlayerBox(currentPlayer, dieSum);
            if (currentIndex == activePlayers.size() - 1) {
                currentIndex = 0;
            }else {
                currentIndex++;
            }           
            
        }

    }

    private void updatePlayerBox(Player currentPlayer, int dieSum) {
        int currBox = currentPlayer.currentBox;
        int initBox = currBox;

        currBox += dieSum;

        if (currBox == 100) {
            gameOver = true; // TO-DO: Needs to be modified for alternate rule where game goes on till 1
                             // player is left.
            System.out.println(
                    currentPlayer.name + " rolled a " + dieSum + " and moved from " + initBox + " to " + currBox);
            System.out.println(currentPlayer.name + " wins the game");
            //removePlayer(); TO-DO: To remove player from the list of current (active) players
            return;
        } else if (currBox > 100) {
            return;
        }

        boolean stop = false;

        while (!stop) {
            if (board.get(currBox).isLadderStart) {
                currBox = ladder.startEnd.get(currBox);
            } else if (board.get(currBox).isSnakeHead) {
                currBox = snake.headTail.get(currBox);
            }

            if (currBox == 100) {
                gameOver = true; // TO-DO: Needs to be modified for alternate rule where game goes on till 1
                                 // player is left.
                System.out.println(
                        currentPlayer.name + " rolled a " + dieSum + " and moved from " + initBox + " to " + currBox);
                System.out.println(currentPlayer.name + " wins the game");
                return;
            }

            if (!board.get(currBox).isLadderStart && !board.get(currBox).isSnakeHead) {
                stop = true;
            }
        }

        currentPlayer.currentBox = currBox;
        System.out
                .println(currentPlayer.name + " rolled a " + dieSum + " and moved from " + initBox + " to " + currBox);
    }

   

    private int rollDice(int dieCount) {
        Random random = new Random();
        int rollSum = 0;

        while (dieCount > 0) {
            rollSum += random.nextInt(6) + 1;
            dieCount--;
        }

        // add condition to keep rolling in case of 6, return rollSum accordingly;

        return rollSum;
    }
}
