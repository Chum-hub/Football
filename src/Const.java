import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Const {
    public static Map<Integer, Team> teams;
    public static List<Match> schedule = new ArrayList<>();
    public static List<Goal> goals = new ArrayList<>();
    public static String Path = "./Teams.csv";
    public static Integer lastPlayerId = 0;
    static String[] firstNames = {
            "James", "John", "Robert", "Michael", "William", "David", "Joseph", "Charles", "Thomas", "Daniel",
            "Matthew", "Anthony", "Donald", "Steven", "Paul", "Andrew", "George", "Kenneth", "Joshua", "Brian",
            "Kevin", "Edward", "Jason", "Timothy", "Mark", "Gary", "Joseph", "Richard", "Jeffrey", "Ryan"
    };

    static String[] lastNames = {
            "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson",
            "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "King", "Scott"
    };
}
