package Model;

import DAO.EmployeeDAOImpl;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private EmployeeDAOImpl dao;
    private List<Employee> employees = new ArrayList<>();


    public EmployeeModel(EmployeeDAOImpl dao){
        this.dao = dao;
    }

    public boolean ajouterEmployee(String Nom, String Prenom, String Email, String Tele, double Salaire, Employee.Role role, Employee.Poste poste) {

        Employee nouveauEmployee = new Employee(Nom, Prenom, Email, Tele, Salaire,role, poste);

        dao.add(nouveauEmployee);
        return true;

    }

    public boolean modifierEmployee(int id, String nom, String prenom, String email, String telephone, double salaire, Employee.Role role, Employee.Poste poste, int solde) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.setNom(nom);
                emp.setPrenom(prenom);
                emp.setEmail(email);
                emp.setTele(telephone);
                emp.setSalaire(salaire);
                emp.setRole(role);
                emp.setPoste(poste);
                emp.setSolde(solde);
                return true;
            }
        }
        return false;
    }

    public boolean supprimerEmployee(int id) {
        dao.delete(id);
        return true;
    }

    public boolean importerEmployees(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String nom = data[1];
                String prenom = data[2];
                String email = data[3];
                String tele = data[4];
                double salaire = Double.parseDouble(data[5]);
                Employee.Role role = Employee.Role.valueOf(data[6]);
                Employee.Poste poste = Employee.Poste.valueOf(data[7]);
                int solde = Integer.parseInt(data[8]);

                ajouterEmployee(nom, prenom, email, tele, salaire,role, poste);
            }
            return true;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean exporterEmployees(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee emp : employees) {
                writer.write(emp.getId() + "," + emp.getNom() + "," + emp.getPrenom() + "," + emp.getEmail() + "," +
                        emp.getTele() + "," + emp.getSalaire() + "," + emp.getRole() + "," + emp.getPoste() + "," + emp.getSolde());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Employee> afficherEmployees() {
        return employees;
    }

}
