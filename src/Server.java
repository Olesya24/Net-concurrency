import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept(); // возвращает экземпляр клиента, кот. подключился к серверу
            String clientMsg = "";
            DataInputStream dInputStream = new DataInputStream(socket.getInputStream());
            while(!clientMsg.equals("exit")) {
                clientMsg = dInputStream.readUTF();
                System.out.println("msg from client: " + clientMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
