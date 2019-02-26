## Software
##### Java classes *S0Interface.java* and *PowerMeter.java*:
Example usage in PowerMeter.java. Run as *__main__*:
```
sudo javac -classpath .:classes:/opt/pi4j/lib/'*' PowerMeter.java
sudo java -classpath .:classes:/opt/pi4j/lib/'*' PowerMeter
```

## Software Prerequisites
##### System
-   JRE  
    ```
    sudo apt install oracle-java8-jdk
    ```
-   wiringPi (_snapshot 1.2_)  
    Get the newest *snapshot* at https://git.drogon.net/?p=wiringPi
    ```
    tar xfz wiringPi-XX.tar.gz
    cd wiringPi-XX
    ./build
    ```
    Test installation:
    ```
    gpio -v
    gpio readall
    ```
-   pi4j  
    ```
    wget http://get.pi4j.com/download/pi4j-1.2-SNAPSHOT.deb
    sudo dpkg -i pi4j-1.2-SNAPSHOT.deb
    ```

## Bibliography
-   http://pi4j.com/index.html
-   http://wiringpi.com/