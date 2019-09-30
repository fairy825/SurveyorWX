package comparator;

import java.util.Comparator;

import com.surveyor.pojo.Survey;



public class SurveyAllComparator implements Comparator<Survey>{

	@Override
	public int compare(Survey p1, Survey p2) {
		return (int) (p2.getPrice()*p2.getHadpaper()-p1.getPrice()*p1.getHadpaper());
	}

}
