import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ScreenInicio extends JFrame {
    private JButton INICIARSESIÓNButton;
    private JButton REGISTROButton;
    private JPanel PanelInicio;
    private JLabel labelPic;
    private ConexionJDBC conexionJDBC;
    private ScreenRegistro reg;
    private ScreenLogin log;
    private ImageIcon logo;


    public ScreenInicio(ConexionJDBC c){
        super("MiCarpetAPP");
        this.setContentPane(this.PanelInicio);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1024,1024));
        this.pack();
        conexionJDBC=c;

        labelPic.setIcon(new ImageIcon("logo.png"));
        validate();

        INICIARSESIÓNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log = new ScreenLogin(conexionJDBC);
                dispose();
                log.setVisible(true);
            }
        });
        REGISTROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reg = new ScreenRegistro(conexionJDBC);
                dispose();
                reg.setVisible(true);
            }
        }


        );
    }



}

