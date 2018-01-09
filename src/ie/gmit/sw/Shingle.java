package ie.gmit.sw;

public class Shingle{
	private int docID;
	private int hashCode;
	public Shingle(int doc, int h){
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + docID;
		result = prime * result + hashCode;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		Shingle other = (Shingle) obj;
		return this.hashCode == other.getHashCode();
	}
	
	//Override hashCode + equals using the shingleHashCode
}