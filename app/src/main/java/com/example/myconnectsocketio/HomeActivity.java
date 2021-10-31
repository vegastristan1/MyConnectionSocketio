package com.example.myconnectsocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class HomeActivity extends AppCompatActivity {

    private TextView txtHomeUserName;
    private TextView txtHomeUserID;
    private EditText editTextJoinRoom;

    private Socket mSocket;
    {
        try{
            mSocket = IO.socket("http://192.168.88.125:3000/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");

        // Capture the layout's TextView and set the string as its text
        txtHomeUserName = findViewById(R.id.txtHomeUserName);
        txtHomeUserID = findViewById(R.id.txtHomeSocketID);

        Button btnCreateRoom = findViewById(R.id.btnHomeCreateRoom);
        Button btnDisconnect = findViewById(R.id.btnDisconnect);
        Button btnJoinRoom = findViewById(R.id.btnHomeJoinRoom);

        txtHomeUserName.setText(message);

        btnCreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mSocket.connect();
                mSocket.emit("create_room", "room1");
            }
        });

        btnJoinRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }

    Emitter.Listener onJoinRoom = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args [0];
                    try {
                        String id = data.getString("id");
                        Log.v("SocketIO", "Player name: " + id);
                        //socket.emit("connect_user", Username);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.v("SocketIO", "Error getting ID " + e);
                    }
                }
            });
        }
    };

    Emitter.Listener onCreateRoom = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args [0];
                    try {
                        String id = data.getString("id");
                        Log.v("SocketIO", "Player name: " + id);
                        //socket.emit("connect_user", Username);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.v("SocketIO", "Error getting ID " + e);
                    }
                }
            });
        }
    };
}