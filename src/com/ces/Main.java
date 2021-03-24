package com.ces;

import java.io.IOException;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        if (args.length != 0) {
            System.out.println("No arguments needed!");
            return;
        }

//        String myString = "ceva.kt";
//        String[] ceva = myString.split("\\.");
//        String aString = ceva[1];
//        System.out.println(aString);

//        String myString1 = "C:\\Users\\rauln\\.dx-platform\\projects\\P001\\repository\\Detox\\.git\\config";
//        String myString2 = "C:\\Users\\rauln\\.dx-platform\\projects\\P001\\repository\\Detox\\detox\\android\\detox\\src\\testFull\\java\\com\\wix\\detox\\reactnative\\idlingresources\\timers\\DelegatedIdleInterrogationStrategySpec.kt";
//
//        String regex = "\\.[a-zA-Z]+$";
//        if (Pattern.matches(myString1, regex)) {
//            System.out.println(myString1);
//        }

//        String[] ceva1 = myString1.split("\\.");
//        System.out.println(ceva1[ceva1.length-1]);
//        System.out.println(myString1);
//        String[] ceva = myString1.split("\\.");
//        System.out.println(ceva[ceva.length-1]);
//        System.out.println(Pattern.matches(ceva[ceva.length-1], regex));
//        if (!Pattern.matches(ceva[ceva.length-1], regex)) {
//            System.out.println(myString1);
//            System.out.println("ceva");
//        }
        FindUnknownTechnologies start = new FindUnknownTechnologies();

        start.runFindUnknownTechnologies();


//        regex = "[a-zA-Z]*\\.[a-zA-Z]+$";
//        System.out.println(myString1);
//        String[] ceva = myString1.split("\\\\");
//        System.out.println(ceva[ceva.length-1]);
//        System.out.println(Pattern.matches(regex, ceva[ceva.length-1]));
//        String[] ceva2 = ceva[ceva.length-1].split("\\.");
//        System.out.println(ceva2[ceva2.length-1]);
//        System.out.println(Pattern.matches(regex, ceva[ceva.length-1]));

    }
}
