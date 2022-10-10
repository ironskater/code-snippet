package codesnippet.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import codesnippet.test.dao.ApplicationDao;
import codesnippet.test.model.CollegeStudent;
import codesnippet.test.service.ApplicationService;
import codesnippet.test.service.ApplicationServiceImpl;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean(name = "applicationExample")
    ApplicationService getApplicationService() {
        return new ApplicationServiceImpl();
    }

    @Bean(name = "applicationDao")
    ApplicationDao getApplicationDao() {
        return new ApplicationDao();
    }

    @Bean(name = "collegeStudent")
    @Scope(value = "prototype")
    CollegeStudent getCollegeStudent() {
        return new CollegeStudent();
    }
}
