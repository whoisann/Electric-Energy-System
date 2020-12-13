package data;

public class Human {
    private int initialBudget;
    private int id;
    private boolean isBankrupt = false;

    public Human(int id, int initialBudget) {
        this.id = id;
        this.initialBudget = initialBudget;
    }

    public Human() {}

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }
}
