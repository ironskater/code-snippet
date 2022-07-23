package codesnippet.spring.fundamentals;

import codesnippet.spring.fundamentals.service.IFortuneService;

public class BaseballCoach implements ICoach
{
    private IFortuneService fortuneService;

    public BaseballCoach(IFortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "\nSpend 30 mins on batting practice\n";
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }
}
