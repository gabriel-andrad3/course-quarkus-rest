package io.github.gabriel.andrad3.quarkussocial.domain.repository;

import io.github.gabriel.andrad3.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {


}
