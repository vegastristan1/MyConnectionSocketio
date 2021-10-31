package com.example.myconnectsocketio;

import android.app.Application;
import io.socket.client.IO;
import io.socket.client.Socket;

//ChatApplication original name of Java Class
//<!-- don't forget to put the name of the class in the application like the MySocketApplication-->
//in AndroidManifest.xml
public class MySocketApplication extends Application {

    private Socket mSocket;
    {
        try{
            //mSocket = IO.socket(Constants.CONNECTION_SOCKET_SERVER_URL);
            mSocket = IO.socket(Constants.MY_CONNECTION_SOCKET_SERVER_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket(){
        return mSocket;
    }
}
