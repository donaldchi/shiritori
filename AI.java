/*
	Player class of Shiritori Framework
	Create by chi on 02/19/2018
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

public class AI extends Player {

    @Override
    public void setDict(ArrayList<String> dict) {
        setStartWord(dict.get(0));
        dict.remove(dict.get(0));
        this.dict = dict;
    }

    @Override
    public void setStartWord(String word) {
        this.startWord = word;
    }

    public static void main(String[] args) {
        if(args.length<0) {
            System.out.println("Please insert word dictionary!");
            System.exit(1);
        }
        
        AI ai = new AI();
        ai.setDict(new ArrayList<String>(Arrays.asList(args)));
        configRefereeInfo("127.0.0.1", 9995);
        joinGame();
    }
}