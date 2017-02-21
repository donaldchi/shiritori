/*
	Main class of Shiritori Framework
	Create by chi on 02/19/2017
*/
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		
		// Shiritori shiritori = new Shiritori();
        if(args.length<0) {
            System.out.println("Please insert word dictionary!");
            System.exit(1);
        }

		ArrayList<String> dict = new ArrayList<String>(Arrays.asList(args));
		Shiritori shiritori = new Shiritori(dict);

	}
}