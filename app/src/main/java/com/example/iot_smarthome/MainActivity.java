package com.example.iot_smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements dialog.ExampleDialogListener{
    private TextView tvIP,st_kamarmandi,st_ruangtamu,st_garasi,st_dapur;
    private TextView tvPORT;
    private Button button;
    private ImageView btnkamarmandi,btnruangtamu,btngarasi,btndapur;
    Thread Thread1 = null;
    String SERVER_IP;
    int SERVER_PORT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvIP = (TextView) findViewById(R.id.textView);
        st_kamarmandi = (TextView) findViewById(R.id.status_kamarmandi);
        st_ruangtamu = (TextView) findViewById(R.id.status_ruangtamu);
        st_garasi = (TextView) findViewById(R.id.status_garasi);
        st_dapur = (TextView) findViewById(R.id.status_dapur);
        st_kamarmandi.setTextColor(Color.parseColor("#C1392D"));
        st_kamarmandi.setText("OFF");
        st_ruangtamu.setTextColor(Color.parseColor("#C1392D"));
        st_ruangtamu.setText("OFF");
        st_garasi.setTextColor(Color.parseColor("#C1392D"));
        st_garasi.setText("OFF");
        st_dapur.setTextColor(Color.parseColor("#C1392D"));
        st_dapur.setText("OFF");

        button = (Button) findViewById(R.id.button);
        button.setText("Connect");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();
            }
        });

        btnkamarmandi = (ImageView) findViewById(R.id.kamarmandi);
        btnkamarmandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String message ;
                if(st_kamarmandi.getText() == "OFF"){
                    st_kamarmandi.setTextColor(Color.parseColor("#52D68A"));
                    st_kamarmandi.setText("ON");
                    message = "a";
                    new Thread(new Thread3(message)).start();
                }
                else{
                    st_kamarmandi.setTextColor(Color.parseColor("#C1392D"));
                    st_kamarmandi.setText("OFF");
                    message = "b";
                    new Thread(new Thread3(message)).start();
                }
            }
        });

        btnruangtamu = (ImageView) findViewById(R.id.ruangtamu);
        btnruangtamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String message ;
                if(st_ruangtamu.getText() == "OFF"){
                    st_ruangtamu.setTextColor(Color.parseColor("#52D68A"));
                    st_ruangtamu.setText("ON");
                    message = "c";
                    new Thread(new Thread3(message)).start();
                }
                else{
                    st_ruangtamu.setTextColor(Color.parseColor("#C1392D"));
                    st_ruangtamu.setText("OFF");
                    message = "d";
                    new Thread(new Thread3(message)).start();
                }
            }
        });

        btngarasi = (ImageView) findViewById(R.id.garasi);
        btngarasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String message ;
                if(st_garasi.getText() == "OFF"){
                    st_garasi.setTextColor(Color.parseColor("#52D68A"));
                    st_garasi.setText("ON");
                    message = "e";
                    new Thread(new Thread3(message)).start();
                }
                else{
                    st_garasi.setTextColor(Color.parseColor("#C1392D"));
                    st_garasi.setText("OFF");
                    message = "f";
                    new Thread(new Thread3(message)).start();
                }
            }
        });

        btndapur = (ImageView) findViewById(R.id.dapur);
        btndapur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String message ;
                if(st_dapur.getText() == "OFF"){
                    st_dapur.setTextColor(Color.parseColor("#52D68A"));
                    st_dapur.setText("ON");
                    message = "g";
                    new Thread(new Thread3(message)).start();
                }
                else{
                    st_dapur.setTextColor(Color.parseColor("#C1392D"));
                    st_dapur.setText("OFF");
                    message = "h";
                    new Thread(new Thread3(message)).start();
                }
            }
        });


    }
    public void openDialog() {

        dialog exampleDialog = new dialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");

    }

    //@Override
    public void applyTexts(String ipp, String port) {
        tvIP.setText(ipp + "/" +port);
        SERVER_IP = ipp.trim();
        SERVER_PORT = Integer.parseInt(port.trim());
        Thread1 = new Thread(new Thread1());
        Thread1.start();
//        SERVER_IP = ipp.trim();
//        SERVER_PORT = Integer.parseInt(port.trim());

        //textViewPassword.setText(password);
    }
    private PrintWriter output;
    private BufferedReader input;


    class Thread1 implements Runnable {
        public void run() {
            Socket socket;
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        button.setText("Connected");
                    }
                });

                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class Thread2 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();
                    if (message != null) {

                    } else {
                        Thread1 = new Thread(new Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Thread3 implements Runnable {
        private String message;
        Thread3(String message) {
            this.message = message;
        }
        @Override
        public void run() {
            output.write(message);
            output.flush();

        }
    }
}
