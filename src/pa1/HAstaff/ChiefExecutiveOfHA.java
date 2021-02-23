package pa1.HAstaff;

import pa1.City;
import pa1.Player;
import pa1.exceptions.BudgetRunoutException;
import pa1.exceptions.NoEnoughBudgetException;

public class ChiefExecutiveOfHA extends HealthAuthorityStaff {

    public ChiefExecutiveOfHA(int leadership, int medicine, int experience) {
        super(leadership, medicine, experience);
    }

    @Override
    protected int getBonusPoints() {
        return leadership + experience;
    }

    @Override
    public void banTravel(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        super.banTravel(player, city);
        player.addPoints(getBonusPoints());
    }

    @Override
    public void leftTravelBan(Player player, City city) {
        super.leftTravelBan(player, city);
        player.addPoints(getBonusPoints());
    }

    /**
     * Example string representation:
     * "ChiefExecutive | READY, leadership, medicine, experience" - when isReady() == true
     * "ChiefExecutive | DONE, leadership, medicine, experience" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        String toStr = String.format("ChiefExecutiveOfHA | %s, leadership %d, medicine %d, experience %d", isReady() ? "READY" : "DONE",leadership, medicine, experience);
        return toStr;
    }
}
