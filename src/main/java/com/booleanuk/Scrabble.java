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
        String wordLower = word.toLowerCase();
        this.calculateScore(wordLower);
        this.verifyNoMultiLetterMultipliers(wordLower);
    }

    private void calculateScore(String word) {
        for (char c : word.toCharArray()) {
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

        // Unclosed multiplier
        if (this.multipliers.pop() != 1)
            this.score = 0;
    }

    // Only verifies there are no multiletter multipliers in the middle of the word,
    // e.g. "he{ll}o"
    private void verifyNoMultiLetterMultipliers(String word) {
        if (word.contains("{"))
            this.verifyNoMultiLetterDoubles(word);
        else if (word.contains("["))
            this.verifyNoMultiLetterTriples(word);
    }

    private void verifyNoMultiLetterDoubles(String word) {
        int openingIndex = word.indexOf('{');
        int closingIndex = word.indexOf('}');
        if (openingIndex > 0 && closingIndex - openingIndex > 2)
            this.score = 0;
    }

    private void verifyNoMultiLetterTriples(String word) {
        int openingIndex = word.indexOf('[');
        int closingIndex = word.indexOf(']');
        if (openingIndex > 0 && closingIndex - openingIndex > 2)
            this.score = 0;
    }

    public int score() {
        return this.score;
    }

}
