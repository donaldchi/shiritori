import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {

    private final Socket socket;
    private final ArrayList<Socket> list;
    private final int playerID;
    private InputStream is;
    private OutputStream os;
    public ServerThread(Socket socket, ArrayList<Socket> list, int playerID) {
        this.socket = socket;
        this.list = list;
        this.playerID = playerID;
    }

    @Override
    public void run() {
        try {

            String clientNum;
            while(true){
                is = socket.getInputStream();
                byte[] b = new byte[1024];
                int len = is.read(b);
                String str = new String(b,0,len);
                for(Socket socket:list){
                    if(this.socket!=socket){  
                        clientNum = (this.playerID==0) ? "SECOND " : "FIRST ";
                        str = clientNum + str;
                        os = socket.getOutputStream();
                        os.write(str.getBytes());
                    }
                }
                System.out.println(str);
                if(str.equals("FIRST Damn!") || str.equals("SECOND Damn!") ) {
                    System.exit(1);
                    // break;
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            b = null;
            System.exit(1);
        }
    }

}