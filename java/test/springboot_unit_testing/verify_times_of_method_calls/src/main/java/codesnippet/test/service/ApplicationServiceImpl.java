package codesnippet.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import codesnippet.test.dao.ApplicationDao;

public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public double addGradeResultsForSingleClass(List<Double> numbers) {
        return applicationDao.addGradeResultsForSingleClass(numbers);
    }

    @Override
    public double findGradePointAverage(List<Double> grades) {
        return applicationDao.findGradePointAverage(grades);
    }

    @Override
    public Object checkNull(Object obj) {
        return applicationDao.checkNull(obj);
    }
}
