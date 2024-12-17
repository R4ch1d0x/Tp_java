package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.Employee;
import Model.Employee.Poste;


public class EmployeeDAOImpl implements GenericDAOI<Employee> {
    private Connection conn;

    public EmployeeDAOImpl() {
        this.conn = DBConnection.getConnexion();
    }

    @Override
    public void add(Employee E) {
        String Query = "INSERT INTO Employee(nom, prenom, email, telephone, salaire, role, poste) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(Query)) {
            stmt.setString(1, E.getNom());
            stmt.setString(2, E.getPrenom());
            stmt.setString(3, E.getEmail());
            stmt.setString(4, E.getTele());
            stmt.setDouble(5, E.getSalaire());
            stmt.setString(6, E.getRole().name());
            stmt.setString(7, E.getPoste().name());
            stmt.executeUpdate();

            System.out.println("Employé ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'employé !");
            //e.printStackTrace();

        }
    }

    public Employee findById(int employeId) {
        String query = "SELECT * FROM employee WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, employeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getDouble("salaire"),
                        Employee.Role.valueOf(rs.getString("role")),
                        Employee.Poste.valueOf(rs.getString("poste"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'employé par ID !!!!");
        }
        return null;
    }


    @Override
    public List<Employee> findAll() {
        List<Employee> employes = new ArrayList<>();
        String query = "SELECT * FROM employee";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                employes.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getDouble("salaire"),
                        Employee.Role.valueOf(rs.getString("role")),
                        Poste.valueOf(rs.getString("poste"))
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de tous les employés !!!!!");
        }
        return employes;
    }


    @Override
    public void update(Employee E, int id) {
        String query = "UPDATE Employee SET nom = ?, prenom = ?, email = ?, telephone = ?, salaire = ?, role = ?, poste = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, E.getNom());
            stmt.setString(2, E.getPrenom());
            stmt.setString(3, E.getEmail());
            stmt.setString(4, E.getTele());
            stmt.setDouble(5, E.getSalaire());
            stmt.setString(6, E.getRole().name());
            stmt.setString(7, E.getPoste().name());
            stmt.setInt(8, id);
            stmt.executeUpdate();

            System.out.println("Employe modifier avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modefication de l'employe !!!!!");
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Employee WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Employe supprimé avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'employe !!!!");
        }
    }


    public List<Employee.Role> findAllRoles() {
        return Arrays.asList(Employee.Role.values());
    }


    public List<Poste> findAllPostes() {
        return Arrays.asList(Poste.values());
    }

    public boolean is_solde_enough(int id, int days) {
        int dayCheck = 0;
        String query = "SELECT solde FROM employee WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dayCheck = rs.getInt("solde");
                    return dayCheck >= days;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void updateSolde(int id, int days) {
        String query = "UPDATE employee SET solde = solde - ? where id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, days);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise a jour de solde de l'employe !!!!");
        }
    }
}
