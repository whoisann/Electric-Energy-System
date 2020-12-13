package input;

import java.util.ArrayList;

public class Input {
    private int NumberOfTurns;
    private InputInitialData initialData;
    private ArrayList<InputMonthlyUpdate> monthlyUpdates;

    public int getNumberOfTurns() {
        return NumberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        NumberOfTurns = numberOfTurns;
    }

    public InputInitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(InputInitialData initialData) {
        this.initialData = initialData;
    }

    public ArrayList<InputMonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public void setMonthlyUpdates(ArrayList<InputMonthlyUpdate> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }

    @Override
    public String toString() {
        return "Input{" + "NumberOfTurns=" + NumberOfTurns
                + ", initialData=" + initialData
                + ", monthlyUpdates=" + monthlyUpdates
                + '}';
    }
}
