package de.lmr;

import de.lmr.connector.PrinterConnector;
import de.lmr.connector.TouchscreenConnector;

public class Starter {

    public static void main(String[] args){

        PrinterConnector printerConnector = PrinterConnector.getInstance("/dev/ttyUSB0");

        TouchscreenConnector touchscreenConnector = TouchscreenConnector.getInstance("/dev/ttyUSB0", printerConnector);

//        while(true) {
//            byte[] bytes = touchscreenConnector.read();
//
//            String string = "x:";
//
//            string += bytes[2];
//            string += ", y:" + bytes[4];
//
//
//            System.out.println(string);
//        }

//        printerConnector.write(printerConnector.hexStringToByteArray("1B4A08"));//1mm feed

        touchscreenConnector.close();
        printerConnector.close();
    }
}
