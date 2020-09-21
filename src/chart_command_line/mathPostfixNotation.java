package chart_command_line;

import Chart_Components.Chart;
import java.util.Scanner;
import java.util.Stack;
import java.util.EmptyStackException;
import java.lang.Math;

public class mathPostfixNotation {
	Stack<Double> s = new Stack<Double>();
	Stack<Chart> c = new Stack<Chart>();

	public void sendCommand(String command) {
		Chart new_box;
		Double a;
		Double b;
		Chart chart_a;
		switch(command) 
		{ 
		case "+":
			if(!(s.size() >= 2))
				throw new EmptyStackException();
			a = s.pop();
			b = s.pop();
			s.push(Double.valueOf(b + a));
			new_box = new Chart(command);
			chart_a = c.pop();
			new_box.add(c.pop());
			new_box.add(chart_a);
			c.push(new_box);
			break;
		case "-":
			if(!(s.size() >= 2))
				throw new EmptyStackException();
			a = s.pop();
			b = s.pop();
			s.push(Double.valueOf(b - a));
			new_box = new Chart(command);
			chart_a = c.pop();
			new_box.add(c.pop());
			new_box.add(chart_a);
			c.push(new_box);
			break;
		case "*":
			if(!(s.size() >= 2))
				throw new EmptyStackException();
			a = s.pop();
			b = s.pop();
			s.push(Double.valueOf(b * a));
			new_box = new Chart(command);
			chart_a = c.pop();
			new_box.add(c.pop());
			new_box.add(chart_a);
			c.push(new_box);
			break;
		case "/":
			if(!(s.size() >= 2))
				throw new EmptyStackException();
			a = s.pop();
			b = s.pop();
			s.push(Double.valueOf(b / a));
			new_box = new Chart(command);
			chart_a = c.pop();
			new_box.add(c.pop());
			new_box.add(chart_a);
			c.push(new_box);
			break;
		case "^":
			if(!(s.size() >= 2))
				throw new EmptyStackException();
			a = s.pop();
			b = s.pop();
			s.push(Double.valueOf(Math.pow(b, a)));
			new_box = new Chart(command);
			chart_a = c.pop();
			new_box.add(c.pop());
			new_box.add(chart_a);
			c.push(new_box);
			break;
		case "log":
			a = s.pop();
			s.push(Double.valueOf(Math.log10(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "lg":
			a = s.pop();
			s.push(Double.valueOf(Math.log10(a)/Math.log10(2)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "ln":
			a = s.pop();
			s.push(Double.valueOf(Math.log(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "sin":
			a = s.pop();
			s.push(Double.valueOf(Math.sin(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "cos":
			a = s.pop();
			s.push(Double.valueOf(Math.cos(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "tan":
			a = s.pop();
			s.push(Double.valueOf(Math.tan(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "asin":
			a = s.pop();
			s.push(Double.valueOf(Math.asin(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "acos":
			a = s.pop();
			s.push(Double.valueOf(Math.acos(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "atan":
			a = s.pop();
			s.push(Double.valueOf(Math.atan(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "abs":
			a = s.pop();
			s.push(Double.valueOf(Math.abs(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "ceil":
			a = s.pop();
			s.push(Double.valueOf(Math.ceil(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "floor":
			a = s.pop();
			s.push(Double.valueOf(Math.floor(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "sqrt":
			a = s.pop();
			s.push(Double.valueOf(Math.sqrt(a)));
			new_box = new Chart(command);
			new_box.add(c.pop());
			c.push(new_box);
			break;
		case "E":
			s.push(Math.E);
			new_box = new Chart(command);
			c.push(new_box);
			break;
		case "PI":
			s.push(Math.PI);
			new_box = new Chart(command);
			c.push(new_box);
		default:
			try {
				s.push(Double.valueOf(Double.parseDouble(command)));
				c.push(new Chart(command));
			}catch(NumberFormatException ex){}
		}
	}

	public Double[] getStack(){
		Double[] ret = new Double[s.size()];
		s.toArray(ret);
		return ret;
	}
	public String getChart(){
		String charts = "";
		for(Chart item : c) {
			charts += item;
		}
		return charts;
	}
	public void clearStack(){	
		s = new Stack<Double>();
		c = new Stack<Chart>();
	}
}
