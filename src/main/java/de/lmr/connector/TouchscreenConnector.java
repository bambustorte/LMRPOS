package de.lmr.connector;

import java.io.FileInputStream;
import java.io.IOException;

public class TouchscreenConnector implements Runnable {
    static TouchscreenConnector instance = null;
    static public boolean running = true;

    FileInputStream in;
    PrinterConnector printer;

    public static TouchscreenConnector getInstance(String devicePath, PrinterConnector printer) {
        if (instance == null)
            instance = new TouchscreenConnector(devicePath, printer);
        return instance;
    }

    private TouchscreenConnector(String devicePath, PrinterConnector connector){
        try {
            in = new FileInputStream(devicePath);
            printer = connector;
        } catch (Exception e) {
            System.err.println("Could not open the touchscreen device!");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(running){
            try {
                in.read();

                System.out.println("opening cash drawer");

//                printer.openCashDrawer((byte)0,(byte)100,(byte)50);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public byte[] read(){
        byte[] bytes = new byte[10];

        try {
            while(bytes[0] != 88 && bytes[5] != 24) {
                System.out.println("read " + in.read(bytes) + " bytes");
                System.out.println("0 = " + bytes[0]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public void close(){
        running = false;
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
