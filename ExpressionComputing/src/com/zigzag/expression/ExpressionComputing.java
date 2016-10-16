package com.zigzag.expression;
import java.util.*;



public class ExpressionComputing {
	
	public static void main(String []args) {
		expression a = new expression();
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()){
			String input=in.nextLine();
			if (input.charAt(0)=='!'){
				String op[] = input.split(" ");
				if (op[0].equals("!simplify")){
					expression b = new expression();
					b.set(a.input);
					b.simplify(op);
				//	b.write();
				}
				else if (op[0].equals("!d/d") && op.length==2){
					expression b=new expression();
					b.set(a.input);
					b.derivative(op[1]);
				}
				else
					System.out.println("Input Error !");
			}
			else{
				if (a.set(input))
					a.write();
				else
					System.out.println("Input Error !");
			}
		}
	}

}
