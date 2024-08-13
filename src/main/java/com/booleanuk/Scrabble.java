package com.booleanuk;

import java.util.HashMap;
import java.util.Stack;

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
    private Stack<Integer> multipliers = new Stack<>();

    public Scrabble(String word) {
        multipliers.push(1);
        this.calculateScore(word);
    }

    private void calculateScore(String word) {
        // TODO: not lowercase whole string
        String wordLower = word.toLowerCase();
        for (char c : wordLower.toCharArray()) {
            if (c == '[') {
                this.multipliers.push(3);
            } else if (c == ']') {
                if (this.multipliers.pop() != 3) {
                    this.score = 0;
                    return;
                }
            } else if (c == '{') {
                this.multipliers.push(2);
            } else if (c == '}') {
                if (this.multipliers.pop() != 2) {
                    this.score = 0;
                    return;
                }
            } else {
                int multiplier = 1;
                for (Integer mul : this.multipliers)
                    multiplier *= mul;
                Integer charScoreNoMultiplier = letterScores.get(c);
                // Invalid character, score is 0
                if (charScoreNoMultiplier == null) {
                    this.score = 0;
                    return;
                }
                this.score += charScoreNoMultiplier * multiplier;
            }
        }

        if (this.multipliers.pop() != 1)
            this.score = 0;
    }

    public int score() {
        return this.score;
    }

}
