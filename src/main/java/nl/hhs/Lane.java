package nl.hhs;

import java.util.Observable;

public class Lane extends Observable {

	private int number;
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