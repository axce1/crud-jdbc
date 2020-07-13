package org.example.repository.jdbc;

import org.example.model.Account;
import org.example.model.AccountStatus;
import org.example.model.Skill;
import org.example.repository.SkillRepo;
import org.example.utils.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcSkillRepoImpl implements SkillRepo {

    private final Connection conn = ConnectionFactory.getConnection();

    @Override
    public boolean save(Skill skill) {
        try {
            PreparedStatement prepareStatement = conn.prepareStatement(
                "INSERT INTO Skill name VALUES (name)");
            prepareStatement.setString(1, skill.getName());
            prepareStatement.setLong(2, skill.getId());

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
            PreparedStatement prepareStatement = conn.prepareStatement("DELETE FROM Skill WHERE id=?");
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

    @Override
    public Skill findById(Long id) {
        try {
            String sql = "SELECT * FROM Skill WHERE id = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setLong(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if(resultSet.next()) {
                return extractSkill(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // TODO need check about return null
        return null;
    }

    @Override
    public boolean update(Skill skill) {
        try {
            PreparedStatement prepareStatement = conn.prepareStatement(
                    "UPDATE Skill SET name=? WHERE id=?");
            prepareStatement.setString(1, skill.getName());
            prepareStatement.setLong(3, skill.getId());

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
    public Set<Skill> findAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM skill");
            Set<Skill> skillSet = new HashSet<>();
            while(resultSet.next())
            {
                Skill skill = extractSkill(resultSet);
                skillSet.add(skill);
            }
            return skillSet;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Skill extractSkill(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();
        skill.setId(resultSet.getLong("id"));
        skill.setName(resultSet.getString("name"));

        return skill;
    }
}
