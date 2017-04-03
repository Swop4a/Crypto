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
 * Relative frequency table of pair letter.
 *
 * @author Pavkin Alenxadr
 * @version 1.0
 */

public class RelativeFrequencyTableOfPairLetters implements Task {
    private Scanner scan;
    private PrintWriter writer;

    @Override
    public void run() throws FileNotFoundException {
        code(Util.ENGLISH_CHARACTER_MAP);
    }

    private void code(Map<Character, Integer> characterMap) throws FileNotFoundException {
        Map<String, Integer> result = new HashMap<>();

        scan = new Scanner(new File("src/main/resources/tableofpairletters/input.txt"));
        writer = new PrintWriter(new File("src/main/resources/tableofpairletters/output.txt"));

        int pairCount = 0;
        while(scan.hasNext()) {
            String word = scan.next();

            for (int i = 0; i < word.length() - 1; i++) {
                char firstChar = word.charAt(i), secondChar = word.charAt(i + 1);
                String pair = firstChar + "" + secondChar;

                if (characterMap.containsKey(firstChar) && characterMap.containsKey(secondChar)) {
                    if (result.containsKey(pair)) {
                        result.put(pair, result.get(pair) + 1);
                    } else {
                        result.put(pair, 1);
                    }
                    pairCount++;
                }
            }
        }

        writer.println("+------------+-------------------+");
        writer.printf("| %-10s | %-17s | %n", "Pair:", "Frequency:");
        writer.println("+------------+-------------------+");
        for (Map.Entry<String, Integer> e : result.entrySet()) {
            writer.printf("| %-10s | %-17f | %n", e.getKey(), ((double) e.getValue() / pairCount) * 100.);
        }
        writer.println("+------------+-------------------+");

        scan.close();
        writer.close();
    }
}
