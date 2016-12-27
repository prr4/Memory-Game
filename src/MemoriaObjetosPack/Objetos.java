package MemoriaObjetosPack;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JButton;

public class Objetos extends JButton {
    
    private Icon imagemPadrao;
    private Icon imagemObjeto;
    private int id;
    
    public Objetos() {}
    
    public Objetos(Icon imagemPadrao, Icon imagemObjeto, int id){
        this.id = id;
        this.imagemPadrao = imagemPadrao;
        this.imagemObjeto = imagemObjeto;
        setBackground(new Color(102, 45, 145));
        setImagemPadrao();
    }
    
    public void setImagemPadrao() {
        this.setIcon(imagemPadrao);
    }
    public void setImagemObjeto() {
        this.setIcon(imagemObjeto);
    }
    public Icon getImagemObjeto(){
        return this.imagemObjeto;
    }
    public int getID() {
        return this.id;
    }
}
