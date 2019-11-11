import java.net.*;
import java.io.*;

public class Client
{
    public static void main(String args[]) throws IOException {
        
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            System.out.println("socket.isConnected()= " + socket.isConnected());
            socket.close();
        }
        catch (ConnectException e)
        {
            System.out.println(e);
        }

    }
}
