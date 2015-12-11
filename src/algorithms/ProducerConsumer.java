package algorithms;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
  private static BlockingQueue<Integer> que=new ArrayBlockingQueue<>(1);
  
  public static void producer() throws InterruptedException{
	  Random random=new Random();
	  while(true){
		  int val=random.nextInt(100);
		  que.put(val);
		  System.out.println("put value: "+val+" queue size is "+que.size());
		  
	  }
  }
  public static void consumer() throws InterruptedException {
	  Random random=new Random();
	  while(true){
		  Thread.sleep(100);
		  if(random.nextInt(10)==0){
			  int val=que.take();
			  System.out.println("Taken value: "+ val+" queue size is "+que.size());
		  }
	  }
  }
  
  public static void main(String[] args) throws InterruptedException{
	  Thread t1=new Thread(new Runnable(){
		  public void run(){
			  try {
				producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	  });
	  
	  Thread t2=new Thread(new Runnable(){
		  public void run(){
			  try {
				consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	  });
	  t1.start();
	  t2.start();
	  t1.join();
	  t2.join();
  }
}
