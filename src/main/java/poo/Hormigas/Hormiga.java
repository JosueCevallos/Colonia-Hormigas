package poo.Hormigas;
/**
 *
 * @author josue
 */
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import poo.Colonia.*;

public class Hormiga extends Thread{
    
    private int id;
    private String tipo;
    //private String tiposH[] = {"obrera","soldado","cria"};
    private Colonia c;
    private int iteraciones;
    
    public Hormiga(int id, String tipo, Colonia c){
        
        this.id=id;
        this.tipo=tipo;
        this.c =c;
        
    }
    
    public void run(){
        
        
        switch(tipo){
            
            //while para que nunca terminen
            case "obrera": 
                
                if (id%2==0){
                    //hormiga PAR
                    c.entraColonia(this);
                    while(true){
                        
                        c.recogeComidaAlmacen(this);
                        c.trasladaComida(this);
                        c.depositaAlimentos(this);
                        this.iteraciones++;
                        if (iteraciones==5){
                            System.out.println("hormiga descansa");
                        }
                        System.out.println(this.getMiId(tipo)+"lleva "+getIteraciones());
                    }
                    
                }else { //hormiga IMPAR
                    c.entraColonia(this);
                    while(true){
                        c.buscaComida(this);
                        c.guardaComidaA(this);
                        this.iteraciones++;
                        System.out.println(this.getMiId(tipo)+" lleva "+this.getIteraciones());
                        if(iteraciones==5){ //SERIAN 10
                            System.out.println(this.getMiId(tipo)+" descansa");
                        }
                    }       
                }    
                
            case "soldado":
                System.out.println("soldado");
                while(true){
                    System.out.println("hola");
                }
                //break; EL BREAK NUNCA SE ALCANZARIA POR EL WHILE (true)
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
    
    public int getIteraciones(){
        return this.iteraciones;
    }
}
