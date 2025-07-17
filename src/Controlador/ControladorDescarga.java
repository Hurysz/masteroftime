package Controlador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ControladorDescarga {

    public static void generateTxtFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
