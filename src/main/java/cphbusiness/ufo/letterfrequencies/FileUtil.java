package cphbusiness.ufo.letterfrequencies;

import java.io.*;

public class FileUtil {
    static BufferedReader reader = null;
    public FileUtil(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        FileInputStream fileStream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(fileStream);
        reader = new BufferedReader(input);
    }

    public static int getCharCount() throws IOException {
        int charCount = 0;
        String data;
        while((data = reader.readLine()) != null) {
            charCount += data.length();
        }
        return charCount;
    }
}
