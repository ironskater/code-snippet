package codesnippet.application_prototypes.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class ExtendableBean {

    private String name;
    private Map<String, String> properties;

    public ExtendableBean() {}

    public ExtendableBean(String name, Map<String, String> properties) {
        this.name = name;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return this.properties;
    }

    @JsonAnySetter
    public void setProperties(String key, String value) {
        if(this.properties == null) {
            this.properties = new HashMap<>();
        }
        this.properties.put(key, value);
    }

    @Override
    public String toString() {
        return "ExtendableBean [name=" + name + ", properties=" + properties + "]";
    }
}
