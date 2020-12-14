import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;
import input.Input;
import input.InputConsumer;
import input.InputDistributor;
import output.Output;
import output.OutputConsumer;
import output.OutputContract;
import output.OutputDistributor;


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
                    inputConsumer.getInitialBudget(), null);
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
                    inputDistributor.getInitialProductionCost(), new ArrayList<>());
            distributors.add(distributor);
        }

        Simulation.simulateMonth(consumers, distributors);
        for (int i = 0; i < input.getNumberOfTurns(); i++) {
            Simulation.monthlyUpdates(i, input, consumers, distributors);
            Simulation.simulateMonth(consumers, distributors);
        }

        ArrayList<OutputConsumer> outputConsumers = new ArrayList<>();
        ArrayList<OutputDistributor> outputDistributors = new ArrayList<>();

        for (Consumer consumer : consumers) {
            OutputConsumer outputConsumer = new OutputConsumer(consumer.getId(), consumer.isBankrupt(), consumer.getInitialBudget());
            outputConsumers.add(outputConsumer);
        }

        for (Distributor distributor : distributors) {
            ArrayList<OutputContract> outputContracts = new ArrayList<>();
            for (Contract contract : distributor.getContracts()) {
                OutputContract outputContract = new OutputContract(contract.getConsumerId(), contract.getPrice(), contract.getRemainedContractMonths());
                outputContracts.add(outputContract);
            }
            OutputDistributor outputDistributor = new OutputDistributor(distributor.getId(), distributor.getInitialBudget(), distributor.isBankrupt(), outputContracts);
            outputDistributors.add(outputDistributor);
        }

        Output output = new Output();
        output.setConsumers(outputConsumers);
        output.setDistributors(outputDistributors);
        mapper.writeValue(fileOut, output);

    }

}
