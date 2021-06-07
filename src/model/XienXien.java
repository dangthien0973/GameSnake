package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


public class XienXien implements AlterPointBehavior {
	// numberOfPointWall phai be hon (((room.weight / 2) / 8) / 2) - 2
	int numberOfPointOfWall = 5;
	int numberOfWall = 20;

	@Override
	public void alterPoint(Map map) {
		map.points = new ArrayList<Point>();
		ArrayList<Point> temp = new ArrayList<Point>();
		for (int i = 0; i < numberOfWall;) {
			Point point1 = Utilities.createRandomPoint(map, numberOfPointOfWall);
			temp.add(point1);
			Point point2 = Utilities.createRandomPoint(map, numberOfPointOfWall);
			temp.add(point2);
			for (int j = 0; j < numberOfPointOfWall - 1; j++) {
				point1 = new Point(point1.x + map.weightOfPoint, point1.y
						+ map.heightOfPoint);
				temp.add(point1);
				point2 = new Point(point2.x - map.weightOfPoint, point2.y
						+ map.heightOfPoint);
				temp.add(point2);
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
