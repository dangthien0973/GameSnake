package model;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	ArrayList<Point> points;
	String huongDiChuyen;
	int life;
	int lenght;
	int level;
	int firstSpeed;
	int lastLevel;
	int lastLenght;
	int speed;

	public Snake() {
		super();
		this.points = new ArrayList<Point>();
		this.huongDiChuyen = "right";
		this.life = 3;
		this.lenght = 0;
		this.level = 0;
		this.firstSpeed = 250;
		this.lastLevel = 9;
		this.lastLenght = 20;
		this.speed = firstSpeed - level * (firstSpeed / lastLevel);
	}

	public int getLife() {
		return life;
	}

	public int getLevel() {
		return level;
	}

	public int getLastLevel() {
		return lastLevel;
	}

	public int getLenght() {
		return lenght;
	}

	public int getLastLenght() {
		return lastLenght;
	}

	public boolean isSnakeAlive() {
		return life != 0;
	}

	public boolean isLevelTheHighest() {
		if (level == lastLevel) {
			return true;
		} else {
			return false;
		}
	}

	public void increaseLenght() {
		lenght = lenght + 1;
	}

	public void increaseLife() {
		life = life + 1;
	}

	public void decreaseLife() {
		if (life == 0) {

		} else {
			life = life - 1;
		}
	}

	public void increaseLevel() {
		level = level + 1;
	}

	public boolean isLenghtTheLongest() {
		if (lenght == lastLenght) {
			return true;
		}
		return false;
	}

	public void revive() {
		points = new ArrayList<Point>();
		huongDiChuyen = "right";
		lenght = 0;
		this.speed = firstSpeed - level * (firstSpeed / lastLevel);
	}

	public void speedUp() {
		points = new ArrayList<Point>();
		huongDiChuyen = "right";
		lenght = 0;
		this.speed = firstSpeed - level * (firstSpeed / lastLevel);
	}

	public void reset() {
		this.points = new ArrayList<Point>();
		this.huongDiChuyen = "right";
		this.life = 3;
		this.lenght = 0;
		this.level = 0;
		this.firstSpeed = 250;
		this.lastLevel = 9;
		this.lastLenght = 20;
		this.speed = firstSpeed - level * 5;
	}
}
