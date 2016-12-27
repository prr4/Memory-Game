package MemoriaObjetosPack;

public class Pontuacao {
    private int pontos;
    private int tentativas;
    private int acertos;
        
    public int getPontos(){
        return pontos;
    }
    public void ganharPontos(){
        pontos += 10;
    }
    public void perderPontos(){
        pontos -= 2;
    }
    public int getTentativas(){
        return tentativas;
    }
    public void setTentativas(){
        tentativas += 1;
    }
    public int getAcertos(){
        return acertos;
    }
    public void setAcertos(){
        acertos += 1;
    }
}
