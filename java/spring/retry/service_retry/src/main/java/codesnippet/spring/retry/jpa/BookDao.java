package codesnippet.spring.retry.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao
	extends JpaRepository<Book, Integer>
{

}
