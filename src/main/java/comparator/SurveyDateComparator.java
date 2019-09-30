package comparator;



import java.util.Comparator;

import com.surveyor.pojo.Survey;


public class SurveyDateComparator implements Comparator<Survey>{

	@Override
	public int compare(Survey p1, Survey p2) {
		return p2.getPublishTime().compareTo(p1.getPublishTime());
	}

}

