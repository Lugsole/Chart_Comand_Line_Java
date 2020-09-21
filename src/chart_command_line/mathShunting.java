package chart_command_line;

import Chart_Components.Chart;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.EmptyStackException;
import chart_command_line.mathPostfixNotation;

public class mathShunting {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Enter formula");
		String mathFormula = myObj.nextLine();  // Read user input
		String[] splited = mathFormula.split("\\s+");
		Stack<String> operators = new Stack<String>();
		Queue<String> output = new LinkedList<>();
		int place = 0;
		mathPostfixNotation calc = new mathPostfixNotation();
		while (place < splited.length) {
			boolean isFloat = false;
			String token = splited[place];
			try{
				Float.parseFloat(token);
				isFloat = true;
			}catch(NumberFormatException e){
				//not float
			}

			if ( isFloat ){
				output.add(token);
			} else if (priority(token) != -1) {
				while (!token.equals("(") &&
						!operators.empty() &&
						(priority(operators.peek()) > priority(token) || 
						(priority(operators.peek()) == priority(token) &&
						isLeft(token))) && 
						!operators.peek().equals('(')) {
					output.add(operators.pop());
				}
				operators.push(token);
			} else if (token.equals("(")){
				operators.push(token);
			} else if (token.equals(")")) {
				while (!operators.peek().equals("("))
					output.add(operators.pop());
				if (operators.peek().equals("("))
					operators.pop();
			} else {
				if ( token.equals("PI") || token.equals("E"))
					output.add(token);
				else
					operators.push(token);
			}
			System.out.printf("%-10s %-20s %-20s\n",token, output.toString(), operators.toString());
			place++;
		}
		while (!operators.empty()){
			output.add(operators.pop());
		}
		for (String token :output){
			System.out.print(token);
			System.out.print(" ");
		}
		System.out.println();
		for (String line : output){
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
		System.out.println();
	}
	private static int priority(String operator){
		//if(operator.equals("(") | operator.equals(")") | operator.equals("[") | operator.equals("]"))
		//	return 2;
		//else 
		if(operator.equals("+") | operator.equals("-"))
			return 3;
		else if(operator.equals("*") | operator.equals("/") | operator.equals("%"))
			return 4;
		else if(operator.equals("^"))
			return 5;
		else
			return -1;
	}
	private static boolean isLeft(String operator){
		//if(operator.equals("(") | operator.equals(")") | operator.equals("[") | operator.equals("]"))
		//	return 2;
		//else 
		if(operator.equals("+") | operator.equals("-"))
			return true;
		else if(operator.equals("*") | operator.equals("/") | operator.equals("%"))
			return true;
		else if(operator.equals("^"))
			return false;
		else
			return true;
	}

}
