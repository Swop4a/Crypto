package com.swop4a.pua.tasks;

import com.swop4a.pua.Task;
import com.swop4a.pua.util.Util;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

/**
 * Substitution cypher (Plybius square).
 *
 * @author Pavkin Alexandr
 * @version 1.0
 */

public class PolybiusSquare implements Task {
    private Scanner scan;
    private PrintWriter writer;

    @Override
    public void run() throws FileNotFoundException {
        code(Util.ENGLISH_CHARACTER_POLYBIUS_MATRIX);
        decode(Util.ENGLISH_CHARACTER_POLYBIUS_MATRIX);
    }

    private void code(Map<Character, Pair<Integer, Integer>> matrix) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/polybiussquare/code_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/polybiussquare/code_out.txt"));

        while (scan.hasNext()) {
            String word = scan.next().toLowerCase();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (matrix.containsKey(ch)) {
                    result.append(getUpperCharacter(matrix, ch));
                }

            }
            writer.print(result + " ");
        }

        scan.close();
        writer.close();
    }

    private void decode(Map<Character, Pair<Integer, Integer>> matrix) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/polybiussquare/decode_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/polybiussquare/decode_out.txt"));

        while (scan.hasNext()) {
            String word = scan.next().toLowerCase();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (matrix.containsKey(ch)) {
                    result.append(getLowerCharacter(matrix, ch));
                }
            }
            writer.print(result + " ");
        }

        scan.close();
        writer.close();
    }

    private Character getUpperCharacter(Map<Character, Pair<Integer, Integer>> matrix, Character ch) {
        Pair<Integer, Integer> cur = matrix.get(ch);
        Pair<Integer, Integer> upper;

        int column = 6;
        int row = matrix.size() % 6 == 0 ? matrix.size() / 6 : (matrix.size() / 6) + 1;

        if (cur.getKey() == 0) {
            if (cur.getValue() >= matrix.size() % row) {
                upper = new Pair<>(row - 2, cur.getValue());
            } else {
                upper = new Pair<>(row - 1, cur.getValue());
            }
        } else {
            upper = new Pair<>(cur.getKey() - 1, cur.getValue());
        }

        for (Map.Entry<Character, Pair<Integer, Integer>> e : matrix.entrySet()) {
            if (e.getValue().equals(upper)) {
                return e.getKey();
            }
        }

        return ' ';
    }

    private Character getLowerCharacter(Map<Character, Pair<Integer, Integer>> matrix, Character ch) {
        Pair<Integer, Integer> cur = matrix.get(ch);
        Pair<Integer, Integer> lower;

        int column = 6;
        int row = matrix.size() % 6 == 0 ? matrix.size() / 6 : (matrix.size() / 6) + 1;

        if (cur.getKey() == row - 1) {
            lower = new Pair<>(0, cur.getValue());
        } else if (cur.getKey() == row - 2) {
            if (cur.getValue() >= matrix.size() % row) {
                lower = new Pair<>(0, cur.getValue());
            } else {
                lower = new Pair<>(cur.getValue() + 1, cur.getValue());
            }
        } else {
            lower = new Pair<>(cur.getKey() + 1, cur.getValue());
        }

        for (Map.Entry<Character, Pair<Integer, Integer>> e : matrix.entrySet()) {
            if (e.getValue().equals(lower)) {
                return e.getKey();
            }
        }

        return ' ';
    }
}
