/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passtest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.TreeSet;
import static java.lang.Math.floorMod;
import static java.lang.Math.ceil;

/**
 *
 * @author harab
 */
public class Passtest {
    private static final Hashtable<Integer, Character> allowedSymbols = new Hashtable<Integer, Character>(256);
    private static final char[] allSymbols = {'a', '<', 'b', ',', 'c', '.', 'd', '>', 'e', '?', 'f', '/', 'g', ';', 'h', ':', 'i',
    '|', 'j', '}', 'k', ']', 'l', '[', 'm', '{', 'n', '=', 'o', '+', 'p', '-', 'q', '_', 'r', ')', 's', '(', 't', '*', 'u', '&', 'v',
    '^', 'w', '%', 'x', '$', 'y', '#', 'z', '@', 'A', '!', 'B', '~', 'C','`', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
    'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    private static final TreeSet<Character> bannedSymbols = new TreeSet();
    private static final int outputlen = 12;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       initializeDict();
       MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
            byte[][] hash = new byte[5][];
            hash[0] = md.digest("p10".getBytes());
            hash[1] = md.digest("dist".getBytes());
            hash[2] = md.digest("2014".getBytes());
            hash[3] = md.digest("epso".getBytes());
            hash[4] = md.digest("t".getBytes());
            StringBuilder sb = new StringBuilder(hash[0].length);
            //for(int i = 0; i < hash[0].length && i < outputlen*(hash[0].length/outputlen); i+=(hash[0].length/(outputlen))) {
            for(int i = 0; i < hash[0].length; i++) {
                int b = 0;
                for(int j = 0; j < 2; j++) {
                    //System.out.println(hash[j][i]);
              //  for (int j = 0; j < hash.length; j++) {
                    b ^= hash[j][i];
                }
                //sb.append(allowedSymbols.get((byte)b));
                //System.out.println(b);
                //System.out.println(floorMod(b, allowedSymbols.size()));
                sb.append(allowedSymbols.get(floorMod(b, allowedSymbols.size())));
            }
            System.out.println((int)64/12);
            System.out.println(bannedSymbols.size());
            System.out.println(sb.toString().length());
            System.out.println(sb.toString());
            System.out.println(sb.length());
            System.out.println(sb.substring(0,outputlen));
            for(int i = 0, j=0; i < 64 && j < outputlen; i+=64/outputlen, j++) {
                System.out.print(sb.charAt(i));
            }
            //editText2.setText(Integer.toHexString(hash[0]));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        for(Entry<Byte, String> entry : dict.entrySet()) {
//            System.out.print(entry.getKey() + " - " +  entry.getValue());
//        }
    }
    
    private static void initializeDict() {
    	bannedSymbols.add('\"');
    	bannedSymbols.add('\\');
    	bannedSymbols.add('\'');
//        bannedSymbols.add("`");
//        bannedSymbols.add("~");
//        bannedSymbols.add("#");
//        bannedSymbols.add("%");
//        bannedSymbols.add("^");
//        bannedSymbols.add("(");
//        bannedSymbols.add(")");
//        bannedSymbols.add("-");
//        //bannedSymbols.add("+");
//        bannedSymbols.add("+");
//        bannedSymbols.add("{");
//        bannedSymbols.add("[");
//        bannedSymbols.add("}");
//        bannedSymbols.add("]");
//        bannedSymbols.add("|");
//        bannedSymbols.add(";");
//        bannedSymbols.add(":");
//        bannedSymbols.add("/");
//        bannedSymbols.add(".");
//        bannedSymbols.add(">");
//        bannedSymbols.add(",");
//        bannedSymbols.add("<");
//        bannedSymbols.add("=");
        int j = 0;
    	for(int i = 0; i < allSymbols.length; i++) {
            if(!bannedSymbols.contains(allSymbols[i])) {
                allowedSymbols.put(j++, allSymbols[i]);
            }
//            } else {
//                while(bannedSymbols.contains(String.valueOf(allSymbols[++j % allSymbols.length]))) {
//                        //j++;
//                }
//                allowedSymbols.put(String.valueOf(allSymbols[j % allSymbols.length]), (byte)i);
//            }
//            j++;
    	}
    }
}
