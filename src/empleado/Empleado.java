/*
6. Cree una clase llamada Empleado, que incluya tres piezas de información como variables de instancia: 
un primer nombre (tipo String), un apellido paterno (tipo String) y un salario mensual (double). 
Su clase debe tener un constructor que inicialice las tres variables de instancia. 
Proporcione un método establecer y un método obtener para cada variable de instancia. 
Si el salario mensual no es positivo, establézcalo a 0.0. 
Escriba una aplicación de prueba llamada PruebaEmpleado, que demuestre las capacidades de cada Empleado. 
Cree dos objetos Empleado y muestre el salario anual de cada objeto. 
Después, proporcione a cada Empleado un aumento del 10% y muestre el salario anual de cada Empleado otra vez.
 */
package empleado;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class Empleado {
    //atributos
    String Nombre,Apellido;
    double salario;
    
    //metodo constructor
    Empleado() {
      this.Nombre="";
      this.Apellido="";
      this.salario=0;
    }
    
    //Metodos getter y setter
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public double validarSalario(double sal){
        if (sal < 0){
            sal=0;
        }
        return sal;
    }
    public double salarioAnual(double sal){
        sal = validarSalario(sal);
        double salAnual;
        salAnual= sal*12;
        return salAnual;
    }
    public double aumentoSalarial(double sal){
        sal = validarSalario(sal);
        double aumentoSal, aum10;
        aum10 = ((sal * 10)/100);
        aumentoSal = (sal + aum10);
        return aumentoSal;
    }
    
    //creando archivo txt para almacenar los datos ingresados
    public void crearArchivo(JTable jtab){
        try{
           String fileRectangulo = "D:\\Documents\\NetBeansProjects\\Empleado\\src\\empleado\\datos.txt";
           BufferedWriter bfw = new BufferedWriter(new FileWriter(fileRectangulo));
            for (int i = 0; i < jtab.getColumnCount(); i++) {
                bfw.write(jtab.getColumnName(i) + "      ");
            }
            bfw.write("\n");
            for (int i = 0; i < jtab.getRowCount(); i++) {
                for (int j = 0; j < jtab.getColumnCount(); j++) {
                    bfw.write(jtab.getValueAt(i, j).toString() + "           ");
                }
                bfw.newLine();
            }
            bfw.close();
            JOptionPane.showMessageDialog(null, "El archivo fue creado correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el archivo." + e.getMessage());
        }
    }
    
    //llenar tabla con los registros
    public void llenarTabla(String nomb, String apel, double sal, double aumSal, double salAnual, JTable tabla){
        //asignar los valores obtenidos
        setNombre(nomb);
        setApellido(apel);
        setSalario(sal);
        salAnual = salarioAnual(sal);
        aumSal = aumentoSalarial(sal);
        //añadimos el registro a la tabla
        try {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object nuevaTab[] = {nomb,apel,sal,aumSal,salAnual};
            tb.addRow(nuevaTab);
            JOptionPane.showMessageDialog(null, "Registro Añadido exitosamente"); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo añadir "+e.getMessage());
        }
    }
    
    // Eliminar datos de una tabla
    public void eliminaRegistro(JTable tabla){
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        if (tabla.getSelectedRow()>=0){
            tb.removeRow(tabla.getSelectedRow());
        } 
    }
    
    //guardar datos modificados
    public void guardarDatosModificados(JTable tabla, int filaSelec, String nomb, String apel, double sal){
        //variables
        double salAn, aumSal;
        //actualización
        try {            
            tabla.setValueAt(nomb, filaSelec, 0);
            tabla.setValueAt(apel, filaSelec, 1);
            tabla.setValueAt(sal, filaSelec, 2);
            
            salAn = salarioAnual(sal);
            aumSal = aumentoSalarial(sal);
            
            tabla.setValueAt(aumSal, filaSelec, 3);
            tabla.setValueAt(salAn, filaSelec, 4);
                        
            JOptionPane.showMessageDialog(null, "Registro Modificado exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
     
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
