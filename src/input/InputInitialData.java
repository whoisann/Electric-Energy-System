package input;

import java.util.ArrayList;

public final class InputInitialData {
    private ArrayList<InputConsumer> consumers;
    private ArrayList<InputDistributor> distributors;

    public ArrayList<InputConsumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<InputConsumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<InputDistributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<InputDistributor> distributors) {
        this.distributors = distributors;
    }

    @Override
    public String toString() {
        return "InitialData{" + "consumers=" + consumers
                + ", distributors=" + distributors + '}';
    }
}
