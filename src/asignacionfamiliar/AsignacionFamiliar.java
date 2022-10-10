package asignacionfamiliar;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Leandro
 */
public class AsignacionFamiliar {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese DNI del empleado:");
        int DNI = in.nextInt();
        ArrayList<Asignacion> asignaciones = obtenerAsignaciones(DNI);
        int montoTotal = 0;
        double IGF = ObtenerIGF(DNI);
        double montoAux;
        for (Asignacion asigAux : asignaciones) {
            montoAux = 0;
            switch (asigAux.tipo.toLowerCase()) {
                case "nacimiento":
                    if (IGF <= 316731) {
                        montoAux = 9875;
                    }
                case "matrimonio":
                    if (IGF <= 316731) {
                        montoAux = 14788;
                    }
                    break;
                case "adopcion":
                    if (IGF <= 316731) {
                        montoAux = 59058;
                    }
                    break;
                case "prenatal":
                    if (IGF <= 131208) {
                        montoAux = 8471;
                    } else if (IGF <= 192432) {
                        montoAux = 5713;
                    } else if (IGF <= 222170) {
                        montoAux = 3454;
                    } else if (IGF <= 316731) {
                        montoAux = 1780;
                    }
                    break;
                case "hijo":
                    if (IGF <= 131208) {
                        montoAux = 20000;
                    } else if (IGF <= 192432) {
                        montoAux = 6830;
                    } else if (IGF <= 222170) {
                        montoAux = 3454;
                    } else if (IGF <= 316731) {
                        montoAux = 1780;
                    }
                    break;
                case "hijodiscapacidad":
                    if (IGF <= 131208) {
                        montoAux = 39199;
                    } else if (IGF <= 192432) {
                        montoAux = 20633;
                    } else {
                        montoAux = 12317;
                    }
                    break;
                default:
                    ;
            }
            montoTotal += montoAux;

        }
        System.out.println("Monto total: " + montoTotal);
    }

    private static ArrayList<Asignacion> obtenerAsignaciones(int DNI) {
        return consultaSQL("SELECT Tipo, ID FROM Asignaciones WHERE DNI = \"DNI\" AND FechaVencimiento < NOW()");
    }

    private static double ObtenerIGF(int DNI) {
        return Double.parseDouble((consultaSQL("Select IGF from Empleados WHERE DNI='" + DNI + "'").toString()));
    }

    private static ArrayList consultaSQL(String query) {
        Connection conn = null;
        String userDB = "sql10525511";
        String passDB = "sEN5PgSAy3";
        String mysqlURL = "sql10.freemysqlhosting.net:3306/sql10525511";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + mysqlURL, userDB, passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            con.close();
            return (ArrayList) rs;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
