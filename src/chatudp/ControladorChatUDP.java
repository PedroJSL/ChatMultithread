package chatudp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Calendar;

import javax.swing.JOptionPane;

class ControladorChatUDP implements ActionListener {
    private VistaChatUDP vs;
    private MulticastSocket m;
    private InetAddress grupo;
    private String usuario;
    private String ip;
    private int puerto;
    boolean conectado = false;

    ControladorChatUDP(VistaChatUDP vs){
        this.vs = vs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Conectar":
                conectarGrupo();
                break;
            case "Desconectar":
                desconectarGrupo();
                break;
            case "Enviar":
                enviarMensaje(vs.taEntrada.getText());
                break;
        }
    }

    private void conectarGrupo(){
        try {
            usuario = vs.tfUsuario.getText().trim();
            ip = vs.tfIP.getText().trim();
            puerto = Integer.parseInt(vs.tfPuerto.getText());

            vs.tfUsuario.setEditable(false);
            vs.tfIP.setEditable(false);
            vs.tfPuerto.setEditable(false);

            m=new MulticastSocket();
            grupo=InetAddress.getByName(ip);
            //m.joinGroup(grupo);
            conectado = true;

            new RecibirMensajes().start();

            vs.taDisplay.append(getHora()+usuario+" acaba de crear el grupo: "+InetAddress.getLocalHost()+":"+m.getPort());
            //enviarMensaje(getHora()+usuario+" se acaba de unir al grupo.");


        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(vs,"Puerto invalido.","Error",JOptionPane.ERROR_MESSAGE);
        }catch (IOException e){

        }
    }

    private void enviarMensaje(String msj){
        try {
            DatagramPacket paquete=new DatagramPacket(msj.getBytes(), msj.length(), grupo, puerto);
            m.send(paquete);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void desconectarGrupo(){

    }

    private String getHora(){
        Calendar c = Calendar.getInstance();
        return "("+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND)+") ";
    }

    class RecibirMensajes extends Thread{

        @Override
        public void run() {
            while(conectado){
                try {
                    byte[] buf = new byte[1000];
                    DatagramPacket paquete = new DatagramPacket(buf, buf.length);
                    m.receive(paquete);
                    String mens = new String(paquete.getData()).trim();
                    vs.taDisplay.append(mens+"\n");
                }catch (IOException e){
                    System.out.printf(e.getMessage());
                }
            }
        }

    }
}
