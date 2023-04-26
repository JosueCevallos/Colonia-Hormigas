package poo.Colonia;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import poo.Hormigas.*;

/**
 *
 * @author josue
 */
public class Colonia {
    
    private Hormiga h;
    private ListaHormigas listaBuscanComida, listaRepelenInsecto, listaAlamacenComida, listaLlevanComida, 
                    listaHacenInstruccion, listaDescansan, listaComiendo, listaRefugio;
    private JTextField undComA, undComZonaComer;
    private int comAlmacen=2, comZonaComer=4;
    
    //MECANISMOS DE SINCRONIZACIÓN PARA ENTRADAS Y SALIDAS
    Semaphore entrada = new Semaphore(1);
    Semaphore salida1 = new Semaphore(1);
    Semaphore salida2 = new Semaphore(1);
    
    public Colonia(JTextField tfBuscanComida, JTextArea tfRepelenInsecto, JTextField tfAlmacenComida, JTextField tfLlevandoComida,
                JTextField tfHacenInstruccion, JTextField tfDescansando, JTextField tfComAlmacen, JTextField tfComZonaComer,
                JTextArea tfComiendo, JTextArea tfRefugio){
        
        this.listaBuscanComida = new ListaHormigas(tfBuscanComida);
        this.listaRepelenInsecto = new ListaHormigas(tfRepelenInsecto);
        this.listaAlamacenComida = new ListaHormigas(tfAlmacenComida);
        this.listaLlevanComida = new ListaHormigas(tfLlevandoComida);
        this.listaHacenInstruccion = new ListaHormigas(tfHacenInstruccion);
        this.listaDescansan = new ListaHormigas(tfDescansando);
        this.undComA = tfComAlmacen;
        this.undComZonaComer = tfComZonaComer;
        this.listaComiendo = new ListaHormigas(tfComiendo);
        this.listaRefugio = new ListaHormigas(tfRefugio);
        }
    
    
    
    public void comprobarInterfaz(Hormiga h){
        
        listaBuscanComida.meter(h);
        listaRepelenInsecto.meter(h);
        listaAlamacenComida.meter(h); 
        listaLlevanComida.meter(h);
        listaHacenInstruccion.meter(h);
        listaDescansan.meter(h); 
        listaComiendo.meter(h);
        listaRefugio.meter(h);
        undComA.setText(String.valueOf(comAlmacen));
        undComZonaComer.setText(String.valueOf(comZonaComer));
    }
    
    // ***************+ BLOQUE FUNCIONES ENTRADA Y SALIDA *******************
    public void entraColonia(Hormiga h){
        
        try {
            entrada.acquire();
            Thread.sleep(100);
            System.out.println(h.getMiId(h.getTipo())+" entra a la colonia");
            entrada.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saleColonia(Hormiga h, int salida){
        
        try {
            System.out.println(h.getMiId(h.getTipo())+" ha elegido la puerta "+salida);
            if(salida==1){
                salida1.acquire();
                Thread.sleep(100);
                System.out.println(h.getMiId(h.getTipo())+" sale por salida 1");
                salida1.release();
                
            }else{
                salida2.acquire();
                Thread.sleep(100);
                System.out.println(h.getMiId(h.getTipo())+" sale por salida 2");
                salida2.release();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //***************** FIN BLOQUE FUNCIONES ENTRADA Y SALIDA ********************

    public void recogeAlimentos(Hormiga h){
        
        try {
            listaBuscanComida.meter(h);
            System.out.println(h.getMiId(h.getTipo())+" recoge 5 alimentos");
            Thread.sleep(3900);
            listaBuscanComida.sacar(h);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
