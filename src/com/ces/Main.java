package com.ces;

public class Main {

    public static void main(String[] args) {
        if (args.length != 0) {
            System.out.println("No arguments needed!");
            return;
        }

        FindUnknownTechnologies start = new FindUnknownTechnologies();

        start.runFindUnknownTechnologies();
    }
}
