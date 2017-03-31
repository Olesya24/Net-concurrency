import java.io.*;
import java.net.Socket;

public class Session implements Runnable {
    Socket _socket;
    public Session(Socket socket){
        this._socket=socket;
    }


    public void run()
    {
        try
        {
            InputStream socketInputStream = _socket.getInputStream();
            OutputStream socketOutputStream = _socket.getOutputStream();

            DataInputStream dataInputStream = new DataInputStream(socketInputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(socketOutputStream);

            String clientMsg="";
            while (!clientMsg.equals("exit"))
            {
                clientMsg = dataInputStream.readUTF();
                System.out.println("msg from client: " + clientMsg);
            }

            _socket.close();
        }
        catch(IOException e) {
            System.err.println("The connection was stopped.");
        }
        finally
        {
            Server.clientStop();
        }
    }
}
