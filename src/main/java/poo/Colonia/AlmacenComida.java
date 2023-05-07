package poo.Colonia;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import poo.Hormigas.Hormiga;
import poo.Hormigas.ListaHormigas;

/**
 *
 * @author josue
 */
public class AlmacenComida {
    
    private int undsComida;
    private JTextField comAlmacen = new JTextField();
    private Lock cerrojo = new ReentrantLock();
    private Condition faltaComida = cerrojo.newCondition();
    private Semaphore entrada = new Semaphore(10);
    
    public AlmacenComida(JTextField tfComAlmacen){
        this.comAlmacen = tfComAlmacen;
        //this.hormigasAlmacen = hormigasAlmacen;
    }
    
    public void acceder(Hormiga h, ListaHormigas hormigasAlmacen){
        
        try {
            entrada.acquire();
            hormigasAlmacen.meter(h);
            System.out.println(h.getMiId(h.getTipo()) + " accede al almacen");
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salir(Hormiga h, ListaHormigas hormigasAlmacen){
        entrada.release();
        hormigasAlmacen.sacar(h);
    }
    
    public void sumaComida(int n){
        
        try{
            cerrojo.lock();
            undsComida = undsComida + n;
            faltaComida.signal();
            comAlmacen.setText(String.valueOf(undsComida));
        }finally{
            
            cerrojo.unlock();  
        }
    }
    
    public void restaComida(int n){
        try {
            cerrojo.lock();
            while(undsComida<5){
                
                faltaComida.await();
            }
            undsComida = undsComida-n;
            comAlmacen.setText(String.valueOf(undsComida));
            
            } catch (InterruptedException ex) {
                Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
        cerrojo.unlock();
        }
    }
    
}
