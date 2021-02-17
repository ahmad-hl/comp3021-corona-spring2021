package pa1;

import pa1.containment.Containment;
import pa1.exceptions.BudgetRunoutException;
import pa1.exceptions.MedicalException;
import pa1.haStaff.HealthAuthorityStaff;
import pa1.exceptions.NoEnoughBudgetException;
import pa1.util.GameMap;

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
    public Player getWinner(Player frstPlayer , Player secPlayer ) {
        // TODO
        if (frstPlayer.getCity().getInfectedCases() < secPlayer.getCity().getInfectedCases())
            return frstPlayer;
        else if (frstPlayer.getCity().getInfectedCases() > secPlayer.getCity().getInfectedCases())
            return secPlayer;
        else {
            if( frstPlayer.getCity().getNumNewCases() < secPlayer.getCity().getNumNewCases())
                return frstPlayer;
            else if( frstPlayer.getCity().getNumNewCases() > secPlayer.getCity().getNumNewCases())
                return secPlayer;
            else{
                if( frstPlayer.getPoints() > secPlayer.getPoints())
                    return frstPlayer;
                else if( frstPlayer.getPoints() < secPlayer.getPoints())
                    return secPlayer;
            }
        }

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

    private void processPlayersTurn() throws MedicalException, BudgetRunoutException, NoEnoughBudgetException {
        turns++;
        for (Player player :  gameMap.getPlayers()) {

            player.getHAStaffs().forEach(HealthAuthorityStaff::beginTurn);

            while (player.hasReadyHAStaff()) {
                System.out.print("\n\n");
                System.out.println(player);
                System.out.print("\n\n");
                HealthAuthorityStaff healthAuthorityStaff = selectHAStaff(player);

                if (healthAuthorityStaff == null) break;

                City city = player.getCity();

                if (city == null) break;

                System.out.print("\n\n");
                selectAndPerformAction(player, healthAuthorityStaff, city);
                healthAuthorityStaff.endTurn();
            }

            //Compute new infected cases & update total cases
            player.computeNewInfectedCases();
            printPlayerInfo(player);
        }
    }

    private HealthAuthorityStaff selectHAStaff(Player player) {
        System.out.println("HAStaff SELECTION");
        for (int i = 0; i < player.getHAStaffs().size(); i++)
            System.out.printf("\t[%d]\t%s\n", i + 1, player.getHAStaffs().get(i));

        HealthAuthorityStaff m = null;
        while (true) {
            int selection = getSelection(1, player.getHAStaffs().size(), "HAStaff (0 to skip turn)");
            if (selection == 0) break;
            m = player.getHAStaffs().get(selection - 1);
            if (!m.isReady()) {
                System.out.println("Selected HAStaff already performed a task");
            } else {
                break;
            }
        }

        return m;
    }

    private void selectAndPerformAction(Player player, HealthAuthorityStaff healthAuthorityStaff, City city) throws BudgetRunoutException, NoEnoughBudgetException {
        System.out.println("SELECT HAStaff ACTION");
        System.out.println("\t[ 1]\tBuild Hospital");
        System.out.println("\t[ 2]\tBuild Mask Factory");
        System.out.println("\t[ 3]\tUpgrade Mask Quality");
        System.out.println("\t[ 4]\tBan Travel");
        System.out.println("\t[ 5]\tDevelop Vaccine");
        System.out.println("\t[ 6]\tUpgrade the Vaccine");

        while (true) {
            int command = getSelection(1, 6, "action");
            processPlayerCommand(command, player, healthAuthorityStaff, city);
            break;
        }
    }

    public void printContTechs(){
        for (Player player: gameMap.getPlayers()) {
            System.out.printf("Player ( %s ) Containment Techniques:\n", player.getName());
            for (Containment cont: player.getContainTechniques()) {
                System.out.printf("\t%s",cont);
            }
        }
    }

    private void processPlayerCommand(int command, Player player, HealthAuthorityStaff healthAuthorityStaff, City city) throws NoEnoughBudgetException, BudgetRunoutException {

        switch (command) {
            case 1:
                healthAuthorityStaff.buildHospital(player, city);
                break;
            case 2:
                healthAuthorityStaff.buildMasksFactory(player, city);
                break;
            case 3:
                healthAuthorityStaff.upgradeFMaskQuality(player, city);
                break;
            case 4:
                healthAuthorityStaff.banTravel(player, city);
                break;
            case 5:
                healthAuthorityStaff.developVaccine(player, city);
                break;
            case 6:
                healthAuthorityStaff.upgradeVaccine(player, city);
                break;
            default:
                break;
        }
    }

    private static void printPlayerInfo(Player player){
        System.out.println(player);
        System.out.printf("HealthAuthorityStaff:");
        for (HealthAuthorityStaff healthAuthorityStaff :  player.getHAStaffs()) {
            System.out.printf("\t %s \n", healthAuthorityStaff);
        }
    }
    private static void printPlayersInfo(List<Player> players){
        for (Player player :  players) {
            printPlayerInfo(player);
        }
    }

    private static void findWinner(GameEngine game ){
        Player firstPlayer = game.gameMap.getPlayers().get(0);
        Player secondPlayer = game.gameMap.getPlayers().get(1);
        Player winner = game.getWinner(firstPlayer, secondPlayer);
        if (winner == null)
            System.out.printf("Players (%s has %d , %s has %d) are equal\n",firstPlayer.getName(), firstPlayer.getPoints(),
                    secondPlayer.getName(), secondPlayer.getPoints() );
        else
            System.out.printf("Player %s has %d infected Cases, %d new cases, %d points, wins the game\n", winner.getName(),winner.getCity().getInfectedCases(), winner.getCity().getNumNewCases(), winner.getPoints());
    }

    public static void main(String[] args) {
        GameEngine game = new GameEngine();

        try {
            game.gameMap.loadPlayers("players.txt");
            game.gameMap.getPlayers().forEach(Player::toString);
            printPlayersInfo(game.gameMap.getPlayers());

            int i =0;
            while (i<10) {
                game.processPlayersTurn();
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MedicalException e) {
            System.out.printf("%s: %s\n",e, e.getMessage());
        } catch (BudgetRunoutException e) {
            System.out.printf("%s: %s\n",e, e.getMessage());
        } catch (NoEnoughBudgetException e) {
            System.out.printf("%s: %s\n",e, e.getMessage());
        } finally {
            findWinner(game);
        }
    }
}
