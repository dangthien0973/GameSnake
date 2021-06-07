package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinView {
	JFrame frame;
	JLabel lable;

	public WinView() {
		super();
	}

	public void createView() {
		frame = new JFrame();
		frame.setTitle("Win");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		lable = new JLabel();
		lable.setText("<html>" + "<span style='font-size:25.0pt;color:blue'>" + "YOU WIN" + "</html>");
		panel.add(lable);
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		frame.setSize(210, 100);
		Dimension sizeOfFrame = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - sizeOfFrame.width) / 2;
		int y = ((screenSize.height - sizeOfFrame.height) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}

	public boolean isShowing() {
		if (frame == null) {
			return false;
		} else {
			return frame.isShowing();
		}
	}

	public void close() {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
			}

		};
		Thread t = new Thread(run);
		t.start();

	}
}
