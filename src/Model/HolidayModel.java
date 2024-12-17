package Model;

import DAO.CongeDAOImpl;
import DAO.EmployeeDAOImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HolidayModel {
    private CongeDAOImpl conge;
    private EmployeeDAOImpl empl;
    public HolidayModel(CongeDAOImpl conge,EmployeeDAOImpl empl ){
        this.conge = conge;
        this.empl = empl;
    }

    public boolean ajouterConge (String date_debut, String date_fin, Holiday.Types type, int id_empl){

        int jours = duree(date_debut,date_fin);
        if(empl.is_solde_enough(id_empl, jours )){
            Holiday nouveauConge = new Holiday(date_debut, date_fin, type, id_empl);
            conge.add(nouveauConge);
            empl.updateSolde(id_empl,jours);
            return true;

        }
        return false;
    }

    public int duree(String startDate, String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date date1 = sdf.parse(startDate);
            Date date2 = sdf.parse(endDate);

            long diffInMillies = Math.abs(date2.getTime() - date1.getTime());

            return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean updateHoliday(String date_debut, String date_fin, Holiday.Types type, int id_empl) {

        Holiday new_conge = new Holiday(date_debut, date_fin, type, id_empl);
        conge.update(new_conge,id_empl);

        return true;
    }





}
