package org.example.model;

public class Account {
    private Long id;
    private String name;
    private Enum<AccountStatus> status;

    public Account() {
    }

    public Account(String name, AccountStatus status) {
        this.name = name;
        this.status = status;
    }

    public Account(Long id, String name, AccountStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum<AccountStatus> getStatus() {
        return status;
    }

    public void setStatus(Enum<AccountStatus> status) {
        this.status = status;
    }
}
