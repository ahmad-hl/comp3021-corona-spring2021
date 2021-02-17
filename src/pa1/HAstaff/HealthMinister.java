package pa1.HAstaff;

import pa1.City;
import pa1.Player;
import pa1.exceptions.BudgetRunoutException;
import pa1.exceptions.NoEnoughBudgetException;

/**
 * An abstract class that represents a minister in the game.
 * All actions in the game are done through ministers.
 * Therefore this class will contain the bulk of your implementation of the game.
 */
public class HealthMinister extends HealthAuthorityStaff {

    public HealthMinister(int leadership, int medicine, int experience) {
        super(leadership, medicine, experience);
    }

    @Override
    protected int getBonusPoints() {
        return leadership + medicine + experience;
    }

    @Override
    public void buildHospital(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        super.buildHospital(player, city);
        player.addPoint(getBonusPoints());
    }

    @Override
    public void buildMasksFactory(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        super.buildMasksFactory(player, city);
        player.addPoint(getBonusPoints());
    }


    /**
     * Example string representation:
     * "HealthMinister | READY" - when isReady() == true
     * "HealthMinister | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        String toStr = String.format("HealthMinister | %s, leadership %d, medicine %d, experience %d", isReady() ? "READY" : "DONE",leadership, medicine, experience);
        return toStr;
    }
}

