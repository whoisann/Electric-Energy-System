package output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public final class OutputDistributor {
    private int id;
    private int budget;
    private boolean isBankrupt;
    private ArrayList<OutputContract> contracts;

    public OutputDistributor() { }

    public OutputDistributor(final int id, final int budget, final boolean isBankrupt,
                             final ArrayList<OutputContract> contracts) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    @JsonProperty("isBankrupt")
    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public ArrayList<OutputContract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<OutputContract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "Distributors{" + "id=" + id
                + ", budget=" + budget
                + ", isBankrupt=" + isBankrupt
                + ", contracts=" + contracts + '}';
    }
}
