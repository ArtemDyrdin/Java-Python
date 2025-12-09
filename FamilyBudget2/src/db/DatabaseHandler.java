package db;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseHandler {

    private static final String URL = "jdbc:sqlite:FamilyBudgetDB.db";
    private Connection connection;

    // подключение
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }

    // ID вспомогательные методы
    private int getSphereId(String sphere) throws SQLException {
        String sql = "SELECT id FROM " + Const.Expences.EXPENCETBL +
                " WHERE " + Const.Expences.EXPENCE + " = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, sphere);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("id") : -1;
        }
    }

    private int getOriginId(String origin) throws SQLException {
        String sql = "SELECT id FROM " + Const.Origins.INCOMETBL +
                " WHERE " + Const.Origins.INCOME + " = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, origin);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("id") : -1;
        }
    }

    private int getFamilyMemberId(String name) throws SQLException {
        String sql = "SELECT id FROM " + Const.Persons.PERSONTBL +
                " WHERE " + Const.Persons.NAME + " = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("id") : -1;
        }
    }

    // вставки
    public void addExpenceL(int amount, String sphere, String member, LocalDate date) throws SQLException {
        String sql = "INSERT INTO " + Const.ExpenceL.EXPENCETBL +
                " (" + Const.ExpenceL.AMOUNT + "," +
                Const.ExpenceL.EXPENCECLASSID + "," +
                Const.ExpenceL.PERSONID + "," +
                Const.ExpenceL.DATE + ") VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setInt(2, getSphereId(sphere));
            ps.setInt(3, getFamilyMemberId(member));
            ps.setDate(4, Date.valueOf(date));
            ps.executeUpdate();
        }
    }

    public void addIncome(int amount, String origin, String member, LocalDate date) throws SQLException {
        String sql = "INSERT INTO " + Const.Income.INCOMETBL +
                " (" + Const.Income.AMOUNT + "," +
                Const.Income.ORIGINID + "," +
                Const.Income.PERSONID + "," +
                Const.Income.DATE + ") VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setInt(2, getOriginId(origin));
            ps.setInt(3, getFamilyMemberId(member));
            ps.setDate(4, Date.valueOf(date));
            ps.executeUpdate();
        }
    }

    public void addPerson(String name) throws SQLException {
        String sql = "INSERT INTO " + Const.Persons.PERSONTBL +
                "(" + Const.Persons.NAME + ") VALUES(?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public void addOrigin(String name) throws SQLException {
        String sql = "INSERT INTO " + Const.Origins.INCOMETBL +
                "(" + Const.Origins.INCOME + ") VALUES(?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public void addExpence(String name) throws SQLException {
        String sql = "INSERT INTO " + Const.Expences.EXPENCETBL +
                "(" + Const.Expences.EXPENCE + ") VALUES(?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    // обновления бюджета
    public void setBudget(int amount) throws SQLException {
        String updateSql = "UPDATE " + Const.Budget.BUDGET +
                " SET " + Const.Budget.BUDGETAMT + " = ? WHERE id = 1";
        try (PreparedStatement ps = getConnection().prepareStatement(updateSql)) {
            ps.setInt(1, amount);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                String insertSql = "INSERT INTO " + Const.Budget.BUDGET +
                        " (id, " + Const.Budget.BUDGETAMT + ") VALUES (1, ?)";
                try (PreparedStatement psIns = getConnection().prepareStatement(insertSql)) {
                    psIns.setInt(1, amount);
                    psIns.executeUpdate();
                }
            }
        }
    }

    public void setWeek(int amount) throws SQLException {
        String updateSql = "UPDATE " + Const.Week.WEEK +
                " SET " + Const.Week.WEEKAMT + " = ?, " + Const.Week.DATE + " = CURRENT_DATE WHERE id = 1";
        try (PreparedStatement ps = getConnection().prepareStatement(updateSql)) {
            ps.setInt(1, amount);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                String insertSql = "INSERT INTO " + Const.Week.WEEK +
                        " (id, " + Const.Week.WEEKAMT + ", " + Const.Week.DATE + ") VALUES (1, ?, CURRENT_DATE)";
                try (PreparedStatement psIns = getConnection().prepareStatement(insertSql)) {
                    psIns.setInt(1, amount);
                    psIns.executeUpdate();
                }
            }
        }
    }

    public void setMonth(int amount) throws SQLException {
        String updateSql = "UPDATE " + Const.Month.MONTH +
                " SET " + Const.Month.MONTHAMT + " = ?, " + Const.Month.DATE + " = CURRENT_DATE WHERE id = 1";
        try (PreparedStatement ps = getConnection().prepareStatement(updateSql)) {
            ps.setInt(1, amount);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                String insertSql = "INSERT INTO " + Const.Month.MONTH +
                        " (id, " + Const.Month.MONTHAMT + ", " + Const.Month.DATE + ") VALUES (1, ?, CURRENT_DATE)";
                try (PreparedStatement psIns = getConnection().prepareStatement(insertSql)) {
                    psIns.setInt(1, amount);
                    psIns.executeUpdate();
                }
            }
        }
    }


    // SELECT-методы
    public ResultSet getPersons() throws SQLException {
        String sql = "SELECT " + Const.Persons.NAME +
                " FROM " + Const.Persons.PERSONTBL;
        PreparedStatement ps = getConnection().prepareStatement(sql);
        return ps.executeQuery();
    }

    public ResultSet getOrigins() throws SQLException {
        String sql = "SELECT " + Const.Origins.INCOME +
                " FROM " + Const.Origins.INCOMETBL;
        PreparedStatement ps = getConnection().prepareStatement(sql);
        return ps.executeQuery();
    }

    public ResultSet getExpences() throws SQLException {
        String sql = "SELECT " + Const.Expences.EXPENCE +
                " FROM " + Const.Expences.EXPENCETBL;
        PreparedStatement ps = getConnection().prepareStatement(sql);
        return ps.executeQuery();
    }

    public int getBudget() throws SQLException {
        String sql = "SELECT " + Const.Budget.BUDGETAMT +
                " FROM " + Const.Budget.BUDGET + " WHERE id = 1";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public int getWeek() throws SQLException {
        String sql = "SELECT " + Const.Week.WEEKAMT +
                " FROM " + Const.Week.WEEK + " WHERE id = 1";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public int getMonth() throws SQLException {
        String sql = "SELECT " + Const.Month.MONTHAMT +
                " FROM " + Const.Month.MONTH + " WHERE id = 1";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    // удаление
    public void deletePerson(String name) throws SQLException {
        try (PreparedStatement ps = getConnection().prepareStatement(
                "DELETE FROM " + Const.Persons.PERSONTBL +
                        " WHERE " + Const.Persons.NAME + " = ?")) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public void deleteOrigin(String name) throws SQLException {
        try (PreparedStatement ps = getConnection().prepareStatement(
                "DELETE FROM " + Const.Origins.INCOMETBL +
                        " WHERE " + Const.Origins.INCOME + " = ?")) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public void deleteExpence(String name) throws SQLException {
        try (PreparedStatement ps = getConnection().prepareStatement(
                "DELETE FROM " + Const.Expences.EXPENCETBL +
                        " WHERE " + Const.Expences.EXPENCE + " = ?")) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    // суммы
    public int getTotalIncome() throws Exception {
        String sql = "SELECT SUM(" + Const.Income.AMOUNT + ") FROM " + Const.Income.INCOMETBL;
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public int getTotalExpenses() throws Exception {
        String sql = "SELECT SUM(" + Const.ExpenceL.AMOUNT + ") FROM " + Const.ExpenceL.EXPENCETBL;
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
}
