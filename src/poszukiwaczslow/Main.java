/*
* Program ma wyszukiwać pliki w katalogu programu,
* a następnie wyszukiwać w nich podane słowo.
*
* edit: Należy uważać na słowa z polskimi znakami, ponieważ 
* program może ich nie wyszukać, jeżeli plikt txt będzie 
* kodowany w ANSI zamiast w UTF-8
*/
package poszukiwaczslow;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauy
 */
public class Main {

    public static void main(String[] args){
        
        BlockingQueue<File> bQueue = new ArrayBlockingQueue<File>(8); 
        new Thread(new Scout(bQueue, mainPath)).start();
        
        for (int i=0; i<16; i++)
             new Thread(new wordProspector(bQueue, keyWord)).start();
            
    }
    final static private File mainPath = new File (System.getProperty("user.dir"));
    
    final static private String keyWord = new String("się");
    
}   
