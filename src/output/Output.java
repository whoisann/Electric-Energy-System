package output;

import input.InputConsumer;
import input.InputDistributor;

import java.util.ArrayList;

public class Output {
    private ArrayList<OutputConsumer> consumers;
    private ArrayList<OutputDistributor> distributors;

    public ArrayList<OutputConsumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(ArrayList<OutputConsumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<OutputDistributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(ArrayList<OutputDistributor> distributors) {
        this.distributors = distributors;
    }

    @Override
    public String toString() {
        return "InitialData{" + "consumers=" + consumers
                + ", distributors=" + distributors
                + '}';
    }
}
