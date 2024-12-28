package db;
import java.sql.*;
import java.time.LocalDate;

public class DatabaseHandler {
    private Connection connection;
    private final String url = "jdbc:sqlite:FamilyBudgetDB.db"; // Укажите путь к вашей базе данных

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
        return connection;
    }

    public void addExpenceL(int amount, String sphere, String familyMember, LocalDate date) throws SQLException {
            String insert = "INSERT INTO " + Const.ExpenceL.EXPENCETBL + " (" + Const.ExpenceL.AMOUNT + ", "
                    + Const.ExpenceL.EXPENCECLASSID + ", " + Const.ExpenceL.PERSONID + ", " + Const.ExpenceL.DATE
                    + ") VALUES (?, ?, ?, ?)";
            PreparedStatement prSt = getConnection().prepareStatement(insert);

            prSt.setInt(1, amount);
            prSt.setInt(2, getSphereId(sphere)); // Метод для получения ID сферы
            prSt.setInt(3, getFamilyMemberId(familyMember)); // Метод для получения ID члена семьи
            prSt.setDate(4, java.sql.Date.valueOf(date));

            prSt.executeUpdate();
    }

    private int getSphereId(String sphere) throws SQLException {
        String select = "SELECT id FROM " + Const.Expences.EXPENCETBL + " WHERE " + Const.Expences.EXPENCE + " = ?";
        PreparedStatement prSt = getConnection().prepareStatement(select);
        prSt.setString(1, sphere);
        ResultSet rs = prSt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }

    private int getFamilyMemberId(String familyMember) throws SQLException {
        String select = "SELECT id FROM " + Const.Persons.PERSONTBL + " WHERE " + Const.Persons.NAME + " = ?";
        PreparedStatement prSt = getConnection().prepareStatement(select);
        prSt.setString(1, familyMember);
        ResultSet rs = prSt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }

    public void addPerson(String Name) throws SQLException {
        String insert = "INSERT INTO " + Const.Persons.PERSONTBL + "(" + Const.Persons.NAME + ")"
                + "VALUES(?)";
        PreparedStatement prSt = getConnection().prepareStatement(insert);
        prSt.setString(1, Name);
        prSt.executeUpdate();
    }

    public void addOrigin(String Name) throws SQLException {
        String insert = "INSERT INTO " + Const.Origins.INCOMETBL + "(" + Const.Origins.INCOME + ")"
                + "VALUES(?)";
        PreparedStatement prSt = getConnection().prepareStatement(insert);
        prSt.setString(1, Name);
        prSt.executeUpdate();
    }

    public void addExpence(String Name) throws SQLException {
        String insert = "INSERT INTO " + Const.Expences.EXPENCETBL + "(" + Const.Expences.EXPENCE + ")"
                + "VALUES(?)";
        PreparedStatement prSt = getConnection().prepareStatement(insert);
        prSt.setString(1, Name);
        prSt.executeUpdate();
    }

    public void setBudget(int amount) throws SQLException {
        String sett = "UPDATE " + Const.Budget.BUDGET + " SET " + Const.Budget.BUDGETAMT + " = ? WHERE id = 1";
        PreparedStatement psSt = getConnection().prepareStatement(sett);
        psSt.setInt(1, amount);
        psSt.executeUpdate();
    }

    public void setWeek(int amount) throws SQLException {
        String sett = "UPDATE " + Const.Week.WEEK + " SET " + Const.Week.WEEKAMT + " = ? ," + Const.Week.DATE + " = date() WHERE id = 1";
        PreparedStatement psSt = getConnection().prepareStatement(sett);
        psSt.setInt(1, amount);
        psSt.executeUpdate();
    }

    public void setMonth(int amount) throws SQLException {
        String sett = "UPDATE " + Const.Month.MONTH + " SET " + Const.Month.MONTHAMT + " = ? ," + Const.Month.DATE + " = date() WHERE id = 1";
        PreparedStatement psSt = getConnection().prepareStatement(sett);
        psSt.setInt(1, amount);
        psSt.executeUpdate();
    }

    public ResultSet getPersons() throws SQLException {
        String select = "SELECT " + Const.Persons.NAME + " FROM " + Const.Persons.PERSONTBL;
        PreparedStatement prSt = getConnection().prepareStatement(select);
        ResultSet rs = prSt.executeQuery();
        return rs;
    }

    public ResultSet getOrigins() throws SQLException {
        String select = "SELECT " + Const.Origins.INCOME + " FROM " + Const.Origins.INCOMETBL;
        PreparedStatement prSt = getConnection().prepareStatement(select);
        ResultSet rs = prSt.executeQuery();
        return rs;
    }

    public ResultSet getExpences() throws SQLException {
        String select = "SELECT " + Const.Expences.EXPENCE + " FROM " + Const.Expences.EXPENCETBL;
        PreparedStatement prSt = getConnection().prepareStatement(select);
        ResultSet rs = prSt.executeQuery();
        return rs;
    }

    public int getBudget() throws SQLException {
        String select = "SELECT " + Const.Budget.BUDGETAMT + " FROM " + Const.Budget.BUDGET + " WHERE id = 1";
        int amount = 0;
        PreparedStatement prSt = getConnection().prepareStatement(select);
        ResultSet rs = prSt.executeQuery();
        if (rs.next()) {
            amount = rs.getInt(Const.Budget.BUDGETAMT);
        }
        connection.close();
        return amount;
    }

    public int getWeek() throws SQLException {
        String select = "SELECT " + Const.Week.WEEKAMT + " FROM " + Const.Week.WEEK + " WHERE id = 1";
        int amount = 0;
        PreparedStatement prSt = getConnection().prepareStatement(select);
        ResultSet rs = prSt.executeQuery();
        if (rs.next()) {
            amount = rs.getInt(Const.Week.WEEKAMT);
        }
        connection.close();
        return amount;
    }

    public int getMonth() throws SQLException {
        String select = "SELECT " + Const.Month.MONTHAMT + " FROM " + Const.Month.MONTH + " WHERE id = 1";
        int amount = 0;
        PreparedStatement prSt = getConnection().prepareStatement(select);
        ResultSet rs = prSt.executeQuery();
        if (rs.next()) {
            amount = rs.getInt(Const.Month.MONTHAMT);
        }
        connection.close();
        return amount;
    }

    public void deletePerson(String name) throws SQLException {
        String delete = "DELETE FROM " + Const.Persons.PERSONTBL + " WHERE " + Const.Persons.NAME + " = ?";
        PreparedStatement prSt = getConnection().prepareStatement(delete);
        prSt.setString(1, name);
        prSt.executeUpdate();
    }

    public void deleteOrigin(String name) throws SQLException {
        String delete = "DELETE FROM " + Const.Origins.INCOMETBL + " WHERE " + Const.Origins.INCOME + " = ?";
        PreparedStatement prSt = getConnection().prepareStatement(delete);
        prSt.setString(1, name);
        prSt.executeUpdate();
    }

    public void deleteExpence(String name) throws SQLException {
        String delete = "DELETE FROM " + Const.Expences.EXPENCETBL + " WHERE " + Const.Expences.EXPENCE + " = ?";
        PreparedStatement prSt = getConnection().prepareStatement(delete);
        prSt.setString(1, name);
        prSt.executeUpdate();
    }
}