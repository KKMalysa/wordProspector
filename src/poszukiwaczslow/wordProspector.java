/*
* Prospector niczym Krzysztof Kolumb zapuszcza się tam, gdzie nikt inny nie dotarł
* penetruje wnętrze pliku w poszukiwaniu zadanego słowa niczym chirurg szukający
* sprawnych narządów u motocyklisty po zderzeniu z drzewem przy prędkości 200km/h :D
*/

package poszukiwaczslow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Mauy
 */
class wordProspector implements Runnable
{
    BlockingQueue<File> queue;
    String keyWord;
    public wordProspector(BlockingQueue<File> queue, String keyWord)
    {
        this.queue = queue;
        this.keyWord = keyWord;
    }
    @Override
    public void run() {
        boolean done = false;
        while(!done)
        {
            File currentFile;
            try {
                currentFile = queue.take();
                if (currentFile.equals(new File("null")))
                {
                    queue.put(currentFile);
                    done = true;
                }
                else
                    search4Word(currentFile);
                    
            } catch (Exception ex) {
                ex.printStackTrace();
            }    
        }
    } 
    public void search4Word (File currentFile) throws FileNotFoundException  
    {
        int lineCounter =0;
        Scanner reader = new Scanner(new BufferedReader(new FileReader(currentFile)));
            
        while (reader.hasNextLine())
        {
            lineCounter++;
            
            if(reader.nextLine().contains(keyWord))
                System.out.println("Znaleziono szukane słowo w pliku: "+currentFile.getName()+" W linii nr: "+lineCounter);
                
        }
        reader.close();
    }
}
