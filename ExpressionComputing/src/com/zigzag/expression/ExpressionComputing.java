package com.zigzag.expression;

import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class ExpressionComputing {
	
	
	public static void main(String[] args) {
		expression a = new expression();
		final Scanner in = new Scanner(System.in);		
		String input = in.nextLine();
		input = input.replace(" ", "");
		input = input.replace("\t", "");
		input = input.replace("-", "+-1*");
		if (input.charAt(0) == '+') {
		    input = input.substring(1);
		}
		if (a.set(input)){
			a.write();
			while (in.hasNextLine()) {
				input = in.nextLine();
				if (input.charAt(0) == '!') {
					String[] op = input.split(" ");
					if (op[0].equals("!simplify")) {
						expression b = new expression();
	                    for(int i=0;i<a.num;i++)
	                    {
	                      b.var[i] = new HashMap<String, Integer>();
	                      b.var[i].putAll(a.var[i]);
	                    }
	                    for(int i=0;i<a.coe.length;i++)
	                    {
	                      b.coe[i]=a.coe[i];
	                    }
	                    b.num=a.num;
						b.simplify(op);
					} else if (op[0].equals("!d/d") && op.length == 2) {
						expression b = new expression();
	                    for(int i=0;i<a.num;i++)
	                    {
	                      b.var[i] = new HashMap<String, Integer>();
	                      b.var[i].putAll(a.var[i]);
	                    }
	                    for(int i=0;i<a.coe.length;i++)
	                    {
	                      b.coe[i]=a.coe[i];
	                    }
	                    b.num=a.num;
						b.derivative(op[1]);
					} else {
						System.out.println("Input Error !");
					}
				
					
				} else {
					System.out.println("Input Error !");		
				}
			}
		}
		else{
			System.out.println("Input Error !");
		
		}
	}
}
