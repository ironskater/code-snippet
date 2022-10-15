package codesnippet.test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import codesnippet.test.persistence.entity.grade.HistoryGrade;
import codesnippet.test.persistence.entity.grade.MathGrade;
import codesnippet.test.persistence.entity.grade.ScienceGrade;
import codesnippet.test.persistence.entity.student.CollegeStudent;

@Configuration
public class AppConfig {

    @Bean
    @Scope(value = "prototype")
    CollegeStudent getCollegeStudent() {
        return new CollegeStudent();
    }

    // @Bean
    // @Scope(value = "prototype")
    // Grade getMathGrade(double grade) {
    //     return new MathGrade(grade);
    // }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("mathGrades")
    MathGrade getGrade() {
        return new MathGrade();
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("scienceGrades")
    ScienceGrade getScienceGrade() {
        return new ScienceGrade();
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("historyGrades")
    HistoryGrade getHistoryGrade() {
        return new HistoryGrade();
    }
}
