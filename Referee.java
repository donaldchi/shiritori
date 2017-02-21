/*
	Referee class of Shiritori Framework
	審判クラス、ゲームの審判になる。
	二人のプレイヤが必要のためThreadで管理
	Create by chi on 02/19/2017
*/

import java.io.IOException;
import java.net.SocketException;
import java.net.BindException;
import java.net.ServerSocket;

import java.net.Socket;
import java.util.ArrayList;

public class Referee {

    private static ServerSocket referee;
    private static boolean isGameOver = false; 
    private static ArrayList<Socket> players = new ArrayList<Socket>();
    public static String word = null;
    public static int activePlayerID = 0;

    public Referee() {
        try {
            //only two players can be connectable
            referee = new ServerSocket(9995, 2); 
        } catch (BindException e) {
            // e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitPlayer(){
        try {
            int playerID = 0;
            while(true) {
                Socket player = referee.accept();
                players.add(player);
                
                new PlayerThread(player, players, playerID).start();

                playerID++;
            }
        } catch (SocketException e) {
            System.exit(1);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public static void showJudgementResult(String word, String playerStr) {
        if(playerStr != null && !isGameOver) {
            //最初に二人のプレイヤが揃える前に送った単語は無視する。
            if(word.equals(" ") || !Shiritori.dict.contains(word)) {
                System.out.println(playerStr + " (NG): " + word);
                System.out.println( "WIN - " + (playerStr.equals("FIRST") ? "SECOND" : "FIRST"));
                isGameOver = true;
            } else {
                System.out.println(playerStr + " (OK): " + word);
            }
        }
    }

    synchronized public static boolean isGameOver() {
        return isGameOver;
    }

    public static void gameOver() {
        try {
            referee.close();
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Errors occured when closing Socket or ServerSocket!");
            e.printStackTrace();
        }
    }
}

