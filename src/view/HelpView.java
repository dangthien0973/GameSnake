package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Controller;

public class HelpView {
	JFrame frame;
	JTextArea textAreaForHelp;
	JButton closeButton;
	Controller controller;

	public HelpView(Controller controller) {
		super();
		this.controller = controller;
	}

	public void createView() {
		frame = new JFrame("Help");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		String help = "";
		help += "                       HƯỚNG DẪN\n\n";
		help += "  - Ấn các phím mũi tên để rắn di chuyển \n";
		help += "sang phải, sang trái hoặc di chuyển lên , xuống.\n";
		help += "  - Ăn thức ăn màu đỏ rắn dài ra và được cộng thêm 2 điểm.\n";
		help += "  - Ăn thức ăn màu đen dược cộng thêm 10 điểm.\n";
		help += "  - Ăn được 20 mồi thì tăng level.\n";
		help += "  - Đạt trên 100 thì được tăng thêm một mạng.\n";
		help += "  - Đụng vô tường hoặc vô chính mình thì rắn sẽ bị mất một mạng.\n\n\n\n";
		
		help += "                  ############################";

		textAreaForHelp = new JTextArea();
		textAreaForHelp.setBackground(Color.WHITE);
		textAreaForHelp.setEnabled(false);
		textAreaForHelp.setText(help);
		textAreaForHelp.setDisabledTextColor(Color.BLACK);
		frame.getContentPane().add(textAreaForHelp, BorderLayout.CENTER);

		JPanel panelForCloseButton = new JPanel();
		panelForCloseButton.setBackground(Color.BLUE);
		panelForCloseButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.closeHelpView();
			}

		});
		panelForCloseButton.add(closeButton);
		frame.getContentPane().add(panelForCloseButton, BorderLayout.SOUTH);

		frame.pack();
		Dimension sizeOfFrame = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - sizeOfFrame.width) / 2;
		int y = ((screenSize.height - sizeOfFrame.height) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}

	public void close() {
		frame.dispose();
	}
}
