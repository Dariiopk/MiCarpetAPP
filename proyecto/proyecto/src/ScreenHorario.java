import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenHorario extends JFrame{
    private JPanel panel1;
    private JButton verHorarioButton;
    private JButton modificarHorarioButton;
    private JButton botonVolver;
    private ConexionJDBC conexionJDBC;
    private ScreenConsultar cons;
    private ScreenModificar mod;
    private ScreenMenu menu;

    public ScreenHorario(ConexionJDBC c, String alias){
        super("MiCarpetAPP");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500,500));
        this.pack();
        conexionJDBC=c;
        verHorarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cons = new ScreenConsultar(conexionJDBC, alias);
                dispose();
                cons.setVisible(true);
            }
        });
        modificarHorarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mod = new ScreenModificar(conexionJDBC, alias);
                dispose();
                mod.setVisible(true);
            }
        }

        );

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu = new ScreenMenu(conexionJDBC, alias);
                dispose();
                menu.setVisible(true);
            }
        });
    }
}
