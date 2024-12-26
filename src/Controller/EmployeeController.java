package Controller;

import DAO.EmployeeDAOImpl;
import Model.Employee;
import Model.EmployeeModel;
import View.EmployeeView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EmployeeController {
    private final EmployeeModel model;
    private final EmployeeView view;
    public EmployeeController(EmployeeView view,EmployeeModel model){
        this.model = model;
        this.view = view;
        this.view.ajouterButton.addActionListener(e->ajouterEmployee());
        this.view.afficherButton.addActionListener(e->afficherEmploye());
        this.view.modifierButton.addActionListener(e->modifierEmploye());
        this.view.supprimerButton.addActionListener(e->supprimerEmploye());
    }
    public void ajouterEmployee(){
        String Nom = view.getNom();
        String Prenom = view.getPrenom();
        String Email = view.getEmail();
        String Tele = view.getTele();
        double Salaire = view.getSalaire();
        Employee.Role role = view.getRole();
        Employee.Poste poste = view.getPoste();

        boolean ajoutReussi = model.ajouterEmployee(Nom,Prenom,Email,Tele,Salaire,role,poste);

        if(ajoutReussi){
            System.out.println("Employee a ete ajouter mzyan");
        } else {
            System.out.println("mamzyanch");
        }
    }

    private void modifierEmploye() {
        int selectedRow = view.employeeTable.getSelectedRow();
        int id = (int) view.employeeTable.getValueAt(selectedRow, 0);

        String nom=view.getNom();
        String prenom=view.getPrenom();
        String email=view.getEmail();
        String telephone=view.getTele();
        double salaire =view.getSalaire();
        Employee.Poste poste=view.getPoste();
        Employee.Role role=view.getRole();

        Employee employe = new Employee(nom, prenom, email, telephone, salaire, role, poste);
        EmployeeDAOImpl employeImpl = new EmployeeDAOImpl();

        employeImpl.update(employe,id);

    }
    public void afficherEmploye() {
        EmployeeDAOImpl employeImpl = new EmployeeDAOImpl();
        List<Employee> employes = employeImpl.findAll();

        DefaultTableModel model = (DefaultTableModel) view.employeeTable.getModel();
        model.setRowCount(0);

        for (Employee emp : employes) {
            model.addRow(new Object[]{
                    emp.getId(),
                    emp.getNom(),
                    emp.getPrenom(),
                    emp.getEmail(),
                    emp.getTele(),
                    emp.getSalaire(),
                    emp.getRole(),
                    emp.getPoste(),
                    emp.getSolde()
            });
        }
    }
    public void supprimerEmploye() {
        int selectedRow = view.employeeTable.getSelectedRow();
        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un employé à supprimer !");

        }
        int id =view.getId(view.employeeTable);
        EmployeeDAOImpl employeImpl = new EmployeeDAOImpl();

        int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cet employé ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            employeImpl.delete(id);

        }
    }
}
