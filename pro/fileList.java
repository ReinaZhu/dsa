package pro;

import java.io.*; 
import java.util.*; 
  
public class fileList { 
  // ����һ�����飬������ļ�·�� 
  public static ArrayList<String> fStrArr = new ArrayList<String>();
  // �����ǽ�����ѯ��ǰ·���µ������ļ��С��ļ����Ҵ����·�����ļ�����,�������������ļ���������ļ� 
  public ArrayList<String> Dir(File file) throws Exception { 
    //�ж��Ƿ�ռ��������õ� 
    if (file.exists()) { 
      // ֱ��ȡ������listFiles()�ѵ�ǰ·���µ������ļ��С��ļ���ŵ�һ���ļ����� 
      File files[] = file.listFiles(); 
      for (File f : files) { 
        // ������ݹ����Ĳ���dirFile�����ļ��ָ�����Ҳ����/����\��β������˹��� 
    	//separator���Ƿָ�������˼�����ù��� 
        if (file.getPath().endsWith(File.separator)) {
          fStrArr.add(f.getPath() + f.getName()); 
        } else { 
          // �������û���ļ��ָ���������һ���ļ��ָ������ټ����ļ���������·������ʵ����ν�ġ����� 
          fStrArr.add(file.getPath() + File.separator 
              + f.getName()); 
        } 
      } 
    } 
    return fStrArr; 
  } 
} 
