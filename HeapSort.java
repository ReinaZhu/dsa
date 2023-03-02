import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HeapSort {

	public static void main(String[] args) {
		String readpath="src\\numbs.txt";//�����ļ�·�������
		String writepath="src\\sorting.txt";//д���ļ�·�������
		int []number=read(readpath);
		LinkedList<String> writefile=new LinkedList<String>();
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
		writefile.add("Heap Sorting:") ;//��������Ҫ������ַ����浽writefile��
		if(number.length==1) {writefile.add("Sorting:["+String.valueOf(number[0])+"]");}
		else if(number.length==2) {
			if(number[0]>number[1]) {
				writefile.add("Sorting:["+String.valueOf(number[1])+", "+String.valueOf(number[0])+"]");
		}
			else {writefile.add("Sorting:["+String.valueOf(number[0])+", "+String.valueOf(number[1])+"]");
			}}
		//����ֻ��һ�����������ֵ�������ٷ��������������ϵ����
		else {	
			number=deletSame(samenumber,number);
		int [][]heap=CreatHeap(number);//�ö�ά�����������һ����һ��node����һ���Ǹ��ף��ڶ��к͵����������ҵĺ��ӣ�-1��ʾnull
		String result=""; 
	while(heap[0][2]!=-1) {
	heap=maxHeap(heap);//���ֵ����root
	int lastrow=heap.length;
	int max=heap[0][0];
	for(int i=0;i<heap.length;i++) {//�õ����������½ǵ�λ�ã�����max��֮����
		if(heap[i][1]==-1) {
			lastrow=i;
			break; 
		}
	}
	if(heap[lastrow-1][2]!=-1) {
		heap[0][0]=heap[lastrow-1][2];
		heap[lastrow-1][2]=-1;
	}
	else if(heap[lastrow-1][2]==-1) {
		heap[0][0]=heap[lastrow-1][1];
		heap[lastrow-1][1]=-1;
		heap[lastrow-1][0]=-1;
	}
	if(result.equals("")) {
		result=String.valueOf(max);
	}else {
	result=String.valueOf(max)+", "+result;}
	String here=show(heap)+result;
	here= ChangeSameNumber(same,here);
	writefile.add("Sorting:["+here+"]");//���ôβ������д���ַ�����
	}
	if(heap[0][0]>heap[0][1]) {
		result=String.valueOf(heap[0][1])+", "+String.valueOf(heap[0][0])+", "+result;
	}
	else {
		result=String.valueOf(heap[0][0])+", "+String.valueOf(heap[0][1])+", "+result;
	}
	result= ChangeSameNumber(same,result);
	writefile.add("Sorting:["+result+"]");
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
	public static int [][] CreatHeap(int [] number){//����heap
		int row=0;
		int num=1;
		while(number.length>=num) {
			num*=2;
			row++;
		}
		if(number.length==1) {row=1;}
		else {row--;}
	    row=(int)Math.pow(2,row)-1;
		int[][] heap=new int[row][3];
		if(number.length==1) {heap[0][0]=number[0];}
		else{
			if(number.length==2) {
				heap[0][0]=number[0];
				heap[0][1]=number[1];
			}
			else if(number.length==3) {
				heap[0][0]=number[0];
				heap[0][1]=number[1];
				heap[0][2]=number[2];
			}
			else {
			for(int i=0;i<row;i++) {
				int k=i+1;
			if(k*2+1<=number.length) {
				heap[i][0]=number[i];
				heap[i][1]=number[2*k-1];
				heap[i][2]=number[2*k];
			}
			else if(2*k<=number.length) {
				heap[i][0]=number[i];
				heap[i][1]=number[2*k-1];
				heap[i][2]=-1;
			}
			else {
				heap[i][0]=number[i];
				heap[i][1]=-1;
				heap[i][2]=-1;
			}
			
			}		
		}
			
		}
		
		if((number.length%2)==0) {
			heap[heap.length-1][2]=-1;
		}
		
	return heap;}
	
	public static int[][]change_one(int[][] heap,int fa,int son,int i) {//����ĳ����node��λ��
		for(int j=0;j<i;j++) {
			if(heap[j][1]==fa) {
				heap[j][1]=son;
			}
			if(heap[j][2]==fa) {
				heap[j][2]=son;
			}
		}
		for(int m=i+1;m<heap.length;m++) {
			if(heap[m][0]==son) {
				heap[m][0]=fa;
			}
		}
		return heap;
	}
	
	public static int[][] changemax(int[][]heap,int fa,int i,int j){//����nodeʹ������к�����ֵ����
		heap[i][0]=heap[i][j];
		heap[i][j]=fa;
		heap=change_one(heap,fa,heap[i][0],i);
		for(int m=i+1;m<heap.length;m++) {
			if(heap[m][0]==fa) {
				if(fa<heap[m][1]) {
					heap=changemax(heap,fa,m,1);
				}
				else if(fa<heap[m][2]) {
					heap=changemax(heap,fa,m,2);
				}
			}
		}
		
		return heap;
	}
	
	public static int[][] maxHeap(int[][]heap){//�õ�root�����ֵ��heap
		for(int i=heap.length-1;i>=0;i--) {
			if(heap[i][0]<heap[i][1]) {
				heap=changemax(heap,heap[i][0],i,1);
			}
			if(heap[i][0]<heap[i][2]){
				heap=changemax(heap,heap[i][0],i,2);
			}
		}
		
		return heap;
	}
	
	public static String show(int[][]heap) {//��heap���д�ӡ��
		String order = "";
		Queue<Integer> que = new LinkedList<Integer>();
		que.offer(heap[0][0]);
		while(!que.isEmpty()) {
			int here=que.peek();
			order=order+here+", ";
			que.poll();
			for(int k=0;k<heap.length;k++) {
			if(heap[k][0]==here) {
				if(heap[k][1]!=-1) {
				que.offer(heap[k][1]);}
				if(heap[k][2]!=-1) {
				que.offer(heap[k][2]);}
			}
			}
		}
		return order;
		}
	public static LinkedList<Integer[]> findsamenumber(int[]number){//�ҵ���ͬ������
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
	public static String ChangeSameNumber(String[][]same,String write) {//�������ַ�������ʾ�����ͬ������
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
	public static int[] deletSame(LinkedList<Integer[]>samenumber,int []number) {//��ͬ�����ֽ������ɾ��
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
