package pro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lab1.fileList;

public class test2 {
	   
	
	
	public static ArrayList<ArrayList<Map>> createAll(String Folder) throws Exception{ 
	    File file = new File(Folder);//�м�����Ҫ�򿪵��ļ���
	    fileList fl  = new fileList();//ֱ��дһ�������ˣ����� 
	    fl.Dir(file); 
	    ArrayList<ArrayList<Map>> files=new ArrayList<ArrayList<Map>>();
	    //����������̱ȽϾ�
	    for(String s:fl.fStrArr) {
	    	String filename=s;
			ArrayList<Map> poet=create(filename);
			files.add(poet);
	    }
	    return files;
	    /*for(int i=0;i<files.size();i++) {
	    	ArrayList<Map> poet=files.get(i);
			for(int j=0;j<poet.size();j++) {
	    		System.out.printf("%d %s ",j,poet.get(j).get(0));
	    		System.out.println(poet.get(j).get(1));
	    		System.out.println(poet.get(j).get(2));
	    	}
	    }	*/
	}
	
	private static ArrayList<Map> create(String filename) {
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
        //poet��array list,ÿ��Ԫ����map
        return poet;
	}
}
