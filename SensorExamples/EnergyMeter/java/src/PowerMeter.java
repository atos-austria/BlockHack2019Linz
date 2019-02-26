
import java.util.ArrayList;
import java.util.List;


public class PowerMeter extends S0Interface {

    public PowerMeter(int pin, boolean debug) {
        super(pin, debug);
    }

    public PowerMeter(int pin, int timeout_ms) {
        super(pin);
    }

    public List<Double> getPowerConsumptionsKw(List<Double> frequencies) {
        List<Double> powerValuesKw = new ArrayList<Double>();
        for ( double frequency: frequencies ) {
            powerValuesKw.add(frequency / (1000.0 / 3600.0));
        }
        return powerValuesKw;
    }

    public static void main(String[] args) {
        PowerMeter powerMeter = new PowerMeter(9, true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            System.out.println("stopping..");
        }
        powerMeter.shutdown();

        List<Double> powerReadings = powerMeter.getPowerConsumptionsKw(powerMeter.getFrequencies(powerMeter.getTimestamps()));
        System.out.print("| ");
        for ( double pwr: powerReadings) {
            System.out.print(pwr + " | ");
        }
        System.out.println();
    }
}