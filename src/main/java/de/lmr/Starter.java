package de.lmr;

import de.lmr.connector.PrinterConnector;
import de.lmr.connector.TouchscreenConnector;

public class Starter {

    public static void main(String[] args){
        PrinterConnector printerConnector = PrinterConnector.getInstance("/dev/ttyS0");
        TouchscreenConnector touchscreenConnector = TouchscreenConnector.getInstance("/dev/ttyUSB0", printerConnector);

        touchscreenConnector.run();

        touchscreenConnector.close();
        printerConnector.close();
    }
}
