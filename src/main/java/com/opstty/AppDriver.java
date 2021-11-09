package com.opstty;

import com.opstty.job.*;
import org.apache.hadoop.util.ProgramDriver;

public class AppDriver {
    public static void main(String argv[]) {
        int exitCode = -1;
        ProgramDriver programDriver = new ProgramDriver();

        try {
            programDriver.addClass("wordcount", WordCount.class,
                    "A map/reduce program that counts the words in the input files");
            programDriver.addClass("dts", DistricsTrees.class,
                    "displays the list of distinct containing trees");
            programDriver.addClass("species", Species.class,
                    "displays the list of distinct species of trees");
            programDriver.addClass("kinds", Kinds.class,
                    "calculates the number of trees of each kinds");
            programDriver.addClass("height", Height.class,
                    "calculates the height of the tallest tree of each kind");
            programDriver.addClass("heightSorted", HeightSorted.class,
                    "sort the trees height from smallest to largest");
            programDriver.addClass("oldest", Oldest.class,
                    "displays the district where the oldest tree is");
            programDriver.addClass("most", Most.class,
                    "displays the district that contains the most trees");

            exitCode = programDriver.run(argv);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.exit(exitCode);
    }
}
