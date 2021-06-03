import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenModificar extends JFrame {
    private static Color color;
    private static Color colorBoton;
    private  Font fuente;
    private JButton volverButton;
    private ScreenHorario hor;

    public ScreenModificar(ConexionJDBC conexionJDBC, String alias) {
        this.setMinimumSize(new Dimension(1280,700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel grande = new JPanel();
        grande.setLayout(new GridLayout(2,1));
        volverButton = new JButton("VOLVER");


        JTable tabla = new JTable(new ModificarHorario(conexionJDBC, alias));
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

        fuente = new Font("Times New Roman", Font.BOLD, 16);
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
