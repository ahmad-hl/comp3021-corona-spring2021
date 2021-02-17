package pa1.containment;

/**
 * An abstract class that represents in-game technology
 */
public abstract class Containment {

    protected String name;
    protected int protection_level;
    protected int medication_level;
    protected int vaccination_level;

    public Containment() {
        this.protection_level = 0;
        this.medication_level = 0;
        this.vaccination_level = 0;
    }

    public void incrementProtection_level(int inLevel) {
        protection_level = Math.min(100, protection_level + inLevel);
    }

    public void halfProtection_level() {
        protection_level = (int) Math.ceil(protection_level * 0.5f);
        protection_level= Math.max(0, protection_level);
    }

    public void incrementVaccination_level(int inLevel) {
        vaccination_level= Math.min(100, vaccination_level + inLevel);
    }

    public void halfVaccination_level() {
        vaccination_level = (int) Math.ceil(vaccination_level * 0.5f);
        vaccination_level= Math.max(0, vaccination_level);
    }

    public void setMedication_level(int medication_level) {
        this.medication_level= Math.min(100, medication_level);
    }

    //Trivial setters & getters
    public int getProtection_level() {
        return protection_level;
    }

    public int getMedication_level() {
        return medication_level;
    }

    public int getVaccination_level() {
        return vaccination_level;
    }

    @Override
    public String toString() {
        return "name=" + name +", protection level=" + protection_level
                +", medication level=" + medication_level +", vaccination level=" + vaccination_level +"\n";
    }
}
