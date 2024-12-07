import Controller.EmployeeController;
import DAO.EmployeeDAOImpl;
import Model.EmployeeModel;
import View.EmployeeView;

public class Main {
    public static void main(String[] args) {

        EmployeeView view = new EmployeeView();
        EmployeeDAOImpl dao = new EmployeeDAOImpl();
        EmployeeModel model = new EmployeeModel(dao);

        new EmployeeController(model,view);
    }
}