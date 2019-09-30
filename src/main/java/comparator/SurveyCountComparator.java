package comparator;


import java.util.Comparator;

import com.surveyor.pojo.Survey;

public class SurveyCountComparator implements Comparator<Survey> {

	@Override
	public int compare(Survey p1, Survey p2) {
		return p2.getHadpaper()-p1.getHadpaper();
	}

}
