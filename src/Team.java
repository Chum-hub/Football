import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Team {
    private int id;
    private String name;
    private List<Player> players;
    private int score = 0;
    private int goalScored = 0;
    private int goalMissed = 0;

    public Team(String id, String name){
        this.id = Integer.parseInt(id);
        this.name = name;
        this.players = IntStream.range(0, 14)
                .mapToObj(index -> {
                    String firstName = Const.firstNames[new Random().nextInt(Const.firstNames.length)];
                    return new Player(firstName);
                })
                .collect(Collectors.toList());
    }
    public int getGoalDiff() {
        return goalScored - goalMissed;
    }

    @Override
    public String toString() {
        return "Team: " + name + '\'' +
                ", goals: " + goalScored;
    }

    public int getGoalScored() {
        return goalScored;
    }
    public void setGoalScored(int goalScored) {
        this.goalScored += goalScored;
    }

    public int getGoalMissed() {
        return goalMissed;
    }

    public void setGoalMissed(int goalMissed) {
        this.goalMissed += goalMissed;
    }

    public int getScore() {
        return score;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score += score;
    }


}
