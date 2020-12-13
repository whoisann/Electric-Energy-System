package data;

public class Consumer extends Human {
    private int id;
    private int monthlyIncome;
    private int initialBudget;
    private Contract currentContract;
    private boolean debt = false;
    private int debtValue;
    private Distributor currentDistributor;
    private Distributor debtDistributor;

    public Consumer() {}

    public void setConsumer(int id, int monthlyIncome, int initialBudget, Contract currentContract) {
        this.id = id;
        this.monthlyIncome = monthlyIncome;
        this.initialBudget = initialBudget;
        this.currentContract = currentContract;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public Contract getCurrentContract() {
        return currentContract;
    }

    public void setCurrentContract(Contract currentContract) {
        this.currentContract = currentContract;
    }

    public Distributor getCurrentDistribuitor() {
        return currentDistributor;
    }

    public void setCurrentDistribuitor(Distributor currentDistribuitor) {
        this.currentDistributor = currentDistribuitor;
    }

    @Override
    public String toString() {
        return "Consumers{"
                + "id=" + id
                + ", monthlyIncome=" + monthlyIncome
                + ", initialBudget=" + initialBudget
                + '}';
    }

    public boolean expiredContract() {
        return currentContract.getRemainedContractMonths() == 0;
    }

    public void setIncome() {
        initialBudget = initialBudget + monthlyIncome;
    }

    public void payTaxes() {
        if(!debt) {
            if(initialBudget - currentContract.getPrice() >= 0) {
                initialBudget = initialBudget - currentContract.getPrice();
                currentDistributor.receiveMoney(currentContract.getPrice());
            } else {
                debt = true;
                debtValue = (int) Math.round(Math.floor(1.2 * currentContract.getPrice()));
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
        currentContract.setRemainedContractMonths(currentContract.getRemainedContractMonths() - 1);
    }

}
