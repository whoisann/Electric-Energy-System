package output;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class OutputConsumer {
    private int id;
    private boolean isBankrupt;
    private int budget;

    public OutputConsumer(final int id, final boolean isBankrupt, final int budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    public OutputConsumer() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @JsonProperty("isBankrupt")
    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Consumers{" + "id=" + id + ", isBankrupt=" + isBankrupt
                + ", budget=" + budget + '}';
    }
}
