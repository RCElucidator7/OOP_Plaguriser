package ie.gmit.sw;

import java.util.concurrent.*;

public class Launcher {
	public void Launch(String f1, String f2) {
		BlockingQueue<Shingle> q = new LinkedBlockingQueue<>(blockingQueueSize)	
		// threadPoolSize	

		Thread t1 = new Thread(new FileParser(f1, q, shingleSize, k), "T1");
		Thread t2 = new Thread(new FileParser(f2, q, shingleSize, k), "T2");
		// t3 for consumer
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
  }