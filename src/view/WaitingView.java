package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class WaitingView {
	JFrame frame;
	JLabel label;
	JPanel panel;
	Controller controller;

	public WaitingView(Controller controller) {
		this.controller = controller;
	}

	public void createView() {
		frame = new JFrame();
		frame.setTitle("Waiting");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 200);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		label = new JLabel();
		label.setText("<html>" + "<span style='font-size:25.0pt;color:blue'>"
				+ " next level" + "</html>");
		panel.setBackground(Color.WHITE);
		panel.add(label);
		frame.getContentPane().add(panel);
		frame.setEnabled(true);
		Dimension sizeOfFrame = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - sizeOfFrame.width) / 2;
		int y = ((screenSize.height - sizeOfFrame.height) / 2 - 120);
		frame.setLocation(x, y);
		frame.setVisible(true);
		waitAndClose();
	}

	public void waitAndClose() {
		Runnable run = new Runnable() {

			public void run() {
				try {
					controller.getModel().setFlag(false);
					for (int i = 3; i > 0; i--) {
						String a = String.valueOf(i);
						Thread.sleep(1000);
						label.setText("<html>"
								+ "<span style='font-size:80.0pt;color:blue'>"
								+ a + "</html>");
					}
					Thread.sleep(1000);
					frame.dispose();
					controller.getModel().setFlag(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
		Thread t = new Thread(run);
		t.start();

	}

}
