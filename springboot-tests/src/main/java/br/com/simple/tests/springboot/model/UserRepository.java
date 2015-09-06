package br.com.simple.tests.springboot.model;

import br.com.simple.tests.springboot.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UserRepository extends CrudRepository<User, BigInteger> {

}
