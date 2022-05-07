/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

/**
 *
 * @author kayane
 */
public class TelaPrincipal extends JFrame {
    private JPanel painel;
    private ActionListener acaoBotoes;
    private String tipoLixo;
    private String lixeira;
    private int pontos;
    private JLabel pontuacao;
    private int quantidadeChances = 3;
    private JLabel jogadasRestantes;
    private JLabel resultado;
    
    public TelaPrincipal(){
        super("Quiz");
        
        // Cria um painel
        painel = new JPanel();
        this.add(painel); // Adiciona o painel na tela
        painel.setLayout(null);
        
        // Localização da tela, largura e altura
        this.setBounds(400, 150, 790, 500);
        
        // Quando fechar a tela o programa é encerrado
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
        
        // Lista de lixos
        ArrayList<String> listaLixos = new ArrayList();
        listaLixos.add("papel");
        listaLixos.add("plastico");
        listaLixos.add("vidro");
        listaLixos.add("metal");
        listaLixos.add("organico");
        listaLixos.add("ambulatorial");
        listaLixos.add("madeira");
        
        // Botão com lixo aleatório
        JButton lixo = new JButton("lixo");
        lixo.setName("lixo");
        lixo.setEnabled(false);
        this.painel.add(lixo);
        lixo.setBounds(300, 10, 200, 200); // Adiciona o botão em uma posição
        
        // Altera para um lixo aleatório
        alteraLixo(lixo, listaLixos);
        
        // Label que mostra a pontuação
        this.pontuacao = new JLabel("Pontuação: 0");
        this.painel.add(this.pontuacao);
        this.pontuacao.setBounds(550, 5, 100, 20);
        
        // Label que mostra a quantidade de chances restantes
        this.jogadasRestantes = new JLabel("Jogadas restantes: " + quantidadeChances);
        this.painel.add(this.jogadasRestantes);
        this.jogadasRestantes.setBounds(550, 30, 200, 20);
        
        // Label que mostra se errou
        this.resultado = new JLabel("Resultado");
        this.painel.add(this.resultado);
        this.resultado.setBounds(340, 280, 200, 200);
        
            
        // Ação dos botões das lixeiras
        acaoBotoes = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // getSource() retorna um objeto Object que é pai de todos os outros, incluindo JButton
                JButton botao = (JButton) e.getSource();
                String tipoLixo = lixo.getName();
                String lixeira = botao.getName();
                
                System.out.println("Lixo: " + tipoLixo);
                    
                if(tipoLixo == "papel" && lixeira == "lixeiraPapel"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "plastico" && lixeira == "lixeiraPlastico"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "vidro" && lixeira == "lixeiraVidro"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "metal" && lixeira == "lixeiraMetal"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "organico" && lixeira == "lixeiraOrganica"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "ambulatorial" && lixeira == "lixeiraAmbulatorial"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "madeira" && lixeira == "lixeiraMadeira"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, listaLixos);
                    pontos += 1;
                }
                else{
                    System.out.println("Errou");
                    resultado.setText("Errou, tente novamente.");
                    quantidadeChances -= 1;
                }
                // Atualiza os pontos
                pontuacao.setText("Pontuação: " + pontos);
                
                // Atualiza as chances restantes
                jogadasRestantes.setText("Jogadas restantes: " + quantidadeChances);
                
                if(quantidadeChances == 0){
                    resultado.setText("Fim de Jogo, você perdeu!");                    
                    finalizaJogo();
                }
            }
        };
        
        // Lista de lixeiras com o caminho da imagem
        Map<String, String> listaLixeiras = new HashMap<>();        
        listaLixeiras.put("lixeiraPapel", "src/images/img-lixeira-papel.jpg");
        listaLixeiras.put("lixeiraPlastico", "src/images/img-lixeira-plastico.png");
        listaLixeiras.put("lixeiraVidro", "src/images/img-lixeira-vidro.jpg");
        listaLixeiras.put("lixeiraMetal", "src/images/img-lixeira-metal.jpg");
        listaLixeiras.put("lixeiraOrganica", "src/images/img-lixeira-organica.jpg");
        listaLixeiras.put("lixeiraAmbulatorial", "src/images/img-lixeira-ambulatorial.jpg");
        listaLixeiras.put("lixeiraMadeira", "src/images/img-lixeira-madeira.png");
        
        // Cria os botões de lixeiras
        int x = 10;
        for(String key : listaLixeiras.keySet()){
            // Cria uma imagem
            Icon imagemLixeira = new ImageIcon(listaLixeiras.get(key));
            
            JButton lixeira = new JButton(key);
            lixeira.setName(key);
            this.painel.add(lixeira);
            lixeira.setBounds(x, 240, 100, 100);
            lixeira.setIcon(imagemLixeira);            
            lixeira.addActionListener(acaoBotoes);
            x += 110;
        }
    }
    
    public JButton alteraLixo(JButton lixo, ArrayList<String> listaLixos){
        if(listaLixos.size() > 0){
            // Escolhe lixo aleatório da lista
            Random rand = new Random();
            int posicao = rand.nextInt(((listaLixos.size() - 1) > 0) ? listaLixos.size() - 1 : 1);
            String nomeLixo = listaLixos.get(posicao);
            listaLixos.remove(posicao); // Remove para não repetir

            // Recebe as características de um novo lixo
            lixo.setText(nomeLixo);
            lixo.setName(nomeLixo);
        }else{
            resultado.setText("Fim de jogo, você ganhou!.");
            
            finalizaJogo();
        }
        
        return lixo;
    }
    
    public void finalizaJogo(){
        // Finaliza o programa depois de alguns segundos
        int delay = 1000;                                    
        int interval = 100000000;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            public void run(){
                System.exit(0);
            }
        }, delay, interval);
    }
    
}
