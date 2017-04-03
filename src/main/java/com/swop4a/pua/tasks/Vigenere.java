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
 * Multilevel encryption system (Vigenere table).
 *
 * @author Pavkin Alexandr
 * @version 1.0
 */

public class Vigenere implements Task {
    private final String KEY = "pavkinalexandr";

    private Scanner scan;
    private PrintWriter writer;

    @Override
    public void run() throws FileNotFoundException {
        code(Util.ENGLISH_CHARACTER_VIGENERE_TABLE);
        decode(Util.ENGLISH_CHARACTER_VIGENERE_TABLE);
    }

    private void code(Map<Pair<Character, Character>, Character> table) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/vigenere/code_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/vigenere/code_out.txt"));

        while (scan.hasNext()) {
            String word = scan.next().toLowerCase();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (table.containsValue(ch)) {
                    result.append(table.get(new Pair<>(KEY.charAt(i % KEY.length()), word.charAt(i))));
                }
            }
            writer.print(result + " ");
        }

        scan.close();
        writer.close();
    }

    private void decode(Map<Pair<Character, Character>, Character> table) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/vigenere/decode_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/vigenere/decode_out.txt"));

        while (scan.hasNext()) {
            String word = scan.next().toLowerCase();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                for (Map.Entry<Pair<Character, Character>, Character> e : table.entrySet()) {
                    if (e.getKey().getKey() == KEY.charAt(i % KEY.length())
                            && e.getValue() == word.charAt(i)) {
                        result.append(e.getKey().getValue());
                    }
                }
            }
            writer.print(result + " ");
        }

        scan.close();
        writer.close();
    }
}
