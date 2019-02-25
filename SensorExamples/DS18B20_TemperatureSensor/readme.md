# DS18B20 Temperatur Sensor Modul
Protocol: 1-Wire

#### Pinout
connect 1 to GIPO4 	    [Pin7]
connect 2 to 3.3V 	    [Pin1]
connect 3 to Ground 	[Pin6]

![Alt text](sensor_pinout.png?raw=true "sensor Pinout")


#### Raspberry PI One-Wire Configuration
edit /boot/config.txt and add following line
```
dtoverlay=w1-gpio,gpiopin=4
```
restart PI

#### Examples provided using DS18B20
 writeSensorValueToMultichain.py <br />
 DS18B20getValue.py