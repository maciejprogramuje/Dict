package commaciejprogramuje.facebook.dict;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Translator {
    BufferedReader bufferedReader = null;
    String testLine;
    Map<String, String> map = new HashMap<>();
    String outputFileName = "output.txt";
    List<String> resultsList = new ArrayList<>();

    public Translator(Context context) {
        Log.w("UWAGA", "tworzę translator");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> findKey(String myKey) {
        resultsList.clear();
        if (myKey.length() >= 2) {
            myKey = myKey.toLowerCase();
            for (Map.Entry<String, String> e : map.entrySet()) {
                if (e.getKey().startsWith(myKey)) {
                    resultsList.add(e.getKey() + e.getValue() + "\n");
                }
            }
        } else {
            resultsList.add("too short");
        }
        Collections.sort(resultsList);

        return resultsList;
    }

}
