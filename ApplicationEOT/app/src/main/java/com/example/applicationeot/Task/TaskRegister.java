package com.example.applicationeot.Task;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TaskRegister extends AsyncTask<String, Void, String> {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("http://192.168.1.247/PHPCK/API/Register");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            Uri.Builder builders = new Uri.Builder()
                    .appendQueryParameter("fullname", strings[0])
                    .appendQueryParameter("taikhoan", strings[1])
                    .appendQueryParameter("matkhau", strings[2])
                    .appendQueryParameter("email", strings[3]);
            String query = builders.build().getEncodedQuery();
            bufferedWriter.write(query);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            connection.connect();
            int response_code = connection.getResponseCode();
            if (response_code == HttpURLConnection.HTTP_OK)
            {
                InputStreamReader isr=new InputStreamReader(connection.getInputStream(),"UTF-8");
                BufferedReader br=new BufferedReader(isr);
                String line=br.readLine();
                StringBuilder builder=new StringBuilder();
                while (line!=null)
                {
                    builder.append(line);
                    line=br.readLine();
                }
                String json=builder.toString();

                br.close();
                isr.close();
                connection.disconnect();
                Log.d("Register", json);
                return json;
            }
        }catch (Exception e){
            Log.e("Loi", e.toString());
        }
        return null;
    }
}
