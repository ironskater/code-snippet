package codesnippet.spring.fundamentals.name_service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import codesnippet.java_utility.Slf4jLogger;

public class NameCollections
{
    private final static Slf4jLogger LOGGER =
        new Slf4jLogger(MethodHandles.lookup().lookupClass());

    /**
     * We can use @Autowired(required = false) to mark the dependency as optional.
     * Instead of throwing an exception, the nameList won't be initialized and its value will stay null.
     *
     * If we need an empty list instead of null, we can initialize nameList with a new ArrayList:
     */
    @Autowired(required = false)
    // @Qualifier("harry")
    private List<Name> nameList = new ArrayList<>();

    public NameCollections() {}

    public NameCollections(List<Name> nameList) {
        this.nameList = nameList;
    }

    public void printNameList() {
        LOGGER.info(this.toString());
    }

    public String toString() {
        return String.join(" ", nameList.stream().map(Name::getName).collect(Collectors.toList()));
    }
}
