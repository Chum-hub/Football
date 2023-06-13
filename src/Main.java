import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        readAndWrite(Const.Path);
        game(createTours());
    }

    public static void game(List<List<Match>> leagues) {
        List<Match> allMatches = leagues.stream()
                .flatMap(matches -> matches.stream())
                .collect(Collectors.toList());

        allMatches.forEach(match -> {
            System.out.println(match.getHomeTeam().getName() + " vs " + match.getAwayTeam().getName());
            countdown();
            results(match);
            if (allMatches.indexOf(match) % 5 == 4) {
                printLeagueTable();
                leagueManager();
            }
        });

    }

    public static void leagueManager() {

        System.out.println("Please choose your action:");
        System.out.println("1) Find Matches By Team");
        System.out.println("2) Find Top Scoring Teams");
        System.out.println("3) Find Players With At Least N Goals");
        System.out.println("4) Get Team By Position");
        System.out.println("5) Get Top Scorers");
        System.out.println("6) Exit to Football");
        LeagueManager lg = new LeagueManager();
        switch (new Scanner(System.in).nextInt()) {

            case 1 -> {
                System.out.print("Choose the TeamId: ");
                lg.findMatchesByTeam(new Scanner(System.in).nextInt()).forEach(System.out::println);
                leagueManager();
            }
            case 2 -> {
                System.out.print("Type number of Teams: ");
                lg.findTopScoringTeams(new Scanner(System.in).nextInt()).forEach(System.out::println);
                leagueManager();
            }
            case 3 -> {
                System.out.print("Type number of Goals: ");
                lg.findPlayersWithAtLeastNGoals(new Scanner(System.in).nextInt()).forEach(System.out::println);
                leagueManager();
            }
            case 4 -> {
                System.out.print("Type number of Position: ");
                System.out.println(lg.getTeamByPosition(new Scanner(System.in).nextInt()));
                leagueManager();
            }
            case 5 -> {
                break;
            }
            case 6 -> {
                break;
            }
        }

    }

    public static void printLeagueTable() {
        Const.teams.values().stream()
                .sorted(Comparator.comparing(Team::getScore)
                        .thenComparing(Team::getGoalDiff).reversed()
                        .thenComparing(Team::getName))
                .forEach(team -> System.out.println("|| " + team.getName() + ", score: " + team.getScore() + ", goal diff: " + team.getGoalDiff() + " ||"));
    }

    public static void results(Match match) {
        int goalsHomeTeam = new Random().nextInt(5);
        int goalsAwayTeam = new Random().nextInt(5);
        if (goalsHomeTeam > goalsAwayTeam) {
            match.getHomeTeam().setScore(3);
            match.getAwayTeam().setScore(0);
        } else if (goalsHomeTeam < goalsAwayTeam) {
            match.getHomeTeam().setScore(0);
            match.getAwayTeam().setScore(3);
        } else {
            match.getHomeTeam().setScore(1);
            match.getAwayTeam().setScore(1);
        }
        match.setGoals(goalsHomeTeam, goalsAwayTeam);
    }

    public static void countdown() {
        Stream.iterate(10, i -> i - 1)
                .limit(10)
                .forEach(i -> {
                    System.out.println("Match ends in: " + i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void readAndWrite(String path) {
        String comaSplit = ",";
        try (Stream<String> lines = Files.lines(Paths.get(path))) {

            Const.teams = lines.map(line -> line.split(comaSplit))
                    .collect(Collectors.toMap(
                            data -> Integer.parseInt(data[0]),
                            data -> new Team(data[0], data[1])));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<List<Match>> createTours() {
        return IntStream.range(0, 9)
                .mapToObj(round -> generateRoundMatches(Const.teams))
                .collect(Collectors.toList());
    }

    public static List<Match> generateRoundMatches(Map<Integer, Team> teams) {

        return teams.values().stream()
                .flatMap(homeTeam -> teams.values().stream()
                        .filter(awayTeam -> !isAlreadyExistInSchedule(awayTeam, homeTeam) && !homeTeam.equals(awayTeam))
                        .map(awayTeam -> {
                            Match match = new Match(homeTeam, awayTeam);
                            Const.schedule.add(match);
                            return match;
                        }))
                .distinct()
                .limit(5)
                .collect(Collectors.toList());
    }

    public static boolean isAlreadyExistInSchedule(Team awayTeam, Team homeTeam) {
        if (awayTeam.equals(homeTeam)) {
            return true;
        }
        if (Const.schedule == null) {
            Const.schedule = new ArrayList<>();
            return false;
        }
        return Const.schedule.stream()
                .anyMatch(match -> (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                        || (match.getHomeTeam().equals(awayTeam) && match.getAwayTeam().equals(homeTeam)));
    }
}