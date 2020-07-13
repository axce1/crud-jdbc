package org.example.repository.jdbc;

import org.example.model.Developer;
import org.example.model.Skill;
import org.example.repository.DeveloperRepo;
import org.example.service.AccountService;
import org.example.utils.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcDeveloperRepoImpl implements DeveloperRepo {

    private final Connection conn = ConnectionFactory.getConnection();
    private AccountService accountService;

    @Override
    public boolean save(Developer developer) {
        try {
            PreparedStatement prepareStatement = conn.prepareStatement(
                    "INSERT INTO Developer (name, account_id) VALUES (?, ?)");
            prepareStatement.setString(1, developer.getNickname());
            prepareStatement.setString(2, String.valueOf(developer.getAccount()));

            ResultSet resultSet = prepareStatement.executeQuery();
            long developer_id = resultSet.getLong("id");

            Set<Skill> skillSet = developer.getSkills();
            for (Skill skill :
                    skillSet) {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO m2m_skill_developer (developer_id, skill_id) VALUES (?, ?)");
                prepareStatement.setString(1, String.valueOf(developer_id));
                prepareStatement.setString(2, String.valueOf(skill.getId()));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            // TODO удалять записи из базы плохая практика
            PreparedStatement prepareStatement = conn.prepareStatement("DELETE FROM Developer WHERE id=?");
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
    public Developer findById(Long id) {
        try {
            String sql = "SELECT * FROM Developer WHERE id = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setLong(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if(resultSet.next()) {
                return extractDeveloper(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // TODO need check about return null
        return null;
    }

    private Developer extractDeveloper(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        developer.setId(resultSet.getLong("id"));
        developer.setNickname(resultSet.getString("nickname"));
        developer.setAccount(accountService.findById(resultSet.getLong("account_id")));

        Set<Skill> skillSet = getDeveloperSkill(resultSet.getLong("id"));
        developer.setSkills(skillSet);

        return developer;
    }

    private Set<Skill> getDeveloperSkill(Long id) {
        try {
            String sql = "SELECT skill_id FROM m2m_skill_developer where developer_id = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setLong(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            Set<Skill> skillSet = new HashSet<>();
            while(resultSet.next())
            {
                Skill skill = new Skill();
                skill.setId(resultSet.getLong("id"));
                skill.setName(resultSet.getString("name"));

                skillSet.add(skill);
            }
            return skillSet;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Developer developer) {
        try {
            PreparedStatement prepareStatement = conn.prepareStatement(
                    "UPDATE Developer SET nickname=?, account_id=? WHERE id=?");
            prepareStatement.setString(1, developer.getNickname());
            prepareStatement.setString(2, String.valueOf(developer.getAccount()));
            prepareStatement.setLong(3, developer.getId());

            int i = prepareStatement.executeUpdate();
            if(i == 1) {
                Set<Skill> skillSet = developer.getSkills();
                for (Skill skill :
                        skillSet) {
                    PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO m2m_skill_developer (developer_id, skill_id) VALUES (?, ?)");
                    prepareStatement.setLong(1, developer.getId());
                    prepareStatement.setLong(2, skill.getId());
                }
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Set<Developer> findAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM developer");
            Set<Developer> developerSet = new HashSet<>();
            while(resultSet.next())
            {
                Developer developer = extractDeveloper(resultSet);
                developerSet.add(developer);
            }
            return developerSet;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
