package eu.incognito.umaandroid.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonFileReader {


    public JsonFileReader(){};

    public static String fileToString(InputStream is) {
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            String lineSeparator = System.getProperty("line.separator");
            while((line = bufferedReader.readLine())!= null) {
                stringBuilder.append(line);
                stringBuilder.append(lineSeparator);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return stringBuilder.toString();

    }

}
