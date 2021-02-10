package pa1;

import pa1.directors.Director;
import pa1.exceptions.NegativeValException;
import pa1.exceptions.NoEnoughBudgetException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Implements the game logic
 */
public class GameEngine {

    private GameMap gameMap = new GameMap();
    private int turns;
    private Scanner userInputScanner = new Scanner(System.in);

    /**
     * Get the the only player with at least one city
     *
     * @return
     */
    public Player getWinner() {
        // TODO
        List<Player> players = gameMap.getPlayers();
        if (players.get(0).getPoints() > players.get(1).getPoints())
            return players.get(0);
        else if  (players.get(0).getPoints() < players.get(1).getPoints())
            return players.get(1);
        else
            return null;
    }

    private int getSelection(int min, int max, String name) {
        while (true) {
            try {
                System.out.printf("\nSelect %s (%d-%d):\n", name, min, max);
                int selection = userInputScanner.nextInt();
                if ((selection < min || selection > max) && selection != 0)
                    throw new IndexOutOfBoundsException();
                return selection;

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.print("Invalid option, choose again");
            }
        }
    }

    private void processPlayersTurn() {
        turns++;
        for (Player player :  gameMap.getPlayers()) {

            player.getDirectors().forEach(Director::beginTurn);

            while (player.hasReadyDirector()) {
                System.out.print("\n\n");
                printMap();
                System.out.print("\n\n");
                System.out.println(player);
                System.out.print("\n\n");
                Director director = selectDirector(player);

                if (director == null) break;

                System.out.print("\n\n");
                City city = selectCity(player);

                if (city == null) break;

                System.out.print("\n\n");
                selectAndPerformAction(player, director, city);
                director.endTurn();
            }

            player.getCities().forEach(City::updateInfectedCasesBySpreadRate);
        }
    }

    private Director selectDirector(Player player) {
        System.out.println("Director SELECTION");
        for (int i = 0; i < player.getDirectors().size(); i++)
            System.out.printf("\t[%d]\t%s\n", i + 1, player.getDirectors().get(i));

        Director m = null;
        while (true) {
            int selection = getSelection(1, player.getDirectors().size(), "director (0 to skip turn)");
            if (selection == 0) break;
            m = player.getDirectors().get(selection - 1);
            if (!m.isReady()) {
                System.out.println("Selected director already performed a task");
            } else {
                break;
            }
        }

        return m;
    }

    private City selectCity(Player player) {
        System.out.println("CITY SELECTION");
        for (int i = 0; i < player.getCities().size(); i++)
            System.out.printf("\t[%d]\t%s\n", i + 1, player.getCities().get(i));

        int selection = getSelection(1, player.getCities().size(), "city (0 to skip turn)");
        if (selection == 0) return null;
        return player.getCities().get(selection - 1);
    }

    private void selectAndPerformAction(Player player, Director director, City city) {


        System.out.println("SELECT Director ACTION");
        System.out.println("\t[ 1]\tBuild Hospital");
        System.out.println("\t[ 2]\tBuild Medical Lab");
        System.out.println("\t[ 3]\tBuild Medicine Factory");
        System.out.println("\t[ 4]\tRecruit Doctors");

        while (true) {
            try {
                int command = getSelection(1, 4, "action");
                processPlayerCommand(command, player, director, city);
                break;
            } catch (NoEnoughBudgetException e) {
                System.out.println(e.getMessage());
            } catch (NegativeValException e) {
                e.printStackTrace();
            }
        }
    }


    private void processPlayerCommand(int command, Player player, Director director, City city) throws NoEnoughBudgetException, NegativeValException {

        switch (command) {
            case 1:
                director.buildHospital(player, city);
                break;

            case 2:
                director.buildMedicalLab(player, city);
                break;

            case 3:
                director.buildMedicineFactory(player, city);
                break;
            case 4:
                director.recruitDoctors(player, city, 10);
                break;
            default:
                break;
        }
    }

    private void printMap() {
        System.out.println(gameMap);
    }

    public static void main(String[] args) {
        GameEngine game = new GameEngine();

        try {
            game.gameMap.loadPlayers("players.txt");
//            game.gameMap.getPlayers().forEach(Player::toString);
            for (Player player :  game.gameMap.getPlayers()) {
                System.out.println(player);
                System.out.printf("City: %s \nDirector:",player.getCities().get(0));
                for (Director director :  player.getDirectors()) {
                    System.out.printf("\t %s \n",director);
                }
            }

            int i =0;
            while (i<1) {
                game.processPlayersTurn();
                i++;
            }

            Player winner = game.getWinner();
            Player firstPlayer = game.gameMap.getPlayers().get(0);
            Player secondPlayer = game.gameMap.getPlayers().get(1);
            if (winner == null)
                System.out.printf("Players (%s has %d , %s has %d) are equal",firstPlayer.getName(), firstPlayer.getPoints(),
                        secondPlayer.getName(), secondPlayer.getPoints() );
            else
                System.out.printf("Player %s has  %d points, wins the game", winner.getName(), winner.getPoints());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
