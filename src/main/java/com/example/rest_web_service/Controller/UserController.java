package com.example.rest_web_service.Controller;

import com.example.rest_web_service.Model.Banco;
import com.example.rest_web_service.Model.User;
import com.example.rest_web_service.Repository.BancoDao;
import com.example.rest_web_service.Repository.UserDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories(basePackageClasses = UserDao.class)
@Controller
public class UserController {
    UserDao userDao;

    public List<User> getAllUser(){
        return userDao.findAll();
    }
    @Autowired
    public UserController(UserDao userDao, BancoDao bancoDao){
        this.userDao = userDao;
        this.bancoDao = bancoDao;
    }
    public User getIdUser(Long id){
        return userDao.findById(id).get();
    }

    public void addUser(User user) {
        userDao.save(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    public void updateUser(User user, Long id) {

        User us = getIdUser(id);

        us.setEmail(user.getEmail());
        us.setPassword(user.getPassword());
        us.setFullName(user.getFullName());

        userDao.save(us);

    }
    /*{
            "email": "rata",
            "password": "1234",
            "fullName": "User 1"
    }*/

    public void applyPatchToUser(JsonPatch patch, Long id) throws JsonPatchException, JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Optional<User> targetUser = userDao.findById(id);
        JsonNode patched = patch.apply(om.convertValue(targetUser.get(), JsonNode.class));
        User userACambiar = om.treeToValue(patched, User.class);
        userDao.save(userACambiar);
    }
//----------------------------------------------------------------------------------------------------------------------
    BancoDao bancoDao;

    public List<Banco> getAllBanco(){
        return bancoDao.findAll();
    }

    public Banco getIdBanco(Long id){
        return bancoDao.findById(id).get();
    }

    public void addBanco(Banco banco) {
        bancoDao.save(banco);
    }

    public void deleteBanco(Long id) {
        bancoDao.deleteById(id);
    }

    public void updateBanco(Banco banco, Long id) {

        Banco b = getIdBanco(id);

        b.setNombre(banco.getNombre());
        b.setUsers(banco.getUsers());


        bancoDao.save(b);

    }


    public void applyPatchToBanco(JsonPatch patch, Long id) throws JsonPatchException, JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Optional<Banco> targetBanco = bancoDao.findById(id);
        JsonNode patched = patch.apply(om.convertValue(targetBanco.get(), JsonNode.class));
        Banco bancoACambiar = om.treeToValue(patched, Banco.class);
        bancoDao.save(bancoACambiar);
    }


}
