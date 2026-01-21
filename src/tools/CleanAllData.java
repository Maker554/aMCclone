package tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CleanAllData {

    public static void main(String[] args) throws IOException {
        Path level = Paths.get("level.dat");
        Path data = Paths.get("data.dat");
        Path player = Paths.get("player.dat");
        Files.deleteIfExists(level);
        Files.deleteIfExists(data);
        Files.deleteIfExists(player);
    }

}
