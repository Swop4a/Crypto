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
 *  Relative frequency table of letter.
 *
 *  @author Pavkin Alenxadr
 *  @version 1.0
 */

public class RelativeFrequencyTableOfLetters implements Task {
    private Scanner scan;
    private PrintWriter writer;

    @Override
    public void run() throws FileNotFoundException {
        code(Util.ENGLISH_CHARACTER_MAP);
    }

    private void code(Map<Character, Integer> characterMap) throws FileNotFoundException {
        Map<Character, Integer> result = new HashMap<>();

        scan = new Scanner(new File("src/main/resources/tableofletters/input.txt"));
        writer = new PrintWriter(new File("src/main/resources/tableofletters/output.txt"));

        int letterCount = 0;
        while (scan.hasNext()) {
            String word = scan.next();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (characterMap.containsKey(ch)) {
                    if (result.containsKey(ch)) {
                        result.put(ch, result.get(ch) + 1);
                    } else {
                        result.put(ch, 1);
                    }
                    letterCount++;
                }
            }
        }

        writer.println("+------------+-------------------+");
        writer.printf("| %-10s | %-17s | %n", "Character:", "Frequency:");
        writer.println("+------------+-------------------+");
        for (Map.Entry<Character, Integer> e : result.entrySet()) {
            writer.printf("| %-10s | %-17f | %n", e.getKey(), ((double) e.getValue() / letterCount) * 100.);
        }
        writer.println("+------------+-------------------+");

        scan.close();
        writer.close();
    }
}
