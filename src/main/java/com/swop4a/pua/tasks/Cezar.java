package com.swop4a.pua.tasks;

import com.swop4a.pua.Task;
import com.swop4a.pua.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

/**
 * Cezar cypher.
 *
 * @author Pavkin Alexandr
 * @version 1.0
 */

public class Cezar implements Task {
    private Scanner scan;
    private PrintWriter writer;

    @Override
    public void run() throws FileNotFoundException {
        code(Util.ENGLISH_CHARACTER_MAP);
        decode(Util.ENGLISH_CHARACTER_MAP);
    }

    private void code(Map<Character, Integer> characterMap) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/cezar/code_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/cezar/code_out.txt"));

        int n = scan.nextInt(), k = scan.nextInt(), m = characterMap.size();

        while (scan.hasNext()) {
            String word = scan.next();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                int ch = codeHelp(word, n, m, k, i, characterMap);

                for (Map.Entry<Character, Integer> x : characterMap.entrySet()) {
                    if (x.getValue() == ch) {
                        result.append(x.getKey());
                        break;
                    }
                }
            }
            writer.print(result + " ");
        }

        scan.close();
        writer.close();
    }

    private void decode(Map<Character, Integer> characterMap) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/cezar/decode_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/cezar/decode_out.txt"));

        int n = scan.nextInt(), k = scan.nextInt(), m = Util.ENGLISH_CHARACTER_MAP.size();

        while (scan.hasNext()) {
            String word = scan.next();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                int ch = decodeHelp(word, n, m, k, i, characterMap);

                for (Map.Entry<Character, Integer> x : characterMap.entrySet()) {
                    if (x.getValue() == ch) {
                        result.append(x.getKey());
                        break;
                    }
                }
            }
            writer.print(result + " ");
        }

        scan.close();
        writer.close();
    }

    private int codeHelp(String word, int n, int m, int k, int i, Map<Character, Integer> characterMap) {
        return (characterMap.get(word.charAt(i)) * n + k) % m;
    }

    private int decodeHelp(String word, int n, int m, int k, int i, Map<Character, Integer> characterMap) {
        int j = 1, ch = characterMap.get(word.charAt(i));

        while ((ch + m * j - k) % n != 0) {
            j++;
        }
        ch = (ch + m * j - k) / n;

        return ch % m;
    }
}
