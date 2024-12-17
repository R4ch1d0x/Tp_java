package View;


import Model.Employee;
import Model.Holiday;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class HolidayView extends JFrame{
    private JPanel mainPanel, topPanel, centerPanel,bottomPanel,employeePanel;
    private JTabbedPane switchPanels;

    private JLabel lblNom, lblType, lblDate_fin,lblDate_debut;
    private JTextField Date_fin, Date_debut,id_empl;

    public JTable congeTable;
    private JComboBox<Holiday.Types> cbTypes;

    public JButton ajouterButton = new JButton("Ajouter");;
    public JButton modifierButton = new JButton("Modifier");;
    public JButton supprimerButton = new JButton("Supprimer");;

    public HolidayView() {
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        centerPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        lblNom = new JLabel("id de l'employe:");
        id_empl = new JTextField();
        lblType = new JLabel("Type:");
        cbTypes = new JComboBox<>(Holiday.Types.values());

        lblDate_debut = new JLabel("Date de debut:");
        Date_debut = new JTextField();
        lblDate_fin = new JLabel("Date de fin:");
        Date_fin = new JTextField();

        topPanel.add(lblNom);
        topPanel.add(lblType);
        topPanel.add(lblDate_debut);
        topPanel.add(lblDate_fin);


        congeTable = new JTable(new DefaultTableModel(new Object[]{"id","id de Employe", "Date de debut", "Date de fin", "Type"},0));
        JScrollPane scrollPane = new JScrollPane(congeTable);

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        bottomPanel.add(ajouterButton);
        bottomPanel.add(modifierButton);
        bottomPanel.add(supprimerButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        employeePanel = new JPanel();
        switchPanels = new JTabbedPane();
        switchPanels.add("Employees", employeePanel);
        switchPanels.add("Holidays", mainPanel);

        add(mainPanel);
        setVisible(true);
    }

    public String getDate_debut() {
        return Date_debut.getText();
    }
    public String getDate_fin() {
        return Date_fin.getText();
    }
    public int getId_empl() {
        return Integer.parseInt(id_empl.getText());
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
