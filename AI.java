/*
	Player class of Shiritori Framework
	Create by chi on 02/19/2017
*/

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AI {
	public static String word = null; //受け取る、言い出す単語
    public static ArrayList<String> dict;

    public static String getShiritoriWord(String inputWord){
        dict.remove(inputWord);

        String tail = inputWord.substring(inputWord.length()-1,inputWord.length());  

        for(String word : dict) {
            if(tail.equals(word.substring(0,1))){
                dict.remove(word);
                return word;
            }
        }

        return " ";
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        if(args.length<0) {
            System.out.println("Please insert word dictionary!");
            System.exit(1);
        }

        dict = new ArrayList<String>(Arrays.asList(args));

        Socket socket = new Socket("127.0.0.1",9999);
        int playerID = 0;
        
        new WordSender(socket).start();
        new WordReceiver(socket, playerID).start();
    }
}