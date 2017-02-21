/*
    Player class of Shiritori Framework
    Create by chi on 02/19/2017
*/

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;

class WordReceiver extends Thread {

    private final Socket socket;
    private final int startPlayerID;

    public WordReceiver(Socket socket, int startPlayerID) {
        this.socket = socket;
        this.startPlayerID = startPlayerID;
    }

    @Override
    public void run() {
        try {
            byte[] b = new byte[1024];
            InputStream is;
            String word;

            while(true){
                synchronized(this) {
                    if(Player.word==null) {
                        is = socket.getInputStream();
                        word = new String(b, 0, is.read(b));
                        word = Player.getShiritoriWord(word);

                        Player.word = word;

                        this.notify();

                        if(word.equals(" ")) {
                            System.exit(1);
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(1);
        }
    }

}