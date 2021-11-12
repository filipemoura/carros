package com.carros.api.users;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "role")
@Data
public class Role implements GrantedAuthority {
   
	private static final long serialVersionUID = 6410012658227848125L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}