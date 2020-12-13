import com.fasterxml.jackson.databind.ObjectMapper;
import data.Human;
import data.HumanFactory;
import input.Input;
import input.InputConsumer;
import input.InputDistributor;
import output.Output;
import data.Consumer;
import data.Distributor;


import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        File fileIn = new File(args[0]);
        File fileOut = new File(args[1]);
        ObjectMapper mapper = new ObjectMapper();
        Input input = mapper.readValue(fileIn, Input.class);


        // data base for consumers
        ArrayList<Consumer> consumers = new ArrayList<>();
        for (InputConsumer inputConsumer : input.getInitialData().getConsumers()) {
            HumanFactory humanFactory = HumanFactory.getInstance();
            Consumer consumer = (Consumer) HumanFactory.create("consumer");
            assert consumer != null;
            consumer.setConsumer(inputConsumer.getId(), inputConsumer.getMonthlyIncome(),
                    inputConsumer.getInitialBudget());
            consumers.add(consumer);
        }

        // data base for distributors
        ArrayList<Distributor> distributors = new ArrayList<>();
        for (InputDistributor inputDistributor : input.getInitialData().getDistributors()) {
            HumanFactory humanFactory = HumanFactory.getInstance();
            Distributor distributor = (Distributor) HumanFactory.create("distributor");
            assert distributor != null;
            distributor.setDistributor(inputDistributor.getId(), inputDistributor.getContractLength(),
                    inputDistributor.getInitialBudget(), inputDistributor.getInitialInfrastructureCost(),
                    inputDistributor.getInitialProductionCost());
            distributors.add(distributor);
        }

    }
}
