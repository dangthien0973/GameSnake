package model;



import java.awt.Point;

public abstract class Food {
	public String kind;
	public Point location;
	public int doDinhDuong;

	public Food() {
		super();
		this.location = null;
	}

}
