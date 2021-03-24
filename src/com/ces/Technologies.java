package com.ces;

import java.util.ArrayList;
import java.util.List;

public class Technologies {
    private String extension;
    private List<String> paths;
    private int count;

    public Technologies(String extension, int count) {
        this(extension, new ArrayList<>(), count);
    }

    public Technologies(String extension, List<String> paths, int count) {
        this.extension = extension;
        this.paths = paths;
        this.count = count;
    }

    public String getExtension() {
        return extension;
    }

    public int getCount() {
        return count;
    }

    public void increaseCounter () {
        count++;
    }

    public void addPath(String path) {
        paths.add(path);
    }

    public String getPath(int position) {
        return paths.get(position);
    }
}
