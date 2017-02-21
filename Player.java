/*
	Player class of Shiritori Framework
	Create by chi on 02/19/2017
*/

import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.StringTokenizer;

abstract class Player {
	public static String word = null; //受け取る、言い出す単語
    public static ArrayList<String> dict; //単語辞書
    public static String startWord = null;

    private static Socket socket;

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

    //単語辞書の初期化を行う
    abstract public void setDict(ArrayList<String> dict);

    //最初の単語を指定する
    abstract public void setStartWord(String word);

    //審判となるサーバーのアドレスとポート番号を設定
    public static void configRefereeInfo(String refereeIP, int refereePort) {
        try {
            if(refereeIP==null) {
                socket = new Socket("127.0.0.1", 9995);
            } else {
                socket = new Socket(refereeIP,refereePort);
            }
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } 
    }

    public static void joinGame() {
        new WordSender(socket).start();
        new WordReceiver(socket, 0).start();
    }
}