
import time
import queue
import numpy as np

from multichain_commit import MultichainCommitter

import RPi.GPIO as GPIO
# package: https://pypi.org/project/RPi.GPIO/
# wiki: https://sourceforge.net/p/raspberry-gpio-python/wiki/Home/


class ButtonChain():

    def __init__(self, pin, rpcuser, rpcpasswd, rpchost, rpcport, chainname, timeout=5000, debug=False):
        self.mc = MultichainCommitter(rpcuser, rpcpasswd, rpchost, rpcport, chainname)
        print(self.mc.getinfo())
        
        self.debug = debug
        
        GPIO.setwarnings(False)
        GPIO.setmode(GPIO.BCM)
        if pin == 3 or pin == 2:
            GPIO.setup(pin, GPIO.IN, pull_up_down=GPIO.PUD_OFF)
        else:
            GPIO.setup(pin, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)

        GPIO.add_event_detect(pin, GPIO.FALLING, bouncetime=500)
        GPIO.add_event_callback(pin, self.button_callback)

    def button_callback(self, *args, **kwargs):
        ts = time.ctime()
        res = self.mc.commit("button_chain", ts)
        if self.debug:
            print(ts)
            print(res)

    
if __name__ == '__main__':
    print('Starting Button Chain...')
    pm = ButtonChain(pin=24, 
                     rpcuser='uname', 
                     rpcpasswd='passwd', 
                     rpchost='192.168.11.2', 
                     rpcport='4111', 
                     chainname='QAChain', 
                     debug=True)
                     
    time.sleep(100000)
    
