public class Goal {
    private int id;
    private int minute;
    private Player scorer;

    public Goal(int minute, Player scorer) {
        this.minute = minute;
        this.scorer = scorer;
        this.scorer.setGoal();
    }

    public int getId() {
        return id;
    }

    public int getMinute() {
        return minute;
    }

    public Player getScorer() {
        return scorer;
    }
}
