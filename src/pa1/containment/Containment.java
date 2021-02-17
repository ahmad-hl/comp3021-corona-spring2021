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

    public Containment(String name, int protection_level, int medication_level, int vaccination_level) {
        this.name = name;
        this.protection_level = protection_level;
        this.medication_level = medication_level;
        this.vaccination_level = vaccination_level;
    }


    public void incrementProtection_level(int protection_level) {
        this.protection_level = Math.min(100, this.protection_level + protection_level);
    }

    public void halfProtection_level() {
        protection_level = (int) Math.ceil(protection_level * 0.5f);
        protection_level= Math.max(0, protection_level);
    }

    public void incrementVaccination_level(int vaccination_level) {
        this.vaccination_level= Math.min(100, this.vaccination_level + vaccination_level);
    }

    public void halfVaccination_level() {
        vaccination_level = (int) Math.ceil(vaccination_level * 0.5f);
        vaccination_level= Math.max(0, vaccination_level);
    }

    public void setMedication_level(int medication_level) {
        this.medication_level= Math.min(100, medication_level);
    }

    public void halfMedication_level() {
        medication_level = (int) Math.ceil(medication_level * 0.5f);
        medication_level= Math.max(0, medication_level);
    }

    //Trivial setters & getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
