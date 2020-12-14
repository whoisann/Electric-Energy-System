package data;

import java.util.ArrayList;
import java.util.Collections;

public class Distributor extends Human {
    private int contractLength;
    private int initialInfrastructureCost;
    private int initialProductionCost;
    private long contractPrice;
    private ArrayList<Contract> contracts;
    private long totalTaxes;

    public Distributor() {}

    public void setDistributor(int id, int contractLength, int initialBudget,
                               int initialInfrastructureCost, int initialProductionCost,
                               ArrayList<Contract> contracts) {
        super.setId(id);
        super.setInitialBudget(initialBudget);
        this.contractLength = contractLength;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.initialProductionCost = initialProductionCost;
        this.contracts = contracts;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(int contractLength) {
        this.contractLength = contractLength;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setInitialInfrastructureCost(int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public int getInitialProductionCost() {
        return initialProductionCost;
    }

    public void setInitialProductionCost(int initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }

    public long getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(long contractPrice) {
        this.contractPrice = contractPrice;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(ArrayList<Contract> contracts) {
        this.contracts = contracts;
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

    public void updateContractPrice() {
        long profit;
        profit = Math.round(Math.floor(0.2 * initialProductionCost));
        if (contracts.size() != 0) {
            contractPrice =  Math.round(Math.floor((double) initialInfrastructureCost / contracts.size())
                    + initialProductionCost + profit);
        } else {
            contractPrice = initialInfrastructureCost + initialProductionCost + profit;
        }
    }

    public static Distributor getLowestPriceDistributor(ArrayList<Distributor> distributors) {
        ArrayList<Distributor> copy = new ArrayList<>(distributors);

        copy.sort((d1, d2) -> {
            return (int) (d1.contractPrice - d2.contractPrice);
        });

        copy.removeIf(Human::isBankrupt);

        if (copy.size() > 0) {
            return copy.get(0);
        }
        return null;
    }

    public void calculateTaxes() {
        totalTaxes = initialInfrastructureCost + initialProductionCost * contracts.size();
    }

    public void payTaxes() {
        this.setInitialBudget((int) (this.getInitialBudget() - totalTaxes));
        if (getInitialBudget() < 0) {
            this.setBankrupt(true);
            //TODO: anulez contractele pe care le am
        }
    }

    public void receiveMoney(long moneySum) {
        setInitialBudget((int) (getInitialBudget() + moneySum));
    }
}
