package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ie.gmit.sw.Shingle;
/**
 * runs the MinHasher, taking in queues and converting Shingles and their data 
 * and stores them in a map 
 * @author Ryan Conway
 * 
 * @see run
 */
public class MinHasher implements Runnable {

	private BlockingQueue<Shingle> q;
	private Map<Integer, List<Integer>> map = new ConcurrentHashMap<Integer, List<Integer>>();
	private ExecutorService pool;
	private Set<Integer> minHash;
	private int hashCount;
	int fileCount = 2;

	/**
	 * Constructor for the MinHasher
	 * @param q blocking queue
	 * @param k hashcount
	 */
	public MinHasher(BlockingQueue<Shingle> q, int k) {
		this.q = q;
		this.hashCount = k;
		pool = Executors.newFixedThreadPool(k);
		init();
	}
	
	/**
	 * Generates random number for hashing shingle data
	 */
	public void init() {
		minHash = new TreeSet<Integer>();

		// generates random number for hashing
		Random randInt = new Random();
		for (int i = 0; i < hashCount; i++) {
			minHash.add(randInt.nextInt());
		}
	}
	
	@Override
	public void run() {
		//Declare lists
		List<Integer> file1List = new ArrayList<>();
		List<Integer> file2List = new ArrayList<>();
		List<Integer> voidList = new ArrayList<>();
		
		while (fileCount > 0) {
			try {
				Shingle s = q.take();
				// checks if the doc id and hashcode are null (Poison)
				if (s.getDocID() == 0 && s.getHashCode() == 0) {
					fileCount = fileCount-1;
				} else {
					pool.execute(new Runnable() {

						@Override
						public void run() {

							if (s.getDocID() == 1) {
								//Add to list one
								file1List.add(hashing(s));
							} else if (s.getDocID() == 2) {
								//Add to list 2
								file2List.add(hashing(s));
							} else {
								voidList.add(hashing(s));
							}
						}
					});// Runnable
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Interuppted exception " + e);
			}
		}

		// Shuts down ExecutorService
		shutdownAndAwaitTermination(pool);

		
		int setA = file1List.size();
		int setB = file2List.size();

		// put file1List, file2List to map at index
		map.put(1, file1List);
		map.put(2, file2List);

		List<Integer> intersect = map.get(1);
		intersect.retainAll(map.get(2));

		// compute jacard index
		Comparer jac = new Jacard(intersect.size(), setA, setB);
		menu.showResults(jac);
	}

	/**
	 * Shuts down pool
	 * @param pool pool
	 */
	void shutdownAndAwaitTermination(ExecutorService pool) {
		pool.shutdown(); // Disable new tasks from being submitted
		try {
			// Wait for tasks to terminate
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow(); // Cancel executions
				// Wait for tasks to respond
				if (!pool.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
	/**
	 * Takes in Shingle and converts it to hash
	 * @param s Shingle
	 * @return hashcode value
	 */
	public synchronized int hashing(Shingle s) {
		//XOR the integer word values with the hashes
		int minValue = Integer.MAX_VALUE;
		for (Integer hash : minHash) {
			int minHashed = s.getHashCode() ^ hash;
			if (minHashed < minValue) {
				minValue = minHashed;
			}
		}
		return minValue;
	}
}