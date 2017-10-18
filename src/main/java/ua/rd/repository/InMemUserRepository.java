package ua.rd.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ua.rd.domain.User;

public class InMemUserRepository implements UserRepository {
	private static Long idCounter = Long.valueOf(0);
	private Map<Long,User> userRepo;
	
	public InMemUserRepository(Map<Long,User> userRepo){
		this.userRepo=userRepo;
	}
	@Override
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(idCounter++);
		}
		put(user);
		return user;
	}
	
	@Override
	public User update(User user) {
		return save(user);
	}

	@Override
	public Optional<User> get(Long userId) {
		return Optional.ofNullable(userRepo.get(userId));
	}

	@Override
	public Optional<User> getUserByName(String username) {
		return userRepo.values().stream().filter(user->user.getName().equals(username)).findAny();
	}

	
	private User put(User user) {
		return userRepo.put(user.getId(),user);
	}
}
