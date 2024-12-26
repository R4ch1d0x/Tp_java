package View;

import javax.swing.*;
import java.awt.*;

public class GlobalView extends JFrame {
    private JTabbedPane globalPanel;
    private EmployeeView Emplview = new EmployeeView();
    private HolidayView HolidayView = new HolidayView();

    public GlobalView(EmployeeView Emplview,HolidayView HolidayView ){
        this.Emplview = Emplview;
        this.HolidayView = HolidayView;

        setTitle("Gestion des Employés et Congés ");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        globalPanel = new JTabbedPane();

        globalPanel.addTab("Employés", Emplview);
        globalPanel.addTab("Congés", HolidayView);

        add(globalPanel,BorderLayout.CENTER);
        setVisible(true);

    }
}
