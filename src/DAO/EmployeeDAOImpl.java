package DAO;

import java.io.*;
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

import javax.swing.*;


public class EmployeeDAOImpl implements GenericDAOI<Employee>, DataImportExport<Employee> {
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
        JOptionPane.showMessageDialog(null, "Solde Invalid!", "Error!", JOptionPane.INFORMATION_MESSAGE);

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

    @Override
    public void importData(String filePath) throws IOException {
        String Query = "INSERT INTO Employee(nom, prenom, email, telephone, salaire, role, poste) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             PreparedStatement stmt = conn.prepareStatement(Query)) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null){
                String[] data = line.split(",");
                if(data.length == 5){
                    stmt.setString(1,data[0].trim());
                    stmt.setString(2,data[1].trim());
                    stmt.setString(3,data[2].trim());
                    stmt.setString(4,data[3].trim());
                    stmt.setString(5,data[4].trim());
                    stmt.setString(6,data[5].trim());
                    stmt.setString(7,data[6].trim());
                    stmt.addBatch();
                }
            }
            stmt.executeBatch();
            System.out.println("Imported successfuly !");
        } catch (IOException | SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'employé !");
            //e.printStackTrace();

        }
    }

    @Override
    public void exportData(String fileName, List<Employee> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {
            writer.write("First Name, Last Name, Email, Phone, Role, Poste, Salary");
            writer.newLine();

            for(Employee emp: data){
                String line = String.format("%s, %s, %s, %s, %s, %s, %.2f",
                        emp.getPrenom(),
                        emp.getNom(),
                        emp.getEmail(),
                        emp.getTele(),
                        emp.getRole(),
                        emp.getPoste(),
                        emp.getSalaire()
                        );
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Les employés ont été exportés avec succès !");
        }
    }







}
