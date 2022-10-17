package codesnippet.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codesnippet.test.models.CollegeStudent;

@Repository
public interface StudentDao extends JpaRepository<CollegeStudent, Integer> {

    public CollegeStudent findByEmailAddress(String emailAddress);
}
