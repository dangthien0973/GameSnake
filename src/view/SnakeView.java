package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.Controller;

import model.LenghtObserver;
import model.LevelObserver;
import model.LifeObserver;
import model.MarkObserver;
import model.Model;

public class SnakeView implements MarkObserver, LifeObserver, LevelObserver,
		LenghtObserver {
	JFrame frame;
	JMenuBar memuBar;
	JMenu menuForOption;
	JMenuItem menuItemForNew;
	JMenuItem menuItemForHelp;
	SnakePanel panel;
	JButton buttonToStartOrCancle;
	JButton buttonToPauseOrResume;
	Controller controller;
	JLabel lableForMark;
	JLabel lableForLife;
	JLabel lableForLevel;
	JLabel lableForLenght;
	Model model;

	public SnakeView(Controller controller, Model model) {
		super();
		this.controller = controller;
		this.model = model;
		model.registerMarkObserver(this);
		model.registerLifeObserver(this);
		model.registerLevelObserver(this);
		model.registerLenghtObserver(this);
	}

	public void createView() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake");
		frame.setResizable(false);
		memuBar = new JMenuBar();
		memuBar.setBackground(Color.WHITE);
		menuForOption = new JMenu("<html>"
				+ "<span style='font-size:15.0pt;color:black'>" + "Option"
				+ "</html>");
		menuForOption.setToolTipText("Click Option to select New or Help");
		memuBar.add(menuForOption);
		menuItemForNew = new JMenuItem("<html>"
				+ "<span style='font-size:15.0pt;color:black'>" + "New"
				+ "</html>");
		menuForOption.add(menuItemForNew);
		menuItemForNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// neu viet code nhu sau:
				// controller.reset();
				// controller.ranHoatDong();
				// dan den loi o truong hop dang choi hoac pause ma reset
				// thi cung luc co 2 phuong thuc ranHoatDong() cung chay khong
				// dong
				// bo
				// khi dang choi hoac pause thi khong can tao phuong thuc
				// ranHoatDong()
				// khi win hoac die thi can tao phuong thuc ranHoatDong() vi
				// phuong thuc ranHoatDong() truoc da break
				if (controller.isSnakeTheOldest() || controller.didSnakeDie()) {
					controller.reset();
					// o day phai tao ra tieu tien trinh neu khong khi an nut
					// new no se bi dung vi hanh dong cua nut new van dang tiep
					// dien
					Runnable run = new Runnable() {
						@Override
						public void run() {
							controller.ranHoatDong();
						}

					};
					Thread t = new Thread(run);// chay song song voi nhau
					t.start();
				} else {
					controller.reset();
				}

			}
		});
		menuItemForHelp = new JMenuItem("<html>"
				+ "<span style='font-size:15.0pt;color:black'>" + "Help"
				+ "</html>");
		menuForOption.add(menuItemForHelp);
		menuItemForHelp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.help();
			}

		});
		frame.getContentPane().add(memuBar, BorderLayout.NORTH);

		JPanel panelForButton = new JPanel();
		panelForButton.setBackground(Color.BLUE);
		panelForButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonToStartOrCancle = new JButton("Start");
		buttonToStartOrCancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.startOrCancle();
			}

		});
		panelForButton.add(buttonToStartOrCancle);
		buttonToPauseOrResume = new JButton("Pause");
		buttonToPauseOrResume.setEnabled(false);
		buttonToPauseOrResume.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.pauseOrResume();
			}

		});
		panelForButton.add(buttonToPauseOrResume);

		JPanel panelForLable = new JPanel();
		panelForLable.setBackground(Color.WHITE);
		panelForLable.setLayout(new GridLayout(2, 2));
		lableForMark = new JLabel("Mark: 0");
		panelForLable.add(lableForMark);
		lableForLife = new JLabel("Lives: 0");
		panelForLable.add(lableForLife);
		lableForLenght = new JLabel("Lenght: 0");
		panelForLable.add(lableForLenght);
		lableForLevel = new JLabel("Level: 0");
		panelForLable.add(lableForLevel);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(panelForLable, BorderLayout.NORTH);
		panel1.add(panelForButton, BorderLayout.SOUTH);

		frame.getContentPane().add(panel1, BorderLayout.SOUTH);

		panel = new SnakePanel();
		panel.setBackground(Color.BLUE);
		// phai goi controller.setFirstState ngay khi tao panel, new khong khi
		// thanh phan panel duoc ve no se bi loi nullPointerException vi
		// food.location = null
		controller.setFirstState();

		frame.getContentPane().add(panel, BorderLayout.CENTER);

		frame.setSize(controller.getModel().getWeightOfPanel(), controller
				.getModel().getHeightOfPanel());
		Dimension sizeOfFrame = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - sizeOfFrame.width) / 2;
		int y = ((screenSize.height - sizeOfFrame.height) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);

		// cho 1 tien trinh ranHoatDong() chay, luc nay flag = false nen ran
		// chua chay, cho nguoi choi an start
		controller.ranHoatDong();

	}

	public String getTextOfButtonToStartOrCancle() {
		return buttonToStartOrCancle.getText();
	}

	public String getTextOfButtonToPauseOrResume() {
		return buttonToPauseOrResume.getText();
	}

	public void start() {
		panel.setFocusable(true);
		buttonToStartOrCancle.setText("Cancle");
		buttonToStartOrCancle.setFocusable(false);
		buttonToPauseOrResume.setEnabled(true);
	}

	public void cancle() {
		frame.dispose();
	}

	public void pause() {
		panel.setFocusable(true);
		buttonToPauseOrResume.setText("Resume");
		buttonToPauseOrResume.setFocusable(false);
	}

	public void resume() {
		panel.setFocusable(true);
		buttonToPauseOrResume.setText("Pause");
		buttonToPauseOrResume.setFocusable(false);
	}

	public void reset() {
		buttonToStartOrCancle.setText("Start");
		buttonToPauseOrResume.setText("Pause");
		buttonToPauseOrResume.setEnabled(false);
	}

	public void disablePause() {
		if (buttonToPauseOrResume.isEnabled()) {
			buttonToPauseOrResume.setEnabled(false);
		} else {

		}
	}

	public void repaintPanel() {
		panel.repaint();
	}

	@Override
	public void updateMark(int mark) {
		String s = "Mark: ";
		s += mark;
		lableForMark.setText(s);
	}

	@Override
	public void updateLife(int lifeOfSnake) {
		String s = "Life: ";
		s += lifeOfSnake;
		lableForLife.setText(s);

	}

	@Override
	public void updateLevel(int levelOfSnake) {
		String s = "Level: ";
		s += levelOfSnake;
		s += "/";
		s += controller.getModel().getLastLevelOfSnake();
		lableForLevel.setText(s);

	}

	@Override
	public void updateLenght(int lenghtOfSnake) {
		String s = "Lenght: ";
		s += lenghtOfSnake;
		s += "/";
		s += controller.getModel().getLastLenghtOfSnake();
		lableForLenght.setText(s);
	}

	private class SnakePanel extends JPanel {

		public SnakePanel() {
			super();
			addKeyListener(new keyHandler());
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			controller.paintPanel(g);
		}

		private class keyHandler implements KeyListener {

			public void keyPressed(KeyEvent arg0) {
				int keyCode = arg0.getKeyCode();
				if (keyCode == KeyEvent.VK_LEFT) {
					// System.out.println("l");
					if (buttonToPauseOrResume.getText().equals("Resume")
							|| buttonToStartOrCancle.getText().equals("Start")) {

					} else {
						controller.caiDatHuongDiChuyen("left");
					}
				} else if (keyCode == KeyEvent.VK_RIGHT) {
					// System.out.println("r");
					if (buttonToPauseOrResume.getText().equals("Resume")
							|| buttonToStartOrCancle.getText().equals("Start")) {

					} else {
						controller.caiDatHuongDiChuyen("right");
					}
				} else if (keyCode == KeyEvent.VK_UP) {
					// System.out.println("u");
					if (buttonToPauseOrResume.getText().equals("Resume")
							|| buttonToStartOrCancle.getText().equals("Start")) {

					} else {
						controller.caiDatHuongDiChuyen("up");
					}
				} else if (keyCode == KeyEvent.VK_DOWN) {
					// System.out.println("d");
					if (buttonToPauseOrResume.getText().equals("Resume")
							|| buttonToStartOrCancle.getText().equals("Start")) {

					} else {
						controller.caiDatHuongDiChuyen("down");
					}
				}
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent arg0) {
			}

		}
	}

}
