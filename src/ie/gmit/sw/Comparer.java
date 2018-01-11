package ie.gmit.sw;

public abstract class Comparer {
	private final int intersect;
	private final int setA;
	private final int setB;

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