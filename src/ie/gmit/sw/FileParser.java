package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
/**
 * Parses the .txt files that the user has input and assigns them to a String array
 * @author Ryan Conway
 *
 */
public class FileParser implements Runnable {
	private BlockingQueue<Shingle>queue;
	private String file;
	private int shingleSize/*, k*/;
	private Deque<String> buffer = new LinkedList<String>();
	private int docId;	

	/**
	 * Comstructor for file parser
	 * @param file file name
	 * @param q Blocking queue size
	 * @param shingleSize Size of Shingle
	 * @param k File ID
	 */
	public FileParser(String file, BlockingQueue<Shingle>q, int shingleSize, int k) {
		this.queue = q;
		this.file = file;
		this.shingleSize = shingleSize;
		this.docId = k;
	}
	
	public void run() {
		try {
			//Read files
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while((line = br.readLine()) != null) {
				if(line.length() > 0){	
					String uLine = line.toUpperCase();
					System.out.println(uLine);
					String[] words = uLine.split(" ");
					addWordsToBuffer(words);
				}
			}
			while (buffer.size() != 0) {
				Shingle s = getNextShingle();
				if(s != null){
					queue.put(s); // Blocking method. Add is not a blocking method
				}
			}
		
		System.out.println("Done" + docId);
		flushBuffer();
		br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error file " + file + " not found, please try again.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * adds words to the array
	 * @param words adds words to the array
	 */
	private void addWordsToBuffer(String [] words) {
		//Add read words from file to buffer
		for(String s : words) {
			buffer.add(s);
		}
    }

	/**
	 * Gets the next shingle
	 * @return Shingle
	 */
 	private Shingle getNextShingle() {
 		//Gets shingles
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		while(counter < shingleSize) {
			if(buffer.peek() != null) {
				sb.append(buffer.poll());
				counter++;
			}
			else{
				counter = shingleSize;
			}
		}  
		if (sb.length() > 0) {
			return(new Shingle(docId, sb.toString().hashCode()));
		}
		else {
			return(null);
		}
 	}
	
 	/**
 	 * Flushes the buffer
 	 * @throws InterruptedException
 	 */
 	private void flushBuffer() throws InterruptedException {
		while (buffer.size() > 0) {
			Shingle s = getNextShingle();
			if (s != null) {
				queue.put(s);
			}
		}
		//Set poison
		queue.put(new Poison(0, 0));
 	}
       
}