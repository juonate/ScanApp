/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escanerapp;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan
 */
public class EscanerApp {

    public static final String CARPETA = "C:\\destino";

    public static void main(String[] args) {

        try {
            System.out.println("Inicio del Programa");
            WatchDirectory watcher = new WatchDirectory();
            watcher.monitor.start();
            Process p = Runtime.getRuntime().exec("C:\\Windows\\twain_32\\Brother\\DS-620\\Capture Tool.exe");
            boolean no_exit = true;

            while (no_exit) {
                try {
                    p.exitValue();
                    no_exit = false;
                    p.destroy();
                    System.out.println("El programa finalizo");
                } catch (IllegalThreadStateException exception) {
                    if (watcher.estado == 1) {
                        JOptionPane optionPane = new JOptionPane("Reports are Generated");
                        JDialog dialog = optionPane.createDialog("Success!");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                        dialog.setModal(true);
                    }
                    no_exit = true;
                    //System.out.println("El programa aun no finaliza");
                }
            }


            /*Inicio de las acciones que siguen a la finalizacion del exe*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
