package project1;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

        //gia na apothikeuontai ta messages sto arxeio log
public class FilesAccess 
{
    DateTimeFormatter prn = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime prnnow = LocalDateTime.now();
        
    public void writer(String action,String msg)
    {
        String text = "log.txt";
        
        try
        {
             PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(text, true))); //the true will append the new data           
             writer.println("TIME: "+prn.format(prnnow)+" || ACTION: "+action+" || MESSAGE: "+msg+"\n");
             writer.close();
        }
        catch(FileNotFoundException | UnsupportedEncodingException e)
        {
             System.out.println(e);
        } 
        catch (IOException ex) 
        {
            java.util.logging.Logger.getLogger(FilesAccess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
