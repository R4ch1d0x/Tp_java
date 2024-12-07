package Model;

public class Employee {
    private String Nom,Prenom,Email,Tele;
    private Double Salaire;
    private Role role;
    private Poste poste;
    private int id;


    public Employee(int id,String Nom, String Prenom, String Email, String Tele, Double Salaire,Role role,Poste poste){
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Tele = Tele;
        this.Salaire = Salaire;
        this.role = role;
        this.poste = poste;
    }

    public Employee(String Nom, String Prenom, String Email, String Tele, Double Salaire,Role role,Poste poste){
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Tele = Tele;
        this.Salaire = Salaire;
        this.role = role;
        this.poste = poste;
    }


    public String getNom() {
        return Nom;
    }
    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }
    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }

    public String getTele() {
        return Tele;
    }
    public void setTele(String tele) {
        Tele = tele;
    }

    public Double getSalaire() {
        return Salaire;
    }
    public void setSalaire(Double salaire) {
        Salaire = salaire;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Poste getPoste() {
        return poste;
    }
    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public enum Poste{
        INGENIEUR_ETUDE_ET_DEVELOPPEMENT,
        TEAM_LEADER,
        Pilote
    }

    public enum Role{
        Admin,
        EMPLOYEE
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


}
