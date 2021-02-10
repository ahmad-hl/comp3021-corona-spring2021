package pa1.containment;

/**
 * An abstract class that represents in-game technology
 */
public abstract class Containment {

    protected String name;

    public Containment( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
