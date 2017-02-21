import java.util.ArrayList;

public class TestInterfaceClass extends TestInterface {
    public static void main(String[] args) {
    	ArrayList<String> dict = new ArrayList<String>();
    	dict.add("sed");
    	dict.add("tail");
    	dict.add("binary");
    	dict.add("apple");
    	dict.add("pine");
    	System.out.println("before: " + dict);

    	dict.remove("pine");

    	System.out.println("after: " + dict);
    	// TestInterfaceClass tc = new TestInterfaceClass();
    	// tc.setDict(dict);

    	// System.out.println(tc.getShiritoriWord("hiphop"));
    }
}