/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author User
 */
public class FileCrypt {
    private static final String SYM_ALGORITHM 	= "AES";
    private static final Integer SYM_KEY_SIZE 	= 128;
    
    private Key key;
    private String filepath;
    private File file;
    private byte [] byte_file;

    public FileCrypt(String chiave, String nome_file) {
        filepath="cartella/nome_file";
        this.file = new File (filepath);
        key = new SecretKeySpec(chiave.getBytes(), SYM_ALGORITHM);
    }
    
    
    public void modifica_file() throws IOException {
        //...
        //...
        byte_file=Files.readAllBytes(Paths.get(filepath));
    }
    
    public void encryption() throws
        NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
        IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException {
        byte [] iv = inizializza_byte();
        Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
        cipher.init( Cipher.ENCRYPT_MODE, key, new IvParameterSpec( iv ) );
        this.key=new SecretKeySpec("0000000".getBytes(), SYM_ALGORITHM); //cambio la chiave per non rendere più disponibile la vecchia chiave (volendo)
        byte [] new_byte = cipher.doFinal( byte_file );
        Path path = Paths.get("cartella/"+filepath);
        Files.write(path, new_byte);
    }
    
    public static byte [] inizializza_byte() {
    SecureRandom random = new SecureRandom();
    byte [] iv = new byte [ SYM_KEY_SIZE / 8 ];
    random.nextBytes( iv );
    return iv;
}
    
    
    public void decryption(String chiave) throws
    NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
        IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException {
        byte [] iv = inizializza_byte();
        Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
        key = new SecretKeySpec(chiave.getBytes(), SYM_ALGORITHM);
        cipher.init( Cipher.ENCRYPT_MODE, key, new IvParameterSpec( iv ) );
        byte [] new_byte = cipher.doFinal( byte_file );
        Path path = Paths.get("cartella/"+filepath);
        Files.write(path, new_byte);
    }
}
