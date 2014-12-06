
import controller.MainController;
import view.MainView;

/**
 *
 * @author yannick
 */
public class Programme {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainController mainC = new MainController();
                MainView mainV = new MainView(mainC);
                mainV.setVisible(true);
            }
        });
    }
}
