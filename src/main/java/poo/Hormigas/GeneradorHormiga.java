package poo.Hormigas;
/*
 * @author josue
 */
import poo.Colonia.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneradorHormiga extends Thread{
    
    private Hormiga h;
    private Colonia c;
    private int contObrera, idObrera=1, idSoldado=1, idCria=1, contHormigas;
    private final int TOTAL=10;
    
    public GeneradorHormiga(Colonia c){
        this.c =c;
    }
    
    
    public void run(){
        
        try {
            
            Random rand = new Random();
            Thread.sleep(rand.nextInt(3501)+800); //800 - 3500            
            int i=0;
            while(contHormigas<TOTAL){
                
                if(contObrera==3){
                    this.creaSoldado();
                    this.creaCria();
                    contObrera=0;
                }else{
                    this.creaObrera();
                    contObrera++; 
                }
                i++;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GeneradorHormiga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void creaObrera(){
        h = new Hormiga(c, "obrera", idObrera);
        idObrera++;
        contHormigas++;
        h.start();
    }
    
    public void creaSoldado(){
        h = new Hormiga(c, "soldado", idSoldado);
        idSoldado++;
        contHormigas++;
        h.start();
    }
    
    public void creaCria(){
        h = new Hormiga(c, "cria", idCria);
        idCria++;
        contHormigas++;
        h.start();
    }
        
}
