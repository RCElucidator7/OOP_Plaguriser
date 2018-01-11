package ie.gmit.sw;

public class Jacard extends Comparer {
	private final int intersect;
	private final int setA;
	private final int setB;
	private float jaccard;

	//Jacrad class for calculating percentage
	public Jacard(int intersect, int setA, int setB) {
		super(intersect, setA, setB);
		this.intersect = intersect;
		this.setA = setA;
		this.setB = setB;
	}

	@Override
	public float calcJac() {
		
		jaccard = (float) intersect / (((float) setA + (float) setB) - (float) intersect);
		return jaccard;
	}

	public int getintersect() {
		return intersect;
	}

	public int getsetA() {
		return setA;
	}

	public int getsetB() {
		return setB;
	}

}