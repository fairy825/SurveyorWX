package com.surveyor.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

public class SpssUtils {
	 public static void main(String[] args) { 
		   List list1 = new ArrayList<>();
		   list1.add(20d); 
	       list1.add(7d); 
	       list1.add(26d); 
		   List list2 = new ArrayList<>();
	       list2.add(7d); 
	       list2.add(3d); 
	       list2.add(6d); 
	       System.out.println("" + getPearsonBydim3(list1,list2)); //0.8多,属于高度相关
      
	  } 
	//项目鉴别度分析	 
	public static Double getDiscrinllnatloil(List<Double>list) {
		//排序
	   Collections.sort(list);
	   List list1 = new ArrayList<>();
	   List list2 = new ArrayList<>();
	   int length = list.size();
	   int highLen = (int) (length*0.27+1);
	   for(int i=0;i<highLen;i++) {
		   list1.add(list.get(i));//低分组27%
	   }
	   for(int i=0;i<highLen;i++) {
		   list2.add(list.get(length-i));//高分组27%
	   }
	   return getPearsonBydim3(list1, list2);
	}
	//计算Spearman相关系数
	public static Double getPearsonBydim3(List<Double> ratingOne, List<Double> ratingTwo) {
		if(ratingOne.size() != ratingTwo.size()) {//两个变量的观测值是成对的，每对观测值之间相互独立。
			return null;
		}
		double sim = 0D;//最后的皮尔逊相关度系数
		double commonItemsLen = ratingOne.size();//操作数的个数
		double oneSum = 0D;//第一个相关数的和
		double twoSum = 0D;//第二个相关数的和
		double onePSum = 0D;//第一个相关数平方的和
		double twoPSum = 0D;//第二个相关数平方的和
		double oneTwoSum = 0D;
		for(int i=0; i<commonItemsLen; i++) {
			oneSum += ratingOne.get(i);
			twoSum += ratingTwo.get(i);
			oneTwoSum += ratingOne.get(i) * ratingTwo.get(i);
			onePSum += Math.pow(ratingOne.get(i), 2);
			twoPSum += Math.pow(ratingTwo.get(i), 2);
		}
		double sonSum = oneTwoSum-((oneSum*twoSum)/commonItemsLen);
		double fatherSum = Math.sqrt((onePSum-(Math.pow(oneSum, 2)/commonItemsLen))*(twoPSum-(Math.pow(twoSum, 2)/commonItemsLen)));
		sim = (fatherSum == 0) ? 1 : sonSum / fatherSum;
		return sim;
	}
	   
}
