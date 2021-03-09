package pa1.HAstaff;

import pa1.City;
import pa1.Player;
import pa1.exceptions.BudgetRunoutException;
import pa1.exceptions.NoEnoughBudgetException;

public class ChiefExecutiveOfHA extends HealthAuthorityStaff {

    /**
     * Initialize HealthMinister
     * call the super class constructor
     *
     * @param leadership
     * @param medicine
     * @param experience
     */
    public ChiefExecutiveOfHA(int leadership, int medicine, int experience) {
        super(leadership, medicine, experience);
    }

    /**
     * compute bonus
     * CEO of HA bonus is based on  summation of leadership and experience
     * <p>
     * HINT:
     *
     * @return int
     */

    @Override
    protected int getBonusPoints() {
        return leadership + experience;
    }

    /**
     * ban travel
     * 1. Call the super class banTravel
     * 2. add points according to bonus
     * <p>
     * HINT:
     *
     */
    @Override
    public void banTravel(Player player, City city) {
        super.banTravel(player, city);
        player.addPoints(getBonusPoints());
    }

    /**
     * lift travel ban
     * 1. Call the super class liftTravelBan
     * 2. add points according to bonus
     * <p>
     * HINT:
     */
    @Override
    public void liftTravelBan(Player player, City city) {
        super.liftTravelBan(player, city);
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
