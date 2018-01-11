package ie.gmit.sw;

/**
 * Jacrad class for calculating percentage, extends Comparer
 * @author Ryan Conway
 *
 */
public class Jacard extends Comparer {
	private final int intersect;
	private final int setA;
	private final int setB;
	private float jaccard;

	/**
	 * Constructor for Jacard
	 * @param intersect intersection point
	 * @param setA list 1
	 * @param setB list 2
	 */
	public Jacard(int intersect, int setA, int setB) {
		super(intersect, setA, setB);
		this.intersect = intersect;
		this.setA = setA;
		this.setB = setB;
	}

	/**
	 * Calculates the jacard by taking in set values
	 */
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