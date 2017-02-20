import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;


public class ThreadWriter extends Thread {

    private Socket socket;

    public ThreadWriter(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            Scanner sc = new Scanner(System.in);
            while(true){
                    // String str = sc.next();
                    OutputStream os = socket.getOutputStream();
                    if(Client.msg != null) {
                        os.write(Client.msg.getBytes());

                        Client.msg = null;
                    }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}