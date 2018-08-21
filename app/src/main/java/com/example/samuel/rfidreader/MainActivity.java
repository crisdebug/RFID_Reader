package com.example.samuel.rfidreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final Pattern RFID_KEYCODE = Pattern.compile("KEYCODE_(\\d)");
    TextView rfidCode;
    StringBuilder sb;
    boolean executed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rfidCode = findViewById(R.id.rfid_number);
        rfidCode.setText("");
        sb = new StringBuilder();
        executed = false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        String key = KeyEvent.keyCodeToString(keyCode);

        if (executed) {
            rfidCode.setText("");
        } else {
            executed = false;

        }

        Matcher matcher = RFID_KEYCODE.matcher(key);

        if (matcher.matches()) {
            rfidCode.append(matcher.group(1));
            sb.append(matcher.group(1));
        }

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            String code = sb.toString();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://maker.ifttt.com/trigger/rfid_passed/with/key/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface api = retrofit.create(ApiInterface.class);

            RFIDJson json = new RFIDJson();
            json.setValue1(code);
            json.setValue2("");
            json.setValue3("");
            Call<RFIDJson> call = api.enviarCode(json);

            call.enqueue(new Callback<RFIDJson>() {
                @Override
                public void onResponse(Call<RFIDJson> call, Response<RFIDJson> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "ERRO", Toast.LENGTH_LONG).show();
                        sb = new StringBuilder();
                    } else {
                        /*String value1 = response.body().getValue1();
                        String value2 = response.body().getValue2();
                        String value3 = response.body().getValue3();
                        Toast.makeText(MainActivity.this, "Value1:"+value1+" Value2:"+value2+" Value3:"+value3, Toast.LENGTH_LONG).show();*/
                        Log.e("ERRO", "SUCESSO");
                        sb = new StringBuilder();
                    }
                }

                @Override
                public void onFailure(Call<RFIDJson> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("ERRO", t.getMessage());
                    sb = new StringBuilder();
                }
            });
            executed = true;
        }


        return super.onKeyDown(keyCode, event);
    }
}
