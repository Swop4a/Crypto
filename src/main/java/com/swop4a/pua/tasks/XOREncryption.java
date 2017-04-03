package com.swop4a.pua.tasks;

import com.swop4a.pua.Task;
import com.swop4a.pua.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

/**
 * XOR-encryption.
 *
 * @author Pavkin Alexandr
 * @version 1.0
 */

public class XOREncryption implements Task {
    private final int KEY = 15;

    private Scanner scan;
    private PrintWriter writer;

    @Override
    public void run() throws FileNotFoundException {
        code(Util.ENGLISH_CHARACTER_MAP);
        decode(Util.ENGLISH_CHARACTER_MAP);
    }

    private void code(Map<Character, Integer> characterMap) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/xorencryption/code_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/xorencryption/code_out.txt"));

        while (scan.hasNext()) {
            String word = scan.next();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (characterMap.containsKey(ch)) {
                    char codeChar = (char) (KEY ^ ch);
                    result.append(codeChar);
                }
            }
            writer.append(result + " ");
        }

        scan.close();
        writer.close();
    }

    private void decode(Map<Character, Integer> characterMap) throws FileNotFoundException {
        scan = new Scanner(new File("src/main/resources/xorencryption/decode_in.txt"));
        writer = new PrintWriter(new File("src/main/resources/xorencryption/decode_out.txt"));

        while (scan.hasNext()) {
            String word = scan.next();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                char decodeChar = (char) (KEY ^ ch);
                result.append(decodeChar);
            }
            writer.append(result + " ");
        }

        scan.close();
        writer.close();
    }
}
