package com.swop4a.pua.tasks;

import com.swop4a.pua.Task;
import com.swop4a.pua.util.Util;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Cezar cypher with key.
 *
 * @author Pavkin Alexandr
 * @version 1.0
 */

public class CezarWithKey implements Task {
    private final String KEY = "шифровка";

    private Scanner scan;
    private PrintWriter writer;

    @Override
    public void run() throws FileNotFoundException {
        code(Util.RUSSIAN_CHARACTER_MAP);
        decode(Util.RUSSIAN_CHARACTER_MAP);
    }

    private void code(Map<Character, Integer> characterMap) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/cezarwithkey/code_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/cezarwithkey/code_out.txt"));

        int k = scan.nextInt();

        Map<Character, Pair<Integer, Integer>> cypher = createKeyCypher(characterMap, k);

        while (scan.hasNext()) {
            String word = scan.next().toLowerCase();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (characterMap.containsKey(ch)) {
                    int pos = cypher.get(ch).getKey();

                    for (Map.Entry<Character, Integer> e : characterMap.entrySet()) {
                        if (e.getValue() == pos) {
                            result.append(e.getKey());
                            break;
                        }
                    }
                }
            }
            writer.append(result + " ");
        }

        scan.close();
        writer.close();
    }

    private void decode(Map<Character, Integer> characterMap) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/cezarwithkey/decode_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/cezarwithkey/decode_out.txt"));

        int k = scan.nextInt();

        Map<Character, Pair<Integer, Integer>> cypher = createKeyCypher(characterMap, k);

        while (scan.hasNext()) {
            String word = scan.next();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (characterMap.containsKey(ch)) {
                    int pos = cypher.get(ch).getValue();

                    for (Map.Entry<Character, Pair<Integer, Integer>> e : cypher.entrySet()) {
                        if (e.getValue().getKey() == pos) {
                            result.append(e.getKey());
                            break;
                        }
                    }
                }
            }
            writer.append(result + " ");
        }

        scan.close();
        writer.close();
    }

    private Map<Character, Pair<Integer, Integer>> createKeyCypher(Map<Character, Integer> characterMap, int k) {
        Map<Character, Integer> cypher = new TreeMap<>();

        for (int i = 0; i < KEY.length(); i++) {
            cypher.put(KEY.charAt(i), i + k);
        }

        int i = KEY.length() + k;
        for (Map.Entry<Character, Integer> e : characterMap.entrySet()) {
            if (i == characterMap.size()) {
                break;
            }

            if (!cypher.containsKey(e.getKey())) {
                cypher.put(e.getKey(), i);
                i++;
            }
        }

        i = 0;
        for (Map.Entry<Character, Integer> e : characterMap.entrySet()) {
            if (i == characterMap.size() - cypher.size()) {
                break;
            }
            if (!cypher.containsKey(e.getKey())) {
                cypher.put(e.getKey(), i);
                i++;
            }
        }

        Map<Character, Pair<Integer, Integer>> result = new HashMap<>();

        for (Map.Entry<Character, Integer> e : cypher.entrySet()) {
            result.put(e.getKey(), new Pair<>(e.getValue(), characterMap.get(e.getKey())));
        }

        return result;
    }
}