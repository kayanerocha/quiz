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
    private String modo;
    private JPanel painel;
    private JLabel currentLixo;
    private JButton btnLixeira;
    private ActionListener escolheLixeira;
    private JLabel pontuacao;
    private int pontos;
    private JLabel errosPermitidos;
    private int numErrosPermitidos;
    private JLabel resultado;
    private JLabel contador;

    
    public TelaPrincipal(String modo, boolean darkMode){
        super("Quiz");
        
        // Modo que o usuário escolheu
        this.modo = modo;
        this.numErrosPermitidos = modo.equals("infinito") ? 1 : 3;
        
        // Cria um painel
        painel = new JPanel();
        this.add(painel); // Adiciona o painel na tela
        painel.setLayout(null);
        this.setBounds(400, 150, 790, 500); // Localização da tela, largura e altura
        painel.setBackground(Color.decode(darkMode ? "#292929" : "#fcfcfc" ));
        
        // Quando fechar a tela o programa é encerrado
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.setVisible(true);
        
        
        
        //--------------------------------------------------------------------//
        //                      Criação de um lixo aleatório                  //
        //--------------------------------------------------------------------//
        
        // Lista de nomes dos lixos
        ArrayList<String> nomesLixos = new ArrayList<String>(
            Arrays.asList("papel","plastico","vidro","metal","organica","ambulatorial","madeira")
        );
        
        // Lista de lixos com os caminhos das imagens
        Map<String, String> listaLixos = new HashMap<String, String>(){{
        put("papel", "src/images/Papel/");
        put("plastico", "src/images/Plastico/");
        put("vidro", "src/images/Vidro/");
        put("metal", "src/images/Metal/");
        put("organica", "src/images/Organico/");
        put("ambulatorial", "src/images/Ambulatorial/");
        put("madeira", "src/images/Madeira/");
        }};
        
        
        // Label com lixo aleatório
        currentLixo = new JLabel("");
        currentLixo.setName("lixo");
        this.painel.add(currentLixo);        
        // Altera para um lixo aleatório
        alteraLixo(currentLixo, nomesLixos, listaLixos);
        
        
        
        // Label que mostra a pontuação
        this.pontuacao = new JLabel("Pontuação: 0");
        this.pontuacao.setForeground(Color.decode( darkMode ? "#fcfcfc" : "#292929"));
        this.painel.add(this.pontuacao);
        this.pontuacao.setBounds(550, 5, 100, 20);
        
        // No modo fácil não tem limites de erros, então só aparece se for nos outros modos
        if(modo != "facil"){
            // Label que mostra a quantidade de chances restantes
            this.errosPermitidos = new JLabel("Erros Permitidos: " + numErrosPermitidos);
            this.errosPermitidos.setForeground(Color.decode( darkMode ? "#fcfcfc" : "#292929"));
            this.painel.add(this.errosPermitidos);
            this.errosPermitidos.setBounds(550, 30, 200, 20);
        }
        
        
        
        // Label que mostra se errou ou acertou
        this.resultado = new JLabel("");
        this.painel.add(this.resultado);
        this.resultado.setBounds(340, 280, 200, 200);  
        this.resultado.setForeground(Color.decode( darkMode ? "#fcfcfc" : "#292929"));

        //Label que mostra o contador do modo dificil
        this.contador = new JLabel("");
        this.painel.add(this.contador);
        this.contador.setBounds(550,50,200,20);
        this.contador.setForeground(Color.decode( darkMode ? "#fcfcfc" : "#292929"));
        
        
        
        //--------------------------------------------------------------------//
        //                      Criação das lixeiras                          //
        //--------------------------------------------------------------------//
            // Lista de lixeiras com as cores correspondentes
            Map<String, String> listaLixeiras = new HashMap<String, String>(){{
                put("lixeiraPapel", "src/images/Papel/lixeiraPapel.png");
                put("lixeiraPlastico", "src/images/Plastico/lixeiraPlastico.png");
                put("lixeiraVidro", "src/images/Vidro/lixeiraVidro.png");
                put("lixeiraMetal", "src/images/Metal/lixeiraMetal.png");
                put("lixeiraOrganica", "src/images/Organico/lixeiraOrganica.png");
                put("lixeiraAmbulatorial", "src/images/Ambulatorial/lixeiraAmbulatorial.png");
                put("lixeiraMadeira", "src/images/Madeira/lixeiraMadeira.png");
            }};  


            // Cria os botões das lixeiras
            int x = 10;
            for(String key : listaLixeiras.keySet()){            
                // Cria um botão para lixeira
                ImageIcon imageButton = new ImageIcon(listaLixeiras.get(key));
                btnLixeira = new JButton(key, imageButton);

                // Define as caracterísitcas dos botões
                btnLixeira.setName(key); // nome que vem na chave do HashMap
                this.painel.add(btnLixeira); // Adiciona no painel
                btnLixeira.setBounds(x, 240, 100, 100); // Posição e tamanho
                btnLixeira.setOpaque(false);
                btnLixeira.setContentAreaFilled(false);
                btnLixeira.setBorderPainted(false);
                btnLixeira.setText("");
                btnLixeira.addActionListener(escolherLixeira(nomesLixos, listaLixos)); // Adiciona a ação criada anteriormente
                x += 110;
            }

            //Diminui o contador de erros caso esteja no modo dificil
            if(modo.equals("dificil")){
                Timer countdown = new Timer();

                countdown.scheduleAtFixedRate(new TimerTask() {
                    int i = 5;
                    public void run(){
                        contador.setText("Tempo restante: " + i);
                        i--;
                        if(i < 0){
                            numErrosPermitidos -= 1;
                            errosPermitidos.setText("Erros Permitidos: " + numErrosPermitidos);
                            if(numErrosPermitidos <= 0){
                                JOptionPane.showMessageDialog(null, "Fim de Jogo, você perdeu!");
                                finalizaJogo();
                            }
                            i = 5;
                        }
                    }
                }, 0, 1000);
            }
        }
        
    
    
    // Retorna a ação de escolher a lixeira e verifica se acertou
    public ActionListener escolherLixeira(ArrayList<String> nomesLixos, Map<String, String> listaLixos){
        // Ação dos botões das lixeiras
        escolheLixeira = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // getSource() retorna um objeto Object que é pai de todos os outros, incluindo JButton
                JButton botao = (JButton) e.getSource();
                String tipoLixo = currentLixo.getName();
                String lixeira = botao.getName().replace("lixeira", "").toLowerCase();
                    
                if(tipoLixo.equals(lixeira)){
                    resultado.setText("Acertou! Continue jogando.");
                    alteraLixo(currentLixo, nomesLixos, listaLixos);
                    pontos += 1;
                }
                else{
                    resultado.setText("Errou, tente novamente.");
                    if(modo != "facil"){
                        numErrosPermitidos -= 1;
                    }
                }
                // Atualiza os pontos
                pontuacao.setText("Pontuação: " + pontos);
                
                // Atualiza as chances restantes
                errosPermitidos.setText("Erros permitidos: " + numErrosPermitidos);
                
                if(numErrosPermitidos < 0){
                    JOptionPane.showMessageDialog(null, "Fim de Jogo, você perdeu!");
                    finalizaJogo();
                }
            }
        };
        return escolheLixeira;
    }
    
    // Função para alterar o lixo aleatoriamente durante o jogo
    public JLabel alteraLixo(JLabel lixo, ArrayList<String> nomesLixos, Map<String, String> listaLixos){      
        if(nomesLixos.size() > 0){
            // Escolhe lixo aleatório da lista
            Random rand = new Random();
            int randomImgIndex = rand.nextInt(5 - 1) + 1;

            int posicao = rand.nextInt(((nomesLixos.size() - 1) > 0) ? nomesLixos.size() - 1 : 1);
            String nomeLixo = nomesLixos.get(posicao);
            if(!modo.equals("infinito")){
                nomesLixos.remove(posicao); // Remove para não repetir
            }   
            
            Icon imagemLixo = new ImageIcon(listaLixos.get(nomeLixo) + "img" + randomImgIndex + ".jpg");
            int largura = imagemLixo.getIconWidth();
            int altura = imagemLixo.getIconHeight();
            
            // Recebe as características de um novo lixo
            lixo.setBounds(300, 10, largura, altura); // posição e tamanho que muda de acordo com a imagem
            currentLixo.setName(nomeLixo);
            lixo.setIcon(imagemLixo);
        }else{
            if(!modo.equals("infinito")){
                JOptionPane.showMessageDialog(null, "Fim de Jogo, você ganhou!");
                finalizaJogo();
            }

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
