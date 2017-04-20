import java.util.LinkedList;

/**
 * Created by Acer on 14.04.2017.
 */
public class Channel <T> {
    private  final int maxCount;
    final  Object lock=new Object();
    LinkedList<T> queue=new LinkedList();
    void put(T x){
        synchronized (lock){
            try {
                while (queue.lastIndexOf(x) == maxCount)
                    lock.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            queue.addLast(x);
        }
    }
    Object take(){
        synchronized (lock){
            while (queue.isEmpty()){
                try{
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.notifyAll();
            return  queue.removeFirst();
        }
    }
    public  Channel (int maxCount){
        this.maxCount=maxCount;
    }

}
