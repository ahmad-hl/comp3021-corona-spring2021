package pa1.directors;


import pa1.City;
import pa1.containment.Treatment;
import pa1.containment.Vaccination;
import pa1.exceptions.AddedContTechException;
import pa1.exceptions.NegativeValException;
import pa1.util.Constants;
import pa1.Player;

public class Epidemiologist extends Director {

    /**
     * Calls the superclass' constructor
     *
     */
    public Epidemiologist() {
        // TODO
        super();
    }

    public Epidemiologist(int leadership, int experience, int science) {
        super(leadership, experience, science);
    }

    @Override
    public int getPromotion(){
        int promotion = experience + science;
        System.out.printf("**** Epidemiologist: getPromotion %d *****\n", promotion);
        return promotion;
    }

    @Override
    public void developVaccine(Player player, City city) throws NegativeValException, AddedContTechException {
        super.developVaccine(player, city);
        player.addPoint(Constants.DEVELOP_VACCINE_POINTS * getPromotion());
        player.addContainmentTech(new Vaccination(""));
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
        String toStr = String.format("Epidemiologist | %s", isReady() ? "READY" : "DONE");
        return toStr;
    }
}
