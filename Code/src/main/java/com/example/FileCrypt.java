/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
    private static final Integer SYM_KEY_SIZE 	= 256;
    
    //private SecretKey key;
    private String chiave;
    private String filePath;
    private File file;
    //private byte [] byte_file;
    //private File copy_file;
    //private String verifica = "Encryption";

    public FileCrypt(String chiave, String path_file) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        this.filePath=path_file;
        this.file = new File (this.filePath);
        if (this.file.createNewFile()) {
            System.out.println("Succeded in creating the new file: " + filePath + ".");
        }
        else {
            System.out.println("File " + filePath + " alredy exist!");
        }
        this.chiave=chiave;
    }
    
    public void update(CryptoList cryptolist) throws IOException {
        PrintWriter pw = new PrintWriter(this.filePath);
        pw.close();
        FileOutputStream fos = new FileOutputStream(this.filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Crypto crypto : cryptolist.getCryptoList()) {
            oos.writeObject(crypto);
        }
    }

    public CryptoList readData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(this.filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        CryptoList cryptolist = new CryptoList(true);

        while (true) {
                try {
                    cryptolist.getCryptoList().add(new Crypto((Crypto) ois.readObject()));
                } catch (EOFException e) {
                    // Fine del file raggiunta, esci dal ciclo
                    break;
                }
            }
        return cryptolist;
    }

    public String getChiave() {
        return this.chiave;
    }

    
}
