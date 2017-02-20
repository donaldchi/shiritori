import java.util.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

// メッセージボックス
class MessageBox {
    boolean flag = false;
    String message = null;

    public synchronized void Read(Socket socket) {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            InputStream is = socket.getInputStream();
            byte[] b = new byte[1024];
            int len = is.read(b);
            String str = new String(b,0,len);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        flag = true;
        notify();
    }

    public synchronized void Write(Scanner sc, Socket socket) {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try{
            String str = sc.next();
            OutputStream os = socket.getOutputStream();
            os.write(str.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        flag = false;
        notify();
    }
}