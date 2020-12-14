package data;

public class Human {
    private int initialBudget;
    private int id;
    private boolean isBankrupt = false;

    public Human(final int id, final int initialBudget) {
        this.id = id;
        this.initialBudget = initialBudget;
    }

    public Human() { }

    /**
     * Get the initial budget
     */
    public  int getInitialBudget() {
        return initialBudget;
    }

    /**
     * Set the initial budget
     */
    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     * Get the initial id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the initial id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * See if someone is bankrupt
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * Set someone bankrupt
     */
    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }
}
