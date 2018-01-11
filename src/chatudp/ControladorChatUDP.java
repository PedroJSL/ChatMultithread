package chatudp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ControladorChatUDP implements ActionListener {
    VistaChatUDP vs;

    public ControladorChatUDP(VistaChatUDP vs){
        this.vs = vs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Conectar":
                break;
            case "Desconectar":
                break;
            case "Enviar":
                break;
        }
    }
}
