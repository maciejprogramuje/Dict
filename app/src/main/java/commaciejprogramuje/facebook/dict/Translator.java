package commaciejprogramuje.facebook.dict;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by m.szymczyk on 2017-08-28.
 */
public class Translator {
    BufferedReader bufferedReader = null;
    String testLine;
    Map<String, String> map = new HashMap<>();
    String outputFileName = "output.txt";

    public Translator(Context context) {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(outputFileName)));

            while ((testLine = bufferedReader.readLine()) != null) {
                if (!testLine.isEmpty()) {
                    String word = testLine.substring(0, testLine.indexOf(":") - 1);
                    String meaning = testLine.substring(testLine.indexOf("("));
                    if (meaning.isEmpty()) {
                        meaning = " ";
                    }

                    if (!map.containsKey(word)) {
                        map.put(word, meaning);
                    } else {
                        map.put(word, map.get(word) + " | " + meaning);
                    }
                }
            }
            bufferedReader.close();
            Log.w("UWAGA", "BAZA UTWORZONA");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String findKey(String myKey) {
        if(myKey != null) {
            Log.w("UWAGA", "sprawdzam: " + myKey);


            myKey = myKey.toLowerCase();


            StringBuilder stringBuilder = new StringBuilder();

            for (Map.Entry<String, String> e : map.entrySet()) {
                if (e.getKey().startsWith(myKey)) {
                    stringBuilder.append(e.getKey());
                    stringBuilder.append(e.getValue());
                    stringBuilder.append("\n");
                    // printing result is very slow
                    System.out.println(stringBuilder.toString());
                }
            }

            //return stringBuilder.toString();

        }
        return "";


        /*myKey = myKey.toLowerCase();

        if (map.keySet().contains(myKey)) {
            return map.get(myKey);
        } else {
            return "";
        }*/
    }
}
