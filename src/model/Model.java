package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;



public class Model implements ModelInterface {
	Boolean flag;
	Boolean wasViewCancled;
	Boolean didSnakeMove;
	Point gocTrenBenTraiCuaCanPhong;
	int weightOfPanel;
	int heightOfPanel;
	int weightOfTheRoom;
	int heightOfTheRoom;
	int gioiHanBenTrai;
	int gioiHanBenPhai;
	int gioiHanBenTren;
	int gioiHanBenDuoi;
	Food food;
	int numberOfSpecialFoodAteIn5s;
	int weightOfPoint;
	int heightOfPoint;
	Snake snake;
	int mark;
	int soLanThuongMang;
	int gioiHanThuongMang;
	ArrayList<MarkObserver> markObservers;
	ArrayList<LifeObserver> lifeObservers;
	ArrayList<LevelObserver> levelObservers;
	ArrayList<LenghtObserver> lenghtObservers;
	Map map;

	public Model() {
		super();
		this.flag = false;
		this.wasViewCancled = false;
		this.didSnakeMove = false;
		this.gocTrenBenTraiCuaCanPhong = new Point(8, 8);
		this.weightOfPanel = 424;
		this.heightOfPanel = 550;
		this.weightOfTheRoom = 400;
		this.heightOfTheRoom = 400;
		this.gioiHanBenTrai = 8;
		this.gioiHanBenPhai = 408;
		this.gioiHanBenTren = 8;
		this.gioiHanBenDuoi = 408;
		setFood(new NormalFood());
		numberOfSpecialFoodAteIn5s = 0;
		this.weightOfPoint = 8;
		this.heightOfPoint = 8;
		this.snake = new Snake();
		this.mark = 0;
		this.soLanThuongMang = 0;
		this.gioiHanThuongMang = 100;
		this.markObservers = new ArrayList<MarkObserver>();
		this.lifeObservers = new ArrayList<LifeObserver>();
		this.levelObservers = new ArrayList<LevelObserver>();
		this.lenghtObservers = new ArrayList<LenghtObserver>();
		map = new Map(weightOfTheRoom, heightOfTheRoom, gioiHanBenTrai,
				gioiHanBenPhai, gioiHanBenTren, gioiHanBenDuoi, weightOfPoint,
				heightOfPoint);
		TypeMap();
	}

	@Override
	public void registerMarkObserver(MarkObserver markObserver) { // su dung observer trong oop
		markObservers.add(markObserver);
	}

	@Override
	public void removeMarkObserver(MarkObserver markObserver) {
		markObservers.remove(markObserver);

	}

	@Override
	public void notifyMarkObserver(int mark) {
		for (int i = 0; i < markObservers.size(); i++) {
			MarkObserver markObserver = markObservers.get(i);
			markObserver.updateMark(mark);
		}

	}

	@Override
	public void registerLifeObserver(LifeObserver lifeObserver) {
		lifeObservers.add(lifeObserver);
	}

	@Override
	public void removeLifeObserver(LifeObserver lifeObserver) {
		lifeObservers.remove(lifeObserver);
	}

	@Override
	public void notifyLifeObserver(int lifeOfSnake) {
		for (int i = 0; i < lifeObservers.size(); i++) {
			LifeObserver lifeObserver = lifeObservers.get(i);
			lifeObserver.updateLife(lifeOfSnake);
		}
	}

	@Override
	public void registerLevelObserver(LevelObserver levelObserver) {
		levelObservers.add(levelObserver);
	}

	@Override
	public void removeLevelObserver(LevelObserver levelObserver) {
		levelObservers.remove(levelObserver);
	}

	@Override
	public void notifyLevelObserver(int levelOfSnake) {
		for (int i = 0; i < levelObservers.size(); i++) {
			LevelObserver levelObserver = levelObservers.get(i);
			levelObserver.updateLevel(levelOfSnake);
		}
	}

	@Override
	public void registerLenghtObserver(LenghtObserver leenghtObserver) {
		lenghtObservers.add(leenghtObserver);
	}

	@Override
	public void removeLenghtObserver(LenghtObserver leenghtObserver) {
		lenghtObservers.remove(leenghtObserver);
	}

