import Controller.EmployeeController;
import Controller.HolidayController;
import DAO.CongeDAOImpl;
import DAO.EmployeeDAOImpl;
import Model.EmployeeModel;
import Model.HolidayModel;
import View.EmployeeView;
import View.HolidayView;

public class Main {
    public static void main(String[] args) {

        EmployeeView Emplview = new EmployeeView();
        EmployeeDAOImpl Empldao = new EmployeeDAOImpl();
        EmployeeModel Emplmodel = new EmployeeModel(Empldao);

        HolidayView HolidayView = new HolidayView();
        CongeDAOImpl CongeDao = new CongeDAOImpl();
        HolidayModel HolidayModel = new HolidayModel(CongeDao,Empldao);

        HolidayController controllerHoliday = new HolidayController(HolidayView,HolidayModel);

        new EmployeeController(Emplmodel,Emplview);
    }
}