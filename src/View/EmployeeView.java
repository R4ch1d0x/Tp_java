package View;

import Model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeeView extends JPanel {
    private JPanel mainPanel, topPanel, centerPanel, bottomPanel;
    private JLabel lblNom, lblPrenom, lblEmail, lblTelephone, lblSalaire, lblPoste, lblRole;

    private JTextField nomField, prenomField, emailField, teleField, salaireField;
    private JComboBox<Employee.Role> cbrole;
    private JComboBox<Employee.Poste> cbPoste;
    public JTable employeeTable;
    public JButton ajouterButton = new JButton("Ajouter");;
    public JButton modifierButton = new JButton("Modifier");;
    public JButton supprimerButton = new JButton("Supprimer");;
    public JButton afficherButton = new JButton("Afficher");;

    public EmployeeView() {

        setSize(800, 600);

        setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        centerPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        lblNom = new JLabel("Nom:");
        nomField = new JTextField();

        lblPrenom = new JLabel("Prénom:");
        prenomField = new JTextField();

        lblEmail = new JLabel("Email:");
        emailField = new JTextField();

        lblTelephone = new JLabel("Téléphone:");
        teleField = new JTextField();

        lblSalaire = new JLabel("Salaire:");
        salaireField = new JTextField();

        lblPoste = new JLabel("Poste:");
        cbPoste = new JComboBox<>(Employee.Poste.values());

        lblRole = new JLabel("Rôle:");
        cbrole = new JComboBox<>(Employee.Role.values());



        topPanel.add(lblNom);
        topPanel.add(nomField);
        topPanel.add(lblPrenom);
        topPanel.add(prenomField);
        topPanel.add(lblEmail);
        topPanel.add(emailField);
        topPanel.add(lblTelephone);
        topPanel.add(teleField);
        topPanel.add(lblSalaire);
        topPanel.add(salaireField);
        topPanel.add(lblRole);
        topPanel.add(cbrole);
        topPanel.add(lblPoste);
        topPanel.add(cbPoste);


        employeeTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Nom", "Prénom", "Email", "Téléphone", "Salaire", "Poste", "Rôle","solde"},0));
        JScrollPane scrollPane = new JScrollPane(employeeTable);


        centerPanel.add(scrollPane, BorderLayout.CENTER);

        bottomPanel.add(ajouterButton);
        bottomPanel.add(modifierButton);
        bottomPanel.add(supprimerButton);
        bottomPanel.add(afficherButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        //setVisible(true);

    }


    public int getId(JTable table) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne !");
            return -1;
        }
        return (int) table.getValueAt(selectedRow, 0);

    }

    public String getNom() {
        return nomField.getText();
    }

    public String getPrenom() {
        return prenomField.getText();
    }
    public String getEmail() {
        return emailField.getText();
    }
    public String getTele() {
        return teleField.getText();
    }
    public double getSalaire() {
        return Double.parseDouble(salaireField.getText());
    }

    public Employee.Role getRole() {
        return (Employee.Role) cbrole.getSelectedItem();
    }
    public Employee.Poste getPoste() {
        return (Employee.Poste) cbPoste.getSelectedItem();
    }


}
