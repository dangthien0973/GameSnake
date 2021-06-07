package model;



import java.awt.Point;
import java.util.ArrayList;



public class Empty implements AlterPointBehavior {

	@Override
	public void alterPoint(Map map) {
		map.points = new ArrayList<Point>();
	}

}
