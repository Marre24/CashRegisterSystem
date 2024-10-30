import java.util.Scanner;
import java.util.ArrayList;
import java.io.InputStream;

public class InputReader{
    private static final ArrayList<InputStream> streams = new ArrayList<>();
    private final Scanner input;

    public InputReader(){
        this(System.in);
    }

    public InputReader(InputStream stream){
        if(streams.contains(stream))
            throw new IllegalStateException("Underlying stream already in use");

        input = new Scanner(stream);
        streams.add(stream);
    }

    public long readLong(String prompt){
        System.out.print(prompt + "?> ");
        long value = input.nextLong();
        clearBuffer();
        return value;
    }

    public String readLine(String prompt){
        System.out.print(prompt + "?> ");
        return input.nextLine();
    }

    private void clearBuffer(){
        input.nextLine();
    }
}
