package chart_command_line;
import chart_command_line.mathPostfixNotation;

import Chart_Components.Chart;
import java.util.Scanner;
import java.util.Stack;
import java.util.EmptyStackException;

public class math {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Enter formula");
		mathPostfixNotation calc = new mathPostfixNotation();
		while (true){
			String mathFormula = myObj.nextLine();  // Read user input
			if (mathFormula.equals("exit"))
				return;
			if (mathFormula.equals("clear"))
				calc.clearStack();
			String[] splited = mathFormula.split("\\s+");
			for (String line : splited){
				try {
					calc.sendCommand(line);
				} catch (EmptyStackException ex){
					System.out.println(ex.getMessage());
					System.out.println("No more stack");
					break;
				}
			}
			System.out.print(calc.getChart());
			for (Double stackItem : calc.getStack()){
				System.out.println(stackItem);
			}
		}
	}

}
