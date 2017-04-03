package com.swop4a.pua.tasks;

import com.swop4a.pua.Task;
import com.swop4a.pua.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Pair cypher.
 *
 * @author Pavkin Alexandr
 * @version 1.0
 */

public class PairCypher implements Task {
    private final String KEY = "qwertyuiopasd";

    private Scanner scan;
    private PrintWriter writer;

    @Override
    public void run() throws FileNotFoundException {
        code(Util.ENGLISH_CHARACTER_MAP);
        decode(Util.ENGLISH_CHARACTER_MAP);
    }

    private void code(Map<Character, Integer> characterMap) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/paircypher/code_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/paircypher/code_out.txt"));

        Map<Character, Character> cypher = createPairCypher(characterMap);

        while (scan.hasNext()) {
            String word = scan.next().toLowerCase();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (characterMap.containsKey(ch)) {
                    result.append(cypher.get(ch));
                }
            }
            writer.print(result + " ");
        }

        scan.close();
        writer.close();
    }

    private void decode(Map<Character, Integer> characterMap) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/paircypher/decode_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/paircypher/decode_out.txt"));

        Map<Character, Character> cypher = createPairCypher(characterMap);

        while (scan.hasNext()) {
            String word = scan.next();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (characterMap.containsKey(ch)) {
                    for (Map.Entry<Character, Character> c : cypher.entrySet()) {
                        if (c.getValue().equals(ch)) {
                            result.append(c.getKey());
                            break;
                        }
                    }
                }
            }
            writer.print(result + " ");
        }

        scan.close();
        writer.close();
    }

    private Map<Character, Character> createPairCypher(Map<Character, Integer> characterMap) {
        HashMap<Character, Character> result = new HashMap<>();

        for (int i = 0; i < KEY.length(); i++) {
            char ch = KEY.charAt(i);


            for (Map.Entry<Character, Integer> e : characterMap.entrySet()) {
                if (!result.containsKey(e.getKey()) && !result.containsKey(ch) && !KEY.contains(e.getKey() + "")) {
                    result.put(ch, e.getKey());
                    result.put(e.getKey(), ch);
                    break;
                }
            }
        }

        return result;
    }
}
