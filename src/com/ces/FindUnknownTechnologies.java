package com.ces;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindUnknownTechnologies {
    private static final String[] EXTENSIONS = {".c", ".cpp", ".hpp", ".cc", ".hh", ".cxx", ".hxx", ".xml", ".h",
            ".xsd", ".dtd", ".xhtml", ".axml", ".xslt", ".xmi", ".ts", ".xcconfig", ".ipa", ".ipsw", ".vcf", ".icns",
            ".plist", ".webarchive", ".scpt", ".pbxproj", ".strings", ".xib", ".storyboard", ".nib", ".entitlements",
            ".xcscheme", ".yaml", ".yml", ".resx", ".properties", ".gitignore", ".gitmodules", ".jschema", ".jws",
            ".json", ".gradle", "gradlew", ".jsm", ".js", ".sh", ".bat", ".bsh", ".md", ".txt", ".java", ".swift"};


//    private static final String[] EXTENSIONS = {".java", ".txt"};

    public FindUnknownTechnologies() {

    }

    public void runFindUnknownTechnologies () {
        List<Path> pathList = listFiles(Path.of("C:\\Users\\rauln\\.dx-platform\\projects\\P001\\repository\\Detox"));

        List<String> pathUnknownList = listUnknownFiles(pathList);

        findAndCountUniqueTechnologies(pathUnknownList);
    }

    private List<Path> listFiles (Path path) {
        List<Path> result = null;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk.filter (Files :: isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private List<String> listUnknownFiles (List<Path> paths) {
        List<String> result = new ArrayList<>();

        for (Path path : paths) {
            if (!Arrays.stream(EXTENSIONS).anyMatch(ext -> path.toString().endsWith(ext))) {
                result.add(path.toString());
            }
        }
        return result;
    }

    private List<Technologies> findAndCountUniqueTechnologies(List<String> unknownPaths) {
        List<Technologies> technologyList = new ArrayList<>();


        for (String myString : unknownPaths) {
            String regex = "[a-zA-Z]+\\.[a-zA-Z]+$";
            String[] word = myString.split("\\\\");
            if (Pattern.matches(regex, word[word.length-1])) {
                System.out.println(word[word.length-1].split("\\.")[1]);
            }
        }
        return null;
    }
}
