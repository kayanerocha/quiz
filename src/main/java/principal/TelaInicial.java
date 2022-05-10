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
    private JLabel nomeJogo;
    private JTextArea descricao;
    private JButton iniciaJogoFacil;
    private JButton iniciaJogoMedio;
    private JButton iniciaJogoDificil;
    private ActionListener iniciar;
    
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
        nomeJogo = new JLabel("Acerte a Lixeira");
        nomeJogo.setName("nomeJogo");
        this.painel.add(nomeJogo);
        nomeJogo.setBounds(170, 100, 200, 15);
        Font fonteNomeJogo = new Font("Arial", Font.BOLD, 20);
        nomeJogo.setFont(fonteNomeJogo);
        
        // Cria descrição do jogo
        descricao = new JTextArea("Escolha a cor correspondente a lixeira correta "
                + "que o lixo apresentado na tela deve ser jogado. É permitido "
                + "errar apenas 3 vezes. Boa sorte!");
        descricao.setName("descricao");
        this.painel.add(descricao);
        descricao.setBounds(80, 150, 365, 50);
        descricao.setEditable(false);
        descricao.setLineWrap(true);
        
        // Cria botão que inicia o jogo no modo fácil
        iniciaJogoFacil = new JButton("Fácil");
        iniciaJogoFacil.setName("facil");
        this.painel.add(iniciaJogoFacil);
        iniciaJogoFacil.setBounds(90, 250, 100, 30);
        iniciaJogoFacil.setBackground(Color.decode("#87CEFA"));
        iniciaJogoFacil.addActionListener(iniciaJogo()); // Insere uma ação no botão
        
        // Cria botão que inicia o jogo no modo médio
        iniciaJogoMedio = new JButton("Médio");
        iniciaJogoMedio.setName("medio");
        this.painel.add(iniciaJogoMedio);
        iniciaJogoMedio.setBounds(200, 250, 100, 30);
        iniciaJogoMedio.setBackground(Color.decode("#87CEFA"));
        iniciaJogoMedio.addActionListener(iniciaJogo()); // Insere uma ação no botão
        
        // Cria botão que inicia o jogo no modo difícil
        iniciaJogoDificil = new JButton("Difícil");
        iniciaJogoDificil.setName("dificil");
        this.painel.add(iniciaJogoDificil);
        iniciaJogoDificil.setBounds(310, 250, 100, 30);
        iniciaJogoDificil.setBackground(Color.decode("#87CEFA"));
        iniciaJogoDificil.addActionListener(iniciaJogo()); // Insere uma ação no botão
    }
    
    // Função que abre o JFrame principal
    public ActionListener iniciaJogo(){
        iniciar = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // getSource() retorna um objeto Object que é pai de todos os outros, incluindo JButton
                JButton botao = (JButton) e.getSource();
                String modo = botao.getName();
                TelaPrincipal telaPrincipal = new TelaPrincipal(modo);
                dispose(); // Fecha a tela inicial                
            }
        };        
        return iniciar;
    }
}
