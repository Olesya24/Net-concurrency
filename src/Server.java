import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) {

        try {
            int port = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(port);
            //несколько клиентов
            while (true)
            {
                Socket socket = serverSocket.accept(); // возвращает экземпляр клиента, кот. подключился к серверу
                Session session = new Session(socket);
                Thread thread1 = new Thread(session);
                thread1.start();
            }

        } catch (IOException e) {
           e.printStackTrace();

        }

    }
}