	@Override
	public void notifyLenghtObserver(int lenghtOfSnake) {
		for (int i = 0; i < lenghtObservers.size(); i++) {
			LenghtObserver lenghtObserver = lenghtObservers.get(i);
			lenghtObserver.updateLenght(lenghtOfSnake);
		}
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Override
	public void setFirstStateForSnake() {
		int weight = weightOfTheRoom / 2;
		int height = heightOfTheRoom / 2;
		Point p1 = new Point(weight, height);
		snake.points.add(p1);
		snake.increaseLenght();
		for (int i = 0; i < 2; i++) {
			Point p2 = new Point(p1.x + weightOfPoint, p1.y);
			snake.points.add(p2);
			snake.increaseLenght();
			p1 = p2;
		}
	}

	public void alterPointsOfSnake() {
		if (snake.huongDiChuyen.equals("right")) {
			for (int j = 0; j < snake.points.size(); j++) {
				if (j == snake.points.size() - 1) {
					Point temp = snake.points.get(j);
					snake.points.remove(j);
					Point p = new Point(temp.x + weightOfPoint, temp.y);
					snake.points.add(j, p);
				} else {
					Point temp = snake.points.get(j + 1);
					snake.points.remove(j);
					snake.points.add(j, temp);
				}
			}
		} else if (snake.huongDiChuyen.equals("left")) {
			for (int j = 0; j < snake.points.size(); j++) {
				if (j == snake.points.size() - 1) {
					Point temp = snake.points.get(j);
					snake.points.remove(j);
					Point p = new Point(temp.x - weightOfPoint, temp.y);
					snake.points.add(j, p);
				} else {
					Point temp = snake.points.get(j + 1);
					snake.points.remove(j);
					snake.points.add(j, temp);
				}
			}
		} else if (snake.huongDiChuyen.equals("up")) {
			for (int j = 0; j < snake.points.size(); j++) {
				if (j == snake.points.size() - 1) {
					Point temp = snake.points.get(j);
					snake.points.remove(j);
					Point p = new Point(temp.x, temp.y - heightOfPoint);
					snake.points.add(j, p);
				} else {
					Point temp = snake.points.get(j + 1);
					snake.points.remove(j);
					snake.points.add(j, temp);
				}
			}
		} else if (snake.huongDiChuyen.equals("down")) {
			for (int j = 0; j < snake.points.size(); j++) {
				if (j == snake.points.size() - 1) {
					Point temp = snake.points.get(j);
					snake.points.remove(j);
					Point p = new Point(temp.x, temp.y + heightOfPoint);
					snake.points.add(j, p);
				} else {
					Point temp = snake.points.get(j + 1);
					snake.points.remove(j);
					snake.points.add(j, temp);
				}
			}
		}
	}

	public boolean snakeHitWall() {
		// phai bat truong hop snake.points.size = 0, vi neu phuong thuc
		// ran hoat dong dang chay toi isSnakeDie -> snakeHitWall. Ngay Luc
		// nay neu an New thi snake se bi reset -> snake.pointOfSnake.size = 0
		// -> snake.pointsOfSnake.size() - 1 = -1 ->
		// ArrayIndexOutOfBoundsException
		if (snake.points.size() == 0) {// khong co tuc la mac dinh no sai

		} else {
			Point headOfSnake = snake.points.get(snake.points.size() - 1);
			// kiem tra ran dung tuong bao quanh phong
			if (headOfSnake.x < gioiHanBenTrai
					|| headOfSnake.x >= gioiHanBenPhai
					|| headOfSnake.y < gioiHanBenTren
					|| headOfSnake.y >= gioiHanBenDuoi) {
				return true;
			}
			// kiem tra ran dung tuong trong phong
			for (int i = 0; i < map.getPoints().size(); i++) {
				Point point = map.getPoints().get(i);
				if (headOfSnake.x == point.x && headOfSnake.y == point.y) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean snakeAteItself() {
		// phai bat truong hop snake.points.size = 0, vi neu phuong thuc
		// ran hoat dong dang chay toi isSnakeDie -> snakeAteItself. Ngay Luc
		// nay neu an New thi snake se bi reset -> snake.pointOfSnake.size = 0
		// -> snake.pointsOfSnake.size() - 1 = -1 ->
		// ArrayIndexOutOfBoundsException
		boolean result = false;
		if (snake.points.size() == 0) {

		} else {
			Point headOfSnake = snake.points.get(snake.points.size() - 1);
			for (int i = 0; i < snake.points.size() - 1; i++) {
				Point temp = snake.points.get(i);
				if ((temp.x == headOfSnake.x) & (temp.y == headOfSnake.y)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public void start() {
		flag = true;
	}

	@Override
	public void cancle() {
		wasViewCancled = true;
	}

	@Override
	public void pause() {
		flag = false;

	}

	@Override
	public void resume() {
		flag = true;

	}

	@Override
	public boolean isSnakeReady() {
		return flag;
	}

	@Override
	// cái này quan trong này
	public void paintPanel(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(gocTrenBenTraiCuaCanPhong.x, gocTrenBenTraiCuaCanPhong.y,
				weightOfTheRoom, heightOfTheRoom);
		g.setColor(Color.WHITE);
		g.fillRect(gocTrenBenTraiCuaCanPhong.x, gocTrenBenTraiCuaCanPhong.y,
				weightOfTheRoom, heightOfTheRoom);
		// draw room
		map.draw(g);
		// draw snake
		drawSnake(g);
		// draw food
		drawFood(g);
	}

	@Override
	public boolean khongTheThayDoiHuongDichuyen(String huongDiChuyenMoi) {
		boolean result = false;
		if (snake.huongDiChuyen.equals("up") && huongDiChuyenMoi.equals("down")) {
			result = true;
		} else if (snake.huongDiChuyen.equals("down")
				&& huongDiChuyenMoi.equals("up")) {
			result = true;
		} else if (snake.huongDiChuyen.equals("left")
				&& huongDiChuyenMoi.equals("right")) {
			result = true;
		} else if (snake.huongDiChuyen.equals("right")
				&& huongDiChuyenMoi.equals("left")) {
			result = true;
		} else {

		}
		return result;
	}

	public void setIsMoved(Boolean b) {
		this.didSnakeMove = b;
	}

	@Override
	public void caiDatHuongDiChuyen(String huongDiChuyenMoi) {
		if (khongTheThayDoiHuongDichuyen(huongDiChuyenMoi) || (!didSnakeMove)) {

		} else {
			snake.huongDiChuyen = huongDiChuyenMoi;
			didSnakeMove = false;
		}
	}

	@Override
	public void setFood(Food food) {
		this.food = food;

	}

	@Override
	public void randomFood() {
		Random random = new Random();
		int i = random.nextInt(20);
		if ((i % 5) == 0) {
			setFood(new SpecialFood());
		} else {
			setFood(new NormalFood());
		}
	}

	@Override
	public void changeSpecialFoodToNormalFood() {
		if (food instanceof NormalFood) {

		} else if (food instanceof SpecialFood) {
			Runnable run = new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (numberOfSpecialFoodAteIn5s != 0) {
						numberOfSpecialFoodAteIn5s -= 1;
					} else if (food instanceof NormalFood) {

					} else if (food instanceof SpecialFood) {
						Point locationOfSpecialFood = food.location;
						setFood(new NormalFood());
						food.location = locationOfSpecialFood;
					}
				}
			};
			Thread t = new Thread(run);
			t.start();
		}
	}

	@Override
	public void createLocationOfFood() {
		// neu food.location == null thi food da bi snake an do do phai tao lai
		// food.location khac de tao food o vi tri moi
		while (food.location == null) {
			int weight = 0;
			int height = 0;
			Random random = new Random();
			weight = random.nextInt(gioiHanBenPhai);
			while (true) {
				if ((gioiHanBenTrai <= weight) && (weight % weightOfPoint == 0)) {
					break;
				}
				weight = random.nextInt(gioiHanBenPhai);
			}
			height = random.nextInt(gioiHanBenDuoi);
			while (true) {
				if ((gioiHanBenTren <= height) && (height % heightOfPoint == 0)) {
					break;
				}
				height = random.nextInt(gioiHanBenDuoi);
			}
			Point temp = new Point(weight, height);
			food.location = temp;
			// kiem tra food.location co trung voi point cua ran khong
			for (int i = 0; i < snake.points.size(); i++) {
				Point pointOfSnake = snake.points.get(i);
				if (temp.x == pointOfSnake.x && temp.y == pointOfSnake.y) {
					food.location = null;
					break;
				}
			}
			// kiem tra food.location co trung voi point cua phong khong
			for (int i = 0; i < map.getPoints().size(); i++) {
				Point pointOfWall = map.getPoints().get(i);
				if (temp.x == pointOfWall.x && temp.y == pointOfWall.y) {
					food.location = null;
					break;
				}
			}
		}
	}

	@Override
	public boolean canSnakeEat() {
		boolean result = false;
		// phai bat truong hop snake.points.size = 0, vi neu phuong thuc
		// ran hoat dong dang chay toi snakeEatPoint -> canSnakeEat. Ngay Luc
		// nay neu an New thi snake se bi reset -> snake.pointOfSnake.size = 0
		// -> snake.pointsOfSnake.size() - 1 = -1 ->
		// ArrayIndexOutOfBoundsException
		if (snake.points.size() == 0) {

		} else {
			Point headOfSnake = snake.points.get(snake.points.size() - 1);
			if (headOfSnake.x == food.location.x
					&& headOfSnake.y == food.location.y) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void snakeEatFood() {
		if (food.kind.equals("Normal")) {
			snakeEatNormalFood();
		} else if (food.kind.equals("Special")) {
			snakeEatSpecialFood();
		} else {

		}

	}

	@Override
	public void snakeEatNormalFood() {
		Point firstPointOfSnake = snake.points.get(0);
		Point extraPoint = null;
		if (snake.huongDiChuyen.equals("right")) {
			extraPoint = new Point(firstPointOfSnake.x - weightOfPoint,
					firstPointOfSnake.y);
		} else if (snake.huongDiChuyen.equals("left")) {
			extraPoint = new Point(firstPointOfSnake.x + weightOfPoint,
					firstPointOfSnake.y);
		} else if (snake.huongDiChuyen.equals("up")) {
			extraPoint = new Point(firstPointOfSnake.x, firstPointOfSnake.y
					+ heightOfPoint);
		} else if (snake.huongDiChuyen.equals("down")) {
			extraPoint = new Point(firstPointOfSnake.x, firstPointOfSnake.y
					- heightOfPoint);
		}
		snake.points.add(0, extraPoint);
		increaseLenghtOfSnake();
		notifyLenghtObserver(getLenghtOfSnake());
	}

	@Override
	public void snakeEatSpecialFood() {
		numberOfSpecialFoodAteIn5s += 1;
	}

	@Override
	public int getMark() {
		return mark;
	}

	@Override
	public void increaseMark() {
		mark += food.doDinhDuong;
	}

	@Override
	public void decreaseLifeOfSnake() {
		snake.decreaseLife();
	}

	@Override
	public int getSpeedOfSnake() {
		return snake.speed;
	}

	@Override
	public void increaseLenghtOfSnake() {
		snake.increaseLenght();

	}

	@Override
	public void snakeRevive() {
		snake.revive();
		randomFood();
		changeSpecialFoodToNormalFood();
	}

	@Override
	public boolean isSnakeAlive() {
		return snake.isSnakeAlive();
	}

	@Override
	public boolean thuongMangChoRan() {
		if (mark >= ((soLanThuongMang + 1) * 100)) {
			soLanThuongMang += 1;
			return true;
		}
		return false;
	}

	@Override
	public void increaseLifeOfSnake() {
		snake.increaseLife();
	}

	@Override
	public boolean isLenghtOfSnakeTheLongest() {
		return snake.isLenghtTheLongest();
	}

	@Override
	public void increaseLevelOfSnake() {
		snake.increaseLevel();

	}

	@Override
	public void snakeSpeedUp() {
		snake.speedUp();
	}

	@Override
	public int getLifeOfSnake() {
		return snake.getLife();
	}

	@Override
	public int getLevelOfSnake() {
		return snake.getLevel();
	}

	@Override
	public int getLastLevelOfSnake() {
		return snake.getLastLevel();
	}

	@Override
	public int getLenghtOfSnake() {
		return snake.getLenght();
	}

	@Override
	public int getLastLenghtOfSnake() {
		return snake.getLastLenght();
	}

	@Override
	public boolean isSnakeTheOldest() {
		return snake.isLevelTheHighest();
	}

	@Override
	public void reset() {
		snake.reset();
		flag = false;
		didSnakeMove = false;
		setFood(new NormalFood());
		numberOfSpecialFoodAteIn5s = 0;
		mark = 0;
		soLanThuongMang = 0;
		TypeMap();
	}

	@Override
	public void drawFood(Graphics g) {
		Point locationOfFood = food.location;
		if (food.kind.equals("Normal")) {
			g.setColor(Color.RED);
		} else if (food.kind.equals("Special")) {
			g.setColor(Color.BLACK);
		}
		int weightOfFood = weightOfPoint;
		int heightOfFood = heightOfPoint;
		if (food.location == null) {
			// do nothing;
		} else {
			g.fillRect(food.location.x, food.location.y, weightOfFood,
					heightOfFood);
		}
	}

	@Override
	public boolean pointOfSnakeIsWall(Point point) {
		for (int i = 0; i < map.getPoints().size(); i++) {
			Point temp = map.getPoints().get(i);
			if (point.x == temp.x && point.y == temp.y) {
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean pointOfSnakeIsOutsideWall(Point point) {
		boolean result = false;
		if (snake.huongDiChuyen.equals("right")
				|| snake.huongDiChuyen.equals("down")) {
			if (point.x >= gioiHanBenPhai || point.y >= gioiHanBenDuoi) {
				result = true;
			} else {

			}
		} else {
			if (point.x < gioiHanBenTrai || point.y < gioiHanBenTren) {
				result = true;
			} else {

			}
		}
		return result;
	}

	@Override
	public void drawSnake(Graphics g) {
		for (int j = 0; j < snake.points.size(); j++) {
			Point point = snake.points.get(j);
			if (pointOfSnakeIsOutsideWall(point)) {
				// not paint
			} else if (pointOfSnakeIsWall(point)) {
				// not paint
			} else {
				g.setColor(Color.RED);
				g.fillRect(point.x, point.y, weightOfPoint, heightOfPoint);
				g.setColor(Color.BLACK);
				g.drawRect(point.x, point.y, weightOfPoint, heightOfPoint);
			}
		}

	}

	@Override
	public void TypeMap() {
		
		if (snake.level == 0) {
			map.setAlterPointBehavior(new Empty());
			map.alterPoint();
		} else if (snake.level == 1) {
			map.setAlterPointBehavior(new Horizontal());
			map.alterPoint();
		} else if (snake.level == 2) {
			map.setAlterPointBehavior(new Vertical());
			map.alterPoint();
		} else if (snake.level == 3) {
			map.setAlterPointBehavior(new XienTrai());
			map.alterPoint();
		} else if (snake.level == 4) {
			map.setAlterPointBehavior(new XienPhai());
			map.alterPoint();
		} else if (snake.level == 5) {
			map.setAlterPointBehavior(new NgangDoc());
			map.alterPoint();
		} else if (snake.level == 6) {
			map.setAlterPointBehavior(new NgangXien());
			map.alterPoint();
		} else if (snake.level == 7) {
			map.setAlterPointBehavior(new DocXien());
			map.alterPoint();
		} else if (snake.level == 4) {
			map.setAlterPointBehavior(new XienXien());
			map.alterPoint();
		} else if (snake.level == 4) {
			map.setAlterPointBehavior(new DayDu());
			map.alterPoint();
		}
	}

	@Override
	public boolean getIsViewCanceled() {
		return wasViewCancled;
	}

	public int getWeightOfPanel() {
		return weightOfPanel;
	}

	public int getHeightOfPanel() {
		return heightOfPanel;
	}

}
