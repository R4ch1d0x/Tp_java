package Model;

import DAO.EmployeeDAOImpl;

public class EmployeeModel {
    private EmployeeDAOImpl dao;

    public EmployeeModel(EmployeeDAOImpl dao){
        this.dao = dao;
    }

    public boolean ajouterEmployee(String Nom, String Prenom, String Email, String Tele, double Salaire, Employee.Role role, Employee.Poste poste) {

        Employee nouveauEmployee = new Employee(Nom, Prenom, Email, Tele, Salaire,role, poste);

        dao.add(nouveauEmployee);
        return true;

    }

}
