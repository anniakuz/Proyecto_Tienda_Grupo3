public class Compra {
    private String nombreClinete;
    private String nombreProducto;
    private int cantidad;

    @Override
    public String toString() {
        return "Compra{" +
                "nombreClinete='" + nombreClinete + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    public String getNombreClinete() {
        return nombreClinete;
    }

    public void setNombreClinete(String nombreClinete) {
        this.nombreClinete = nombreClinete;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Compra(String nombreClinete, String nombreProducto, int cantidad) {
        this.nombreClinete = nombreClinete;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
    }
}
