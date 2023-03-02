package pro;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {
	ArrayList<LearnedPoet> Poet;
	String WayToLearn;
	int LearnNumber;
	int ReviewNumber;
	String UserName;
	ArrayList<ArrayList<String>> tag;
	public User() {}
	public User(String username,int learn,int review,String waytolearn) {
		this.UserName=username;
		this.LearnNumber=learn;
		this.ReviewNumber=review;
		this.WayToLearn=waytolearn;
	}
	
	public void LearnedPoet(Map learnedpoet) {
		LearnedPoet newone=new LearnedPoet(learnedpoet.get(0).toString(),learnedpoet.get(1).toString());
		newone.lastdate=Administrator.now;
		newone.time=1;
		this.Poet.add(newone);
	}
	public void ReviewPoet(LearnedPoet reviewpoet) {
		reviewpoet.lastdate=Administrator.now;
		reviewpoet.time++;
	}
	public void addTag(ArrayList<String> mytag) {//tag���ͻ���tag���������
		tag.add(mytag);
	}
	public ArrayList<LearnedPoet> getReviewList() {//ÿ��1��3��7�츴ϰһ�Σ����ѹ�1��3��7��û�и�ϰ��
		ArrayList<LearnedPoet> reviewlist=new ArrayList<LearnedPoet>();
		int count=0;
		for (int i=0;i<Poet.size();i++) {
			int days=Administrator.now-Poet.get(i).lastdate;
			if(days==1&&days==3&&days==7) {
				reviewlist.add(Poet.get(i));
				count++;
			}
			if(count>=ReviewNumber) {
				break;
			}
			if(days>1&&Poet.get(i).time<2) {
				reviewlist.add(Poet.get(i));
				count++;
			}
			if(count>=ReviewNumber) {
				break;
			}
			if(days>3&&Poet.get(i).time<3) {
				reviewlist.add(Poet.get(i));
				count++;
			}
			if(count>=ReviewNumber) {
				break;
			}
			if(days>7&&Poet.get(i).time<4) {
				reviewlist.add(Poet.get(i));
				count++;
			}
			if(count>=ReviewNumber) {
				break;
			}		
		}
		return reviewlist;
	}
	public ArrayList<LearnedPoet> getAlready(){
		ArrayList<LearnedPoet> list=new ArrayList<LearnedPoet>();
		for (int i=0;i<Poet.size();i++) {
			int days=Administrator.now-Poet.get(i).lastdate;
			if (days>=7&Poet.get(i).time>=4) {
				list.add(Poet.get(i));
			}
	}
		return list;
}
}
