
package poo.Controladores;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josue
 */
public class Pausar {
    
    private boolean parado = false;
    private Lock cerrojo = new ReentrantLock();
    private Condition parar = cerrojo.newCondition();
    
    public void comprobar(){
        try{
            
            cerrojo.lock();
            while(parado)
                parar.await();
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Pausar.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            cerrojo.unlock();
        }
    }
    
    public void pausar(){
        try {
            cerrojo.lock();
            parado = true;
        }
        finally{
            cerrojo.unlock();
        }
    }
    
    public void reanudar(){
        try{
           cerrojo.lock();
           parado = false;
           parar.signalAll();
        }
        finally{
            cerrojo.unlock();
        }
    }
}
