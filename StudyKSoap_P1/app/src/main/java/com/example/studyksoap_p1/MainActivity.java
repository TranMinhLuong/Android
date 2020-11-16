package com.example.studyksoap_p1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.config.Configuration;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    EditText txtC;
    Button btnF;
    TextView txtF;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        txtC = findViewById(R.id.txtC);
        txtF = findViewById(R.id.txtF);
        btnF = findViewById(R.id.btnF);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang xử lý....");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void addEvents() {
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulydoF();
            }
        });
    }

    private void xulydoF() {
        String c = txtC.getText().toString();
        CFTask cfTask = new CFTask();
        cfTask.execute(c);
    }

    class CFTask extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtF.setText("");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            txtF.setText(s);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String c = params[0];

                SoapObject request = new SoapObject(Configuration.NAME_SPACE,Configuration.METHOD_C_TO_F);
                request.addProperty(Configuration.PARAMS_C_TO_F_CELSIUS, c);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE = new HttpTransportSE(Configuration.SERVER_URL);
                httpTransportSE.call(Configuration.SOAP_ACTION_C_TO_F, envelope);

                SoapPrimitive data = (SoapPrimitive) envelope.getResponse();

                return  data.toString();
            }catch (Exception e){
                Log.e("Loi", e.toString());
            }
            return null;
        }
    }
}
