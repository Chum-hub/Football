import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeagueManager {
    public LeagueManager() {
    }

    public List<Match> findMatchesByTeam(int teamId) {
        return Const.schedule.stream()
                .filter(match -> match.getAwayTeam().equals(Const.teams.get(teamId)) ||
                        match.getHomeTeam().equals(Const.teams.get(teamId)))
                .collect(Collectors.toList());
    }

    public List<Team> findTopScoringTeams(int n) {
        return Const.teams.values().stream()
                .limit(n)
                .sorted(Comparator.comparing(Team::getGoalScored).reversed())
                .collect(Collectors.toList());
    }

    public List<Player> findPlayersWithAtLeastNGoals(int n) {
        Map<Player, Long> goalsByPlayer = Const.goals.stream().collect(Collectors.groupingBy(Goal::getScorer, Collectors.counting()));
        return goalsByPlayer.entrySet().stream()
                .filter(entry -> entry.getValue() >= n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Team getTeamByPosition(int position) {
        List<Team> leaders = Const.teams.values().stream()
                .sorted(Comparator.comparing(Team::getScore)
                        .thenComparing(Team::getGoalDiff).reversed()
                        .thenComparing(Team::getName))
                .collect(Collectors.toList());
        return leaders.get(position -1);
    }
//    public Map<Integer, Integer> getTopScorers(int n) {
//
//        Map<Integer, Integer> topPlayers = Const.goals.stream()
//                .map(Goal::getScorer)
//                .collect(Collectors.groupingBy(Player::getId, Collectors.summingInt(Player::getGoals)));
//        //Этот метод принимает параметр n и должен возвращать n игроков, забивших
//        // наибольшее количество голов
//        // key – id, value – мячи.
//    }

}
