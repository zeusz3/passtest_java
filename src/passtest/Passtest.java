/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passtest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.TreeSet;
import static java.lang.Math.floorMod;

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
            hash[0] = md.digest("test".getBytes());
            hash[1] = md.digest("test".getBytes());
            hash[2] = md.digest("test".getBytes());
            hash[3] = md.digest("test".getBytes());
            hash[4] = md.digest("test".getBytes());
            StringBuilder sb = new StringBuilder(hash[0].length);
            for(int i = 0; i < hash[0].length; i++) {
                int b = 0;
                for(int j = 0; j < hash.length; j++) {
                    b ^= hash[j][i];
                }
                sb.append(allowedSymbols.get(floorMod(b, allowedSymbols.size())));
            }
            System.out.println((int)64/12);
            System.out.println(bannedSymbols.size());
            System.out.println(sb.toString().length());
            System.out.println(sb.toString());
            System.out.println(sb.length());
            System.out.println(sb.substring(0,outputlen));
            for(int i = 0, j=0; i < hash[0].length && j < outputlen; i+=hash[0].length/outputlen, j++) {
                System.out.print(sb.charAt(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    private static void initializeDict() {
    	bannedSymbols.add('\"');
    	bannedSymbols.add('\\');
    	bannedSymbols.add('\'');
        bannedSymbols.add('`');
        bannedSymbols.add('~');
        bannedSymbols.add('#');
        bannedSymbols.add('%');
        bannedSymbols.add('^');
        bannedSymbols.add('(');
        bannedSymbols.add(')');
        bannedSymbols.add('-');
        bannedSymbols.add('+');
        bannedSymbols.add('{');
        bannedSymbols.add('[');
        bannedSymbols.add('}');
        bannedSymbols.add(']');
        bannedSymbols.add('|');
        bannedSymbols.add(';');
        bannedSymbols.add(':');
        bannedSymbols.add('/');
        bannedSymbols.add('.');
        bannedSymbols.add('>');
        bannedSymbols.add(',');
        bannedSymbols.add('<');
        bannedSymbols.add('=');
        int j = 0;
    	for(int i = 0; i < allSymbols.length; i++) {
            if(!bannedSymbols.contains(allSymbols[i])) {
                allowedSymbols.put(j++, allSymbols[i]);
            }
    	}
    }
}
