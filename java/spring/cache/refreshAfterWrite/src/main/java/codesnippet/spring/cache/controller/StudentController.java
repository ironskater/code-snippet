package codesnippet.spring.cache.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import codesnippet.spring.cache.model.StudentDto;

@RestController
@RequestMapping("/api")
public class StudentController {

    private Logger logger = LogManager.getLogger();

    private Map<Long, StudentDto> studentDtoById;

    @GetMapping("/students/{id}")
    @Cacheable(cacheNames = "userCache", key = "#id")
    public StudentDto getStudents(@PathVariable("id") Long id) {

        logger.info("get data from controller");

        return this.studentDtoById.get(id);
    }

    @PostConstruct
    public void loadData() {
        this.studentDtoById = new HashMap<>();
        this.studentDtoById.put(1L, new StudentDto(1L, "Poornima", "Patel"));
        this.studentDtoById.put(2L, new StudentDto(2L, "Mario", "Rossi"));
        this.studentDtoById.put(3L, new StudentDto(3L, "Mary", "Smith"));
    }
}
