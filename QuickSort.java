import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Scanner;

public class QuickSort { 

	public static void main(String[] args) {
		String readpath="src\\numbs.txt";//�����ļ�·�������
		String writepath="src\\sorting.txt";//д���ļ�·�������
		int []number=read(readpath);
		LinkedList<Integer[]>samenumber=findsamenumber(number);//ȥ�����ظ������֣���¼����������ź����ټ���
		String[][] same=new String[samenumber.size()][2];
		if(!samenumber.isEmpty()) {
			for(int k=0;k<samenumber.size();k++) {
				same[k][0]=String.valueOf(samenumber.get(k)[0]);
				String thissame=String.valueOf(samenumber.get(k)[0]);
				for(int h=1;h<samenumber.get(k)[1];h++) {
				thissame=thissame+", "+String.valueOf(samenumber.get(k)[0]);}
				same[k][1]=thissame;
			}
		} 
		LinkedList<String> writefile=new LinkedList<String>();
		writefile.add("Quick Sorting:") ;//��������Ҫ������ַ����浽writefile��
		if(number.length==1) {writefile.add("Sorting:["+String.valueOf(number[0])+"]");}
		else if(number.length==2) {
			if(number[0]>number[1]) {
				writefile.add("Sorting:["+String.valueOf(number[1])+", "+String.valueOf(number[0])+"]");
		}
			else {writefile.add("Sorting:["+String.valueOf(number[0])+", "+String.valueOf(number[1])+"]");
			}} 
		else {	//����ֻ��һ�����������ֵ�������ٷ��������������ϵ����
			number=deletSame(samenumber,number);
			int i=number.length/2;//ѡ�����е����֣���������������Աȣ��õ�����������ң�����list
			LinkedList<LinkedList<Integer>>first= firstsorting(number,i);
			String aline="";//д����һ�β���
			for(int m=0;m<first.size();m++) {
				for(int n=0;n<first.get(m).size();n++) {
					if(n==0&&m==0) {
					aline=String.valueOf(first.get(m).get(n));}
					else {aline=aline+", "+String.valueOf(first.get(m).get(n));}
				}
			}
			aline= ChangeSameNumber(same,aline);
			writefile.add("Sorting:["+aline+"]");
			int all1=0;
			while(all1==0) {//�ظ�ѡ�񲢱Ƚϣ�ֱ��ÿ��list���涼ֻ��һ������
			for(int k=0;k<first.size();k++) {
				if(first.get(k).size()>1) {
					 LinkedList<LinkedList<Integer>> sorting=quicksorting(first.get(k));
					 first.remove(k);//��������ȴ���1��list������ѡ���������ң�����list��ListΪ�յ�ʱ����ӣ�
							 for(int j=sorting.size()-1;j>=0;j--) {
								 first.add(k,sorting.get(j));
							 }
				}
			}
			aline="";//�浽д���ַ�����
			for(int m=0;m<first.size();m++) {
				for(int n=0;n<first.get(m).size();n++) {
					if(n==0&&m==0) {
					aline=String.valueOf(first.get(m).get(n));}
					else {aline=aline+", "+String.valueOf(first.get(m).get(n));}
				}
			}
			aline= ChangeSameNumber(same,aline);
			writefile.add("Sorting:["+aline+"]");
			all1=1;
			for (int k=0;k<first.size();k++) {
				if(first.get(k).size()>1) {
					all1=0;
					break;
				}
			}
			}
			}
		write(writefile,writepath);
		

	}
	
