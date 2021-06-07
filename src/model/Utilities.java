package model;



import java.awt.Point;
import java.util.Random;

public class Utilities {
	// tao 1 point bat ky, sao cho point do khi tao thanh wall se thoa man dieu
	// kien( khong dung vao ran hoac che duong di cua ran luc dau)
	public static Point createRandomPoint(Map room, int numberOfPointOfWall) {
		int gioiHanBenTraiNgoai = room.gioiHanBenTrai + room.weightOfPoint
				* numberOfPointOfWall;
		int gioiHanBenTraiTrong = (room.weight / 2) - room.weightOfPoint
				* numberOfPointOfWall;
		int gioiHanBenPhaiNgoai = room.gioiHanBenPhai - room.weightOfPoint
				* numberOfPointOfWall;
		int gioiHanBenPhaiTrong = (room.weight / 2) + (room.weightOfPoint * 3)
				+ (room.weightOfPoint * numberOfPointOfWall);
		int gioiHanBenTrenNgoai = room.gioiHanBenTren + room.heightOfPoint
				* numberOfPointOfWall;
		int gioiHanBenTrenTrong = (room.height / 2) - room.heightOfPoint
				* numberOfPointOfWall;
		int gioiHanBenDuoiNgoai = room.gioiHanBenDuoi - room.heightOfPoint
				* numberOfPointOfWall;
		int gioiHanBenDuoiTrong = (room.height / 2) + room.heightOfPoint
				* numberOfPointOfWall;
		Point result = null;
		Random random = new Random();
		int weight = 0;
		weight = random.nextInt(room.gioiHanBenPhai);
		while (true) {
			if ((gioiHanBenTraiNgoai < weight && weight < gioiHanBenTraiTrong && weight
					% room.weightOfPoint == 0)
					|| (gioiHanBenPhaiTrong < weight
							&& weight < gioiHanBenPhaiNgoai && weight
							% room.weightOfPoint == 0)) {
				break;
			}
			weight = random.nextInt(room.gioiHanBenPhai);

		}
		int height = 0;
		height = random.nextInt(room.gioiHanBenDuoi);
		while (true) {
			if ((gioiHanBenTrenNgoai < height && height < gioiHanBenTrenTrong && height
					% room.heightOfPoint == 0)
					|| (gioiHanBenDuoiTrong < height
							&& height < gioiHanBenDuoiNgoai && height
							% room.heightOfPoint == 0)) {
				break;
			}
			height = random.nextInt(room.gioiHanBenDuoi);
		}
		result = new Point(weight, height);
		return result;
	}
}
