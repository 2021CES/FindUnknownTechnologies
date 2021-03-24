package com.ces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindUnknownTechnologies {
    private static final int NUMBER_OF_APPARITIONS_NEEDED_TO_REGISTER_A_TECHNOLOGY = 10;
    private static final String[] EXTENSIONS = {".c", ".cpp", ".hpp", ".cc", ".hh", ".cxx", ".hxx", ".xml", ".h",
            ".xsd", ".dtd", ".xhtml", ".axml", ".xslt", ".xmi", ".ts", ".xcconfig", ".ipa", ".ipsw", ".vcf", ".icns",
            ".plist", ".webarchive", ".scpt", ".pbxproj", ".strings", ".xib", ".storyboard", ".nib", ".entitlements",
            ".xcscheme", ".yaml", ".yml", ".resx", ".properties", ".gitignore", ".gitmodules", ".jschema", ".jws",
            ".json", ".gradle", "gradlew", ".jsm", ".js", ".sh", ".bat", ".bsh", ".md", ".txt", ".java", ".swift"};

    private List<Technologies> technologyList;

    public FindUnknownTechnologies() {
        technologyList = new ArrayList<>();
    }

    public void runFindUnknownTechnologies () {
        String projectPath = getPathFromPropertiesFile();

        List<Path> pathList = listFiles(Path.of(projectPath));

        List<String> pathUnknownList = listUnknownFiles(pathList);

        findAndCountUniqueTechnologies(pathUnknownList);

        saveResult();
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

    private void findAndCountUniqueTechnologies(List<String> unknownPaths) {
        boolean ok = true;

        for (String myString : unknownPaths) {
            String regex = "[a-zA-Z]+\\.[a-zA-Z]+$";
            String[] word = myString.split("\\\\");
            if (Pattern.matches(regex, word[word.length-1])) {
                String myExtension = word[word.length-1].split("\\.")[1];
                if (technologyList.isEmpty()) {
                    technologyList.add(new Technologies(myExtension, 0));
                }

                for (Technologies technology : technologyList) {
                    ok = false;
                    if (myExtension.equals(technology.getExtension())) {
                        technology.increaseCounter();
                        String newString = myString.split("[0-9a-zA-Z:\\\\\\.-]*Detox\\\\")[1];
                        String anotherString = newString.replace ("\\", "/");
                        technology.addPath(anotherString);
                        ok = true;
                        break;
                    }
                }

                if (!ok) {
                    technologyList.add(new Technologies(myExtension, 0));
                }
            }
        }
    }

    private void saveResult () {
        JSONArray finalObject = new JSONArray();

        for (Technologies technology : technologyList) {
            if (technology.getCount() >= NUMBER_OF_APPARITIONS_NEEDED_TO_REGISTER_A_TECHNOLOGY) {
                try {
                    for (int i = 0; i < technology.getCount() - 1; i++) {
                        JSONObject newObject = new JSONObject();
                        String aString = technology.getPath(i);
                        newObject.put("file", aString);
                        newObject.put("name", technology.getExtension());
                        newObject.put("category", "NewTechnologies");
                        newObject.put("value", technology.getCount());
                        finalObject.put(newObject);
                    }
                    System.out.println("Technology " + technology.getExtension() + " ADDED.");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        try (FileWriter file = new FileWriter("findUTJSON.JSON")) {
            file.write(finalObject.toString());
            file.flush();
            System.out.println("JSON file created: findUTJSON.JSON");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPathFromPropertiesFile(){
        try {
            File file = new File("config.properties");
            FileInputStream inputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();

            Enumeration keys = properties.keys();
            String path = properties.getProperty((String) keys.nextElement());
            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
