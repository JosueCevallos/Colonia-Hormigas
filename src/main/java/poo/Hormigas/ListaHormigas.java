package poo.Hormigas;

import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import poo.Controladores.Pausar;

public class ListaHormigas {
    
    private ArrayList<Hormiga> listaHormigas = new ArrayList <Hormiga>();
    private JTextField tf;
    private JTextArea tA;
    private Pausar p;
    
    public ListaHormigas(JTextField tf, Pausar p){
        this.tf=tf;
        this.p = p;
    }
    
    public ListaHormigas(JTextArea tA, Pausar p){
        this.tA=tA;
        this.p = p;
    }
    
    public synchronized void meter(Hormiga h){
        
        listaHormigas.add(h);
        if(tA==null){       //dependiendo si se añade al textArea o al TextField imprime una o la otra
            imprimirTF();
        }else{
            imprimirTA();
        }
        
        
    }
    
    public synchronized void sacar(Hormiga h){
        listaHormigas.remove(h);
        if(tA==null){
            imprimirTF();
        }else{
            imprimirTA();
        }
        
    }
    
    public void imprimirTF(){
        
        String contenido="";
        for(int i=0; i<listaHormigas.size(); i++)
        {
           contenido=contenido+listaHormigas.get(i).getMiId(listaHormigas.get(i).getTipo())+" ";
        }
        tf.setText(contenido);
        p.comprobar();
        
    }
    
    public void imprimirTA(){
        
        String contenido="";
        for(int i=0; i<listaHormigas.size(); i++)
        {
           contenido=contenido+listaHormigas.get(i).getMiId(listaHormigas.get(i).getTipo())+" ";
        }
        tA.setText(contenido);
        p.comprobar();
        
    }
}
