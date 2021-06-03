import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ScreenMenu extends JFrame {
    private JLabel tituloMenu;
    private JButton botonPerfil;
    private JButton botonCalendario;
    private JButton botonGArchivos;
    private JButton botonFinanzas;
    private JButton botonConfiguracion;
    private JButton botonHorario;
    private JButton botonJuego;
    private JPanel MainPanel;
    private JButton cerrarSesiónButton;
    private ScreenEnProceso enPro;
    private ScreenPerfil perfil;
    private ScreenInicio ini;

    private ConexionJDBC conexionJDBC;
    private String alias;

    public ScreenMenu (ConexionJDBC c, String aliasLogtxt) {
        super("MiCarpetAPP");
        this.setContentPane(this.MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500,500));
        this.pack();
        alias = aliasLogtxt;
        conexionJDBC = c;
        cerrarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ini = new ScreenInicio(conexionJDBC);
                dispose();
                ini.setVisible(true);
            }
        });

        botonPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                perfil = new ScreenPerfil(conexionJDBC,alias);
                dispose();
                perfil.setVisible(true);
            }
        });

        botonCalendario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enPro = new ScreenEnProceso(conexionJDBC, alias);
                dispose();
                enPro.setVisible(true);
            }
        });

        botonGArchivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enPro = new ScreenEnProceso(conexionJDBC, alias);
                dispose();
                enPro.setVisible(true);
            }
        });

        botonFinanzas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enPro = new ScreenEnProceso(conexionJDBC, alias);
                dispose();
                enPro.setVisible(true);
            }
        });

        botonJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enPro = new ScreenEnProceso(conexionJDBC, alias);
                dispose();
                enPro.setVisible(true);
            }
        });

        botonHorario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenHorario horario = new ScreenHorario(conexionJDBC, alias);
                dispose();
                horario.setVisible(true);
            }
        });

        botonConfiguracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enPro = new ScreenEnProceso(conexionJDBC, alias);
                dispose();
                enPro.setVisible(true);
            }
        });

    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
