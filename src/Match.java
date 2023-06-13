import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Match {
    private int id;
    private Team homeTeam;
    private Team awayTeam;

    public void setGoals(int homeGoals, int awayGoals) {
       getHomeTeam().getPlayers().stream()
               .filter(player -> player.getId() == new Random().nextInt(14))
               .limit(homeGoals)
               .forEach(player -> Const.goals.add(new Goal(new Random().nextInt(91), player)));

       getAwayTeam().getPlayers().stream()
               .filter(player -> player.getId() == new Random().nextInt(14))
               .limit(awayGoals)
               .forEach(player -> Const.goals.add(new Goal(new Random().nextInt(91), player)));
       getHomeTeam().setGoalMissed(awayGoals);
       getAwayTeam().setGoalMissed(homeGoals);
       getHomeTeam().setGoalScored(homeGoals);
       getAwayTeam().setGoalScored(awayGoals);
    }

    @Override
    public String toString() {
        return "Match between: " +
                "Home Team: " + homeTeam.getName() +
                ", Away Team: " + awayTeam.getName();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

}
