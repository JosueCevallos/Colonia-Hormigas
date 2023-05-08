package poo.Hormigas;
/**
 *
 * @author josue
 */
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.logging.Level;
import java.util.logging.Logger;
import poo.Colonia.*;
import poo.Controladores.Pausar;

public class Hormiga extends Thread{
    
    private Colonia c;
    private int id;
    private String tipo;
    private int iteraciones;
    
    public Hormiga(Colonia c, String tipo, int id){
        
        this.c =c;
        this.tipo = tipo;
        this.id = id;
    }
    
    @Override
    public void run(){
        
        
        switch(this.tipo){
            //while para que nunca terminenÇ
            //SOUT(NACE HORMIGA ... CONCYCLIBARRIER)
            case "obrera":
                
                if (id%2==0){
                    //hormiga PAR
                    c.entraColonia(this);
                    while(true){
                        //p.comprobar();
                        c.recogeComidaAlmacen(this);
                        //p.comprobar();
                        c.trasladaComida(this);
                        //p.comprobar();
                        c.depositaAlimentos(this);
                        //p.comprobar();
                        this.iteraciones++;
                        if (iteraciones==5){
                            c.come(this);
                            //p.comprobar();
                            c.descansa(this);
                            //p.comprobar();
                            this.iteraciones=0;
                        }
                        System.out.println(this.getMiId(this.getTipo())+"lleva "+getIteraciones());
                    }
                    
                }else { //hormiga IMPAR
                    c.entraColonia(this);
                    while(true){
                        //p.comprobar();
                        c.buscaComida(this);
                        //p.comprobar();
                        c.guardaComidaA(this);
                        //p.comprobar();
                        this.iteraciones++;
                        System.out.println(this.getMiId(tipo)+" lleva "+this.getIteraciones());
                        if(iteraciones==5){ //SERIAN 10
                            //p.comprobar();
                            c.come(this);
                            //p.comprobar();
                            c.descansa(this);
                            //p.comprobar();
                            this.iteraciones=0;
                        }
                        System.out.println("HOLA SOY OBRERA IMPAR "+this.getMiId(this.getTipo()));
                    }       
                }    

            case "soldado":
                System.out.println("HOLA SOY SOLDADO "+this.getMiId(this.getTipo()));
                //break; EL BREAK NUNCA SE ALCANZARIA POR EL WHILE (true)
                break;
            case "cria":
                System.out.println("HOLA SOY CRIA "+this.getMiId(this.getTipo()));
                break;
            default: 
                System.out.println("tipo no valido");
        }
    }
    /*
    public int getMiId() {
        
        return this.id;
    }*/
    
    public String getMiId(String tipo) {
        String str = "";
        char letra;
        if(tipo=="obrera"){
            letra = 'O';
            str = formato(letra);
        } else if(tipo=="soldado"){
            letra = 'S';
            str = formato(letra); 
        } else {
            letra = 'C';
            str = formato(letra); 
        }
        
        return str;
    }
    
    public String formato(char letra){
        
        String str="";
        if (id < 10) {
            str = "H"+letra+"0000"+id;
        } else if (id < 100) {
            str = "H" +letra+"000"+id;
        } else if (id < 1000) {
            str = "H"+letra+"00"+id;
        } else if (id < 10000) {
            str = "H"+letra+"0"+id;
        } else {
            str = "H"+letra+ id;
        }
        return str;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public int eligeSalida(){
        
        Random rand = new Random();
        return rand.nextInt(2)+1;
    }
    
    public int getIteraciones(){
        return this.iteraciones;
    }
    
    public void setTipo(String t){
        this.tipo = t;
    }
}
