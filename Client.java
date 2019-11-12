import java.net.*;
import java.io.*;

public class Client
{
    public static void main(String args[]) throws IOException {
        
        try {
            Socket socket = new Socket("172.26.0.2", 8080);
            System.out.println("socket.isConnected()= " + socket.isConnected());
            socket.close();
        }
        catch (ConnectException e)
        {
            System.out.println(e);
        }

    }
}
