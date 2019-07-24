package de.lmr.connector;

import java.io.*;

public class PrinterConnector {
    static PrinterConnector instance = null;

    FileOutputStream out;

    /**
     * Singleton.
     * @param devicePath specifies the path to the printer device
     * @return the running instance
     */
    public static PrinterConnector getInstance(String devicePath) {
        if (instance == null)
            instance = new PrinterConnector(devicePath);
        return instance;
    }

    /***
     * Constructor for the connector.
     * @param devicePath Specifies the path to the printer device
     */
    private PrinterConnector(String devicePath){
        try {
            out = new FileOutputStream(devicePath);
        } catch (Exception e) {
            System.err.println("Could not open the printer device!");
            e.printStackTrace();
        }
    }

    /**
     * Cuts the paper without paper feed
     */
    public void cut(){
        write(hexStringToByteArray("1B69"));
    }

    /**
     * Prints out all printable characters
     */
    public void printAllChars(){
        for (byte i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; i++) { write(i); }
    }

    /**
     * Opens the connected cash drawer(s)
     * @param m the cash drawer number - 0 or 1.
     * @param n1 pulse width on time = (n1 × 2) milliseconds.
     * @param n2 pulse width off time = (n2 × 2) milliseconds.
     */
    public void openCashDrawer(byte m, byte n1, byte n2){
        write(hexStringToByteArray("1B70" + String.format("%02X", m) + String.format("%02X", n1) + String.format("%02X", n2)));
    }

    /**
     * Send one byte to the printer
     * @param byteToWrite the byte to send
     */
    public void write(byte byteToWrite){
        byte[] bytes = {byteToWrite};
        write(bytes);
    }

    /**
     * Send an array of bytes to the printer
     * @param bytes the byte array to send
     */
    public void write(byte[] bytes){
        try {
            out.write(bytes);
        }catch (Exception e){
            System.err.println("Failed to send data to printer!");
            e.printStackTrace();}
    }

    /**
     * Send an ascii string to the printer
     * @param toWrite the string to send
     * @param cut whether to feed and cut after the print or not
     */
    public void write(String toWrite, boolean cut){
        char [] charArray = toWrite.toCharArray();
        byte[] bytes = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            bytes[i] = (byte)charArray[i];
        }

        write(bytes);

        if (cut)
            feedAndCut(4);
    }

    /**
     * Linefeed and cut
     * @param n lines to feed
     */
    public void feedAndCut(int n){
        write(hexStringToByteArray("1B64" + String.format("%02X", n)));
        write(hexStringToByteArray("0C"));
    }

    /**
     * To close the connection to the printer device
     */
    public void close(){
        try {
            out.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    /**
     * Converting a hex string to an byte array
     * @param s
     * @return
     */
    public byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    public void playTetris(){
        write(hexStringToByteArray("1B0705A4"));
        write(hexStringToByteArray("1B07029B"));
        write(hexStringToByteArray("1B0702A0"));
        write(hexStringToByteArray("1B0705A2"));
//        connector.write(connector.hexStringToByteArray("1B0702A4"));
//        connector.write(connector.hexStringToByteArray("1B0702A2"));
        write(hexStringToByteArray("1B0702A0"));
        write(hexStringToByteArray("1B07029B"));
        write(hexStringToByteArray("1B070599"));
    }

    public void setUpsidedown(boolean b){
        if(b)
            write(hexStringToByteArray("1B7B01"));
        else
            write(hexStringToByteArray("1B7B00"));
    }

    public void setFontSize(int width, int height){
        write(hexStringToByteArray("1D21" + width + height));
    }

    public void storeLogo(String data){
        write(hexStringToByteArray("1D2A084848" + data));
    }

    public void printLogo(int logoNumber){
        write(hexStringToByteArray("1D2F000" + logoNumber));
    }

    public void printLogo(int a, int b, String data){
        write(hexStringToByteArray("1B37"));
        write(hexStringToByteArray("1B2A00" + String.format("%02X", a) + String.format("%02X", b) + data));
        write(hexStringToByteArray("100531"));
    }

    public void playTone(String tone) {
        switch(tone){
            case "C":
                write(hexStringToByteArray("1B070590"));
                break;
            case "D":
                write(hexStringToByteArray("1B070592"));
                break;
            case "E":
                write(hexStringToByteArray("1B070594"));
                break;
            case "F":
                write(hexStringToByteArray("1B070595"));
                break;
            case "G":
                write(hexStringToByteArray("1B070597"));
                break;
            case "A":
                write(hexStringToByteArray("1B070599"));
                break;
            case "B":
                write(hexStringToByteArray("1B07059B"));
                break;
        }
    }
}
