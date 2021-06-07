package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Map {
	int weight;// chieu dài cua map
	int height;// chieu rong cua map
	int gioiHanBenTrai;// gioi han ran dung vao la chet
	int gioiHanBenPhai;// gioi han ran dung vao la chet
	int gioiHanBenTren;//gioi han ran dung vao la chet
	int gioiHanBenDuoi;//gioi han ran dung vao la chet
	int weightOfPoint;// chi?u cao c?a Point 
	int heightOfPoint;// chi?u r?ng c?a Point
	ArrayList<Point> points;// ch?a các di?m
	AlterPointBehavior alterPointBehavior;// các thay d?i di?m cung nhu thay d?i level

	public Map(int weight, int height, int gioiHanBenTrai, int gioiHanBenPhai,
			int gioiHanBenTren, int gioiHanBenDuoi, int weightOfPoint,
			int heightOfPoint) {
		super();
		this.weight = weight;
		this.height = height;
		this.gioiHanBenTrai = gioiHanBenTrai;
		this.gioiHanBenPhai = gioiHanBenPhai;
		this.gioiHanBenTren = gioiHanBenTren;
		this.gioiHanBenDuoi = gioiHanBenDuoi;
		this.weightOfPoint = weightOfPoint;
		this.heightOfPoint = heightOfPoint;
		this.points = new ArrayList<Point>();
		this.alterPointBehavior = new Empty();
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setAlterPointBehavior(AlterPointBehavior alterPointBehavior) {
		this.alterPointBehavior = alterPointBehavior;
	}

	public void alterPoint() {
		alterPointBehavior.alterPoint(this);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < points.size(); i++) {
			Point point = points.get(i);
			g.setColor(Color.BLUE);
			g.fillRect(point.x, point.y, weightOfPoint - 1, heightOfPoint - 1);
			g.setColor(Color.BLACK);
			g.drawRect(point.x, point.y, weightOfPoint, heightOfPoint);
		}
	}

}
