package br.com.hroauth.model.dto;

import java.util.Set;

public record User(Long id, String name, String email, String password, Set<Role> roles) {
}
