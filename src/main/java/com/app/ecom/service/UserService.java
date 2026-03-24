package com.app.ecom.service;

import com.app.ecom.dto.AddressDTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.model.Address;
import com.app.ecom.repository.UserRepository;
import com.app.ecom.model.User;
import com.app.ecom.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    private List<User> userList = new ArrayList<>();
//    private Long nextId = 1L;

//    public List<User> fetchAllUsers(){
    public List<UserResponse> fetchAllUsers(){

//        return userList;
//        return userRepository.findAll();
//        List<User> userList = userRepository.findAll();
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

//    public void addUser(User user){
    public void addUser(UserRequest userRequest){
//        user.setId(nextId++);
//        userList.add(user);
        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);

    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if( userRequest.getAddressDTO() != null) {
            Address address = new Address();
            address.setCity(userRequest.getAddressDTO().getCity());
            address.setZipcode(userRequest.getAddressDTO().getZipcode());
            address.setStreet(userRequest.getAddressDTO().getStreet());
            address.setState(userRequest.getAddressDTO().getState());
            address.setCountry(userRequest.getAddressDTO().getCountry());
            user.setAddress(address);


        }
    }

    public Optional<UserResponse> fetchUser(Long id){
//        for(User user: userList) {
//            if(user.getId().equals(id)) {
//                return user;
//            }
//        }
//        return null;

//        return userList.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst();
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }


    public boolean updateUser(Long id, UserRequest updatedUserRequest){
//        return userList.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .map(existingUser -> {
//                    existingUser.setFirstName(updatedUser.getFirstName());
//                    existingUser.setLastName(updatedUser.getLastName());
//                    return true;
//                }).orElse(false);
        return userRepository.findById(id)
                .map(existingUser -> {
//                    existingUser.setFirstName(updatedUser.getFirstName());
//                    existingUser.setLastName(updatedUser.getLastName());
                    updateUserFromRequest(existingUser, updatedUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);

    }


    private UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setLastName(user.getLastName());
        response.setRole(user.getRole());

        if (user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddressDTO(addressDTO);
        }
        return response;
    }
}
