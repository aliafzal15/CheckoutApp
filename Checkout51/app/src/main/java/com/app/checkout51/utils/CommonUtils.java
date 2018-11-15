package com.app.checkout51.utils;

import android.content.Context;


import com.app.checkout51.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CommonUtils {

    private static CommonUtils shared_instance = null;

    private CommonUtils() {

    }
    public static CommonUtils getSharedInstance()
    {
        if (shared_instance == null)
            shared_instance = new CommonUtils();

        return shared_instance;
    }

    public String readFromFile(Context context) {

        String jsonString = "";

        try {

            InputStream inputStream = context.getResources().openRawResource(R.raw.offers);

            if (inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                jsonString = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No File Error: " + "File not found: " + e.toString());
        } catch (IOException e) {
            System.out.println("Read Error: " +"Can not read file: " + e.toString());
        }
        return jsonString;
    }
}
