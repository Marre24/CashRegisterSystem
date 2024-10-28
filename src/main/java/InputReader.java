import java.util.Scanner;
import java.util.ArrayList;
import java.io.InputStream;

public class InputReader{
    private static ArrayList<InputStream> streams = new ArrayList<>();
    private Scanner input;

    // System.in is the default stream
    public InputReader(){
        this(System.in);
    }

    public InputReader(InputStream stream){
        if(streams.contains(stream))
            throw new IllegalStateException("Underlying stream already in use");

        input = new Scanner(stream);
        streams.add(stream);
    }

    public int readInt(String prompt){
        System.out.print(prompt + "?> ");
        int value = input.nextInt();
        clearBuffer();
        return value;
    }

    public long readLong(String prompt){
        System.out.print(prompt + "?> ");
        long value = input.nextLong();
        clearBuffer();
        return value;
    }

    public String readLine(String prompt){
        System.out.print(prompt + "?> ");
        String str = input.nextLine();
        return str;
    }

    private void clearBuffer(){
        input.nextLine();
    }
}
