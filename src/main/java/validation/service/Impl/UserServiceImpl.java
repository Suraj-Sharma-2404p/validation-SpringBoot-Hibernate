package validation.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validation.model.Address;
import validation.model.User;
import validation.repository.AddressRepository;
import validation.repository.UserRepository;
import validation.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Override
    public User createUser(User user) {
        Address address = addressRepository.save(user.getAddress());
        user.setAddress(address);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}
