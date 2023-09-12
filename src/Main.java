import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try {
            FileManager.copyDirectory(Path.of(FileManager.getDownloadPath() + "batch-processing"), Path.of(FileManager.getDownloadPath() + "batch-processing-copy"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al copiar el directorio");
        }

        ArrayList<File> files = FileManager.getFiles(Path.of(FileManager.getDownloadPath() + "./batch-processing-copy").toFile());

        for(File file : files)
            FileManager.replaceCharacters(file);

    }
}
