import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private static int clientNum_=0;
    public static void clientStop()
    {
        clientNum_--;
    }

    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(port);
            int maxClientNum=4;
            //несколько клиентов
            while (true)
            {
                Socket socket = serverSocket.accept(); // возвращает экземпляр клиента, кот. подключился к серверу
                if(clientNum_ < maxClientNum)
                {
                    clientNum_++;
                    Session session = new Session(socket);
                    Thread thread1 = new Thread(session);
                    thread1.start();

                }
                else
                {
                    DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeUTF("Server isn`t available now. Please try again later");
                }
            }

        } catch (IOException e) {
           e.printStackTrace();

        }

    }
}
