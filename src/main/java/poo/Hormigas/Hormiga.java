package poo.Hormigas;
/**
 *
 * @author josue
 */
import java.util.Random;
import poo.Colonia.*;

public class Hormiga extends Thread{
    
    private int id;
    private String tipo;
    //private String tiposH[] = {"obrera","soldado","cria"};
    private Colonia c;
    
    public Hormiga(int id, String tipo, Colonia c){
        
        this.id=id;
        this.tipo=tipo;
        this.c =c;
    }
    
    public void run(){
        
        
        switch(tipo){
            
            case "obrera":
                System.out.println("obrera");
                c.comprobarInterfaz(this);
                
                break;
            case "soldado":
                System.out.println("soldado");
                break;
            case "cria":
                System.out.println("cria");
                break;
            default: 
                System.out.println("tipo no valido");
        }
    }

    public int getMiId() {
        
        return this.id;
    }
}
