/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
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
    private String filePath;
    private File file;
    private byte [] byte_file;
    private File copy_file;
    private String verifica = "Encryption";

    public FileCrypt(String chiave, String path_file) {
        this.filePath=path_file;
        this.file = new File (this.filePath);
        key = new SecretKeySpec(chiave.getBytes(), SYM_ALGORITHM);
    }
    
    
    public void modifica_file() throws IOException {
        //...
        //...
        byte_file=Files.readAllBytes(Paths.get(this.filePath));
    }
    
    public void encryption() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException {
        byte [] iv = inizializza_byte();
        Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
        cipher.init( Cipher.ENCRYPT_MODE, key, new IvParameterSpec( iv ) );
        this.key=new SecretKeySpec("0000000".getBytes(), SYM_ALGORITHM); //cambio la chiave per non rendere più disponibile la vecchia chiave (volendo)
        byte [] new_byte = cipher.doFinal( byte_file );
        Path path = Paths.get(this.filePath);
        Files.write(path, new_byte);
        
        FileWriter fileWriter = new FileWriter(this.filePath, true); // true per appendere al file
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(this.verifica);
        writer.newLine();
        writer.close();
    }
    
    public byte [] inizializza_byte() {
        SecureRandom random = new SecureRandom();
        byte [] iv = new byte [ SYM_KEY_SIZE / 8 ];
        random.nextBytes( iv );
        return iv;
    }
    
    
    public boolean decryption(String chiave) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException {
        this.copy_file = new File ("copy_"+this.filePath);
        this.copy_file = this.file; //copia nel caso la decriptazione avvenga con la chiave errata
        Scanner reader;
        byte [] iv = inizializza_byte();
        Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
        key = new SecretKeySpec(chiave.getBytes(), SYM_ALGORITHM);
        cipher.init( Cipher.ENCRYPT_MODE, key, new IvParameterSpec( iv ) );
        byte [] new_byte = cipher.doFinal( byte_file );
        Path path = Paths.get(this.filePath);
        Files.write(path, new_byte);
        
        reader = new Scanner(this.file);
        String str_verifica = "";
        while (reader.hasNextLine()) {
            str_verifica = reader.nextLine();
        }
        reader.close();
        if (this.verifica.equals(str_verifica)) return true; //ritorno true se la chiave fornita è corretta e dunque è stata eseguita una decriptazione corretta, in caso contrario ritorno false e si ripeterà il login

        this.file = this.copy_file;
        return false;
    }
}
