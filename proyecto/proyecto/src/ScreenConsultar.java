import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenConsultar extends JFrame{
    private Color color;
    private Color colorBoton;
    private  Font fuente;
    private JButton volverButton;
    private ScreenHorario hor;

    public ScreenConsultar(ConexionJDBC conexionJDBC, String alias) {
        super("MiCarpetAPP");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1280,700));
        JPanel grande = new JPanel();
        grande.setLayout(new GridLayout(2,1));
        volverButton = new JButton("VOLVER");

        JTable tabla = new JTable( new ConsultarHorario(conexionJDBC, alias));
        JScrollPane panel = new JScrollPane( tabla );

        JPanel panel2 = new JPanel();
        panel2.add(volverButton);

        grande.add(panel);
        grande.add(panel2);
        grande.setVisible(true);

        color = new Color(223, 208, 193);
        colorBoton = new Color(201, 185, 169);
        tabla.setBackground(color);
        panel.setBackground(color);
        panel2.setBackground(color);
        setBackground(color);
        

        volverButton.setBackground(colorBoton);

        fuente = new Font("Times New Roman", Font.BOLD , 16);
        volverButton.setFont(fuente);

        add(grande);

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hor = new ScreenHorario(conexionJDBC, alias);
                dispose();
                hor.setVisible(true);
            }
        });

    }

}
