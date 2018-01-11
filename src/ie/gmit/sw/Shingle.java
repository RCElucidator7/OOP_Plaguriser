package ie.gmit.sw;
/**
 * An array of strings read in from a .txt file and is converted to hashcode
 * @author Ryan Conway
 *
 */
public class Shingle{
	private int docID;
	private int hashCode;
	
	/**
	 * Constructor for a Shingle
	 * @param doc File ID
	 * @param h hashCode
	 */
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