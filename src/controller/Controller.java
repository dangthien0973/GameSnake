package controller;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import view.HelpView;
import view.LoseView;
import view.SnakeView;
import view.WaitingView;
import view.WinView;

import model.Model;

public class Controller implements ControllerInterface {
	SnakeView view;
	Model model;
	WinView winView;
	LoseView loseView;
	HelpView helpView;
	WaitingView waitingView;

	public Controller(Model model) {
		super();
		this.model = model;
		this.winView = new WinView();
		this.loseView = new LoseView();
		this.helpView = new HelpView(this);
		this.waitingView = new WaitingView(this);
		this.view = new SnakeView(this, model);
		this.view.createView();
	}

	public Model getModel() {
		return model;
	}

	@Override
	public void setFirstState() {
		model.setFirstStateForSnake();
		model.notifyMarkObserver(model.getMark());
		model.notifyLifeObserver(model.getLifeOfSnake());
		model.notifyLevelObserver(model.getLevelOfSnake());
		model.notifyLenghtObserver(model.getLenghtOfSnake());
		model.createLocationOfFood();
		view.repaintPanel();
	}

	@Override
	public void paintPanel(Graphics g) {
		model.paintPanel(g);
	}

	@Override
	public void startOrCancle() {
		if (view.getTextOfButtonToStartOrCancle().equals("Cancle")) {
			view.cancle();
			model.cancle();
		} else {
			view.start();
			model.start();
			// khong the de phuong thuc ranHoatDong() ngay trong phuong thuc
			// startOrCancle vi khi  click start se dan den dung do nut start van
			// dang tiep dien
		}
	}

	@Override
	public void pauseOrResume() {
		if (view.getTextOfButtonToPauseOrResume().equals("Pause")) {
			view.pause();
			model.pause();
		} else {
			view.resume();
			model.resume();
		}

	}

	@Override
	public boolean wasViewCancled() {
		return model.getIsViewCanceled();
	}

	@Override
	public boolean didSnakeDie() {
		boolean result = model.snakeHitWall() || model.snakeAteItself();
		return result;
	}

	@Override
	public boolean isSnakeTheOldest() {
		return model.isSnakeTheOldest();
	}

	@Override
	public void caiDatHuongDiChuyen(String huongDiChuyenMoi) {
		model.caiDatHuongDiChuyen(huongDiChuyenMoi);
	}

	@Override
	public void snakeMove() {
		if (model.isSnakeReady()) {
			model.alterPointsOfSnake();
			model.createLocationOfFood();
			view.repaintPanel();
			model.setIsMoved(true);
		}
	}

	@Override
	public void snakeEatFood() {
		if (model.canSnakeEat()) {
			model.snakeEatFood();
			model.increaseMark();
			model.notifyMarkObserver(model.getMark());
			// model.randomFood() phai nam sau model.increaseMark neu ko se co
			// the tang lon diem
			model.randomFood();
			model.changeSpecialFoodToNormalFood();
			if (model.thuongMangChoRan()) {
				model.increaseLifeOfSnake();
				model.notifyLifeObserver(model.getLifeOfSnake());
			}
			snakeSpeedUp();
			model.createLocationOfFood();
			view.repaintPanel();
		}

	}

	@Override
	public void snakeRevive() {
		if (model.isSnakeAlive()) {
			model.snakeRevive();
			setFirstState();
			ranHoatDong();
		}
	}

	@Override
	public void snakeSpeedUp() {
		if (model.isLenghtOfSnakeTheLongest()) {
			model.increaseLevelOfSnake();
			model.notifyLevelObserver(model.getLevelOfSnake());
			if (isSnakeTheOldest()) {
				// do nothing
			} else {
				model.snakeSpeedUp();
				model.TypeMap();
				setFirstState();
				waitingView.createView();
			}
		}
	}

	@Override
	public void ranHoatDong() {
		while (true) {
			if (didSnakeDie() || wasViewCancled()) {
				model.decreaseLifeOfSnake();
				model.notifyLifeObserver(model.getLifeOfSnake());
				if (wasViewCancled()) {

				} else {
					snakeRevive();
					// phai bat dieu kien isSnakeTheOldest() vi khi chet n lan
					// va hoi sinh, sau do choi het level luc nay se xay
					// ra truong hop lose va win cung duoc ve
					// khong viet: snakeRevive(); lose();
					if (isSnakeTheOldest()) {
						// do nothing
					} else {
						lose();
					}
				}
				view.disablePause();
				System.out.println("end");
				break;
			}
			if (isSnakeTheOldest()) {
				win();
				view.disablePause();
				break;
			}
			try {
				Thread.sleep(model.getSpeedOfSnake());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			snakeMove();
			snakeEatFood();
		}

	}

	@Override
	public void lose() {
		if (loseView.isShowing()) {

		} else {
			loseView.createView();
			loseView.close();
		}
	}

	@Override
	public void win() {
		if (winView.isShowing()) {

		} else {
			winView.createView();
			winView.close();
		}

	}

	@Override
	public void help() {
		helpView.createView();
	}

	@Override
	public void closeHelpView() {
		helpView.close();

	}

	@Override
	public void reset() {
		view.reset();
		model.reset();
		setFirstState();
	}

}
