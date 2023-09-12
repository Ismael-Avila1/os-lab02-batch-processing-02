import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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

}
