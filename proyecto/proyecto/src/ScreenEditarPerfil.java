import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenEditarPerfil extends JFrame{
    private JLabel perfil;
    private JLabel alias;
    private JLabel telefono;
    private JButton CONFIRMARCAMBIOSButton;
    private JButton VOLVERButton;
    private JTextField aliasCamtxt;
    private JTextField telefonoCamtxt;
    private JPanel Main;
    private JPasswordField contrasenaCamtxt;
    private ConexionJDBC conexionJDBC;
    private String a;
    private ScreenPerfil screenPerfil;

    public ScreenEditarPerfil(ConexionJDBC c, String a){
        super("MiCarpetAPP");
        this.setContentPane(this.Main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500,500));
        this.pack();
        conexionJDBC = c;
        this.a = a;

        VOLVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenPerfil = new ScreenPerfil(conexionJDBC, a);
                dispose();
                screenPerfil.setVisible(true);
            }
        });

        CONFIRMARCAMBIOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!aliasCamtxt.getText().equals("") || !telefonoCamtxt.getText().equals("")|| !contrasenaCamtxt.getText().equals("")) {
                    boolean actualizarAlias = false, actualizaraTelefono = false, actualizarContrasena = false;
                    if(!telefonoCamtxt.getText().equals("") && telefonoCamtxt.getText()!=null){
                        actualizaraTelefono = conexionJDBC.actualizarTelefono(telefonoCamtxt.getText(), a);
                    }
                    if(!contrasenaCamtxt.getText().equals("") && contrasenaCamtxt.getText() != null){
                        actualizarContrasena = conexionJDBC.actualizarContrasena(contrasenaCamtxt.getText(), a);
                    }
                    if (!a.equals(aliasCamtxt)) {
                        actualizarAlias = conexionJDBC.actualizarAlias(a, aliasCamtxt.getText());
                    }
                    if (actualizaraTelefono || actualizarAlias || actualizarContrasena) {
                        if (actualizarAlias) {
                            screenPerfil = new ScreenPerfil(conexionJDBC, aliasCamtxt.getText());
                        } else {
                            screenPerfil = new ScreenPerfil(conexionJDBC, a);
                        }
                        dispose();
                        screenPerfil.setVisible(true);
                    }
                }
            }
        });



    }

}
