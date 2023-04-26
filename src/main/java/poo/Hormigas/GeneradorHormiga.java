package poo.Hormigas;
/*
 * @author josue
 */
import poo.Colonia.*;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneradorHormiga extends Thread{
    
    private Hormiga h;
    private Colonia c;
    //private int n=0, contO=0, contS=0, contC=0, idO=0,idS=0,idC=0;
    private String[] tiposH = {"obrera","soldado","cria"};
    private String tipo;
    //private CountDownLatch c;
    
    public GeneradorHormiga(Colonia c){
        this.c =c;
    }
    
    
    public void run(){
        
        h = new Hormiga(1,"obrera",c);
        h.start();
        
    }
        
}
