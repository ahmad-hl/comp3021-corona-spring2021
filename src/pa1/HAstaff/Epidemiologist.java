package pa1.HAstaff;


import pa1.City;
import pa1.Player;
import pa1.exceptions.BudgetRunoutException;
import pa1.exceptions.NoEnoughBudgetException;

public class Epidemiologist extends HealthAuthorityStaff {

    public Epidemiologist(int leadership, int medicine, int experience) {
        super(leadership, medicine, experience);
    }

    @Override
    protected int getBonusPoints() {
        return medicine + experience;
    }

    @Override
    public void developVaccine(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        super.developVaccine(player, city);
        player.addPoint(getBonusPoints());
    }

    @Override
    public void upgradeVaccine(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        super.upgradeVaccine(player, city);
        player.addPoint(getBonusPoints());
    }

    @Override
    public void upgradeFMaskQuality(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        super.upgradeFMaskQuality(player, city);
        player.addPoint(getBonusPoints());
    }

    /**
     * Example string representation:
     * "Epidemiologist | READY" - when isReady() == true
     * "Epidemiologist | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        String toStr = String.format("Epidemiologist | %s, leadership %d, medicine %d, experience %d", isReady() ? "READY" : "DONE",leadership, medicine, experience);
        return toStr;
    }
}
