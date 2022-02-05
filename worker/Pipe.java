package worker;

import java.util.concurrent.SynchronousQueue;

public class Pipe implements Runnable {

	private SynchronousQueue<Task> input;
	private SynchronousQueue<Task> output;
	private String stage;

	public Pipe(String stage,SynchronousQueue<Task> queue1, SynchronousQueue<Task> queue2) {
		this.stage = stage;
		this.input = queue1;
		this.output = queue2;
	}

	@Override
	public void run() {	
		while(true) {
			Task t;
			try {
				t = input.take();
				Task t1 = new Task(stage,t.get());
				t1.run();
				if (output!=null) output.put(t);
                if (t.get() == "") break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
