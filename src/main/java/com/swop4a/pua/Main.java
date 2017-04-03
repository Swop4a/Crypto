package com.swop4a.pua;

import com.swop4a.pua.tasks.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String... args) throws FileNotFoundException {
        TaskLauncher.launch(new Cezar());                                       //Task #1
        TaskLauncher.launch(new RelativeFrequencyTableOfLetters());             //Task #2
        TaskLauncher.launch(new RelativeFrequencyTableOfPairLetters());         //Task #3
        TaskLauncher.launch(new PolybiusSquare());                              //Task #4
        TaskLauncher.launch(new Vigenere());                                    //Task #5
        TaskLauncher.launch(new CezarWithKey());                                //Task #6
        TaskLauncher.launch(new PairCypher());                                  //Task #7
        TaskLauncher.launch(new XOREncryption());                               //Task #8
    }
}
