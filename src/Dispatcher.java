/**
 * Created by Acer on 14.04.2017.
 */
public class Dispatcher implements Runnable {
    private Channel<Runnable> channel;
    public Dispatcher(Channel <Runnable> channel){
        this.channel=channel;
    }
    public void run(){
        while(true){
            Session session=(Session) channel.take();
            Thread client =new Thread(session);
            client.start();
        }


    }
}
