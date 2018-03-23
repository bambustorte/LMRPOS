package connector;

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
     * Just some old print test
     * @deprecated old stuff
     */
    @Deprecated
    public void printOldStuff(){
        write("              .;odooodxl.                " +
            "         ;odkXo.       :WK               " +
            "        xMMMX'K    .'.  'W:              " +
            "        xXKKxkOl.,NMMWl0.W:              " +
            "       :Wc':xccc 0MNO: OcM.              " +
            "      dK.';codoollddl:' XK               " +
            "     xK                lM;               " +
            "    xX                .Wd                " +
            "    W;          cx.k  Xd             _   " +
            "    W.          X.0o kO           o0' ok " +
            "    W.         ::dx ,M...''.    o0;  .k. " +
            "    W.         xlk  ;Nc;do,:xOdd'  .kl   " +
            "    Oo       .l ::     :dOkkkK'  '0K.    " +
            "    .W.     ,:ldod.      .   ;KO. .,co   " +
            "     cN;      ..       . .     K0 .dx    " +
            "      .l0l.            x       0X;,:0    " +
            "  lcc  xo'Kxxo:'.      .'   .ox;  .      " +
            "  'o :o'lc    ;clN0 olloxolo:.           " +
            "   ;c:d:     .WxOo cd                    " +
            "     .        d0  :d\n\n", false);


        write(".ck00kc                 " +
            "                  ,OWKo::oXWd               " +
            "                lXXl.      dWN,             " +
            "           'x00WW,          xMW.            " +
            "          cMMMMN0:           XMo            " +
            "          NMMMMK'X           ;Mk            " +
            "          MMMMX..W'.  ;O0kc.  WO            " +
            "          WMMWo:OMN: lMMMWdNc NO            " +
            "          NxdOWMKc  .WMMMMloW.Wx            " +
            "         cW  .WWcxK cMMMMW'.W;Mo            " +
            "        .WNo;.':::' ;WMKc. ;llM;            " +
            "        XNkKWMWKxc'  ;kNWK0l KM.            " +
            "       cM;   .,cx0NWKx:. .  .WW             " +
            "       NK           .;coc   lMO             " +
            "      oM,                   KM:             " +
            "     .W0                   .WW              " +
            "     kM'                   oMx              " +
            "    .M0             ..     NW.              " +
            "    .Mo             W,.l  ;Ml               " +
            "    .M;            ;W dW  0N               l" +
            "    .M'            dO NO .Wl             .K0" +
            "    .M.            K;;M' dW.            .NK." +
            "    .M.           .N O0  WK            'WK  " +
            "    .M.           ;x.W' 'Md  ....     ,WO   " +
            "    .M.           x'OO  'MO0WMWWMWk. :Wo    " +
            "    .M'           0.W.  'Wx, ;. .lXWxN;    k" +
            "     W:          ,lcO    o.  oK    K0.    0W" +
            "     Nd         .K.,k      .lOKNX00O    .XW;" +
            "     kX        ,c.  lo     ,olloxKMMk   OWK " +
            "     ;M.      .0o';X.X            .xWX.  'lk" +
            "      Wk        lOKo0;        l,    oM0     " +
            "      OW'        :;           o,     KM' :0k" +
            "      .NN.                  .        lMc  ;0" +
            "       .XN'                lX        :M;   O" +
            "         xWx.              ..        oMk:,:N" +
            "   ;.    oW0Wd.            .K.      ,Wdlxxd'" +
            "   0Ol  ;W, kNM0l'                 xW;      " +
            "   K dx'N, xk .dXMMXkl.      k.  :NO.       " +
            "   0. lW, Od     .;oOWX c0Oxl:;lXX;         " +
            "   :o   .Xc     .oc'kW. Nl;oOKKx'           " +
            "    0. :N,      oWNWMl ;X                   " +
            "    .0KX.       :M,;:  K:", false);


        write(" _____ " +
            "< lol >" +
            " ----- " +
            "        \\   ^__^" +
            "         \\  (oo)\\_______" +
            "            (__)\\       )\\/\\" +
            "                ||----w |" +
            "                ||     ||", true);
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
//
//        write(hexStringToByteArray("1B2A00" + "14" + "14" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffe000000000007fc000000000000000000001ff" +
//                "ffe00000000003ffff00000000000000000001ff" +
//                "ffe0000000000fffffc0000000000000000001ff" +
//                "ffe0000000003e001bf0000000000000000001ff" +
//                "ffe000000000f8000078000000000000000001ff" +
//                "ffe000000001e000003c000000000000000001ff" +
//                "ffe0000000078000000e000000000000000001ff" +
//                "ffe00000000f00000007000000000000000001ff" +
//                "ffe00000003c00000003800000000000000001ff" +
//                "ffe00000007800000001c00000000000000001ff" +
//                "ffe0000000f000000001c00000000000000001ff" +
//                "ffe0000001e000000000e00000000000000001ff" +
//                "ffe0000003c000000000600000000000000001ff" +
//                "ffe00000078000000000700000000000000001ff" +
//                "ffe00000070000000000300000000000000001ff" +
//                "ffe000000e0000000000380000000000000001ff" +
//                "ffe000001e0000000000180000000000000001ff" +
//                "ffe000003e0000000000180000000000000001ff" +
//                "ffe000003f00000000001c0000000000000001ff" +
//                "ffe000007fc0000000001c0000000000000001ff" +
//                "ffe00001ffe00000ff800c0000000000000001ff" +
//                "ffe00003fff00003ffc00c0000000000000001ff" +
//                "ffe0000fff18000ffe600c0000000000000001ff" +
//                "ffe0000ffe08001ffe301c0000000000000001ff" +
//                "ffe0001ffe0c003ffc181c0000000000000001ff" +
//                "ffe0001ffe0c607ffc081c0000000000000001ff" +
//                "ffe0001ffc0fc07ff8081c0000000000000001ff" +
//                "ffe0003ff80f80fff00c1c0000000000000001ff" +
//                "ffe0001ff01e00fff0081c0000000000000001ff" +
//                "ffe0001fc07c00ffe0181c0000000000000001ff" +
//                "ffe0001fc1f8007f80381c0000000000000001ff" +
//                "ffe0000ffff0007000701c0000000000000001ff" +
//                "ffe0000ffee0003800e01c0000000000000001ff" +
//                "ffe0001e0060701c0fc01c0000000000000001ff" +
//                "ffe0001c00e0780fff001c0000000000000001ff" +
//                "ffe0001c00e03803fc001c0000000000000001ff" +
//                "ffe0001c0070780000001c0000000000000001ff" +
//                "ffe0001c007ff00000001c0000000000000001ff" +
//                "ffe0003c001fe00001e01c0000000000000001ff" +
//                "ffe00038000000001fe01c0000000000000001ff" +
//                "ffe000380000003fffc01c0000000000000001ff" +
//                "ffe0003c0000ffffe0001c0000000001fc0001ff" +
//                "ffe0003ffffffffc00001c0000000003ff0001ff" +
//                "ffe0003fffffb80000001c0000000007878001ff" +
//                "ffe000380000000000001c0000000007038001ff" +
//                "ffe000300000000000001c000000000f00c001ff" +
//                "ffe000700000000000001c000000000e00e001ff" +
//                "ffe000700000000000001c000000000e006001ff" +
//                "ffe000700000000000001c000000001c006001ff" +
//                "ffe000700000000000001c000000001c003001ff" +
//                "ffe000300000000000001c000000001c003001ff" +
//                "ffe000380000000000001c0000000038003001ff" +
//                "ffe000700000000000001c0000000078003001ff" +
//                "ffe000300000000000001c0000000078007001ff" +
//                "ffe000700000000000001c0000000070007001ff" +
//                "ffe000700000000000001c00000000e0006001ff" +
//                "ffe000700000000000000e00000000e0006001ff" +
//                "ffe000700000000018700e00000000e0006001ff" +
//                "ffe00070000000001c700e00000001c000c001ff" +
//                "ffe00070000000000c700e00000001c000c001ff" +
//                "ffe00070000000000c700f000000038000c001ff" +
//                "ffe00070000000000c7007000000038001c001ff" +
//                "ffe00070000000000c70070000000700018001ff" +
//                "ffe00070000000000c70070000000f00018001ff" +
//                "ffe00070000000000c7007003fff9e00038001ff" +
//                "ffe00030000000000c700381fffffc00070001ff" +
//                "ffe00030000000000c700387f001f8000f0001ff" +
//                "ffe00038000000000c7003df0000f0001e0001ff" +
//                "ffe00038000000000c3003fc000070003c0001ff" +
//                "ffe00018000000000c3003f00000e000787801ff" +
//                "ffe00018000000000c3003e00000c000fdfe01ff" +
//                "ffe0001c000000000c3003c03801c000ffff01ff" +
//                "ffe0001c000000000c3001801f1fe000fc0f81ff" +
//                "ffe0000c000000000c7001800ffffc00100f01ff" +
//                "ffe0000c000000000c3001800fffff80000f01ff" +
//                "ffe0000e000000000c7000800f800ff0001ff1ff" +
//                "ffe0000e000000000c7000001c0000f8070ff9ff" +
//                "ffe0000f000000000c3000007000001e03df81ff" +
//                "ffe00007000000000c700000c000000701ff81ff" +
//                "ffe00007000000000c70000180000003807fc1ff" +
//                "ffe00003000000000c30000000000001c003c1ff" +
//                "ffe00003800000000c70000000000000c000e1ff" +
//                "ffe00003800000000e70000000000000600061ff" +
//                "ffe00001c00000000c38000000000000300071ff" +
//                "ffe00001c00000000c1c000000000000300071ff" +
//                "ffe00000e00000001c0f8000000000001803e1ff" +
//                "ffe00000e00000001800e000000000001e03e1ff" +
//                "ffe000007000000030007000000000001fffc1ff" +
//                "ffe000007000000060303800000000001fffc1ff" +
//                "ffe0000038000000c0387c00000000001fff81ff" +
//                "ffe0000038000000c23ffc00000000001cfc01ff" +
//                "ffe000001c000001821ff80000000000181c01ff" +
//                "ffe000001c000001c73c000000000000180801ff" +
//                "ffe000000e000000eff0000000000000180001ff" +
//                "ffe000000e000000fff0000000000000380001ff" +
//                "ffe000000700000079f0000000000000380001ff" +
//                "ffe000000780000000e0000000000000700001ff" +
//                "ffe0000003c000000000000000000000700001ff" +
//                "ffe0000001c000000000000000000000e00001ff" +
//                "ffe0000000e000000000000000000001c00001ff" +
//                "ffe00000007000000000000000000003c00001ff" +
//                "ffe00000003c00000000000000000007800001ff" +
//                "ffe00000003e00000000000000000007000001ff" +
//                "ffe00000000f0000000000000000001e000001ff" +
//                "ffe00000000f8000000000000000003c000001ff" +
//                "ffe000000003c0000000000000000078000001ff" +
//                "ffe000000001f00000000000000000f0000001ff" +
//                "ffe000000000fc0000000000000001e0000001ff" +
//                "ffe0000000003f8000000000000007c0000001ff" +
//                "ffe0000000000fe00000000000001f00000001ff" +
//                "ffe00000000003ff000000000800fe00000001ff" +
//                "ffe00000000000ffe00000001ffff800000001ff" +
//                "ffe000000000007ffe00007c3fffe000000001ff" +
//                "ffe000000000003ffff81ffc1ffe0000000001ff" +
//                "ffe0000000000039fffffffc1e000000000001ff" +
//                "ffe0000000000030e7fffe1c1c000000000001ff" +
//                "ffe0000000000071c000c01c1c000000000001ff" +
//                "ffe0000000000061c000001c1c000000000001ff" +
//                "ffe0000000000061c000001c1c000000000001ff" +
//                "ffe00000000000e3c000001c1c000000000001ff" +
//                "ffe00000000000c38000fe1c18000000000001ff" +
//                "ffe00000000001c38001ffbc18000000000001ff" +
//                "ffe00000007e01c38001e3fc18000000000001ff" +
//                "ffe0000000ff81878000e0fc18000000000001ff" +
//                "ffe0000000f1c1870000e07c38000000000001ff" +
//                "ffe000000070e38f0000701c18000000000001ff" +
//                "ffe0000000707f8e0000380018000000000001ff" +
//                "ffe0000000303f0c00003c0018000000000001ff" +
//                "ffe0000000381f1c00001e0018000000000001ff" +
//                "ffe00000001c0e1800000f0018000000000001ff" +
//                "ffe00000000e0018000007c038000000000001ff" +
//                "ffe0000000070038000003e038000000000001ff" +
//                "ffe0000000078038000000f838000000000001ff" +
//                "ffe000000001e0700000003c78000000000001ff" +
//                "ffe000000000f0f00000001ff8000000000001ff" +
//                "ffe0000000007be000000007f8000000000001ff" +
//                "ffe0000000003fc000000001f0000000000001ff" +
//                "ffe0000000000f800000000000000000000001ff" +
//                "ffe00000000003000000000000000000000001ff" +
//                "ffe00000000000000000000000000000000001ff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff" +
//                "ffffffffffffffffffffffffffffffffffffffff"));




//        write(hexStringToByteArray("1B2A00" + "ff" + "ff" + "61616161"));
//
//        write(hexStringToByteArray("1B2A02" + "06" + "06" +
//                "0000000000000000" +
//                "0000000000000000" +
//                "3ff81ffc3ff81ffc" +
//                "3ff81ffc3ff81ffc" +
//                "3ff81ffc3ff81ffc" +
//                "3ff81ffc3ff81ffc" +
//                "3ff81ffc3ff81ffc" +
//                "3ff81ffc3ff81ffc" +
//                "3ff81ffc3ff81ffc" +
//                "3ff81ffc3ff81ffc" +
//                "3ff81ffc3ff81ffc" +
//                "3ff81ffc3ff81ffc" +
//                "3ff81ffc3ff81ffc" +
//                "0000000000000000" +
//                "0000000000000000" +
//                "0000000000000000" +
//                "0000000000000000" +
//                "0000000000000000" +
//                "001ff000001ff000" +
//                "00fffe0000fffe00" +
//                "01ffff0001ffff00" +
//                "03ffff8003ffff80" +
//                "07ffffc007ffffc0" +
//                "0fffffe00fffffe0" +
//                "1fe01ff01fe01ff0" +
//                "0fc007f00fc007f0" +
//                "078003c0078003c0" +
//                "0380018003800180" +
//                "0300010003000100" +
//                "0000000000000000" +
//                "0000000000000000" +
//                "0000000000000000"));

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
