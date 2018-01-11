package ie.gmit.sw;

/**
 * Comparer compares two sets and determines if they are similar or not
 * @author Ryan Conway
 *
 */
public abstract class Comparer {
	private final int intersect;
	private final int setA;
	private final int setB;

	/**
	 * Constructor for Comparer Class
	 * @param intersect intersection point
	 * @param setA file 1
	 * @param setB file 2
	 */
	public Comparer(int intersect, int setA, int setB) {
		this.intersect = intersect;
		this.setA = setA;
		this.setB = setB;
	}

	public abstract float calcJac();

	public int getIntersect() {
		return intersect;
	}

	public int getsetA() {
		return setA;
	}

	public int getsetB() {
		return setB;
	}

}