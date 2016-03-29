/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecc.elgamal;

/**
 *
 * @author Candy Olivia Mawalim
 */
public class HexaConverter {
    private String hexaAlpha = "0123456789ABCDEF";
    public int SixteenthPower(int number) {
        int res = 1;
        for (int i = 0; i < number; i++) {
            res *= 16;
        }
        return res;
    }
    public String convertCharToHexa(char in) {
        String out = "";
        
        int number = (char) in;
        out += hexaAlpha.charAt(number/16);
        out += hexaAlpha.charAt(number%16);
        
        return out;
    }
    
    public char deconvertHexaToChar(String hexa) {
        int temp = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 16; j++) {
                if (hexa.charAt(i)==hexaAlpha.charAt(j)) {
                    temp += j * (SixteenthPower(1-i));
                }                    
            }
        }
        char out = (char) (temp);
        return out;
    }
    
    public String convertStringToHexa(String in) {
        String res = "";
        for (int i = 0; i < in.length(); i++) {
            res += convertCharToHexa(in.charAt(i));
        }
        return res;
    }
    
    public String deconvertHexaToString(String hexa) {
        String res = "";
        for (int i = 0; i < hexa.length()/2; i++) {
            res += deconvertHexaToChar(hexa.substring(i*2,(i+1)*2));
        }
        return res;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        HexaConverter hc = new HexaConverter();
        
        System.out.println(hc.convertCharToHexa('z'));
        System.out.println(hc.deconvertHexaToChar(hc.convertCharToHexa('z')));
        System.out.println(hc.convertStringToHexa("Halo"));
        System.out.println(hc.deconvertHexaToString(hc.convertStringToHexa("Halo")));
        
        
    }
}
