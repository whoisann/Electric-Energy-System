package data;

public class Consumer extends Human {
    private int id;
    private int monthlyIncome;
    private int initialBudget;

    public Consumer() {}

    public void setConsumer(int id, int monthlyIncome, int initialBudget) {
        this.id = id;
        this.monthlyIncome = monthlyIncome;
        this.initialBudget = initialBudget;
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

    @Override
    public String toString() {
        return "Consumers{"
                + "id=" + id
                + ", monthlyIncome=" + monthlyIncome
                + ", initialBudget=" + initialBudget
                + '}';
    }
}
