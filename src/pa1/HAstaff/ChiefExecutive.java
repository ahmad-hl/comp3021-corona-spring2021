package pa1.HAstaff;

import pa1.City;
import pa1.Player;
import pa1.exceptions.BudgetRunoutException;
import pa1.exceptions.NoEnoughBudgetException;

public class ChiefExecutive extends HealthAuthorityStaff {

    public ChiefExecutive(int leadership, int medicine, int experience) {
        super(leadership, medicine, experience);
    }

    @Override
    protected int getBonusPoints() {
        return leadership + experience;
    }

    @Override
    public void banTravel(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        super.banTravel(player, city);
        player.addPoint(getBonusPoints());
    }

    /**
     * Example string representation:
     * "ChiefExecutive | READY" - when isReady() == true
     * "ChiefExecutive | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        String toStr = String.format("ChiefExecutive | %s, leadership %d, medicine %d, experience %d", isReady() ? "READY" : "DONE",leadership, medicine, experience);
        return toStr;
    }
}