package org.example.model.hibernate;

import org.example.model.hibernate.Account;
import org.example.model.hibernate.Skill;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="developer")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String nickname;

    @ManyToMany
    @JoinTable(
        name="m2m_skill_developer",
        joinColumns=@JoinColumn(name="fk_developer", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="fk_skill", referencedColumnName="id"))
    Set<Skill> skills;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id", referencedColumnName = "id")
    Account account;

    public Developer() {
    }

    public Developer(String nickname) {
        this.nickname = nickname;
    }

    public Developer(Long id, String nickname, Set<Skill> skills, Account account) {
        this.id = id;
        this.nickname = nickname;
        this.skills = skills;
        this.account = account;
    }

    public Developer(Set<Skill> skills, Account account) {
        this.skills = skills;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", skills=" + skills +
                ", account=" + account +
                '}';
    }
}
