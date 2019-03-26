/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saju
 */
public class WriteAndReadFromFile {

    public static void writeToFile(String fileName, String data, boolean append) {
//        String s = "bHello World ";

        File file = new File(fileName);

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {

            fos = new FileOutputStream(file, append);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            // Writes bytes from the specified byte array to this file output stream 
//            fos.write(data.getBytes());

            bw.write(data);
            bw.newLine();

        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        } finally {

            try {

                if (bw != null) {
                    bw.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }

            } catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }

        }
    }

    public static void readFromFile(String fileName) {
        List<String> lineList = new ArrayList<>();

        BufferedReader br = null;
        FileReader fr = null;

        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                lineList.add(sCurrentLine);
            }

        } catch (IOException e) {

            System.out.println("Error while read from file");
            e.printStackTrace();
            

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {
                System.out.println("Error while closing resource");
                ex.printStackTrace();

            }

        }
    }

    public static boolean searchFromFile(String fileName, String data) {
       
        BufferedReader br = null;
        FileReader fr = null;

        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                if(sCurrentLine.equals(data)){
                    return true;
                }
            }

        } catch (IOException e) {

            System.out.println("Error while read from file");
            e.printStackTrace();
            

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {
                System.out.println("Error while closing resource");
                ex.printStackTrace();

            }

        }
        
        return false;
    }
    public static void main(String[] args) {
        WriteAndReadFromFile wr = new WriteAndReadFromFile();
        wr.writeToFile("src/files/file1.txt", "Hello World ", true);
        
        wr.readFromFile("src/files/file1.txt");
    }

}
