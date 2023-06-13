import java.util.Random;

public class Player {
    private int id;
    private int goals = 0;
    private String firstName;
    private String lastName;

    public void setGoal() {
        this.goals++;
    }

    public int getGoals() {
        return goals;
    }

    @Override
    public String toString() {
        return "Player: " + firstName +
                " " + lastName;
    }

    public Player(String firstName) {
        this.firstName = firstName;
        this.id = Const.lastPlayerId++;
        this.lastName = Const.lastNames[new Random().nextInt(Const.lastNames.length)];
    }

    public int getId() {
        return id;
    }
}
