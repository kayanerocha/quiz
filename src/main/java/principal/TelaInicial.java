/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.awt.Color;

import java.awt.Font;
import javax.swing.*;

import java.awt.event.*;

/**
 *
 * @author kayane
 */
public class TelaInicial extends JFrame{
    private JPanel painel;
    private JLabel nomeJogo = new JLabel("Acerte a Lixeira");
    private JTextArea descricao;
    private JButton difficultyButton;
    private JButton darkModeToggle;
    private boolean darkMode = false;
    private ActionListener iniciar;


    //Create Button
    private void CreateButton(String gameMode , String color, Integer BoundArg){
        this.difficultyButton = new JButton(gameMode.toUpperCase());
        this.difficultyButton.setName(gameMode);
        this.painel.add(difficultyButton);
        this.difficultyButton.setBounds(BoundArg, 250, 100, 30);
        this.difficultyButton.setBackground(Color.decode(color));
        this.difficultyButton.addActionListener(iniciaJogo()); // Insere uma ação no botão
    
    }


    
    public TelaInicial(){
        super("Bem-vindo(a)");
        
        // Cria um painel
        painel = new JPanel();
        this.add(painel); // Adiciona o painel na tela
        painel.setLayout(null);

        
        
        // Localização da tela, largura e altura
        this.setBounds(400, 150, 500, 500);
        
        // O programa não encerra quando a tela fecha
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.setVisible(true);
        
        // Cria o título do jogo
        nomeJogo.setName("nomeJogo");
        this.painel.add(nomeJogo);
        nomeJogo.setBounds(170, 100, 200, 15);
        
        Font fonteNomeJogo = new Font("Arial", Font.BOLD, 20);
        nomeJogo.setFont(fonteNomeJogo);
        
        // Cria descrição do jogo
        descricao = new JTextArea("""
                            Escolha a cor correspondente a lixeira correta 
                            que o lixo apresentado na tela deve ser jogado. Boa sorte!
                 """);
        descricao.setName("descricao");
        this.painel.add(descricao);
        descricao.setBounds(80, 150, 365, 50);
        descricao.setEditable(false);
        descricao.setLineWrap(true);

        //Cria botão de modo escuro
        Icon darkIcon = new ImageIcon("src/images/darkmodeicon2525.png");
        darkModeToggle = new JButton(darkIcon);
        darkModeToggle.setName("darkmode");
        painel.add(darkModeToggle);

        darkModeToggle.setBounds(400, 10, 50, 50);
        darkModeToggle.setText("");
        darkModeToggle.setOpaque(false);
        darkModeToggle.setContentAreaFilled(false);
        darkModeToggle.setBorderPainted(false);
        darkModeToggle.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                darkMode = !darkMode;
                painel.revalidate();
                painel.repaint();


                painel.setBackground(Color.decode(darkMode ? "#292929" : "#fcfcfc" ));
                descricao.setBackground(Color.decode(darkMode ? "#292929" : "#fcfcfc" ));
                descricao.setForeground(Color.decode( darkMode ? "#fcfcfc" : "#292929"));
                nomeJogo.setForeground(Color.decode( darkMode ? "#fcfcfc" : "#292929"));

                
                
            }
        });

       
        //Cria botões para dificuldades
        CreateButton("facil","#50f04a",  20);
        CreateButton("médio","#f0e54a", 140);
        CreateButton("dificil","#f04a4a", 250);
        CreateButton("infinito", "#040317", 360);
    }
    
    // Função que abre o JFrame principal
    public ActionListener iniciaJogo(){
        iniciar = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // getSource() retorna um objeto Object que é pai de todos os outros, incluindo JButton
                JButton botao = (JButton) e.getSource();
                String modo = botao.getName();
                new TelaPrincipal(modo, darkMode);
                dispose(); // Fecha a tela inicial                
            }
        };        
        return iniciar;
    }
}
