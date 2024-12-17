package Controller;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import DAO.EmployeeDAOImpl;
import DAO.CongeDAOImpl;

import Model.Employee;
import Model.Holiday;
import Model.HolidayModel;
import View.HolidayView;

public class HolidayController {
    private HolidayView view;
    private HolidayModel model;

    public HolidayController(HolidayView view, HolidayModel model){
        this.view = view;
        this.model = model;

        this.view.ajouterButton.addActionListener(e->ajouterConge());
        this.view.supprimerButton.addActionListener(e->supprimerConge());
        this.view.modifierButton.addActionListener(e->modifierConge());
    }

    public void ajouterConge(){
        int id_empl = view.getId_empl();
        String Date_debut = view.getDate_debut();
        String Date_fin = view.getDate_fin();

        Holiday.Types types = view.getTypes();


        boolean ajoutReussi = model.ajouterConge (Date_debut, Date_fin, types,id_empl);

        if(ajoutReussi){
            System.out.println("Conge a ete ajouter mzyan");
        } else {
            System.out.println("mamzyanch");
        }
    }

    private void modifierConge() {
        int selectedRow = view.congeTable.getSelectedRow();
        int id = (int) view.congeTable.getValueAt(selectedRow, 0);

        String Date_debut = view.getDate_debut();
        String Date_fin = view.getDate_fin();
        Holiday.Types types = view.getTypes();
        int id_empl = view.getId_empl();


        Holiday conge = new Holiday(Date_debut, Date_fin, types,id_empl);
        CongeDAOImpl holidayImpl = new CongeDAOImpl();

        holidayImpl.update(conge,id_empl);

    }

    public void supprimerConge() {
        int selectedRow = view.congeTable.getSelectedRow();
        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un employé à supprimer !");

        }
        int id =view.getId(view.congeTable);
        CongeDAOImpl congeImpl = new CongeDAOImpl();

        int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cet employé ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            congeImpl.delete(id);

        }
    }
}