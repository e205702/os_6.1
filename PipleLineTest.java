import  worker.*;

import java.util.concurrent.SynchronousQueue;


public class PipleLineTest {

		static public void main(String [] arg) {
			SynchronousQueue<Task> queue1 = new SynchronousQueue<Task>();
			SynchronousQueue<Task> queue2 = new SynchronousQueue<Task>();
			SynchronousQueue<Task> queue3 = new SynchronousQueue<Task>();
			SynchronousQueue<Task> queue4 = new SynchronousQueue<Task>();
			
			Thread t1 = new Thread(new Pipe("1",queue1,queue2));
			Thread t2 = new Thread(new Pipe("2",queue2,queue3));
			Thread t3 = new Thread(new Pipe("3",queue3,queue4));
			Thread t4 = new Thread(new Pipe("4",queue4,null));
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			
			new Thread(new Runnable(){
				@Override
				public void run() {
					while(true) {
						try {
							Thread.sleep(50);
							System.out.println(); // " chick");
                            if(t4.isAlive() != true) break;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			String [] data = {
					"a","b","c","d","e","f",
			""};
			for( String s : data) {
				try {
					queue1.put(new Task("1", s));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				t1.join();
				t2.join();
				t3.join();
				t4.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
			
		}
	}



