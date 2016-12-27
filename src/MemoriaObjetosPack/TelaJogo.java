package MemoriaObjetosPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaJogo extends JFrame implements ActionListener {
    
    private Pontuacao pontuacao = new Pontuacao();
    private Objetos obj1, obj2;
    private int nivel, tema, nivelObj;
    private String temaObj;
            
    private boolean primeiroClique = true;
    private boolean acertou = true;
    private Object img1 = new Object();
    private Object img2 = new Object();
    
    private JButton btSair;
    private JLabel pontos, labelCabecalho;
    private JPanel painelCabecalho, painelJogo, painelSub, painelGeral;
    private Icon imagemPadrao;
    private ArrayList<Icon> imagens;
    private GridLayout gridCabecalho, gridJogo, gridSub;
    private ArrayList<Objetos> objetos;
    private final Container container;
    
    public TelaJogo(int nivel, int tema) {
        super( "Jogo da Memória" );
        
        /***********************************************/
        // inserir os parametros recebidos na variavel previamente definida
        this.nivel = nivel;
        this.tema = tema;
        // preparar jogo
        prepararModelo();
        prepararObjetos();
        // definir imagemPadrao
        imagemPadrao = new ImageIcon(getClass().getResource("images/" + temaObj + ".png"));

        /***********************************************/
        // gerenciadores de layout
        gridCabecalho = new GridLayout(1,1,0,0);
        gridSub = new GridLayout(1,2,0,0);    
        if(this.nivel == 1) gridJogo = new GridLayout(3,4,0,0);
        else if(this.nivel == 2) gridJogo = new GridLayout(3,8,0,0);
        else gridJogo = new GridLayout(4,9,0,0);
        
        /***********************************************/
        // configurar pontos
        pontos = new JLabel("Pontuação: " + String.valueOf(pontuacao.getPontos()));

        /***********************************************/
        // configurar btSair
        btSair = new JButton(null, new ImageIcon(getClass().getResource("images/sair.png")) );
        btSair.setBackground(new Color(0, 146, 69));
        btSair.addActionListener(this);
        btSair.setHorizontalAlignment( SwingConstants.RIGHT );
        
        /***********************************************/
        // configurar painelCabecalho
        painelCabecalho = new JPanel();
        painelCabecalho.setLayout(gridCabecalho);
        painelCabecalho.setBackground(new Color(0, 146, 69));
        labelCabecalho = new JLabel(new ImageIcon(getClass().getResource("images/titulo.png")));
        painelCabecalho.add(labelCabecalho);
        
        /***********************************************/
        // configurar painelJogo
        painelJogo = new JPanel();
        painelJogo.setLayout(gridJogo);
        painelJogo.setBackground(new Color(102, 45, 145));
        
        int numObj = 0;
        objetos = new ArrayList<Objetos>();
        for (Icon imagem : imagens) {
            numObj++;
            objetos.add(new Objetos(imagemPadrao, imagem, numObj));
            objetos.get(numObj-1).addActionListener(this);
        }
        Collections.shuffle(objetos);
        for (Objetos objeto : objetos) {
            painelJogo.add(objeto);
        }
        
        /***********************************************/
        // configurar painelSub
        // contem pontuacao, botao iniciar jogo e botao sair
        painelSub = new JPanel();
        painelSub.setLayout(gridSub);
        painelSub.setBackground(new Color(0, 146, 69));
        painelSub.add(pontos);
        painelSub.add(btSair);
        
        /***********************************************/
        // configurar painelGeral
        // contem todos os outros paineis
        painelGeral = new JPanel();
        painelGeral.setLayout(new BorderLayout(10,10));
        painelGeral.setBackground(new Color(102, 45, 145));
        painelGeral.add(painelCabecalho, BorderLayout.NORTH);
        painelGeral.add(painelJogo, BorderLayout.CENTER);
        painelGeral.add(painelSub, BorderLayout.SOUTH);       
        
        /***********************************************/
        // configurar container
        container = getContentPane();
        container.setLayout(new BorderLayout(5,5));
        container.setBackground(new Color(102, 45, 145));
        container.add(painelGeral);

        if(this.nivel == 1) { setSize(470,470); }
        else { if(this.nivel == 2) { setSize(930,470); }
        else { setSize(1070,600); } }
        
        setResizable(false);
        setVisible(true);
    }
    
    private void prepararModelo() {
        // verifica nivel
        if(this.nivel == 1) nivelObj = 12;
        else if(this.nivel == 2) nivelObj = 24;
        else nivelObj = 36;
        
        // verifica tema
        if(this.tema == 1) { temaObj = "bri"; }
        else { if(this.tema == 2) { temaObj = "esc"; }
        else { if(this.tema == 3) { temaObj = "cas"; }
        else { temaObj = "ani"; } } }
    }
    
    private void prepararObjetos() {
        imagens = new ArrayList<Icon>();
        int i;
        for(i=0; i<nivelObj; i++)
            this.imagens.add(new ImageIcon(getClass().getResource("images/" + temaObj + (i+1) + ".png")));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Se o evento for do botão Sair...
        if (e.getSource() == btSair){
            JOptionPane.showMessageDialog( null, "SAIR DO JOGO",
                    "Sair", JOptionPane.INFORMATION_MESSAGE );
            setVisible( false );
            System.gc();
        } else {
            // testa se é o primeiro clique
            if(primeiroClique){
                // testa se o jogador errou na jogada anterior
                if (!acertou){
                    obj1 = (Objetos) img1;
                    obj2 = (Objetos) img2;
                    obj1.setImagemPadrao();
                    obj2.setImagemPadrao();
                }
                img1 = e.getSource();
                obj1 = (Objetos) img1;
                obj1.setImagemObjeto();

                // indica que este foi o primeiro clique
                primeiroClique = false;
            }

            // se não for o primeiro clique
            else{
                img2 = e.getSource();
                obj2 = (Objetos) img2; 

                // verifica se jogador clicou no mesmo objeto
                if (obj1.getID() == obj2.getID()) {
                    acertou = false;
                    JOptionPane.showMessageDialog( null, "CLIQUE EM OUTRO OBJETO!",
                        "Atenção", JOptionPane.WARNING_MESSAGE );
                } else{

                    // atualiza imagem
                    obj2.setImagemObjeto();

                    // compara com o primeiro
                    if ( (obj1.getID()%2 == 0) && (obj1.getID()-1 == obj2.getID())
                            || (obj1.getID()%2 != 0) && (obj1.getID()+1 == obj2.getID()) ) {

                        acertou = true;
                        pontuacao.setAcertos();
                        pontuacao.ganharPontos();
                        pontos.setText("Pontuação: " + String.valueOf(pontuacao.getPontos()));

                        // Desabilita os botões
                        obj1.setEnabled( false );
                        obj2.setEnabled( false );

                        // se foi o último par encontrado, encerra jogo
                        if (pontuacao.getAcertos() == ((nivelObj)/2) ){
                            JOptionPane.showMessageDialog( null, "PARABÉNS!!!" +
                                    "\nVocê conseguiu " + pontuacao.getPontos() + " pontos",
                        "Fim", JOptionPane.INFORMATION_MESSAGE );
                            setVisible( false );
                            System.gc();
                        }
                    } else{
                        acertou = false;
                        pontuacao.perderPontos();
                        pontos.setText("Pontuação: " + String.valueOf(pontuacao.getPontos()));
                    }
                    pontuacao.setTentativas();
                    // indica que este foi o segundo clique
                    primeiroClique = true;
                }
            }
        }
    }
}
