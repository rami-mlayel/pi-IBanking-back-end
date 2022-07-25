package esprit.pi.SoftIB.services;

import java.util.List;

import esprit.pi.SoftIB.entities.User;

public interface IUserService {

	User getUser(Long id);

	List<User> getUsers();

	public User addUser(User user);

	User updateUser(User user);

	void deleteUser(Long id);

	User findUserByUsername(String username);
}
