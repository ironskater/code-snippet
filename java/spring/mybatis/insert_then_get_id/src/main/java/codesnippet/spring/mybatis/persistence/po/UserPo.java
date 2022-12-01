package codesnippet.spring.mybatis.persistence.po;

public class UserPo {

    private Long id;
    private String username;
    private String password;

    public UserPo() {}

    public UserPo(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserPo [id=" + id + ", username=" + username + ", password=" + password + "]";
    }
}
