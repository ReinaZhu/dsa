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
	public class SearchPoetry {
		static ArrayList<ArrayList<Map>> allfiles=new ArrayList<ArrayList<Map>>();
		
	//main����Ϊ����
		public static void main(String[] args) {
			try{String a="��������";
			ArrayList<Map> sear=search_one_words(a);
			
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
		
		//strΪ�����ϵΪ��Ĵ��������ÿո�ֿ�
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
		
	//strΪ�����ϵΪ�͵Ĵ��������ÿո�ֿ�
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
			//allfiles = test2.createAll("poetry");
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
		
		
		
		
}