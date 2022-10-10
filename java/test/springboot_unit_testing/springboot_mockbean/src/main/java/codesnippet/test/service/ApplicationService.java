package codesnippet.test.service;

import java.util.List;

public interface ApplicationService {

    double addGradeResultsForSingleClass(List<Double> numbers);

    double findGradePointAverage(List<Double> grades);

    Object checkNull(Object obj);
}
