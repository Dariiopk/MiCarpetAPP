import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenLogin extends JFrame{
    private JTextField aliasLogtxt;
    private JPasswordField contrasenaLogtxt;
    private JButton INICIARSESIÓNButton;
    private JLabel Titulo;
    private JPanel MainPanel;
    private JButton botonVolver;
    private ConexionJDBC conexionJDBC;
    private ScreenMenu menu;
    private ScreenInicio ini;
    public ScreenLogin(ConexionJDBC c){
        super("MiCarpetAPP");
        this.setContentPane(this.MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500,500));
        this.pack();
        conexionJDBC=c;

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ini = new ScreenInicio(conexionJDBC);
                dispose();
                ini.setVisible(true);
            }
        });

        INICIARSESIÓNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aliasLogtxt.getText().equals("") || contrasenaLogtxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Campo obligatorio vacío", "ERROR", JOptionPane.WARNING_MESSAGE);
                }else {
                    boolean correcta = conexionJDBC.sesionCorrecta(aliasLogtxt.getText(),contrasenaLogtxt.getText());
                    if(correcta) {
                        menu = new ScreenMenu(conexionJDBC,aliasLogtxt.getText());
                        dispose();
                        menu.setVisible(true);

                    }else{
                        contrasenaLogtxt.setText("");
                    }
                }
            }
        });
    }


}
