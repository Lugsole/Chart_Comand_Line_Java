package chart_command_line;

import Chart_Components.Chart;
import java.util.Scanner;
import java.util.Stack;
public class math {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Enter formula");
		String mathFormula = myObj.nextLine();  // Read user input
		String[] splited = mathFormula.split("\\s+");
		Stack<Double> s = new Stack<Double>();
		Stack<Chart> c = new Stack<Chart>();
		for(String command : splited) {
			if (command.equals("")){
			}else if (command.equals("+")) {
				Double a = s.pop();
				Double b = s.pop();
				s.push(Double.valueOf(b + a));
				Chart new_box = new Chart(command);
				Chart chart_a = c.pop();
				new_box.add(c.pop());
				new_box.add(chart_a);
				c.push(new_box);
			} else if( command.equals("-")){
				Double a = s.pop();
				Double b = s.pop();
				s.push(Double.valueOf(b - a));
				Chart new_box = new Chart(command);
				Chart chart_a = c.pop();
				new_box.add(c.pop());
				new_box.add(chart_a);
				c.push(new_box);
			} else if (command.equals("*")) {
				Double a = s.pop();
				Double b = s.pop();
				s.push(Double.valueOf(b * a));
				Chart new_box = new Chart(command);
				Chart chart_a = c.pop();
				new_box.add(c.pop());
				new_box.add(chart_a);
				c.push(new_box);
			} else if (command.equals("/")) {
				Double a = s.pop();
				Double b = s.pop();
				s.push(Double.valueOf(b / a));
				Chart new_box = new Chart(command);
				Chart chart_a = c.pop();
				new_box.add(c.pop());
				new_box.add(chart_a);
				c.push(new_box);
			} else {
				s.push(Double.valueOf(Double.parseDouble(command)));
				c.push(new Chart(command));
			}
		}
		while(!s.empty()){
			System.out.println(s.pop());
		}
		while(!c.empty()){
			System.out.print(c.pop());
		}
	}

}
