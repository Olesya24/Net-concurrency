import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server
{

    private static int clientNum_ = 0;
    static final Object lock = new Object();

    public static void clientStop() {

        synchronized (lock) {
            clientNum_--;
            lock.notifyAll();
        }
    }

    public static void clientStart() {

        synchronized (lock) {
            clientNum_++;
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(port);
            int maxClientNum = 4;
            //несколько клиентов
            while (true) {
                Socket socket = serverSocket.accept(); // возвращает экземпляр клиента, кот. подключился к серверу
                try
                    {
                        synchronized (lock)
                        {
                            if (clientNum_ == maxClientNum)
                            {
                                lock.wait();
                            }
                        }
                        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
                        dataOutputStream.writeUTF("Server is available now.");

                        Server.clientStart();
                        Session session = new Session(socket);
                        Thread thread1 = new Thread(session);
                        thread1.start();
                    }

                    catch (InterruptedException e)
                    {

                        e.printStackTrace();
                    }


                }


        } catch (IOException e) {
           e.printStackTrace();

        }
    }
}
