package worker;

public class Task implements Runnable {

	private String s;	
	private String name;

	public Task(String name,String s) {
		this.name = name;
		this.s = s;
	}
	
	public String get() {
		return s;
	}

	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print(s+name);
	}
		

}
