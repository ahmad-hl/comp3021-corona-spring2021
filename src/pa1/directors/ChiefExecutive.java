package pa1.directors;

import pa1.City;
import pa1.Player;
import pa1.containment.Isolation;
import pa1.containment.Treatment;
import pa1.exceptions.AddedContTechException;
import pa1.exceptions.NegativeValException;
import pa1.util.Constants;

public class ChiefExecutive extends Director {

    public ChiefExecutive() {
        // TODO
        super();
    }

    public ChiefExecutive(int leadership, int experience, int science) {
        super(leadership, experience, science);
    }

    @Override
    public int getPromotion() {
        System.out.printf("**** ChiefExecutive: getPromotion %d *****\n", leadership);
        return leadership;
    }

    @Override
    public void banTravel(Player player, City city) throws NegativeValException, AddedContTechException {
        super.banTravel(player, city);
        player.addPoint(Constants.BAN_TRAVEL_POINTS * getPromotion());
        player.addContainmentTech(new Isolation(""));

    }

    @Override
    public void TransferInfectedCases(Player player, City source, City dest, int cases) throws NegativeValException {
        super.TransferInfectedCases(player, source, dest, cases);
        player.addPoint(Constants.TRANSFER_ACASE_POINTS *  getPromotion());
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
        String toStr = String.format("ChiefExecutive | %s", isReady() ? "READY" : "DONE");
        return toStr;
    }
}
