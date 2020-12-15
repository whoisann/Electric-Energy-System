package data;

import common.Constants;

import java.util.ArrayList;

public final class Distributor extends Human {
    private int contractLength;
    private int initialInfrastructureCost;
    private int initialProductionCost;
    private long contractPrice;
    private ArrayList<Contract> contracts;
    private long totalTaxes;

    public Distributor() { }

    /**
     * Role of constructor
     */
    public void setDistributor(final int pid, final int pcontractLength,
                               final int pinitialBudget,
                               final int pinitialInfrastructureCost,
                               final int pinitialProductionCost,
                               final ArrayList<Contract> pcontracts) {
        super.setId(pid);
        super.setInitialBudget(pinitialBudget);
        this.contractLength = pcontractLength;
        this.initialInfrastructureCost = pinitialInfrastructureCost;
        this.initialProductionCost = pinitialProductionCost;
        this.contracts = pcontracts;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(final int pcontractLength) {
        this.contractLength = pcontractLength;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setInitialInfrastructureCost(final int pinitialInfrastructureCost) {
        this.initialInfrastructureCost = pinitialInfrastructureCost;
    }

    public int getInitialProductionCost() {
        return initialProductionCost;
    }

    public void setInitialProductionCost(final int pinitialProductionCost) {
        this.initialProductionCost = pinitialProductionCost;
    }

    public long getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(final long pcontractPrice) {
        this.contractPrice = pcontractPrice;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<Contract> pcontracts) {
        this.contracts = pcontracts;
    }

    @Override
    public String toString() {
        return "Distributors{" + "id=" + super.getId()
                + ", bankrupt=" + isBankrupt()
                + ", contractLength=" + contractLength
                + ", initialBudget=" + super.getInitialBudget()
                + ", initialInfrastructureCost=" + initialInfrastructureCost
                + ", initialProductionCost=" + initialProductionCost
                + ", contracts= " + contracts + '}';
    }

    /**
     * Calculate the profit and the final price of the contract
     */
    public void updateContractPrice() {
        long profit;
        profit = Math.round(Math.floor(Constants.MAGIC2 * initialProductionCost));
        if (contracts.size() != 0) {
            // calculate contract price
            contractPrice = Math.round(Math.floor((double) initialInfrastructureCost
                    / contracts.size())
                    + initialProductionCost + profit);
        } else {
            contractPrice = initialInfrastructureCost + initialProductionCost + profit;
        }
    }

    /**
     * This function returns the lowest price distributor and removes the bankrupt distributors
     */
    public static Distributor getLowestPriceDistributor(final ArrayList<Distributor>
                                                                distributors) {

        ArrayList<Distributor> copy = new ArrayList<>(distributors);

        // sort the distributors list after the contract price
        copy.sort((d1, d2) -> {
            return (int) (d1.contractPrice - d2.contractPrice);
        });

        // remove the bankrupt distributors
        copy.removeIf(Human::isBankrupt);

        // get the lowest price distributor
        if (copy.size() > 0) {
            return copy.get(0);
        }

        return null;
    }

    /**
     * Calculate distributor taxes
     */
    public void calculateTaxes() {
        totalTaxes = initialInfrastructureCost + initialProductionCost * contracts.size();
    }

    /**
     * Pay taxes or if it is the case set the distributor bankrupt
     */
    public void payTaxes() {
        this.setInitialBudget((int) (this.getInitialBudget() - totalTaxes));
        // verify if a distributor is bankrupt
        if (getInitialBudget() < 0) {
            this.setBankrupt(true);
        }
    }

    /**
     * Receive money from the consumers
     */
    public void receiveMoney(final long moneySum) {
        setInitialBudget((int) (getInitialBudget() + moneySum));
    }
}
