/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecc.elgamal;

import java.nio.ByteBuffer;

/**
 *
 * @author angelynz95
 */
public class ByteConverter {
    // Atribut
    private ByteBuffer byteBuffer;
    
    // Konstruktor
    public ByteConverter() {
        byteBuffer = ByteBuffer.allocate(Long.BYTES);
    }
    
    // Method
    public long convertBytesToLong(byte[] bytes) {
        byteBuffer.put(bytes);
        byteBuffer.flip();
        
        return byteBuffer.getLong();
    }
    
    public byte[] deconvertBytesFromLong(long x) {
        byteBuffer.putLong(x);
        
        return byteBuffer.array();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
