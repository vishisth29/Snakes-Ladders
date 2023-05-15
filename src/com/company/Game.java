package com.company;
import java.util.*;
public class Game {
    private List<Player> players;
    private Board board;
    private Dice dice;

    public Game() {
        this.players = new ArrayList<>();
        this.board = new Board();
        this.dice = new Dice();
    }

    public void addPlayer(String playerName) {
        Player player = new Player(playerName);
        players.add(player);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean newGame = true;

        while (newGame) {
            players.clear();
            board = new Board();
            System.out.print("Enter the total number of players:");
            int numPlayers = scanner.nextInt();
            scanner.nextLine();

            for (int i = 1; i <= numPlayers; i++) {
                System.out.print("Enter the name of player " + i + ": ");
                String playerName = scanner.nextLine();
                addPlayer(playerName);
            }

            boolean gameEnd = false;

            while (!gameEnd) {
                for (Player player : players) {
                    System.out.println(player.getName() + ", press key to roll the dice.");
                    scanner.nextLine();

                    int diceValue = dice.roll();
                    System.out.println(player.getName() + " rolled a " + diceValue);

                    if (diceValue == 6 && player.getPosition() == 0) {
                        player.setPosition(1);
                    } else if (player.getPosition() > 0) {
                        int newPosition = board.getNextPosition(player.getPosition(), diceValue);
                        player.setPosition(newPosition);
                        System.out.println();
                    }

                    System.out.println(player.getName() + " now at " + player.getPosition());

                    if (player.getPosition() == board.getSize()) {
                        gameEnd = true;
                        System.out.println(player.getName() + " Won the GAME!");
                        break;
                    }
                }
            }
        }

    }

}