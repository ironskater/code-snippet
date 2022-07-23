package codesnippet.spring.fundamentals;

import codesnippet.spring.fundamentals.service.IFortuneService;

public class FootballCoach implements ICoach
{
    private String emailAddress;
    private String team;
    private Integer age;

    private IFortuneService fortuneService;

    public FootballCoach() {
        System.out.println("FootballCoach initialized: in ctor");
    }

    @Override
    public String getDailyWorkout() {
        return "\nSpend 1 hour on shooting practice\n";
    }

    @Override
    public String getDailyFortune() {
        return "FootballCoach: " + this.fortuneService.getFortune();
    }

    public void setFortuneService(IFortuneService fortuneService) {
        System.out.println("FootballCoach initialized: in setFortuneService");
        this.fortuneService = fortuneService;
    }

    public void setEmailAddress(String emailAddress) {
        System.out.println("FootballCoach initialized: in setEmailAddress");
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setTeam(String team) {
        System.out.println("FootballCoach initialized: in setTeam");
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }
}
