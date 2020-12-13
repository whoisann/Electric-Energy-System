package output;

public class OutputConsumer {
    private int id;
    private boolean isBankrupt;
    private int budget;

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

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Consumers{" +
                "id=" + id + ", isBankrupt=" + isBankrupt
                + ", budget=" + budget + '}';
    }
}
