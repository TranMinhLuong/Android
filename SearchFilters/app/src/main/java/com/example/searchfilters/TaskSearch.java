package com.example.searchfilters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TaskSearch extends AsyncTask<String, Void, ArrayList<ModelSearch>> {

    @Override
    protected ArrayList<ModelSearch> doInBackground(String... strings) {
        ArrayList<ModelSearch> ds = new ArrayList<>();
        String search = strings[0];
        // Java program to replace spaces with %20
        // Driver Code
        char[] str = search.toCharArray();
        // Prints the replaced string
        str = replaceSpaces(str);
        String chuoimoi= "";
        for (int i = 0; i < str.length; i++){
              chuoimoi+=""+str[i];
        }

        try {
            URL url = new URL("http://172.20.10.3/PHPCK/API/Search/"+chuoimoi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
            connection.setRequestProperty("Accept", "*/*");

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            StringBuilder builder = new StringBuilder();
            while (line!=null){
                builder.append(line);
                line=br.readLine();
            }

            String json = builder.toString();
            Log.d("ABC", json);
            JSONArray jsonArray = new JSONArray(json);
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject item = jsonArray.getJSONObject(i);
                ModelSearch modelSearch = new ModelSearch();
                if (item.has("hinhanh")){
                    modelSearch.setUlrimage(item.getString("hinhanh"));
                    url = new URL("http://172.20.10.3/PHPCK/public/img/"+modelSearch.getUlrimage());
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
                    connection.setRequestProperty("Accept", "*/*");
                    Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());

                    modelSearch.setImghinhsp(bitmap);
                }
                modelSearch.setIdsp(item.getInt("idsp"));
                modelSearch.setTensp(item.getString("tensp"));
                modelSearch.setGiasp(item.getString("gia"));
                modelSearch.setKhuyenmai(item.getInt("km"));
                modelSearch.setIddm(item.getInt("iddm"));
                modelSearch.setNoidung(item.getString("noidung"));
                modelSearch.setNhacungcap(item.getString("nhacungcap"));

                ds.add(modelSearch);
                Log.d("JSONSP", json);
            }
        }catch (Exception e){
            Log.e("Loi", e.toString());
        }
        return ds;
    }
    // Maximum length of string
    // after modifications.
    static int MAX = 1000;

    // Replaces spaces with %20 in-place
    // and returns new length of modified string.
    // It returns -1 if modified string
    // cannot be stored in str[]
    static char[] replaceSpaces(char[] str)
    {

        // count spaces and find current length
        int space_count = 0, i = 0;
        for (i = 0; i < str.length; i++)
            if (str[i] == ' ')
                space_count++;

        // count spaces and find current length
//        while (str[i - 1] == ' ')
//        {
//            space_count--;
//            i--;
//        }

        // Find new length.
        int new_length = i + space_count * 2;

        // New length must be smaller than length
        // of string provided.
        if (new_length > MAX)
            return str;

        // Start filling character from end
        int index = new_length - 1;

        char[] new_str = str;
        str = new char[new_length];

        // Fill rest of the string from end
        for (int j = i - 1; j >= 0; j--)
        {

            // inserts %20 in place of space
            if (new_str[j] == ' ')
            {
                str[index] = '0';
                str[index - 1] = '2';
                str[index - 2] = '%';
                index = index - 3;
            }

            else
            {
                str[index] = new_str[j];
                index--;
            }
        }
        return str;
    }
}
