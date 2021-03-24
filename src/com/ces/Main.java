package com.ces;

import java.io.IOException;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        if (args.length != 0) {
            System.out.println("No arguments needed!");
            return;
        }

//        String myString = "C:\\Users\\rauln\\.dx-platform\\projects\\P001\\repository\\Detox\\generation\\utils\\downloadFile.js";
//        System.out.println(myString);
//
//        String newString = myString.split("[0-9a-zA-Z:\\\\\\.-]*Detox\\\\")[1];
//        System.out.println(newString);

//        String myString = "\\ceva\\altceva.js";
//        String newString = myString.replace ("\\\\", "/");
//        System.out.println(newString);

        FindUnknownTechnologies start = new FindUnknownTechnologies();

        start.runFindUnknownTechnologies();
    }
}
