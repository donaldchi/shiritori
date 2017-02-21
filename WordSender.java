/*
    Player class of Shiritori Framework
    Create by chi on 02/19/2017
*/
    
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

class WordSender extends Thread {

    private final Socket socket;

    public WordSender(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while(true){
                OutputStream os = socket.getOutputStream();
                if(Player.word != null) {
                    os.write(Player.word.getBytes());
                    Player.word = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}