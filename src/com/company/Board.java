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
}