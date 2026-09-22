package app;

public class App {
    public static void main(String[] args) {
        // Hace que la interfaz gráfica se ejecute y sea visible
        java.awt.EventQueue.invokeLater(() -> {
            new VistaMenu().setVisible(true);
        });
    }
}