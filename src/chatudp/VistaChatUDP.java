package chatudp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;


class VistaChatUDP extends JFrame{
    private ControladorChatUDP cc;
    JTextField tfUsuario,tfIP,tfPuerto;
    JButton bEnviar;
    JTextArea taDisplay,taEntrada;
    private JPanel pSuperior,pInferior;

    VistaChatUDP(){
        cc = new ControladorChatUDP(this);
        setTitle("Chat UDP");
        setSize(700,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        crearVentana();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void crearPanelSuperior(){
        JLabel lUsuario,lIP,lPuerto;
        JButton bConectar, bDesconectar;
        pSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER,5,2));

        lUsuario = new JLabel("Usuario: ");
        tfUsuario = new JTextField(10);

        lIP = new JLabel("IP: ");
        tfIP = new JTextField(10);

        lPuerto = new JLabel("Puerto: ");
        tfPuerto = new JTextField(5);

        bConectar = new JButton("Conectar");
        bConectar.addActionListener(cc);

        bDesconectar = new JButton("Desconectar");
        bDesconectar.addActionListener(cc);

        pSuperior.add(lUsuario);
        pSuperior.add(tfUsuario);
        pSuperior.add(lIP);
        pSuperior.add(tfIP);
        pSuperior.add(lPuerto);
        pSuperior.add(tfPuerto);
        pSuperior.add(bConectar);
        pSuperior.add(bDesconectar);
    }

    private void crearPanelInferior(){
        pInferior = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(1,1,1,1);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.fill = GridBagConstraints.VERTICAL;

        taEntrada = new JTextArea(4,50);
        taEntrada.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(taEntrada);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.weightx=3;
        pInferior.add(scroll,constraints);
        constraints.weightx=2;

        bEnviar = new JButton("Enviar");
        bEnviar.addActionListener(cc);
        constraints.gridx=4;
        constraints.gridy=0;
        pInferior.add(bEnviar,constraints);
    }

    private void crearVentana(){
        crearPanelSuperior();
        crearPanelInferior();

        taDisplay = new JTextArea();
        taDisplay.setFont(new Font("Arial",Font.PLAIN,14));
        taDisplay.setLineWrap(true);
        taDisplay.setEditable(false);
        taDisplay.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(taDisplay);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(pSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(pInferior,BorderLayout.SOUTH);
    }

}
