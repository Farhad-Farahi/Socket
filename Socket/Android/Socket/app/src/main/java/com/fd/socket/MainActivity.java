package com.fd.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import com.fd.socket.databinding.ActivityMainBinding;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    MyThread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        myThread = new MyThread();

        new Thread(myThread).start();

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = binding.et.getText().toString();
                myThread.sendMsg(msg);
            }
        });




    }

    /**
     * background thread for network
     */
    private class  MyThread implements  Runnable{

        private volatile String msg = "";
        Socket socket;
        DataOutputStream dos;

        @Override
        public void run() {
            try {
                socket = new Socket("192.168.216.62",5678);
                dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(msg);
                dos.close();
                dos.flush();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void  sendMsg(String msg){
            this.msg = msg;
            run();
        }
    }


}