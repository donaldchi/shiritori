import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;


public class ThreadReader extends Thread {

    private Socket socket;
    static ArrayList<String> words=new ArrayList<>();

    public ThreadReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            FileReader fr = new FileReader("test.csv");//csvファイルが入っているファイルの場所
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            StringTokenizer token;
            while ((line = br.readLine()) != null) {
                token = new StringTokenizer(line, ",");
                while (token.hasMoreTokens()) {
                    words.add(token.nextToken().trim());
                }
            }

            Client.msg = "shiritori";
            while(true){
                InputStream is = socket.getInputStream();
                byte[] b = new byte[1024];
                int len = is.read(b);
                String str = new String(b,0,len);
                System.out.println(str);

                if(str == "SECOND Damn!" || str == "FIRST Damn!") {
                    System.out.println("break;");
                    break;
                }

    String input = str;
    String in=input.substring(0,1);
    String outs=input.substring(input.length()-1,input.length());  

    for(int t=0;t<words.size();t++) {

        String real=words.get(t);
        String reals=real.substring(0,1);  

        if(outs.equals(reals)){
            // System.out.println(real); 
            // os.write(real.getBytes()); 
            Client.msg = real;             
            words.remove(t);
            break;
        }        

        if(t==words.size()-1){
            // System.out.println("Damn!");
            // os.write("Damn!".getBytes());
            Client.msg = "Damn!";
            break;
        }    
    }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}