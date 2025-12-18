/**
 ################################################################
                           Descripción:
 o Esta clase contiene los datos del conjunto de entrenamiento
 o (DataSet Benetton) que será utilizado para el modelo predictivo.
 o Contiene los valores de inversión en publicidad y las ventas.
 ################################################################
 */

public class DataSetBenetton {

    // Datos del caso Benetton (Advertising y Sales)
    private double[] advertising = {23, 26, 30, 34, 43, 48, 52, 57, 58};
    private double[] sales = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};

    // Métodos de acceso
    public double[] getAdvertising() {
        return advertising;
    }

    public double[] getSales() {
        return sales;
    }
}
