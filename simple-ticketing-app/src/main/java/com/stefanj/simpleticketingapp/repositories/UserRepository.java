package com.stefanj.simpleticketingapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.User;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
	Optional<User> findByUserName(String userName);
}
