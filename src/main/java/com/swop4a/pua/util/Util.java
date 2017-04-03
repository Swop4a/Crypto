package com.swop4a.pua.util;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Class include two alphabets which necessary for tasks.
 *
 * @author Pavkin Alexandr
 * @version 1.0
 */

public class Util {
    public static final Map<Character, Integer> RUSSIAN_CHARACTER_MAP = setRussianMap();
    public static final Map<Character, Integer> ENGLISH_CHARACTER_MAP = setEnglishMap();

    public static final Map<Character, Pair<Integer, Integer>> RUSSIAN_CHARACTER_POLYBIUS_MATRIX = setRussianPolybiusMatrix();
    public static final Map<Character, Pair<Integer, Integer>> ENGLISH_CHARACTER_POLYBIUS_MATRIX = setEnglishPolybiusMatrix();

    public static final Map<Pair<Character, Character>, Character> RUSSIAN_CHARACTER_VIGENERE_TABLE = setRussianVigenereTable();
    public static final Map<Pair<Character, Character>, Character> ENGLISH_CHARACTER_VIGENERE_TABLE = setEnglishVigenereTable();

    private static Map<Character, Integer> setRussianMap() {
        Map<Character, Integer> characters = new HashMap<>();
        char ch = 'а';

        for (int i = 0; i < 32; i++)
            characters.put((char) (ch + i), i);

        return characters;
    }

    private static Map<Character, Integer> setEnglishMap() {
        Map<Character, Integer> characters = new HashMap<>();
        char ch = 'a';

        for (int i = 0; i < 26; i++)
            characters.put((char) (ch + i), i);

        return characters;
    }

    private static Map<Character, Pair<Integer, Integer>> setRussianPolybiusMatrix() {
        HashMap<Character, Pair<Integer, Integer>> matrix = new HashMap<>();
        char ch = 'а';

        for (int i = 0; i < 32; i++) {
            matrix.put((char) (ch + i), new Pair<>(i / 6, i % 6));
        }

        return matrix;
    }

    private static Map<Character, Pair<Integer, Integer>> setEnglishPolybiusMatrix() {
        HashMap<Character, Pair<Integer, Integer>> matrix = new HashMap<>();
        char ch = 'a';

        for (int i = 0; i < 26; i++) {
            matrix.put((char) (ch + i), new Pair<>(i / 6, i % 6));
        }

        return matrix;
    }

    private static Map<Pair<Character, Character>, Character> setRussianVigenereTable() {
        HashMap<Pair<Character, Character>, Character> table = new HashMap<>();
        char ch = 'а';

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.append((char) (ch + i));
        }

        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                table.put(new Pair<>((char) (ch + i), (char) (ch + j)), sb.charAt(j));
            }
            char temp = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(temp);
        }

        return table;
    }

    private static Map<Pair<Character, Character>, Character> setEnglishVigenereTable() {
        HashMap<Pair<Character, Character>, Character> table = new HashMap<>();
        char ch = 'a';

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            sb.append((char) (ch + i));
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                table.put(new Pair<>((char) (ch + i), (char) (ch + j)), sb.charAt(j));
            }
            char temp = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(temp);
        }

        return table;
    }
}
