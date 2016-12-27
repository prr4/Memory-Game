package MemoriaObjetosPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaInicial extends JFrame implements ActionListener {
    
    private TelaJogo telaJogo;
    private int idTema=1, idNivel=1;
    private ButtonGroup groupNivel, groupTema; 
    private JLabel labelCabecalho, labelTema, labelNivel;
    private JButton btSair, btIniciar, t1, t2, t3, t4, n1, n2, n3, n4;
    private JPanel painelCabecalho, painelSub, painelCentral, painelGeral, painelNivel, painelTema;
    private Container container;
    private GridLayout gridCabecalho, gridSub, gridCentral, gridNivel, gridTema;
      
    public TelaInicial () {
        super( "Jogo da Memória" );
        
        /***********************************************/
        // gerenciadores de layout
        gridCabecalho = new GridLayout(1,1,0,0);
        gridSub = new GridLayout(1,2,0,0);
        gridCentral = new GridLayout(4,1,0,0);
        gridNivel = new GridLayout(1,3,0,0);
        gridTema = new GridLayout(1,4,0,0);
        
        /***********************************************/
        // Opcoes de jogo
        t1 = new JButton(null, new ImageIcon(getClass().getResource("images/bri.png")));
        t2 = new JButton(null, new ImageIcon(getClass().getResource("images/esc.png")));
        t3 = new JButton(null, new ImageIcon(getClass().getResource("images/cas.png")));
        t4 = new JButton(null, new ImageIcon(getClass().getResource("images/ani.png")));
        groupTema = new ButtonGroup();
        groupTema.add(t1);
        groupTema.add(t2);
        groupTema.add(t3);
        groupTema.add(t4);
        t1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                idTema = 1; } });
        t2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                idTema = 2; } });
        t3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                idTema = 3; } });
        t4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                idTema = 4; } });        
       
        /***********************************************/
        // Nivel de dificuldade
        n1 = new JButton(null, new ImageIcon(getClass().getResource("images/nivel1.png")));
        n2 = new JButton(null, new ImageIcon(getClass().getResource("images/nivel2.png")));
        n3 = new JButton(null, new ImageIcon(getClass().getResource("images/nivel3.png")));
        n4 = new JButton(null, new ImageIcon(getClass().getResource("images/nivel4.png")));
        groupNivel = new ButtonGroup();
        groupNivel.add(n1);
        groupNivel.add(n2);
        groupNivel.add(n3);
        n1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                idNivel = 1; } });
        n2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                idNivel = 2; } });
        n3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                idNivel = 3; } });
        
        /***********************************************/
        // configurar btIniciar
        btIniciar = new JButton(null, new ImageIcon(getClass().getResource("images/iniciar.png")) );
        btIniciar.setBackground(new Color(0, 146, 69));
        btIniciar.addActionListener(this);
        btIniciar.setHorizontalAlignment( SwingConstants.RIGHT );
        
        /***********************************************/
        // configurar btSair
        btSair = new JButton(null, new ImageIcon(getClass().getResource("images/sair.png")) );
        btSair.setBackground(new Color(0, 146, 69));       
        btSair.addActionListener(this);
        btSair.setHorizontalAlignment( SwingConstants.RIGHT );
        
        /***********************************************/
        // configurar painelCabecalho
        // contem titulo = Jogo da Memoria
        painelCabecalho = new JPanel();
        painelCabecalho.setLayout(gridCabecalho);
        painelCabecalho.setBackground(new Color(0, 146, 69));
        labelCabecalho = new JLabel(new ImageIcon(getClass().getResource("images/titulo.png")));
        painelCabecalho.add(labelCabecalho);

        /***********************************************/
        // configurar painelSub
        painelSub = new JPanel();
        painelSub.setLayout(gridSub);
        painelSub.setBackground(new Color(0, 146, 69));
        painelSub.add(btIniciar);
        painelSub.add(btSair);
        
        /***********************************************/
        // configurar painelNivel
        // contem titulo = Jogo da Memoria
        painelNivel = new JPanel();
        painelNivel.setLayout(gridNivel);
        painelNivel.setBackground(new Color(102, 45, 145));
        painelNivel.add(n1);
        painelNivel.add(n2);
        painelNivel.add(n3);
        painelNivel.add(n4);

        /***********************************************/
        // configurar painelTema
        // contem titulo = Jogo da Memoria
        painelTema = new JPanel();
        painelTema.setLayout(gridTema);
        painelTema.setBackground(new Color(102, 45, 145));
        painelTema.add(t1);
        painelTema.add(t2);
        painelTema.add(t3);
        painelTema.add(t4);
        
        /***********************************************/
        // configurar painelCentral
        labelNivel = new JLabel(new ImageIcon(getClass().getResource("images/nivel.png")));
        labelTema = new JLabel(new ImageIcon(getClass().getResource("images/tema.png")));
        painelCentral = new JPanel();
        painelCentral.setLayout(gridCentral);
        painelCentral.setBackground(new Color(102, 45, 145));
        painelCentral.add(labelTema);
        painelCentral.add(painelTema);
        painelCentral.add(labelNivel);
        painelCentral.add(painelNivel);
        
        /***********************************************/
        // configurar painelGeral
        painelGeral = new JPanel();
        painelGeral.setLayout(new BorderLayout(10,10));
        painelGeral.setBackground(new Color(102, 45, 145));
        painelGeral.add(painelCabecalho, BorderLayout.NORTH);
        painelGeral.add(painelCentral, BorderLayout.CENTER);
        painelGeral.add(painelSub, BorderLayout.SOUTH);
        
        /***********************************************/
        // configurar container
        container = getContentPane();
        container.setLayout(new BorderLayout(5,5));
        container.setBackground(new Color(102, 45, 145));
        container.add(painelGeral);
        
        setSize(400, 500);
        setResizable(false);
        setVisible(true);
        
    }       

    @Override
    public void actionPerformed(ActionEvent e) {
        //sair do jogo
        if (e.getSource() == btSair){
            JOptionPane.showMessageDialog(null, "ATÉ BREVE!",
                    "Sair do Jogo", JOptionPane.INFORMATION_MESSAGE);
            System.exit( 0 );
        }
        // iniciar jogo
        else if (e.getSource() == btIniciar){
            JOptionPane.showMessageDialog(null, "TEMA: " + idTema
                    + " | NÍVEL: " + idNivel + "\nBOM JOGO!",
                    "Iniciar Jogo", JOptionPane.INFORMATION_MESSAGE);
            telaJogo = new TelaJogo(idNivel, idTema);
        }
    }
    
}
