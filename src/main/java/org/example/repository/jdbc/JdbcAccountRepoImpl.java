package org.example.repository.jdbc;

import org.example.model.Account;
import org.example.model.AccountStatus;
import org.example.repository.AccountRepo;
import org.example.utils.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcAccountRepoImpl implements AccountRepo {

    private final Connection conn = ConnectionFactory.getConnection();

    @Override
    public Account findById(Long id) {
        try {
            String sql = "SELECT * FROM Account WHERE id = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setLong(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if(resultSet.next()) {
                return extractAccount(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // TODO need check about return null
        return null;
    }

    @Override
    public Set<Account> findAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM account");
            Set<Account> accounts = new HashSet<>();
            while(resultSet.next())
            {
                Account account = extractAccount(resultSet);
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement prepareStatement = conn.prepareStatement(
                    "INSERT INTO Account (name, status) VALUES (?, ?)");
            prepareStatement.setString(1, account.getName());
            prepareStatement.setString(2, account.getStatus().name());

            int i = prepareStatement.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Account account) {
        try {
            PreparedStatement prepareStatement = conn.prepareStatement(
                    "UPDATE Account SET name=?, status=? WHERE id=?");
            prepareStatement.setString(1, account.getName());
            prepareStatement.setString(2, "test");
            prepareStatement.setLong(3, account.getId());

            int i = prepareStatement.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            // TODO удалять записи из базы плохая практика
            PreparedStatement prepareStatement = conn.prepareStatement("DELETE FROM Account WHERE id=?");
            prepareStatement.setLong(1, id);
            int i = prepareStatement.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Account extractAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("id"));
        account.setName(resultSet.getString("name"));
        account.setStatus(AccountStatus.valueOf(resultSet.getString("status")));

        return account;
    }
}
