import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;
import java.util.StringJoiner;


public class ConexionJDBC {
    private Connection conn;

    private static ConexionJDBC instanciaInterfaz = null;

    private ConexionJDBC() {
        try {
            // create connection for database called DBB_SCHEMA in a server installed in
            // DB_URL, with a user USER with password PASS
            conn = DriverManager.getConnection("jdbc:mysql://iis2021.cobadwnzalab.eu-central-1.rds.amazonaws.com" +
                    "/" + "grupoA", "usuarioA", "loridocapitan");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Usuario mostrarUsuario(String alias) {
        Usuario user= new Usuario();
        String selectQueryBody = "SELECT * FROM Usuario WHERE alias=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectQueryBody);
            preparedStatement.setString(1, alias);
            ResultSet rs = preparedStatement.executeQuery(); // guarda búsqueda
            // position result to first
            if (rs.next()) {
                user.setNombre(rs.getString(2));
                user.setApellidos(rs.getString(3));
                user.setEmail(rs.getString(5));
                user.setAlias(rs.getString(6));
                user.setTelefono(rs.getString(7));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }


    public static ConexionJDBC getInstance() {
        if (instanciaInterfaz == null) {
            instanciaInterfaz = new ConexionJDBC();
        }
        return instanciaInterfaz;
    }

    public boolean inscribirNuevoUsuario(Usuario u) {
        int usuarioId = 0;
        boolean inscrito=false;
        String insertBody = "INSERT INTO Usuario (nombre, apellidos, contrasena, email, alias, telefono) VALUES (?, ?, ?, ?,?, null)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insertBody,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, u.getNombre());
            preparedStatement.setString(2, u.getApellidos());
            preparedStatement.setString(3, u.getContrasena());
            preparedStatement.setString(4, u.getEmail());
            preparedStatement.setString(5, u.getAlias());

            int res = preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                usuarioId = rs.getInt(1);
            }
            JOptionPane.showMessageDialog(null, "usuario registrado", "", JOptionPane.INFORMATION_MESSAGE);
            //System.out.println("nuevo usuario añadido");
            u.setId(usuarioId);
            inscrito=true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "alias ya en uso", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return inscrito;
    }

    public boolean actualizarTelefono(String telefono,String alias) {
        boolean actualizado = false;
        PreparedStatement preparedStatement = null;
        String updateBody = null;
        try {
            updateBody = "UPDATE Usuario SET telefono = ? WHERE (alias = ?)";
            preparedStatement = conn.prepareStatement(updateBody);
            if (telefono == null || telefono.equals("")) {
                preparedStatement.setNull(1, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setString(1, telefono);
            }

            preparedStatement.setString(2, alias);
            int res = preparedStatement.executeUpdate();
            actualizado = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar teléfono", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return actualizado;
    }

    public boolean actualizarAlias(String aliasAnt, String aliasNew) {
        boolean actualizado = false;
        if(!aliasNew.equals("")) {
            PreparedStatement preparedStatement = null;
            String updateBody = null;
            try {
                updateBody = "UPDATE Usuario SET alias = ? WHERE (alias = ?)";
                preparedStatement = conn.prepareStatement(updateBody);
                preparedStatement.setString(1, aliasNew);
                preparedStatement.setString(2, aliasAnt);
                int res = preparedStatement.executeUpdate();
                actualizado = true;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Alias ya existente", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
        return actualizado;
    }

    public boolean actualizarContrasena(String contrasena,String alias) {
        boolean actualizado = false;
        PreparedStatement preparedStatement = null;
        String updateBody = null;
        try {
            updateBody = "UPDATE Usuario SET contrasena = ? WHERE (alias = ?)";
            preparedStatement = conn.prepareStatement(updateBody);
            if (contrasena == null || contrasena.equals("")) {
                preparedStatement.setNull(1, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setString(1, contrasena);
            }

            preparedStatement.setString(2, alias);
            int res = preparedStatement.executeUpdate();
            actualizado = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la contraseña", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return actualizado;
    }

    public boolean sesionCorrecta(String alias, String contrasena){
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        String sql = null;
        boolean correcto = false;
        try {
            sql = "Select contrasena FROM Usuario WHERE (alias = ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,alias);
            res = preparedStatement.executeQuery();
            if(res.next()){
                correcto = contrasena.equals(res.getString(1));
                if(!correcto){
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Usuario no encontrado", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return correcto;
    }

    public void actualizarHorario(String[][] datos, String alias){

        StringJoiner datosMod = new StringJoiner("/");

        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 6; j++){
                if(datos[i][j] == ""){
                    datosMod.add("~");
                }else{
                    datosMod.add(datos[i][j]);
                }
            }
        }

        PreparedStatement preparedStatement = null;
        String updateBody = null;
        try {
            updateBody = "UPDATE Usuario SET horario = ? WHERE (alias = ?)";
            preparedStatement = conn.prepareStatement(updateBody);
            preparedStatement.setString(1, datosMod.toString());
            preparedStatement.setString(2, alias);

            int res = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public String[][] consultarHorario(String alias){

        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        String sql = null;
        String hor = null;

        try {
            sql = "Select horario FROM Usuario WHERE (alias = ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,alias);
            res = preparedStatement.executeQuery();

            if(res.next()){
                hor = res.getString(1);
            }else{
                JOptionPane.showMessageDialog(null, "Usuario no encontrado", "ERROR", JOptionPane.WARNING_MESSAGE);
            }


        } catch (SQLException ex) {
            ex.getStackTrace();
        }

       //System.out.println(hor);

        String[][] horario = new String[7][6];

        if(hor != null){
            Scanner leer = new Scanner(hor);
            leer.useDelimiter("/");
            String sig = null;

            for(int i = 0; i < 7; i++){
                for(int j = 0; j < 6; j++){
                    if(leer.hasNext()) {
                        sig = leer.next();
                        if (sig.equals("~")) {
                            horario[i][j] = "";
                        } else {
                            horario[i][j] = sig;
                        }
                    }
                }
            }
            leer.close();
        }else{
            horario = null;
        }

        return horario;
    }


}
