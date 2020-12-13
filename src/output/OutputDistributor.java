package output;

import java.util.ArrayList;

public class OutputDistributor {
    private int id;
    private int budget;
    private boolean isBankrupt;
    private ArrayList<OutputContract> contracts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public ArrayList<OutputContract> getContracts() {
        return contracts;
    }

    public void setContracts(ArrayList<OutputContract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "Distributors{" +
                "id=" + id
                + ", budget=" + budget
                + ", isBankrupt=" + isBankrupt
                + ", contracts=" + contracts + '}';
    }
}
