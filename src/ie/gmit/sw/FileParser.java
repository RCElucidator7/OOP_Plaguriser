package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class FileParser implements Runnable {
	private BlockingQueue<Shingle>queue;
	private String file;
	private int shingleSize, k;
	private Deque<String> buffer = new LinkedList<>();
	private int docId;	

	public FileParser(String file, BlockingQueue<Shingle>q, int shingleSize, int k) {
		this.queue = q;
		//...
		//...
	}
	
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while((line = br.readLine()) != null) {
				String uLine = line.toUpperCase();
				String[] words = uLine.split(" "); // Can also take a regexpression
				addWordsToBuffer(words);
				Shingle s = getNextShingle();
				queue.put(s); // Blocking method. Add is not a blocking method
			}
		flushBuffer();
		br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// Run


	private void addWordsToBuffer(String [] words) {
		for(String s : words) {
			buffer.add(s);
		}
 
       }

 	private Shingle getNextShingle() {
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		while(counter < shingleSize) {
			if(buffer.peek() != null) {
				sb.append(buffer.poll());
				counter++;
			}
		}  
		if (sb.length() > 0) {
			return(new Shingle(docId, sb.toString().hashCode()));
		}
		else {
			return(null);
		}
 	} // Next shingle
	

	private void flushBuffer() {
		while(buffer.size() > 0) {
			Shingle s = getNextShingle();
			if(s != null) {
				try {
					queue.put(s);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					queue.put(new Poison(docId, 0));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

       
 }// Document Parser
