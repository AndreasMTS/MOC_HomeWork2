package com.example.moc_homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Button btn_loadCards;
    TextView textView_cards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_loadCards = (Button) findViewById(R.id.btn_load_cards);
        textView_cards = (TextView) findViewById(R.id.textView_cards);

        btn_loadCards.setOnClickListener((v) -> {
            btn_loadCards.setEnabled(false);

            URL url = null;
            HttpURLConnection conn = null;
            int responseCode = 0;
            String inline = "";
            try {
                url = new URL(FETCH_URL);
                conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                responseCode = conn.getResponseCode();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(responseCode != 200)
                throw new RuntimeException("HttpCresponseCode: " + responseCode);
            else {
                Scanner sc = null;
                try {
                    sc = new Scanner(url.openStream());

                    while(sc.hasNext()) {
                        inline+=sc.nextLine();
                    }
                    Log.i("json data", inline);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



            //textView_cards.setText(json_response);
            //Log.i("test", json_response);

            btn_loadCards.setEnabled(true);
        });
    }







}
