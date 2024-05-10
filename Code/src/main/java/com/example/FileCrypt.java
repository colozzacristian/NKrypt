/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
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
    private String verifica = "Encryption";

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


    public void pero() throws IOException {
        String key = "your_key_here"; // Chiave per l'XOR
        File inputFile = new File("peppopippo.txt"); // File di input
        File encryptedFile = new File("pippofinalpleasepippo.txt"); // File cifrato
        encryptedFile.createNewFile();

        try {
            encryptFile(inputFile, encryptedFile, key);
            System.out.println("File cifrato con successo.");
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante la cifratura del file: " + e.getMessage());
        }

        if (inputFile.exists()) {
            // Prova a cancellare il file
            if (inputFile.delete()) {
                System.out.println("Il file è stato cancellato con successo.");
            } else {
                System.out.println("Impossibile cancellare il file.");
            }
        } else {
            System.out.println("Il file non esiste.");
        }
    }

    // Metodo per cifrare il file utilizzando l'XOR con la chiave
    public static void encryptFile(File inputFile, File outputFile, String key) throws IOException {
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);

        byte[] buffer = new byte[1024];
        byte[] keyBytes = key.getBytes();
        int keyIndex = 0;

        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            for (int i = 0; i < bytesRead; i++) {
                buffer[i] ^= keyBytes[keyIndex]; // Esegui l'XOR tra il byte del file e il byte corrispondente della chiave
                keyIndex = (keyIndex + 1) % keyBytes.length; // Passa al successivo byte della chiave
            }
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();
    }

    
    public void update(CryptoList cryptolist) throws IOException {
        File decryptedfile = new File("tmp.txt"); // I create temporary file (decryption of the existing file) to read data
        PrintWriter pw = new PrintWriter(this.filePath);
        pw.close();
        FileOutputStream fos = new FileOutputStream(this.filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Crypto crypto : cryptolist.getCryptoList()) {
            oos.writeObject(crypto);
        }

        try (FileWriter writer = new FileWriter(filePath, true)) { //true -> mode append to file
            writer.write(this.verifica);
        }

        if (decryptedfile.createNewFile()) {
            System.out.println("Succeded in creating the tmp file.");
        } else {
            System.out.println("tmp file alredy exists.");
        }
        

        //file XORING

        try {
            encryptFile(this.file, decryptedfile, this.chiave);
            System.out.println("Succeded in decrypting the file");
        } catch (IOException e) {
            System.out.println("An error occurred during file encryption." + e.getMessage());
        }

        this.file.delete();

        this.file = new File(this.filePath);
        if (this.file.createNewFile()) {
            System.out.println("Succeded in creating the new file: " + filePath + ".");
        }
        else {
            System.out.println("File " + filePath + " alredy exist!");
        }

        try {
            copyFile(decryptedfile, this.file);
            System.out.println("Il file è stato copiato con successo nel nuovo percorso.");
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante la copia del file: " + e.getMessage());
        }
        

    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(sourceFile);
        FileOutputStream outputStream = new FileOutputStream(destFile);
        
        byte[] buffer = new byte[1024];
        int length;
        
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        
        inputStream.close();
        outputStream.close();
    }

    public Boolean readData(CryptoList cryptolist) throws IOException, ClassNotFoundException {
        
        File decryptedfile = new File("tmp.txt"); // I create temporary file (decryption of the existing file) to read data
        if (decryptedfile.createNewFile()) {
            System.out.println("Succeded in creating the tmp file.");
        } else {
            System.out.println("tmp file alredy exists.");
        }
        
        //file XORING

        try {
            encryptFile(this.file, decryptedfile, this.chiave);
            System.out.println("Succeded in decrypting the file");
        } catch (IOException e) {
            System.out.println("An error occurred during file encryption." + e.getMessage());
        }
        
        FileInputStream fis = new FileInputStream("tmp.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        while (true) {
                try {
                    System.out.println("yoin");
                    cryptolist.getCryptoList().add(new Crypto((Crypto) ois.readObject()));
                    //System.out.println("yoin");
                }
                catch (EOFException e) {
                    // All file readed
                    System.out.println("pera");
                    break;
                }
                catch (Exception ex) {
                    System.out.println("entra?");
                    String currentLine;
                    String lastLine="";
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        while ((currentLine = reader.readLine()) != null) {
                            lastLine = currentLine;
                        }
                    }
                    catch (IOException e) {
                        // Gestione dell'eccezione IOException
                        e.printStackTrace();
                    }
                    if (lastLine.equals(this.verifica)) {
                        decryptedfile.delete();
                        System.out.println(lastLine);
                        System.out.println(this.verifica);
                        System.out.println("pass ok.");
                        return true; //decryption is OK
                    } else {
                        decryptedfile.delete();
                        System.out.println(lastLine);
                        System.out.println(this.verifica);
                        System.out.println("pass not ok.");
                        return false;//decryption is not OK
                    }
                }
            }
        decryptedfile.delete();
        //return cryptolist;
        System.out.println("error (reading the verify String).");
        return false;
    }

    public String getChiave() {
        return this.chiave;
    }
}