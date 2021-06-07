package controller;



import model.Model;
import controller.Controller;

public class SnakeApplication {
	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller(model);
	}
}
