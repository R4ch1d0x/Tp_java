package View;


import Model.Employee;
import Model.Holiday;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class HolidayView extends JPanel{
    private JPanel mainPanel, topPanel, centerPanel,bottomPanel,employeePanel;
    private JTabbedPane switchPanels;

    private JLabel lblNom, lblType, lblDate_fin,lblDate_debut;
    private JTextField txtDate_fin, txtDate_debut,txtid_empl;

    public JTable congeTable;
    private JComboBox<Holiday.Types> cbTypes;

    public JButton ajouterButton = new JButton("Ajouter");
    public JButton modifierButton = new JButton("Modifier");
    public JButton supprimerButton = new JButton("Supprimer");
    public JButton afficherButton = new JButton("Afficher");;


    public HolidayView() {
        setSize(800, 600);
        setLayout(new BorderLayout());


        mainPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        centerPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        lblNom = new JLabel("id de l'employe:");
        txtid_empl = new JTextField();

        lblType = new JLabel("Type:");
        cbTypes = new JComboBox<>(Holiday.Types.values());

        lblDate_debut = new JLabel("Date de debut:");
        txtDate_debut = new JTextField();

        lblDate_fin = new JLabel("Date de fin:");
        txtDate_fin = new JTextField();

        topPanel.add(lblNom);
        topPanel.add(txtid_empl);
        topPanel.add(lblType);
        topPanel.add(cbTypes);
        topPanel.add(lblDate_debut);
        topPanel.add(txtDate_debut);
        topPanel.add(lblDate_fin);
        topPanel.add(txtDate_fin);


        congeTable = new JTable(new DefaultTableModel(new Object[]{"id","id de Employe", "Date de debut", "Date de fin", "Type"},0));
        JScrollPane scrollPane = new JScrollPane(congeTable);

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        bottomPanel.add(ajouterButton);
        bottomPanel.add(modifierButton);
        bottomPanel.add(supprimerButton);
        bottomPanel.add(afficherButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        add(mainPanel);
        //setVisible(true);
    }

    public String getDate_debut() {
        return txtDate_debut.getText();
    }
    public String getDate_fin() {
        return txtDate_fin.getText();
    }
    public int getId_empl() {
        return Integer.parseInt(txtid_empl.getText());
    }
    public Holiday.Types getTypes() {
        return (Holiday.Types) cbTypes.getSelectedItem();
    }

    public int getId(JTable table) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne !");
            return -1;
        }
        return (int) table.getValueAt(selectedRow, 0);

    }
}
