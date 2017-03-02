import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        String host = args[1];
        try {
            Socket socket = new Socket(host, port); // по локальному хосту подключаемся к тому порту, что указывали на сервере
            DataOutputStream dOutputStream = new DataOutputStream(socket.getOutputStream());
            String message = "";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while(!message.equals("exit")) {
                message = bufferedReader.readLine();
                dOutputStream.writeUTF(message);
                System.out.println("Sent");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
