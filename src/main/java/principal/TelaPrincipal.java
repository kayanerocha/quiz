/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.awt.Color;
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
        ArrayList<String> nomesLixos = new ArrayList();
        nomesLixos.add("papel");
        nomesLixos.add("plastico");
        nomesLixos.add("vidro");
        nomesLixos.add("metal");
        nomesLixos.add("organico");
        nomesLixos.add("ambulatorial");
        nomesLixos.add("madeira");
        
        // Lista de lixos
        Map<String, String> listaLixos = new HashMap<>();
        listaLixos.put("papel", "src/images/img-papel.jpg");
        listaLixos.put("plastico", "src/images/img-plastico.png");
        listaLixos.put("vidro", "src/images/img-vidro.jpg");
        listaLixos.put("metal", "src/images/img-metal.jpeg");
        listaLixos.put("organico", "src/images/img-organico.jpg");
        listaLixos.put("ambulatorial", "src/images/img-ambulatorial.jpg");
        listaLixos.put("madeira", "src/images/img-madeira.jpg");
        
        // Label com lixo aleatório
        JLabel lixo = new JLabel("");
        lixo.setName("lixo");
        this.painel.add(lixo);
        
        // Altera para um lixo aleatório
        alteraLixo(lixo, nomesLixos, listaLixos);
        
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
                    alteraLixo(lixo, nomesLixos, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "plastico" && lixeira == "lixeiraPlastico"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, nomesLixos, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "vidro" && lixeira == "lixeiraVidro"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, nomesLixos, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "metal" && lixeira == "lixeiraMetal"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, nomesLixos, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "organico" && lixeira == "lixeiraOrganica"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, nomesLixos, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "ambulatorial" && lixeira == "lixeiraAmbulatorial"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo,nomesLixos, listaLixos);
                    pontos += 1;
                }else if(tipoLixo == "madeira" && lixeira == "lixeiraMadeira"){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(lixo, nomesLixos, listaLixos);
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
        
        // Lista de lixeiras com as cores correspondentes
        Map<String, Color> listaLixeiras = new HashMap<>();  
        listaLixeiras.put("lixeiraPapel", Color.BLUE);
        listaLixeiras.put("lixeiraPlastico", Color.RED);
        listaLixeiras.put("lixeiraVidro", Color.GREEN);
        listaLixeiras.put("lixeiraMetal", Color.YELLOW);
        listaLixeiras.put("lixeiraOrganica", Color.decode("#A0522D"));
        listaLixeiras.put("lixeiraAmbulatorial", Color.WHITE);
        listaLixeiras.put("lixeiraMadeira", Color.BLACK);
        
        // Cria os botões de lixeiras
        int x = 10;
        for(String key : listaLixeiras.keySet()){            
            // Cria um botão para lixeira
            JButton lixeira = new JButton(key);
            
            // Define as caracterísitcas dos botões
            lixeira.setName(key); // nome que vem na chave do HashMap
            this.painel.add(lixeira); // Adiciona no painel
            lixeira.setBounds(x, 240, 100, 100); // Posição e tamanho
            lixeira.setBackground(listaLixeiras.get(key)); // Cor de fundo
            lixeira.setText("");
            lixeira.addActionListener(acaoBotoes); // Adiciona a ação criaada anteriormente
            x += 110;
        }
    }
    
    // Função para alterar o lixo aleatoriamente durante o jogo
    public JLabel alteraLixo(JLabel lixo, ArrayList<String> nomesLixos, Map<String, String> listaLixos){        
        if(nomesLixos.size() > 0){          
            // Escolhe lixo aleatório da lista
            Random rand = new Random();
            int posicao = rand.nextInt(((nomesLixos.size() - 1) > 0) ? nomesLixos.size() - 1 : 1);
            String nomeLixo = nomesLixos.get(posicao);
            nomesLixos.remove(posicao); // Remove para não repetir
            
            Icon imagemLixo = new ImageIcon(listaLixos.get(nomeLixo));
            int largura = imagemLixo.getIconWidth();
            int altura = imagemLixo.getIconHeight();
            
            // Recebe as características de um novo lixo
            lixo.setBounds(300, 10, largura, altura); // posição e tamanho que muda de acordo com a imagem
            lixo.setName(nomeLixo);
            lixo.setIcon(imagemLixo);
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
