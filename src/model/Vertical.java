package model;




import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Vertical implements AlterPointBehavior {
	// numberOfPointWall phai be hon (((room.weight / 2) / 8) / 2) - 2
	int numberOfPointOfWall = 3;
	int numberOfWall = 15;

	@Override
	public void alterPoint(Map map) {
		map.points = new ArrayList<Point>();
		ArrayList<Point> temp = new ArrayList<Point>();
		for (int i = 0; i < numberOfWall;i) {
			Point point1 = Utilities.createRandomPoint(map,
					numberOfPointOfWall);
			temp.add(point1);
			for (int j = 0; j < numberOfPointOfWall - 1; j++) {
				point1 = new Point(point1.x, point1.y + map.heightOfPoint);// tao thanh ngang
				temp.add(point1);// tao mot diem Point co chieu dai ma point y+ chieu dai cua diem
			}
			boolean isContained = false;
			for (int k = 0; k < numberOfPointOfWall; k++) {// kiem tra xem diem co trong map.point neu chua thi them vao
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
