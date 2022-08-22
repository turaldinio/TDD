import Controller.TurboBankController;
import Model.TurboBank;
import View.TurboBankView;

public class Main {
    public static void main(String[] args) {
        TurboBank turboBank = new TurboBank(1_000_000, 23.5, 72);

        TurboBankController controller = new TurboBankController(turboBank, new TurboBankView());

        controller.out(controller.getLoanInformation());
    }
}
