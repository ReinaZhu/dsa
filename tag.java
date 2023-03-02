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

public class tag {
	
	public static void main(String[] args) throws Exception {
		String a="���";
		String b="����";	
		char []aa=a.toCharArray();
		char[] bb=b.toCharArray();
		ArrayList<Map> bie=search_words(aa,bb);//��ÿ��tag�ķ����ɴ�ӡ����
		for(int i=0;i<bie.size();i++) {
    		System.out.printf("%d %s ",i,bie.get(i).get(0));
    		System.out.println(bie.get(i).get(1));
    		System.out.println(bie.get(i).get(2));	
    	}
	}
	
	
	static ArrayList<ArrayList<Map>> allfiles=new ArrayList<ArrayList<Map>>();
	
	
	public static ArrayList<Map> bye () throws Exception {//�ͱ�ʫ
		ArrayList<Map> poet= search_words('��','��');
	return poet;
	}
	public static ArrayList<Map> china () throws Exception {//����ʫ
		ArrayList<Map> poet= search_words('��','־');
	return poet;
	}
	public static ArrayList<Map> home () throws Exception {//˼��ʫ
		ArrayList<Map> poet= search_words('��','��');
	return poet;
	}
	public static ArrayList<Map> side () throws Exception {//����ʫ
		ArrayList<Map> poet= search_words('��','Į');
	return poet;
	}
	public static ArrayList<Map> field () throws Exception {//��԰ʫ
		ArrayList<Map> poet= search_words('ũ','��');
	return poet;
	}
	public static ArrayList<Map> war () throws Exception {//ս��ʫ
		ArrayList<Map> poet= search_words('ս','��');
	return poet;
	}
	public static ArrayList<Map> lv () throws Exception {//��ʫ
		ArrayList<Map> search_poet= new ArrayList<Map>();
		allfiles = createAll("E:\\dsaa\\chinese-poetry-zhCN-master\\chinese-poetry-zhCN-master\\poetry");
		for(int i=0;i<allfiles.size();i++) {
	    	ArrayList<Map> poet=allfiles.get(i);
	    	for(int j=0;j<poet.size();j++) {
	    		char[] body=(poet.get(j).get(2).toString()).toCharArray();
	    		int comma=0;
	    		for(int k=0;k<body.length;k++) {
	    			if(body[k]=='��') {
	    				comma++;
	    			}
	    		}
	    		if(comma==4) {
	    			search_poet.add(poet.get(j));
	    		}
	    	}}
	return search_poet;
	}
	public static ArrayList<Map> jue () throws Exception {//����
		ArrayList<Map> search_poet= new ArrayList<Map>();
		allfiles = createAll("E:\\dsaa\\chinese-poetry-zhCN-master\\chinese-poetry-zhCN-master\\poetry");
		for(int i=0;i<allfiles.size();i++) {
	    	ArrayList<Map> poet=allfiles.get(i);
	    	for(int j=0;j<poet.size();j++) {
	    		char[] body=(poet.get(j).get(2).toString()).toCharArray();
	    		int comma=0;
	    		for(int k=0;k<body.length;k++) {
	    			if(body[k]=='��') {
	    				comma++;
	    			}
	    		}
	    		if(comma==2) {
	    			search_poet.add(poet.get(j));
	    		}
	    	}}
	return search_poet;
	}
	
   
//�������������ַ�
 public static ArrayList<Map> search_words(char word,char word2) throws Exception {
    	ArrayList<Map> searched= new ArrayList<Map>();
    	allfiles = createAll("E:\\dsaa\\chinese-poetry-zhCN-master\\chinese-poetry-zhCN-master\\poetry");
    	for(int i=0;i<allfiles.size();i++) {
    	ArrayList<Map> poet=allfiles.get(i);
    	for(int j=0;j<poet.size();j++) {
    		char[] body=(poet.get(j).get(2).toString()).toCharArray();
    		for(int k=0;k<body.length;k++) {
    			if(body[k]==word) {
    				for(int m=0;m<body.length;m++) {
    					if(word2==body[m]) {
    						searched.add(poet.get(j));
    						break;
    					}}}}}}
    return searched;}
 
//��������string��
//Ҫ��string.toCharArray�ĳ�char array 
public static ArrayList<Map> search_words (char[]word,char[] word2) throws Exception {
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
		    	for(int kk=0;kk<body.length;kk++) {
					for(int mm=0;mm<word2.length;mm++)
				if(word2[mm]==body[kk]) {
					if(kk<body.length-1) {kk++;}
					else {break;}
				    if(mm==word2.length-1) {
			searched.add(poet.get(j));
		}}
				else{kk=kk-mm;
			     break;}
				}
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

