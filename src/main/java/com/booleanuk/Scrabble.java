package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    private static HashMap<Character, Integer> letterScores = new HashMap<>();
    static {
        letterScores.put('a', 1);
        letterScores.put('b', 3);
        letterScores.put('c', 3);
        letterScores.put('d', 2);
        letterScores.put('e', 1);
        letterScores.put('f', 4);
        letterScores.put('g', 2);
        letterScores.put('h', 4);
        letterScores.put('i', 1);
        letterScores.put('j', 8);
        letterScores.put('k', 5);
        letterScores.put('l', 1);
        letterScores.put('m', 3);
        letterScores.put('n', 1);
        letterScores.put('o', 1);
        letterScores.put('p', 3);
        letterScores.put('q', 10);
        letterScores.put('r', 1);
        letterScores.put('s', 1);
        letterScores.put('t', 1);
        letterScores.put('u', 1);
        letterScores.put('v', 4);
        letterScores.put('w', 4);
        letterScores.put('x', 8);
        letterScores.put('y', 4);
        letterScores.put('z', 10);
    }
    private int score = 0;

    public Scrabble(String word) {
        this.calculateScore(word);
    }

    private void calculateScore(String word) {
        for (char c : word.toCharArray()) {
            char cLower = Character.toLowerCase(c);
            if (letterScores.containsKey(cLower))
                this.score += letterScores.get(cLower);
        }
    }

    public int score() {
        return this.score;
    }

}
