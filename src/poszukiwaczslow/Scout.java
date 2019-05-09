/*
* zwiadowca podąża krętymi ścieżkami projektu w poszukiwaniu plików
* i folderów, wktórych może szukać plików ;)
*/
package poszukiwaczslow;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Mauy
 */
public class Scout implements Runnable
{
    
    BlockingQueue<File> queue;
    File mainPath;
    public Scout(BlockingQueue<File> queue, File mainPath)
    {
        this.queue = queue;
        this.mainPath = mainPath;
    }
    @Override
    public void run() {
        try{
        search(mainPath);
        queue.put(new File("null"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void search(File path) throws InterruptedException
    {
        File[] paths = path.listFiles();
        
        for (int i =0; i < paths.length; i++)
            if (paths[i].isDirectory())
                search(paths[i]);
             else
                queue.put(paths[i]);
        
    }
 
}
