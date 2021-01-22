package org.alexeykarlyganov.rest.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    private @Id @GeneratedValue Long id;
    private String name;
    private String email;

    public User() {
    }

    public User(String name, String email) {
        this.setName(name);
        this.setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
        this.id = id;
	}

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.email);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", email='" + this.email + '\'' + "}\n";
    }
}
