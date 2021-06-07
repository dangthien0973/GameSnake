package model;



import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Horizontal implements AlterPointBehavior {
	
	int numberOfPointOfWall = 3;
	int numberOfWall = 15;

	@Override
	public void alterPoint(Map map) {
		map.points = new ArrayList<Point>();
		ArrayList<Point> temp = new ArrayList<Point>();
		for (int i = 0; i < numberOfWall;) {
			Point point1 = Utilities.createRandomPoint(map,
					numberOfPointOfWall);
			temp.add(point1);
			for (int j = 0; j < numberOfPointOfWall - 1; j++) {
				point1 = new Point(point1.x + map.weightOfPoint, point1.y);
				temp.add(point1);
			}
			boolean isContained = false;
			for (int k = 0; k < numberOfPointOfWall; k++) {
				if (map.points.contains(temp.get(k))) {
					isContained = true;
					break;
				}
			}
			if (!isContained) {
				for (int h = 0; h < numberOfPointOfWall; h++) {
					map.points.add(temp.get(h));
				}
				i++;
			}
			temp = new ArrayList<Point>();
		}
	}
}
