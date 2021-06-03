public class main {
        public static void main(String[] args) {
            ConexionJDBC conexionJDBC = ConexionJDBC.getInstance();
            ScreenInicio inicio = new ScreenInicio(conexionJDBC);
            inicio.setVisible(true);
        }
}
