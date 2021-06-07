package model;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;


public interface ModelInterface {

	public void registerMarkObserver(MarkObserver markObserver);

	public void removeMarkObserver(MarkObserver markObserver);

	public void notifyMarkObserver(int mark);

	public void registerLifeObserver(LifeObserver lifeObserver);

	public void removeLifeObserver(LifeObserver lifeObserver);

	public void notifyLifeObserver(int lifeOfSnake);

	public void registerLevelObserver(LevelObserver levelObserver);

	public void removeLevelObserver(LevelObserver levelObserver);

	public void notifyLevelObserver(int levelOfSnake);

	public void registerLenghtObserver(LenghtObserver lenghtObserver);

	public void removeLenghtObserver(LenghtObserver lenghtObserver);

	public void notifyLenghtObserver(int lenghtOfSnake);

	public void setFlag(Boolean flag);

	public void setFirstStateForSnake();

	public void alterPointsOfSnake();

	public boolean snakeHitWall();

	public boolean snakeAteItself();

	public void start();

	public void cancle();

	public void pause();

	public void resume();

	public boolean isSnakeReady();

	public void paintPanel(Graphics g);

	public boolean khongTheThayDoiHuongDichuyen(String huongDiChuyenMoi);

	public void setIsMoved(Boolean b);

	public void caiDatHuongDiChuyen(String huongDiChuyenMoi);

	public void setFood(Food food);

	public void randomFood();

	public void changeSpecialFoodToNormalFood();

	public void createLocationOfFood();

	public boolean canSnakeEat();

	public void snakeEatFood();

	public void snakeEatNormalFood();

	public void snakeEatSpecialFood();

	public int getMark();

	public void increaseMark();

	public void decreaseLifeOfSnake();

	public int getSpeedOfSnake();

	public void increaseLenghtOfSnake();

	public void snakeRevive();

	public boolean isSnakeAlive();

	public boolean thuongMangChoRan();

	public void increaseLifeOfSnake();

	public boolean isLenghtOfSnakeTheLongest();

	public void increaseLevelOfSnake();

	public void snakeSpeedUp();

	public int getLifeOfSnake();

	public int getLevelOfSnake();

	public int getLastLevelOfSnake();

	public int getLenghtOfSnake();

	public int getLastLenghtOfSnake();

	public boolean isSnakeTheOldest();

	public void reset();

	public void drawFood(Graphics g);

	public boolean pointOfSnakeIsWall(Point point);

	public boolean pointOfSnakeIsOutsideWall(Point point);

	public void drawSnake(Graphics g);

	public void TypeMap();

	public boolean getIsViewCanceled();

	public int getWeightOfPanel();

	public int getHeightOfPanel();
}
