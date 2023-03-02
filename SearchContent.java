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

public class SearchContent {
	static ArrayList<ArrayList<Map>> allfiles=new ArrayList<ArrayList<Map>>();
	public static void main(String[] args) {
		try{String a="����������";
		ArrayList<Map> sear=search_and(a);
		
		for(int i=0;i<sear.size();i++) {
    		System.out.printf("%d %s ",i,sear.get(i).get(0));
    		System.out.println(sear.get(i).get(1));
    		System.out.println(sear.get(i).get(2));	
    	}
		/*a="��ɪ�޶���ʮ�� һ��һ��˼����";
		sear=search_or(a);
		for(int i=0;i<sear.size();i++) {
    		System.out.printf("%d %s ",i,sear.get(i).get(0));
    		System.out.println(sear.get(i).get(1));
    		System.out.println(sear.get(i).get(2));	
    	}*/
		}
		catch(Exception E) {}
	}
	
	//strΪ������ϵ�Ĵ��������ÿո�ֿ�
	public static ArrayList<Map> search_or (String str) throws Exception {
		 String[] keywords=str.split(" ");
		 ArrayList<Map> re=new ArrayList<Map>();
		 ArrayList<Map> result=new ArrayList<Map>();
		 result=search_one_words(keywords[0]);
		 for(int i=1;i<keywords.length;i++) {
			 ArrayList<Map> next=search_one_words(keywords[i]);
			 result.addAll(next);
		 }
		 for(int j=0;j<result.size();j++) {
			 int same=0;
			 for(int m=j+1;m<result.size();m++) {
				 String a=result.get(j).get(2).toString();
				 String b=result.get(m).get(2).toString();
				 if(a.equals(b)) {
					 same=1;
				 }
			 }
			 if(same==0) {
				 re.add(result.get(j));
			 }
		 }
	 if(re.isEmpty()) {
		 Map m1 = new HashMap(); 
		 m1.put(0, "no find");
		 re.add(m1);
	 }
		 return re;
	}
	
//strΪ���벢��ϵ�Ĵ��������ÿո�ֿ�
	public static ArrayList<Map> search_and (String str) throws Exception {
		 String[] keywords=str.split(" ");
		 ArrayList<Map> result=new ArrayList<Map>();
		 result=search_one_words(keywords[0]);
		 for(int i=1;i<keywords.length;i++) {
			  result=search_second(result,keywords[i]);
		 }	
		 if(result.isEmpty()) {
			 Map m1 = new HashMap(); 
			 m1.put(0, "no find");
			 result.add(m1);
		 }
		return result;
	}
	
	//������һ��string
	public static ArrayList<Map> search_one_words (String str) throws Exception {
		char[]word=str.toCharArray();
		ArrayList<Map> searched= new ArrayList<Map>();
		allfiles = createAll("E:\\dsaa\\chinese-poetry-zhCN-master\\chinese-poetry-zhCN-master\\poetry");
		for(int i=0;i<allfiles.size();i++) {
		ArrayList<Map> poet=allfiles.get(i);
		for(int j=0;j<poet.size();j++) {
			char[] body=(poet.get(j).get(2).toString()).toCharArray();
			for(int k=0;k<body.length;k++) {
				for(int m=0;m<word.length;m++) {
			if(word[m]==body[k]) {
				if(k<body.length-1) {k++;}
				else {break;}
			    if(m==word.length-1) {			    	
				searched.add(poet.get(j));
			}					    	
			}
			else{k=k-m;
			     break;
				}
			}
		}
	}}
	return searched;
	}
	//�ڵ�һ�������Ļ�����������
	public static ArrayList<Map> search_second (ArrayList<Map>before,String str) throws Exception {
		char[]word=str.toCharArray();
		ArrayList<Map> searched= new ArrayList<Map>();	
		for(int j=0;j<before.size();j++) {
			char[] body=(before.get(j).get(2).toString()).toCharArray();
			for(int k=0;k<body.length;k++) {
				for(int m=0;m<word.length;m++) {
			if(word[m]==body[k]) {
				if(k<body.length-1) {k++;}
				else {break;}
			    if(m==word.length-1) {			    	
				searched.add(before.get(j));
			}					    	
			}
			else{k=k-m;
			     break;
				}
			
		}
	}}
	return searched;
	}
	
	
	
	
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
