package input;

import java.util.ArrayList;

public final class Input {
    private int numberOfTurns;
    private InputInitialData initialData;
    private ArrayList<InputMonthlyUpdate> monthlyUpdates;

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public InputInitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(final InputInitialData initialData) {
        this.initialData = initialData;
    }

    public ArrayList<InputMonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public void setMonthlyUpdates(final ArrayList<InputMonthlyUpdate> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }

    @Override
    public String toString() {
        return "Input{" + "NumberOfTurns=" + numberOfTurns
                + ", initialData=" + initialData
                + ", monthlyUpdates=" + monthlyUpdates
                + '}';
    }
}
