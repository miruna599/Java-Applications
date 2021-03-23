import javax.swing.*;
public class PolMVC {
    

    public static void main(String[] args) {
        
        PolView view = new PolView();
        PolController controller = new PolController(view);
        view.setVisible(true);
    }
}
