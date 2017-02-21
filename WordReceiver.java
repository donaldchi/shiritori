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
    private final int playerID;

    public WordReceiver(Socket socket, int playerID) {
        this.socket = socket;
        this.playerID = playerID;
    }

    @Override
    public void run() {
        try {

            Player.word = this.playerID==0 ? "start" : null;//player0が先に単語を言い出す
            byte[] b = new byte[1024];
            InputStream is;
            String word;

            while(true){

                is = socket.getInputStream();
                word = new String(b, 0, is.read(b));
                word = Player.getShiritoriWord(word);
                Player.word = word;

            if(word.equals(" ")) {
                System.exit(1);
                // break;
            }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.exit(1);
        }
    }

}