package DisenoDePatrones.app;

import DisenoDePatrones.Controlador.AuthController;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) throws SQLException {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {}

        SwingUtilities.invokeLater(() -> new AuthController());
    }
}
