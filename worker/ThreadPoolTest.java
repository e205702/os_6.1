package worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
	static public void main(String [] arg) {
		int cpu = 16;
		System.out.println("hello");
		ExecutorService pool = Executors.newFixedThreadPool(cpu);
		System.out.println(pool);
		
		// make new line for visual display
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(50);
						System.out.println(); // " chick");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		String [] data = {
				"a","b","c","d","e","f",
				"a","b","c","d","e","f",
				"a","b","c","d","e","f",
				"a","b","c","d","e","f",
				"a","b","c","d","e","f",
				"a","b","c","d","e","f",
				"a","b","c","d","e","f",
				"a","b","c","d","e","f",
		""};
		for( String s : data) {
			pool.execute(new Task("",s));
		}

	}
}

