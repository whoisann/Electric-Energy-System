package data;

import common.Constants;

public final class Consumer extends Human {
    private int id;
    private int monthlyIncome;
    private int initialBudget;
    private Contract currentContract;
    private boolean debt = false;
    private int debtValue;
    private Distributor currentDistributor = null;
    private Distributor debtDistributor;

    public Consumer() {
    }

    /**
     * role of constructor
     */
    public void setConsumer(final int pid, final int pmonthlyIncome, final int pinitialBudget,
                            final Contract pcurrentContract) {
        this.id = pid;
        this.monthlyIncome = pmonthlyIncome;
        this.initialBudget = pinitialBudget;
        this.currentContract = pcurrentContract;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public Contract getCurrentContract() {
        return currentContract;
    }

    public void setCurrentContract(final Contract currentContract) {
        this.currentContract = currentContract;
    }

    public Distributor getCurrentDistributor() {
        return currentDistributor;
    }

    public void setCurrentDistributor(final Distributor currentDistributor) {
        this.currentDistributor = currentDistributor;
    }

    @Override
    public String toString() {
        return "Consumers{"
                + "id=" + id
                + ", bankrupt=" + isBankrupt()
                + ", monthlyIncome=" + monthlyIncome
                + ", initialBudget=" + initialBudget
                + ", contracts= " + currentContract
                + '}';
    }

    /**
     * See if a contract is expired
     */
    public boolean expiredContract() {
        return currentContract.getRemainedContractMonths() == 0;
    }

    /**
     * Calculate the budget
     */
    public void setIncome() {
        initialBudget = initialBudget + monthlyIncome;
    }

    /**
     * Pay the taxes, if it is the case pay the debt or declare a consumer bankrupt
     */
    public void payTaxes() {
        if (!debt) {
            if (initialBudget - currentContract.getPrice() >= 0) {
                initialBudget = initialBudget - currentContract.getPrice();
                currentDistributor.receiveMoney(currentContract.getPrice());
            } else {
                debt = true;
                debtValue = (int) Math.round(Math.floor(Constants.MAGIC1
                        * currentContract.getPrice()));
                debtDistributor = currentDistributor;
            }
        } else {
            if (initialBudget - currentContract.getPrice() - debtValue >= 0) {
                debt = false;
                initialBudget = initialBudget - currentContract.getPrice() - debtValue;
                currentDistributor.receiveMoney(currentContract.getPrice());
                debtDistributor.receiveMoney(debtValue);
            } else {
                this.setBankrupt(true);
            }
        }
        currentContract.setRemainedContractMonths(
                currentContract.getRemainedContractMonths() - 1);
    }

}
