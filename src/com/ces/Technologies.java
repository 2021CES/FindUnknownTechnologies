package com.ces;

public class Technologies {
    private String name;
    private String extension;
    private int count;

    public Technologies(String extension) {
        this("", extension);
    }

    public Technologies(String name, String extension) {
        this(name, extension, 0);
    }

    public Technologies(String name, String extension, int count) {
        this.name = name;
        this.extension = extension;
        this.count = count;
    }

    public boolean foundTechnology(String extension) {
        return this.extension == extension;
    }
}
