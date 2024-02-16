package net.mortalsilence.olli.space.utility;

import java.io.*;

public class WriterLine {

    public void writeToLine(String filePath, int lineNumber, String newContent){
        try {
            // Read the original file
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            int currentLine = 1;

            // Read lines and modify the desired line
            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumber) {
                    // Modify line
                    content.append(newContent).append("\n");
                } else {
                    content.append(line).append("\n");
                }
                currentLine++;
            }
            reader.close();

            // Write the modified content back to the file
            FileWriter writer = new FileWriter(file);
            writer.write(content.toString());
            writer.close();

            System.out.println("Content modified successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
