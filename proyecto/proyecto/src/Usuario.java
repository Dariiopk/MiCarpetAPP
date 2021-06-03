
public class Usuario {
    private int id;
    private String nombre,apellidos, contrasena, email, alias, telefono;

    public Usuario() {
        id=0;
        nombre = null;
        apellidos = null;
        contrasena = null;
        email = null;
        alias = null;
        telefono = null;

    }


    public Usuario(int id, String nombre, String apellidos , String contraseña , String email , String alias ) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasena = contraseña;
        this.email = email;
        this.alias = alias;
        this.telefono = null;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


}
