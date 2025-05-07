package view;
import model.Parc;
import view.MenuPrincipalView;

public class MainApp {
    public static void main(String[] args) {
        Parc parc = Parc.chargerParc();
        new MenuPrincipalView(parc);
    }
}
