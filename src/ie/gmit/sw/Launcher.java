package ie.gmit.sw;

import java.util.concurrent.*;

public class Launcher {
	
	
	public void Launch(String f1, String f2, int shingleSize, int blockingQueueSize) {
		//initalise blocking queue
		BlockingQueue<Shingle> q = new LinkedBlockingQueue<>(blockingQueueSize);

		//Parse the files and allocate them to a thread
		Thread t1 = new Thread(new FileParser(f1, q, shingleSize, 1), "T1");
		Thread t2 = new Thread(new FileParser(f2, q, shingleSize, 2), "T2");
		
		//Start threads
		t1.start();
		t2.start();
				
		try {
			//join the two threads
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Start comparison thread
		Thread t3 = new Thread(new MinHasher(q,shingleSize),"T3");
		t3.start();
		try {
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  }
