package Model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Holiday {
    private String date_debut, date_fin;
    private Types type;
    private int id,id_empl;

    public Holiday (int id, String date_debut, String date_fin, Types type){
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
    }

    public Holiday ( String date_debut, String date_fin, Types type, int id_empl){
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.id_empl = id_empl;
    }

    public String getDate_debut() {
        return date_debut;
    }
    public void setDate_debut(String date_debut){this.date_debut = date_debut;}

    public String getDate_fin() {
        return date_fin;
    }
    public void setDate_fin(String date_fin){this.date_fin = date_fin;}


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_empl() {
        return id_empl;
    }
    public void setId_empl(int id_empl) {
        this.id_empl = id_empl;
    }

    public Holiday.Types getTypes() {
        return type;
    }
    public void setTypes(Holiday.Types type) {
        this.type = type;
    }


    public enum Types{
        Conge_Paye,
        Conge_non_Paye,
        Conge_Maladie
    }

}
