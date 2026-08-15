
import java.io.*;
import java.util.ArrayList;



public class FileManager {
    public static void ensureFileExists(String filepath) {
        try {

            File file = new File(filepath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            System.out.println("Error creating file or directory: " + e.getMessage());
        }
    }

    public static void saveLine(String filepath, ArrayList<String> lines) {
        ensureFileExists(filepath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error creating file or directory: " + e.getMessage());
        }

    }

    public static ArrayList<String> loadLines(String filepath) {
        ArrayList<String> lines = new ArrayList<>();
        ensureFileExists(filepath);
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error creating file or directory: " + e.getMessage());

        }
        return lines;
    }

}