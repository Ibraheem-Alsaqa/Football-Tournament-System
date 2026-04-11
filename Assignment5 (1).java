package assignment.pkg5;

import java.util.Arrays;
import java.util.Scanner;

class Player {
    private String name;
    private int number;
    private int goals = 0;

    public Player() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter player name: ");
        this.name = input.nextLine();
        System.out.print("Enter player number: ");
        this.number = input.nextInt();
    }

    public Player(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void incrementGoals() {
        this.goals++;
    }

    public int getGoals() {
        return goals;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}

class Team {
    private String name;
    private Player[] players;
    private int points = 0;

    public Team() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter team name: ");
        this.name = input.nextLine();
        System.out.print("Enter number of players: ");
        int numPlayers = input.nextInt();
        this.players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter data for player " + (i + 1) + ":");
            this.players[i] = new Player();
        }
    }

    public Team(String name, Player[] players) {
        this.name = name;
        this.players = new Player[players.length];
        for (int i = 0; i < players.length; i++) {
            this.players[i] = new Player(players[i].getName(), players[i].getNumber());

            for (int j = 0; j < players[i].getGoals(); j++) {
                this.players[i].incrementGoals();
            }
        }
    }

    public Player[] getPlayers() {
        Player[] copy = new Player[players.length];
        for (int i = 0; i < players.length; i++) {
            copy[i] = new Player(players[i].getName(), players[i].getNumber());
            for (int j = 0; j < players[i].getGoals(); j++) {
                copy[i].incrementGoals();
            }
        }
        return copy;
    }

    public void incrementPoints(int amount) {
        if (amount == 1 || amount == 3) {
            this.points += amount;
        } else {
            System.out.println("Failed to increment points!");
        }
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }
}

class Group {
    private Team[] teams;
    private boolean[][] matches;
    private int numOfTeams = 4;

    public Group() {
        this.teams = new Team[0];
        this.matches = new boolean[numOfTeams][numOfTeams];
        for (int i = 0; i < numOfTeams; i++) {
            for (int j = 0; j < numOfTeams; j++) {
                matches[i][j] = false;
            }
        }
    }

    public void addTeam(Team team) {
        Team[] newTeams = new Team[teams.length + 1];
        System.arraycopy(teams, 0, newTeams, 0, teams.length);
        newTeams[teams.length] = team;
        this.teams = newTeams;
    }

    public void printTeamsPoints() {
        System.out.println("Team-Name\tTeam-Points");
        for (Team team : teams) {
            System.out.println(team.getName() + "\t\t" + team.getPoints());
        }
    }

    public int unplayedMatches() {
        int count = 0;
        for (int i = 0; i < numOfTeams; i++) {
            for (int j = 0; j < numOfTeams; j++) {
                if (i != j && !matches[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public String qualifiedTeams() {
        if (teams.length < 2) return "";
        Team[] sorted = teams.clone();
        Arrays.sort(sorted, (t1, t2) -> t2.getPoints() - t1.getPoints());
        return sorted[0].getName() + ", " + sorted[1].getName();
    }

    public int getNumOfTeams() {
        return numOfTeams;
    }

    public void setNumOfTeams(int numOfTeams) {
        this.numOfTeams = numOfTeams;
    }

    public Team playNextMatch() {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < numOfTeams; i++) {
            for (int j = 0; j < numOfTeams; j++) {
                if (i != j && !matches[i][j]) {
                    matches[i][j] = true;
                    Team host = teams[i];
                    Team guest = teams[j];

                    System.out.println("MATCH STARTED");
                    System.out.println("Host Team: " + host.getName() + ".....");
                    System.out.println("Guest Team: " + guest.getName() + "....");

                    int hostGoals = 0, guestGoals = 0;
                    while (true) {
                        System.out.println("What happened?");
                        System.out.println("1. A goal is scored.");
                        System.out.println("2. The match ended.");
                        int choice = input.nextInt();

                        if (choice == 1) {
                            System.out.println("Which team scored? 1. Host 2. Guest");
                            int teamChoice = input.nextInt();
                            System.out.print("Enter the number of the player who scored: ");
                            int playerNumber = input.nextInt();

                            Team scoringTeam = (teamChoice == 1) ? host : guest;
                            Player[] players = scoringTeam.getPlayers();
                            boolean found = false;
                            for (Player p : players) {
                                if (p.getNumber() == playerNumber) {
                                    p.incrementGoals();
                                    if (teamChoice == 1) hostGoals++;
                                    else guestGoals++;
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Player not found!");
                            }
                        } else if (choice == 2) {
                            break;
                        }
                    }

                    if (hostGoals > guestGoals) {
                        host.incrementPoints(3);
                        return host;
                    } else if (guestGoals > hostGoals) {
                        guest.incrementPoints(3);
                        return guest;
                    } else {
                        host.incrementPoints(1);
                        guest.incrementPoints(1);
                        return null;
                    }
                }
            }
        }
        return null;
    }
}

public class Assignment5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Group tempGroup = new Group(); 
        int totalTeams;

        do {
            System.out.print("Enter number of teams (must be divisible by " + tempGroup.getNumOfTeams() + "): ");
            totalTeams = input.nextInt();
        } while (totalTeams % tempGroup.getNumOfTeams() != 0);

        Team[] allTeams = new Team[totalTeams];
        for (int i = 0; i < totalTeams; i++) {
            System.out.println("Enter data for Team " + (i + 1) + ":");
            allTeams[i] = new Team();
        }

        int numGroups = totalTeams / tempGroup.getNumOfTeams();
        Group[] groups = new Group[numGroups];
        for (int i = 0; i < numGroups; i++) {
            groups[i] = new Group();
        }

        boolean[] assigned = new boolean[totalTeams];
        for (int i = 0; i < numGroups; i++) {
            int added = 0;
            while (added < tempGroup.getNumOfTeams()) {
                int index = (int) (Math.random() * totalTeams);
                if (!assigned[index]) {
                    groups[i].addTeam(allTeams[index]);
                    assigned[index] = true;
                    added++;
                }
            }
        }

        for (int i = 0; i < numGroups; i++) {
            System.out.println("Group " + i + " Matches:");
            while (groups[i].unplayedMatches() > 0) {
                groups[i].playNextMatch();
            }
        }

        System.out.println("Qualified Teams:");
        for (int i = 0; i < numGroups; i++) {
            System.out.println("Group-" + i + ": " + groups[i].qualifiedTeams());
        }

        Player topScorer = null;
        for (Team team : allTeams) {
            for (Player player : team.getPlayers()) {
                if (topScorer == null || player.getGoals() > topScorer.getGoals()) {
                    topScorer = player;
                }
            }
        }
        if (topScorer == null) {     
            System.out.println("No goals scored.");

        } else {        
            System.out.println("Top Scorer is: " + topScorer.getName() + " with " + topScorer.getGoals() + " goals");

        }
    }
}