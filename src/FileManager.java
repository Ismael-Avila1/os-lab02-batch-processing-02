import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Random;

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

    public static void replaceCharacters(File file)
    {
        File output = new File(file.getPath() + ".tmp");

        try(FileReader reader = new FileReader(file);
            FileWriter writer = new FileWriter(output)) {

            int c;
            Random random = new Random();

            while((c = reader.read()) != -1) {
                if(Character.isLetter(c)) {
                    char randomDigit = (char) (random.nextInt(10) + '0');
                    writer.write(randomDigit);
                }
                else if(Character.isDigit(c)) {
                    char randomLetter = (char) (random.nextInt(26) + 'a');
                    writer.write(randomLetter);
                }
                else {
                    writer.write(c);
                }
            }

            file.delete();
            output.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDownloadPath() {
        String os = System.getProperty("os.name").toLowerCase();

        if(os.contains("win"))
            return System.getProperty("user.home") + "\\Downloads\\";

        else if(os.contains("nix") || os.contains("nux") || os.contains("mac"))
            return System.getProperty("user.home") + "/Downloads/";

        return null;
    }

}
