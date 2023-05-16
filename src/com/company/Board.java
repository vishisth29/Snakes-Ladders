package com.company;

import java.util.*;

class Board {
    private static final int SIZE = 100;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;

    public Board() {
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        initializeSnakes();
        initializeLadders();
    }

    public int getSize() {
        return SIZE;
    }

    public int getNextPosition(int currentPosition, int diceValue) {
        int newPosition = currentPosition + diceValue;
        if (newPosition > SIZE) {
            newPosition = SIZE;
        }
        return adjustPosition(newPosition);
    }

    private int adjustPosition(int position) {
        if (snakes.containsKey(position)) {
            return snakes.get(position);
        }
        if (ladders.containsKey(position)) {
            return ladders.get(position);
        }
        return position;
    }

    private void initializeSnakes() {
        Random random = new Random();
        int numSnakes = random.nextInt(5) + 5;

        for (int i = 0; i < numSnakes; i++) {
            int start = random.nextInt(SIZE - 1) + 1;
            int end = random.nextInt(start) + 1;
            snakes.put(start, end);
        }
    }

    private void initializeLadders() {
        Random random = new Random();
        int numLadders = random.nextInt(5) + 5;
        for (int i = 0; i < numLadders; i++) {
            int start = random.nextInt(SIZE - 1) + 1;
            int end = random.nextInt(SIZE - start) + start + 1;
            ladders.put(start, end);
        }
    }

    public void printBoard() {
        System.out.println("----- INITIAL BOARD -----");
        for (int i = SIZE; i >= 1; i--) {
            if (snakes.containsKey(i)) {
                System.out.print("S ");
            } else if (ladders.containsKey(i)) {
                System.out.print("L ");
            } else {
                System.out.print(i + " ");
            }

            if (i % 10 == 1) {
                System.out.println();
            }
        }
        System.out.println("-----------------");
    }

    public void printBoardWithPlayers(List<Player> players) {
        System.out.println("----- FINAL BOARD -----");
        for (int i = SIZE; i >= 1; i--) {
            boolean hasPlayer = false;
            for (Player player : players) {
                if (player.getPosition() == i) {
                    System.out.print(player.getName().charAt(0) + " ");
                    hasPlayer = true;
                    break;
                }
            }
            if (!hasPlayer) {
                if (snakes.containsKey(i)) {
                    System.out.print("S ");
                } else if (ladders.containsKey(i)) {
                    System.out.print("L ");
                } else {
                    System.out.print(i + " ");
                }
            }

            if (i % 10 == 1) {
                System.out.println();
            }
        }
        System.out.println("-----------------");
    }

}
