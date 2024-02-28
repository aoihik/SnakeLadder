import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Game game = new Game();
        takeInputs(game);
        //generateBoardElements(game); For auto-generation of board without user input.     
        
        game.generateBoard();
        game.play();
    }

    private static void takeInputs(Game game)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter board size: ");
        game.boardSize = scanner.nextInt();
        scanner.nextLine();        

        Snake snake = new Snake();
        snake.count = scanner.nextInt();
        scanner.nextLine();        
        
        for (int i = 0; i < snake.count; i++)
        {
            String snakeLine = scanner.nextLine();
            Scanner indexScanner = new Scanner(snakeLine);
            int head = indexScanner.nextInt();
            int tail = indexScanner.nextInt();

            snake.headTail.put(head, tail);
            indexScanner.close();
        }

        game.snake = snake;

        Ladder ladder = new Ladder();
        ladder.count = scanner.nextInt();
        scanner.nextLine();        

        for (int i = 0; i < ladder.count ; i++)
        {
            String ladderLine = scanner.nextLine();
            Scanner indexScanner = new Scanner(ladderLine);
            int start = indexScanner.nextInt();
            int end = indexScanner.nextInt();

            ladder.startEnd.put(start, end);
            indexScanner.close();
        }       
        
        game.ladder = ladder;

        game.totalPlayers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < game.totalPlayers; i++)
        {
            String playerName = scanner.nextLine();
            Player player = new Player(playerName);
            game.players.add(player);
        }

        scanner.close();
    }
    
}
