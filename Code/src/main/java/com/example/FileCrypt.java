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
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author User
 */
public class FileCrypt {
    private static final String SYM_ALGORITHM 	= "AES";
    private static final Integer SYM_KEY_SIZE 	= 128;
    
    private SecretKey key;
    private String filePath;
    private File file;
    private byte [] byte_file;
    private File copy_file;
    private String verifica = "Encryption";

    public FileCrypt(String chiave, String path_file) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        this.filePath=path_file;
        this.file = new File (this.filePath);
        if (this.file.createNewFile()) {
            System.out.println("Succeded in creating the new file: " + filePath + ".");
            FileWriter fileWriter = new FileWriter(this.filePath, true); // true per appendere al file
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(this.verifica);
            writer.newLine();
            writer.close();
        }
        else {
            System.out.println("File " + filePath + " alredy exist!");
        }
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(chiave.toCharArray(), "crypto".getBytes(), 65536, 256);
        //KeyGenerator kg = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(chiave.getBytes());
        //kg.init(128, secureRandom);
        //this.key = kg.generateKey();
        this.key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        //key=getKeyFromPassword(chiave, "crypt");
        aggiorna_byte_file();
        //key = new SecretKeySpec(chiave.getBytes(), SYM_ALGORITHM);
    }
    
    public static SecretKey getKeyFromPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return secret;
}
    
    public void aggiorna_byte_file() throws IOException {
        //...
        //...
        byte_file=Files.readAllBytes(Paths.get(this.filePath));
    }
    
    public void encryption() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException {
        byte [] iv = inizializza_byte();
        System.out.println("hey 1");
        Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
        cipher.init( Cipher.ENCRYPT_MODE, this.key);
        //this.key=new SecretKeySpec("0000000".getBytes(), SYM_ALGORITHM); //cambio la chiave per non rendere più disponibile la vecchia chiave (volendo)
        byte [] new_byte = cipher.doFinal( byte_file );
        Path path = Paths.get(this.filePath);
        Files.write(path, new_byte);

        System.out.println("hey 2");
        /*
        FileWriter fileWriter = new FileWriter(this.filePath, true); // true per appendere al file
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(this.verifica);
        writer.newLine();
        writer.close();
        */
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
        ////byte [] iv = inizializza_byte();
        ////Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5Padding" );
        /*
        try {
            key=getKeyFromPassword(chiave, "crypt");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */

        //
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        //

        //key = new SecretKeySpec(chiave.getBytes(), SYM_ALGORITHM);
        byte [] new_byte = cipher.doFinal( byte_file );
        Path path = Paths.get(this.filePath);
        Files.write(path, new_byte);
        
        reader = new Scanner(this.file);
        String str_verifica = "";
        if (reader.hasNextLine()) {
            str_verifica = reader.nextLine();
        }
        else {
            System.out.println("Something went wrong.");
        }
        reader.close();
        if (this.verifica.equals(str_verifica)) return true; //ritorno true se la chiave fornita è corretta e dunque è stata eseguita una decriptazione corretta, in caso contrario ritorno false e si ripeterà il login

        this.file = this.copy_file;
        return false;
    }
}
