package pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		String a="һ&�㡢��|ѽ";
		 ArrayList<Map> b=and_or(a);
		 System.out.print(b);
	} 
	public static ArrayList<Map> and_or(String strinput) {
		//map0��ʾ��ϵ����Ϊ&����Ϊ|,1�������Ĵ�
		ArrayList<Map> searchkey=new ArrayList<Map>();
		char[] input=strinput.toCharArray();
		int countstr=0;
		int countchar=0;
		for(int i=0;i<input.length;i++) {
			if(input[i]=='&'||input[i]=='|') {
				 Map m1 = new HashMap(); 
				 if(countstr==0) {
					 m1.put(0,'&');
				 }
				 else {
				 m1.put(0,input[countchar-1]);}
				 String key="";
				for(int j=countchar;j<i;j++) {				
					key+=input[j];
				}
				m1.put(1, key);
				searchkey.add(m1);
					countstr++;
					countchar=i+1;
			}
		}
		Map m1 = new HashMap(); 
		m1.put(0,input[countchar-1]);
		String key="";
		for(int j=countchar;j<input.length;j++) {				
			key+=input[j];
		}
		m1.put(1, key);
		searchkey.add(m1);
		return searchkey;
	}
	
	
}
