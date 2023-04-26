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
                //c.comprobarInterfaz(this);
                
                //hormiga IMPAR
                c.entraColonia(this);
                c.saleColonia(this, eligeSalida());
                c.recogeAlimentos(this);
                c.entraColonia(this);
                
                
                //HORMIGA PAR
                
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
}
