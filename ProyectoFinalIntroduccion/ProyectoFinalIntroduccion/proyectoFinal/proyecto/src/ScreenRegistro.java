import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenRegistro extends JFrame{
    private JPanel main;
    private JPanel top;
    private JPanel bot;
    private JTextField nombretxt;
    private JTextField apellidostxt;
    private JTextField aliastxt;
    private JPasswordField contrasenatxt;
    private JPasswordField confirmarcontrasenatxt;
    private JTextField emailtxt;
    private JCheckBox heLeídoYAceptoCheckBox;
    private JButton REGISTRARButton;
    private JLabel Nombre;
    private JLabel Apellidos;
    private JLabel Alias;
    private JLabel Contrasena;
    private JLabel ConfirmarContrasena;
    private JLabel Email;
    private JButton botonVolver;
    private ScreenInicio ini;
    private ScreenMenu menu;
    private ConexionJDBC conexion ;


    public ScreenRegistro(ConexionJDBC c) {
        super("MiCarpetAPP");
        this.setContentPane(this.main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500,500));
        this.pack();
        conexion = c;

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ini = new ScreenInicio(conexion);
                dispose();
                ini.setVisible(true);
            }
        });

        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombretxt.getText().equals("") || apellidostxt.getText().equals("") || aliastxt.getText().equals("") || contrasenatxt.getText().equals("")
                        || confirmarcontrasenatxt.getText().equals("") || emailtxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Campo obligatorio vacío", "ERROR", JOptionPane.WARNING_MESSAGE);
                } else if (!confirmarcontrasenatxt.getText().equals(contrasenatxt.getText())) {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "ERROR", JOptionPane.WARNING_MESSAGE);
                } else if (!heLeídoYAceptoCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Debe leer y aceptar las condiciones de registro", "ERROR", JOptionPane.WARNING_MESSAGE);
                } else {
                    Usuario u = new Usuario(0, nombretxt.getText(), apellidostxt.getText(), contrasenatxt.getText(), emailtxt.getText(), aliastxt.getText());
                    boolean registrado = conexion.inscribirNuevoUsuario(u);
                    if(registrado){
                        menu = new ScreenMenu(conexion,aliastxt.getText());
                        dispose();
                        menu.setVisible(true);
                    }else{
                        aliastxt.setText("");
                        contrasenatxt.setText("");
                        confirmarcontrasenatxt.setText("");
                        heLeídoYAceptoCheckBox.setSelected(false);
                    }
                }


            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
