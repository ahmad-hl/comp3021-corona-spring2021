package pa1.containment;

/**
 * An abstract class that represents in-game technology
 */
public abstract class Containment {

    protected String name;
    protected int pos;

    public String getName() {
        return name;
    }

    public int getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return "name=" + name +", pos=" + pos +"\n";
    }
}
