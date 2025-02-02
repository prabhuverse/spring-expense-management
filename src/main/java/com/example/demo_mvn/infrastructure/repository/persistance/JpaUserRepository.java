package com.example.demo_mvn.infrastructure.repository.persistance;


import com.example.demo_mvn.domain.model.User;
import com.example.demo_mvn.domain.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// implements the technical details
@Repository
public class JpaUserRepository implements UserRepository {

	@Autowired
	SpringDataUserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
