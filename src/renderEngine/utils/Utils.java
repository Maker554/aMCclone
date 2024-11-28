package renderEngine.utils;

import org.lwjgl.system.MemoryUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Utils {

    public static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    public static IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    public static String loadResource(String fileName) throws Exception {
        String result;
        try{
            File file = new File(fileName);
            Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
            result = scanner.useDelimiter("\\A").next();
        } catch (Exception e) {
            throw new FileNotFoundException("file not found");
        }
        return result;
    }

}
