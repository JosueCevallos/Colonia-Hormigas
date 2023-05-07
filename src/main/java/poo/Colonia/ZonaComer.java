package poo.Colonia;

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
public class ZonaComer {
    
    private int undsComida;
    private JTextField comidaZonaComer;
    private Lock cerrojo = new ReentrantLock();
    private Condition esperaComida = cerrojo.newCondition();
    
    public ZonaComer(JTextField tfComidaZonaComer){
        
        this.comidaZonaComer = tfComidaZonaComer;
    }

    public void acceder(Hormiga h, ListaHormigas hormigasEnZonaComer){
        
        hormigasEnZonaComer.meter(h);
        System.out.println(h.getMiId(h.getTipo()) + " accede a zona para comer");
    }
    
    public void salir(Hormiga h, ListaHormigas hormigasEnZonaComer){
        hormigasEnZonaComer.sacar(h);
        System.out.println(h.getMiId(h.getTipo())+" sale de la zona para comer");
    }
    
    public void sumaComida(int n){
        try{
            cerrojo.lock();
            undsComida += n;
            esperaComida.signal();
            comidaZonaComer.setText(String.valueOf(undsComida));
        }finally{
            cerrojo.unlock();
        }
        
    }
    
    public void restaComida(){
        try {
            cerrojo.lock();
            while(undsComida==0){
            
                esperaComida.await();
            }
            undsComida--;
            comidaZonaComer.setText(String.valueOf(undsComida));
        }catch (InterruptedException ex) {
                Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
            }
        finally{
            cerrojo.unlock();
        }
    }
    
}
