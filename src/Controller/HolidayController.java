package Controller;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
        this.view.afficherButton.addActionListener(e->afficherConge());

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
            System.out.println("hna controller makhdamch");
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

            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un congé à supprimer !");

        }
        int id =view.getId(view.congeTable);
        CongeDAOImpl congeImpl = new CongeDAOImpl();

        int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cet congé ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            congeImpl.delete(id);

        }
    }

    public void afficherConge(){
        CongeDAOImpl congeDAO = new CongeDAOImpl();
        List<Holiday> conges = congeDAO.findAll();

        DefaultTableModel model = (DefaultTableModel) view.congeTable.getModel();
        model.setRowCount(0);

        for (Holiday cong : conges) {
            System.out.println("Conge " + cong.getId_empl());
            model.addRow(new Object[]{
                    cong.getId(),
                    cong.getId_empl(),
                    cong.getDate_debut(),
                    cong.getDate_fin(),
                    cong.getTypes(),
            });
        }

    }
}