import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class test {
	public static void main(String[] args) {
		// �ǵøĵ�ַ
		long startTime = System.currentTimeMillis();
		String filename="E:\\eclipse-workspace\\data struction\\src\\poet.song.5000.json";
		 ArrayList<Map> poet= new ArrayList<Map>();
	        try {
	            File jsonFile = new File(filename);
	            FileReader fileReader = new FileReader(jsonFile);
	            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
	            int ch = 0;
	            StringBuffer sb = new StringBuffer();
                int last_ch=0;
                ch = reader.read();
                last_ch=ch;
                //����ѭ������ѭ���ǰ�һ���ļ����������Сѭ���Ƕ���һ��ʫ����
                while((ch = reader.read())!=-1) {
                Map m1 = new HashMap(); 
                Boolean a_poet_end=false;
	            while ((ch = reader.read())!=-1&&a_poet_end==false) {
	            	//�����ߣ�map��keyΪ0
	            	if((char)ch=='r'&&(char)last_ch=='o') {
	            		ch = reader.read();ch = reader.read();ch = reader.read();ch = reader.read();
	            		 while((char)(ch = reader.read())!='"') {
	            			 sb.append((char) ch);
	            		 }
	            		 String author=sb.toString();
	            		 m1.put(0, author);
	            		 sb.delete(0,sb.length());
	            		 last_ch=ch;
	            		 ch = reader.read();
	            	}
	            	//��ʫ��map��keyΪ2
	            	if((char)ch=='s'&&(char)last_ch=='h') {
	            		Boolean end=false;
	            		while(end==false&&(char)(ch = reader.read())!=']') {
	            		while(end==false&&(char)(ch = reader.read())!='"') {if((char)ch==']') {end=true;}}
	            		while(end==false&&(char)(ch = reader.read())!='"') {
	            			if((char)ch==']') {end=true;}
	            			sb.append((char) ch);
	            		}
	            		}	
	            		String body=sb.toString();
	            		m1.put(2, body);
	            		sb.delete(0,sb.length());
	            		 last_ch=ch;
	            		 ch = reader.read();
	            	}
	            	//����⣬map��keyΪ1
	            	if((char)ch=='e'&&(char)last_ch=='l') {
	            		ch = reader.read();ch = reader.read();ch = reader.read();ch = reader.read();
	            		 while((char)(ch = reader.read())!='"') {
	            			 sb.append((char) ch);
	            		 }
	            		 String title=sb.toString();
	            		 m1.put(1, title);
	            		 poet.add(m1);
	            		 sb.delete(0,sb.length());
	            		 last_ch=ch;
	            		 ch = reader.read();
	            		 a_poet_end=true;
	            	}
	            last_ch=ch;
	            }
	            }
	            fileReader.close();
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        //poet��array list,ÿ��Ԫ����map��
	    	for(int i=0;i<poet.size();i++) {
	    		System.out.printf("%d %s ",i,poet.get(i).get(0));
	    		System.out.println(poet.get(i).get(1));
	    		System.out.println(poet.get(i).get(2));
	    		
	    	}
	    	long endTime   = System.currentTimeMillis(); //���������¼ʱ��
    		long TotalTime = endTime - startTime;
    		TotalTime=TotalTime;
    		System.out.print(TotalTime);
	}


}
