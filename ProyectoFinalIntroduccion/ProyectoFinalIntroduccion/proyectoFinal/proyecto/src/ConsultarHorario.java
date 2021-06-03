import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class ConsultarHorario extends AbstractTableModel{

        public String datos[][];

        // Constructor
        public ConsultarHorario(ConexionJDBC con, String alias) {
            addTableModelListener( new TablaListener() );
            datos = new String[7][6];
            datos = con.consultarHorario(alias);
            if(datos == null ){
                datos = new String[][]{
                        {"Tramo Horario", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"},
                        {"", "", "", "", "", ""},
                        {"", "", "", "", "", ""},
                        {"", "", "", "", "", ""},
                        {"", "", "", "", "", ""},
                        {"", "", "", "", "", ""},
                        {"", "", "", "", "", ""},
                };
            }
        }

        // Devuelve el número de columnas de la tabla
        public int getColumnCount() {
            return( 6 );
        }

        // Devuelve el número de filas de la tabla
        public int getRowCount() {
            return( 7 );
        }

        // Devuelve el valor de una determinada casilla de la tabla identificada mediante fila y columna
        public Object getValueAt( int fila,int col ) {
            return( datos[fila][col] );
        }

        // Indica si la casilla identificada por fila y columna es editable
        public boolean isCellEditable( int fila,int col ) {
            return false;
        }


        public class TablaListener implements TableModelListener {
            public void tableChanged( TableModelEvent evt ) {
                /*for( int i=0; i < datos.length; i++ ) {
                    for( int j=0; j < datos[0].length; j++ )
                        System.out.print( datos[i][j] + " " );
                    System.out.println();
                }*/
            }
        }
}
