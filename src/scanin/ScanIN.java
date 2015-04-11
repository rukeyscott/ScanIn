/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @scott purcell
 */
public class ScanIN extends JFrame {

   private static BufferedWriter writeFile;
   
   
    public static void main(String args[]){
        ScanIN parse = new ScanIN();
        parse.convert();

        System.exit(0);
    }

    private void convert()
    {
        int count ;
        //Input file which needs to be parsed
        String fileToParse = "foo.csv";
        BufferedReader fileReader = null;
         
        //Delimiter used in CSV file
        final String DELIMITER = ",";
        try
        {
            String line = "";
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileToParse));
            String outputName = fileToParse.toString().substring(0, 
                    fileToParse.toString().lastIndexOf(".")) + ".json"; 
            writeFile = new BufferedWriter(new FileWriter(new File(outputName)));
            writeFile.write("{\n");
            writeFile.write("\""+fileToParse+"\":[");
             
            //Read the file line by line
            while ((line = fileReader.readLine()) != null)
            {
                writeFile.write("\n{");
                count=1;
                //Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                for(String token : tokens)
                {
                   System.out.print(token);
                    writeFile.write("\n\"col" + count++ + "\":\"" + token + "\",");
                }
               
                writeFile.write("\n},");
                
            }
            JOptionPane.showMessageDialog(this, "Your CSV file has been succesfully converted to: "     + outputName, 
                    "System Dialog", JOptionPane.PLAIN_MESSAGE);
              writeFile.write("\n]");
             writeFile.write("\n}");
             
             writeFile.close();
        }
        catch (Exception e) {
             JOptionPane.showMessageDialog(this, "ERROR: CSV has more entries than columns, please fix. " , 
                            "System Dialog", JOptionPane.PLAIN_MESSAGE);                    
                    System.exit(-1); //print error message
        }
        finally
        {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

