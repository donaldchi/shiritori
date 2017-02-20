import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.io.Console;

public class shiritori {


static ArrayList<String> als=new ArrayList<>();

    public static void main(String args[]) {

        try {
            FileReader fr = new FileReader("test.csv");//csvファイルが入っているファイルの場所
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            StringTokenizer token;
            while ((line = br.readLine()) != null) {
                token = new StringTokenizer(line, ",");
                while (token.hasMoreTokens()) {
                    als.add(token.nextToken().trim());
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }



        System.out.println("最初の言葉をひらがなで入力して下さい");

        int flag=0;
        int count=-1;
        Console console=System.console();

        int tri=0;
            String mat="";    
        while(flag==0){
            count++;
            String input=console.readLine();

            String in=input.substring(0,1);
            String outs=input.substring(input.length()-1,input.length());  

            for(int t=0;t<als.size();t++) {

                String real=als.get(t);
                String reals=real.substring(0,1);  

                if(outs.equals(reals)){
                    System.out.println(real);                
                    mat=real.substring(real.length()-1,real.length());
                    als.remove(t);
                    break;
                }        

                if(t==als.size()-1){
                    System.out.println("プログラムの語彙切れ:あなたの勝ちです");
                    flag=1;
                }    

            }
        }
    }
}