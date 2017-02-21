import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ThreadWriter extends Thread {

    private final Socket socket;

    public ThreadWriter(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            Scanner sc = new Scanner(System.in);
            while(true){
                OutputStream os = socket.getOutputStream();
                if(Client.msg != null) {
                    os.write(Client.msg.getBytes());
                    Client.msg = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}