import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenPerfil extends JFrame {
    private JLabel alias;
    private JLabel nombre;
    private JLabel apellidos;
    private JLabel email;
    private JLabel telefono;
    private JButton editarPerfilButton;
    private JLabel perfil;
    private JPanel main;
    private JButton volverButton;
    private JLabel aliastext;
    private JLabel nombretext;
    private JLabel apellidostext;
    private JLabel emailtext;
    private JLabel telefonotext;
    private ConexionJDBC conexionJDBC;
    private ScreenMenu menu;
    private String a;
    private Usuario user;
    private ScreenEditarPerfil edit;

    public ScreenPerfil (ConexionJDBC c, String a) {
        super("MiCarpetAPP");
        this.setContentPane(this.main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500,500));
        this.pack();
        conexionJDBC = c;
        this.a = a;
        user= c.mostrarUsuario(a);

        aliastext.setText(user.getAlias());
        nombretext.setText(user.getNombre());
        apellidostext.setText(user.getApellidos());
        emailtext.setText(user.getEmail());
        if(user.getTelefono()!=null){
            telefonotext.setText(user.getTelefono());
        }else{
            telefonotext.setText("El usuario no tiene un teléfono asociado");
        }

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu = new ScreenMenu(conexionJDBC, a);
                dispose();
                menu.setVisible(true);
            }
        });

        editarPerfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit = new ScreenEditarPerfil(conexionJDBC, a);
                dispose();
                edit.setVisible(true);
            }
        });

    }
}
