
import time
import queue
import numpy as np

import RPi.GPIO as GPIO
# package: https://pypi.org/project/RPi.GPIO/
# wiki: https://sourceforge.net/p/raspberry-gpio-python/wiki/Home/


class S0Interface():

    s0_queue = queue.Queue()

    def __init__(self, pin, timeout=5000, debug=False):
        self.debug = debug
        GPIO.setwarnings(False)
        GPIO.setmode(GPIO.BCM)
        if pin == 3 or pin == 2:
            GPIO.setup(pin, GPIO.IN, pull_up_down=GPIO.PUD_OFF)
        else:
            GPIO.setup(pin, GPIO.IN, pull_up_down=GPIO.PUD_UP)

        GPIO.add_event_detect(pin, GPIO.FALLING)
        GPIO.add_event_callback(pin, self.pwm_callback)

    def pwm_callback(self, *args, **kwargs):
        ts = time.time()
        if self.debug:
            print(ts)
        self.s0_queue.put(ts)

    def get_ts(self):
        ts = []
        try:
            while True:
                ts.append(self.s0_queue.get_nowait())
        except queue.Empty:
            return np.array(ts)

    def get_freq(self, timestamps):
        tp = timestamps[1:] - timestamps[0:-1]
        freq = 1.0 / tp
        return freq


class PowerMeter(S0Interface):

    def get_kw(self):
        freq = self.get_freq(self.get_ts())
        kw = freq / (1000.0 /  3600.0)
        return kw


if __name__ == '__main__':
    print('Starting power meter measurement...')
    pm = PowerMeter(pin=3, debug=True)
    time.sleep(10)
    print('Results:')
    print(pm.get_kw())