package com.zigzag.expression;
//233
import java.util.HashMap;
import java.util.Map;

public class expression{
	
	public static String input = new String();
	public static Map<String, Integer> var[]=new Map[1000];
	public static int[] coe = new int [1000];
	public static int num;
	
	public static void write(){
		coe[num]=0;boolean flag=false;
		for (int i=0;i<num;i++){
			if (num==1 && coe[i]==0) System.out.print("0");
			if (coe[i]==0) continue;
			if (flag && var[i].size()!=0 && coe[i]>0)
				System.out.print("+");
			
			if(var[i].size()!=0){
				flag=true;
				if (Math.abs(coe[i])!=1  || var[i].isEmpty()){
					System.out.print(coe[i]);
					if (!var[i].isEmpty()) System.out.print("*");
				}
				else if (coe[i]==-1)
					System.out.print("-");
			}
			else 
				coe[num]+=coe[i];
			int k=0;
			for (Map.Entry<String, Integer> m :var[i].entrySet())  { 
				if (m.getValue()>1)
					System.out.print(m.getKey()+"^"+m.getValue());
				else
					System.out.print(m.getKey());
				if (++k!=var[i].size())
					System.out.print("*");
	        } 
			//if(var[i].size()!=0)System.out.print("+");
		}
		
		if(coe[num]!=0){
			if(flag && coe[num]>0) System.out.print("+");
			System.out.print(coe[num]);
		}
		System.out.println();
	}
	
	private static int judge(String term){
		int now=0;
		for (int i=0;i<term.length();i++){
			if (i==0 && term.charAt(0)=='-'){
				now=1;
			}
			else if ((now==0 || now==1) && Character.isDigit(term.charAt(i))){
				now=1;
			}
			else if ((now==0 || now==2) && Character.isLowerCase(term.charAt(i))){
				now=2;
			}
			else return 0;
		}
		return now;
	}
	
	public static void simplify(String[] op){
		
		for (int i=1;i<op.length;i++){
			
			String tmp[]=op[i].split("=");
			if (tmp.length<2) {
				System.out.println("Input Error (Invalid input)!");
				return;
			}
			if (judge(tmp[0])==2 && judge(tmp[1])==1){
				boolean flag=false;
				
				for(int j=0;j<num;j++){
					
					if(var[j].containsKey(tmp[0])){
						flag=true;
						for(int k=1;k<=var[j].get(tmp[0]);k++)
							coe[j]*=Integer.parseInt(tmp[1]);
						var[j].remove(tmp[0]);
					}
				}
				if(!flag){
					System.out.println("Input Error (Variable not exist)!");
					return;
				}
			}
			else{
				System.out.println("Input Error (Invalid input)!");
				return;
			}
		}
		write();
		
	}
	
	public static void derivative(String dx){
		boolean flag=false;
		for(int i=0;i<num;i++){
			if(var[i].containsKey(dx)){
				flag=true;
				int m=var[i].get(dx);
				coe[i]*=m;
				var[i].remove(dx);
				if (m-1>0) var[i].put(dx, m-1);
			}
			else{
				coe[i]=0;
			}
		}
		if(!flag){
			System.out.println("Input Error (Variable not exist)!");
			return;
		}
		write();
	}
	
	public static boolean set(String st){
		st=st.replace(" ", "");
		st=st.replace("\t", "");
		st=st.replace("-","+-1*");
		if (st.charAt(0)=='+') st=st.substring(1);
		input=st;
		String poly[]=st.split("\\+");
		num=poly.length;
		for (int i=0;i<num;i++){
			var[i]=new HashMap<String, Integer>();
			coe[i]=1;
			String term[] = poly[i].split("\\*");
			
			for (int j=0;j<term.length;j++){
				int sign=judge(term[j]);
				if (sign==1){
					coe[i]*=Integer.parseInt(term[j]);
				}
				else if (sign==2){
					if (var[i].containsKey(term[j])){
						var[i].put(term[j], var[i].get(term[j])+1);
					}
					else{
						var[i].put(term[j], 1);
					}
				}
				else
					return false;
			}				
		}
		return true;
	}
	
}