/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escanerapp;

/**
 *
 * @author Juan
 */
import java.io.File;
import java.io.IOException;
 
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
 
public class WatchDirectory {
    // A hardcoded path to a folder you are monitoring .
    public static final String FOLDER =  "C:\\destino";
    protected File folder = new File(FOLDER);
    public int estado = 0;
    final long pollingInterval = 1000;
    public FileAlterationObserver observer = new FileAlterationObserver(folder);
    public FileAlterationMonitor monitor = new FileAlterationMonitor(pollingInterval);
 
    public WatchDirectory() throws Exception {
        // The monitor will perform polling on the folder every 5 seconds
        
 
        if (!folder.exists()) {
            // Test to see if monitored folder exists
            throw new RuntimeException("El Directorio no existe: " + FOLDER);
        }
 
        
        FileAlterationListener listener = new FileAlterationListenerAdaptor() {
            // Is triggered when a file is created in the monitored folder
            @Override
            public void onFileCreate(File file) {
                try {
                    // "file" is the reference to the newly created file
                    estado = 1;
                    System.out.println("Archivo creado: "
                            + file.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
 
            // Is triggered when a file is deleted from the monitored folder
            @Override
            public void onFileDelete(File file) {
                try {
                    // "file" is the reference to the removed file
                    System.out.println("Archivo eliminado: "
                            + file.getCanonicalPath());
                    // "file" does not exists anymore in the location
                    System.out.println("File still exists in location: "
                            + file.exists());
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        };
 
        observer.addListener(listener);
        monitor.addObserver(observer);
//        monitor.start();
    }
}