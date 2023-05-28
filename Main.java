import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "Main.java";
        String fileContent = "public class Main {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println(\"Hello, World!\");\n\t}\n}";

        File file = new File(fileName);

        try {
            if (file.exists()) {
                file.delete(); // Delete the existing file
            }

            file.createNewFile(); // Create a new file

            // Write content to the file (you can use any file writing mechanism here)
            // For simplicity, using java.io.FileWriter in this example
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);
            fileWriter.write(fileContent);
            fileWriter.close();

            System.out.println("File created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
