package controller;

import java.awt.Graphics;

import model.Model;

public interface ControllerInterface {
	public Model getModel();

	public void setFirstState();

	public void paintPanel(Graphics g);

	public void startOrCancle();

	public void pauseOrResume();

	public boolean wasViewCancled();

	public boolean didSnakeDie();

	public boolean isSnakeTheOldest();

	public void caiDatHuongDiChuyen(String huongDiChuyenMoi);

	public void snakeMove();

	public void snakeEatFood();

	public void snakeRevive();

	public void snakeSpeedUp();

	public void ranHoatDong();

	public void lose();

	public void win();

	public void help();

	public void closeHelpView();

	public void reset();
}
