
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.PinState;


public class S0Interface implements GpioPinListenerDigital {

    boolean debug = false;
    GpioController gpio = null;
    GpioPinDigitalInput s0Pin = null;
    LinkedBlockingDeque<Long> s0Queue = null;

    public S0Interface(int pin, boolean debug) {
        this.debug = debug;

        this.gpio = GpioFactory.getInstance();

        if (pin == 9 || pin == 8) {
            this.s0Pin = this.gpio.provisionDigitalInputPin(RaspiPin.GPIO_09, "s0Pin");
        } else {
            this.s0Pin = this.gpio.provisionDigitalInputPin(RaspiPin.GPIO_09, "s0Pin", PinPullResistance.PULL_UP);
        }

        this.s0Queue = new LinkedBlockingDeque<Long>();
        this.s0Pin.addListener(this);
    }

    public S0Interface(int pin) {
        this(pin, false);
    }

    public void shutdown() {
        this.s0Pin.removeAllListeners();
        this.gpio.shutdown();
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        if (event.getState().isLow() == true) {
            Long timestamp = System.currentTimeMillis();
            if (this.debug == true) {
                System.out.println("" + timestamp);
            }
            this.s0Queue.addLast(timestamp);
        }
    }

    public List<Long> getTimestamps() {
        List<Long> timestamps = new ArrayList<Long>();
        this.s0Queue.drainTo(timestamps);
        return timestamps;
    }

    public List<Double> getFrequencies(List<Long> timestamps) {
        int len = timestamps.size();
        List<Double> frequencies = new ArrayList<Double>();
        for ( int idx = 1; idx < len; idx++ ) {
            frequencies.add( 1.0 / ((timestamps.get(idx) - timestamps.get(idx - 1)) / 1000.0) );
        }
        return frequencies;
    }

}
