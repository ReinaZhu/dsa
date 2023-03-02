import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Scanner;

public class BucketSort { 

	public static void main(String[] args) {
		String readpath="src\\numbs.txt";//�����ļ�·�������
		String writepath="src\\sorting.txt";//д���ļ�·�������
		int []number=read(readpath);
		LinkedList<String> writefile=new LinkedList<String>();
		writefile.add("Bucket Sorting:");//��������Ҫ������ַ����浽writefile��
		int []bucket=new int[101];	//��������Ϊ0��100�䣬����101��buckets
		for(int i=0;i<number.length;i++) {
			bucket[number[i]]++;//����ӦͰ�е����ּ�һ
			String line="";
			int bucket2[]=new int[101];
			for(int j=0;j<101;j++) {
				bucket2[j]=bucket[j];//����bucket[]��ֹ�ı�����ֵ
			}
			for(int k=0;k<bucket.length;k++) {
				if(bucket2[k]!=0) {
					if(line.equals("")) {
						line=String.valueOf(k);
					}
					else {line=line+", "+String.valueOf(k);}
				    bucket2[k]--;//�Է�ֹ�ж���ظ�����
				    k--;
				}				
			}
			writefile.add("Sorting:["+line+"]");//�浽д���ַ�����
			}//д���ļ�
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
