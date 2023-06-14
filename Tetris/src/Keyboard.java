import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Keyboard {

    public static void main(String[] args) throws IOException {

        BufferedReader buffer = new BufferedReader(
                 new InputStreamReader(System.in));
        int c = 0;
        while((c = buffer.read()) != -1) {
            char character = (char) c;          
            System.out.println(character);          
        }       
    }   
}