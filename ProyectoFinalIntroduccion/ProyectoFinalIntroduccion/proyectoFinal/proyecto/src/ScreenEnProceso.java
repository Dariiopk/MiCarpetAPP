import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenEnProceso extends JFrame {
    private JLabel tituloMenu;
    private JLabel enProceso;
    private JPanel MainPanel;
    private JButton botonInicio;
    private ConexionJDBC conexionJDBC;
    private String alias;
    private ScreenMenu menu;

    public ScreenEnProceso (ConexionJDBC c, String aliasLogtxt) {
        super("MiCarpetAPP");
        this.setContentPane(this.MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500,500));
        this.pack();

        conexionJDBC = c;
        alias = aliasLogtxt;

        botonInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu = new ScreenMenu(conexionJDBC, alias);
                dispose();
                menu.setVisible(true);
            }
        });

    }
}
