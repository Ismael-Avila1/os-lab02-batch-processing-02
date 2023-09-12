import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class FileManager {

    public FileManager() {}

    public static void copyDirectory(Path source, Path destination) throws IOException {
        if(Files.isDirectory(source)) {
            if(!Files.exists(destination))
                Files.createDirectory(destination);

            try(var stream = Files.newDirectoryStream(source)) {
                for(var entry : stream)
                    copyDirectory(entry, destination.resolve(entry.getFileName()));
            }
        }
        else {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static ArrayList<File> getFiles(File source)
    {
        ArrayList<File> files = new ArrayList<File>();

        File[] txtFiles = source.listFiles();

        if(txtFiles != null) {
            for(File file : txtFiles) {
                if(file.isFile())
                    files.add(file);
                else if(file.isDirectory())
                    files.addAll(getFiles(file));
            }
        }

        return files;
    }

}
