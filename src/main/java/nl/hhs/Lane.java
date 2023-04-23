package nl.hhs;

public class Lane extends Observable {

	private final int number;
	private boolean occupied;

	public Lane (int number) {
		this.number = number;
		setOccupied (false);
	}

	public int getNumber () {
		return this.number;
	}

	public boolean isOccupied () {
		return this.occupied;
	}

	public void setOccupied (boolean occupied) {
		this.occupied = occupied;
		setChanged ();
		notifyObservers ();
	}
}