	public static int[] read (String filename)  {//���벢��Ϊ����
		String str = "";
		try {
		Scanner scanner = new Scanner(System.in);
		BufferedReader bf = new BufferedReader(new FileReader(filename));
		String textLine;
		while ((textLine = bf.readLine()) != null) {
			str += textLine + "&";
		}
		bf.close();
		}	
		catch( IOException e) {}
		String[] numbs = str.split("&");
		int[] number = new int[numbs.length];
		for (int i = 0; i < numbs.length; i++) {
			number[i] = Integer.parseInt(numbs[i]);			
		}			
		return number;		
	}
	public static LinkedList<LinkedList<Integer>> firstsorting(int[] number,int i) {//��һ�β�������int[]��Ϊlist
		LinkedList<LinkedList<Integer>>result=new LinkedList<LinkedList<Integer>>();
		LinkedList<Integer>left=new LinkedList<Integer>();
		LinkedList<Integer>here=new LinkedList<Integer>();
		here.add(number[i]);
		LinkedList<Integer>right=new LinkedList<Integer>();
		for(int j=0;j<number.length;j++) {
			if(number[j]<number[i]) {
				left.add(number[j]);  
			} 
			if(number[j]>number[i]) {
				right.add(number[j]);
			}
		}
		if(!left.isEmpty()) {
		result.add(left);}
		result.add(here); 
		if(!right.isEmpty()) {	
		result.add(right);}
		return result;
	}
	public static LinkedList<LinkedList<Integer>> quicksorting(LinkedList<Integer> besort){//ÿ�ν�listѡ�����ֱȽϴ�С
		int r=besort.size()/2;
		LinkedList<LinkedList<Integer>>result=new LinkedList<LinkedList<Integer>>();
		LinkedList<Integer>left=new LinkedList<Integer>();
		LinkedList<Integer>here=new LinkedList<Integer>();
		here.add(besort.get(r));
		LinkedList<Integer>right=new LinkedList<Integer>();
		for(int j=0;j<besort.size();j++) {
			if(besort.get(j)<besort.get(r)) {
				left.add(besort.get(j));
			}
			if(besort.get(j)>besort.get(r)) {
				right.add(besort.get(j));
			}
		} 
		if(!left.isEmpty()) {	
			result.add(left);}
			result.add(here);
			if(!right.isEmpty()) {	
			result.add(right);}
		return result;
		
	}
	public static LinkedList<Integer[]> findsamenumber(int[]number){//�Է����ظ�����
		int[]number2=new int[number.length];
		for(int i=0;i<number.length;i++) {
			number2[i]=number[i];
		}
		LinkedList<Integer[]> count=new LinkedList<Integer[]>();
		for(int i=0;i<number.length;i++) {
			int acount=1;
			for(int k=i+1;k<number.length;k++) {
				if(number2[i]!=-1&&number2[i]==number[k]) {
					acount++;
					number2[k]=-1;
				}
			}
			if(acount>1) {
				Integer []one= {number[i],acount};
				count.add(one);
			}
		}
		return count;
	}
	public static String ChangeSameNumber(String[][]same,String write) {//�������ַ��������ɾ�����ظ�����
		String[]num=write.split(", ");
		for(int i=0;i<num.length;i++) {
			for(int k=0;k<same.length;k++) {
			if(num[i].equals(same[k][0])) {
				num[i]=same[k][1];
			}
			}
		}
		String result=num[0];
		for(int i=1;i<num.length;i++) {
			result=result+", "+num[i];
		}
		return result;
	}
	public static int[] deletSame(LinkedList<Integer[]>samenumber,int []number) {//ɾ���ظ�������
		int len=number.length;
		for(int k=0;k<samenumber.size();k++) {
			len=len-samenumber.get(k)[1]+1;
		}
		int[]newone=new int[len];
		int count=0;
		int []note=new int[samenumber.size()];
		for(int i=0;i<number.length;i++) {
			int no=0;
			for(int k=0;k<samenumber.size();k++) {
			if(number[i]==samenumber.get(k)[0]) {
				if(note[k]==0) {
					note[k]=1;}
				else {
				count++;
				no=1;}
			}     
			}
			if(no==0) {
				newone[i-count]=number[i];
			}		
		}
		return newone;
	}
	public static void write(LinkedList<String> writefile,String filename) {//д���ļ�
		try {
		File file =new File(filename);
		Writer out =new FileWriter(file);
		for(int i=0;i<writefile.size();i++) {
			String data=writefile.get(i);
			out.write(data);
			out.write("\n");
		}
		
		out.close();
		
		System.out.print("writing file successfully!");
	}
     catch( IOException e) {}

	}
}
