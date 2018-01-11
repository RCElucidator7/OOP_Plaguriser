package ie.gmit.sw;

public class Shingle{
	private int docID;
	private int hashCode;
	
	public Shingle(int doc, int h){
		super();
		this.docID = doc;
		this.hashCode = h;
	}
	
	public int getDocID() {
		return docID;
	}
	
	public void setDocID(int docID) {
		this.docID = docID;
	}
	
	public int getHashCode() {
		return hashCode;
	}
	
	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}
}