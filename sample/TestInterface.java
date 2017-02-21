import java.util.ArrayList;

abstract class TestInterface {
	private static String server;
	private static int port;
	private static ArrayList<String> dict;

	public static void setServer(String server, int port) {
		server = server;
		port = port;
	}

	public static void setDict(ArrayList<String> dict) {
		dict = dict;
	}

	public static String getShiritoriWord(String inputWord){
	    String head = inputWord.substring(0,1);
	    String tail = inputWord.substring(inputWord.length()-1,inputWord.length());  

	    for(String word : dict) {
	        if(tail.equals(word.substring(0,1))){
	            dict.remove(word);
	            return word;
	        }
    	}

    	return " ";
	}
}