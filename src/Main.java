import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;
import input.Input;
import input.InputConsumer;
import input.InputCostsChange;
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

        simulateMonth(consumers, distributors);
        for (int i = 0; i < input.getNumberOfTurns(); i++) {

            monthlyUpdates(i, input, consumers, distributors);
            simulateMonth(consumers, distributors);
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

    public static void monthlyUpdates(int month, Input input, ArrayList<Consumer> consumers, ArrayList<Distributor> distributors) {
        ArrayList<InputCostsChange> costsChanges = input.getMonthlyUpdates().get(month).getCostsChanges();
        ArrayList<InputConsumer> inputConsumers = input.getMonthlyUpdates().get(month).getNewConsumers();
        for (InputConsumer inputConsumer : inputConsumers) {
            HumanFactory humanFactory = HumanFactory.getInstance();
            Consumer consumer = (Consumer) HumanFactory.create("consumer");
            assert consumer != null;
            consumer.setConsumer(inputConsumer.getId(), inputConsumer.getMonthlyIncome(),
                    inputConsumer.getInitialBudget(), null);
            consumers.add(consumer);
        }
        // update monthly the distributors
        for (InputCostsChange costsChange : costsChanges) {
            for (Distributor distributor : distributors) {
                if (distributor.getId() == costsChange.getId()) {
                    distributor.setInitialInfrastructureCost(costsChange.getInfrastructureCost());
                    distributor.setInitialProductionCost(costsChange.getProductionCost());
                    break;
                }
            }
        }
    }

    public static void simulateMonth(ArrayList<Consumer> consumers, ArrayList<Distributor> distributors) {

        for (Distributor distributor : distributors) {
            distributor.updateContractPrice();
        }
        Distributor lowestPriceDistributor = Distributor.getLowestPriceDistributor(distributors);
        for (Consumer consumer : consumers) {
            if (!consumer.isBankrupt()) {
                if (consumer.getCurrentContract() == null || consumer.expiredContract()) {
                    assert lowestPriceDistributor != null;
                    Contract contract = new Contract(consumer.getId(), (int) lowestPriceDistributor.getContractPrice(),
                            lowestPriceDistributor.getContractLength());
                    consumer.setCurrentContract(contract);
                    consumer.setCurrentDistribuitor(lowestPriceDistributor);
                    lowestPriceDistributor.getContracts().add(contract);
                }
            }
        }

        for (Distributor distributor : distributors) {
            distributor.getContracts().removeIf(contract -> contract.getRemainedContractMonths() == 0);
            distributor.calculateTaxes();
        }


        for (Consumer consumer : consumers) {
            if (!consumer.isBankrupt()) {
                consumer.setIncome();
                consumer.payTaxes();
            }
        }

        for (Consumer consumer : consumers) {
            if (consumer.isBankrupt()) {
                consumer.getCurrentDistribuitor().getContracts().removeIf(contract ->
                        contract.getConsumerId() == consumer.getId());
            }
        }

        for (Distributor distributor : distributors) {
            if (!distributor.isBankrupt()) {
                distributor.payTaxes();
            }
        }

    }

}
