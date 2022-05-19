package com.example.rest_web_service.Resource;

import com.example.rest_web_service.Controller.UserController;
import com.example.rest_web_service.Model.Banco;
import com.example.rest_web_service.Model.User;
import com.example.rest_web_service.Model.User;
import com.example.rest_web_service.Service.SecuenceGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UserResource.USER_RESOURCE)
public class UserResource {
    public final static String USER_RESOURCE = "/users";
    UserController userController;

    @Autowired
    private SecuenceGenerator secuenceGenerator;


    @Autowired
    public UserResource(UserController userController){
        this.userController = userController;
    }

    @GetMapping("/users")
    public List<User> user(){
        return userController.getAllUser();
    }
    @GetMapping("/bancos")
    public List<Banco> bancos(){
        return userController.getAllBanco();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable("id") Long id){
        return userController.getIdUser(id);
    }
    @GetMapping("/bancos/{id}")
    public Banco findByIdBanco(@PathVariable("id") Long id){
        return userController.getIdBanco(id);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        user.setId(secuenceGenerator.generateSequence(User.SEQUENCE_NAME));
        userController.addUser(user);
    }
    @PostMapping("/bancos")
    public void addBanco(@RequestBody Banco banco){
        banco.setId(secuenceGenerator.generateSequence(Banco.SEQUENCE_NAME));
        for (User us : banco.getUsers()) {
            addUser(us);
        }
        userController.addBanco(banco);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userController.deleteUser(id);
    }

    @DeleteMapping("/bancos/{id}")
    public void deleteBanco(@PathVariable("id") Long id){
        userController.deleteBanco(id);
    }
    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id){
        userController.updateUser(user, id);
    }
    @PutMapping("/bancos/{id}")
    public void updateBanco(@RequestBody Banco banco, @PathVariable Long id){
        userController.updateBanco(banco, id);
    }

    @PatchMapping("/users/{id}")
    public void updatePartialUser(@RequestBody JsonPatch patch, @PathVariable Long id){
        try {
            userController.applyPatchToUser(patch, id);
        } catch (JsonPatchException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("/bancos/{id}")
    public void updatePartialBanco(@RequestBody JsonPatch patch, @PathVariable Long id){
        try {
            userController.applyPatchToBanco(patch, id);
        } catch (JsonPatchException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    /*{"op":"replace",
    "path":"/email",
    "value":"ratita@ratita"}

    * {"op":"add",
    * "path":"/email/0",
    * "value": "hola@hola"}

    * {"op":"test",
    "path":"/email",
    "value":"rata@rata"}

    {
    "op":"copy",
    "from":"/email/0",
    "path":"/email/-"
}
{
    "op":"move",
    "from":"/email/0",
    "path":"/email/-"
}
{
    "op":"remove",
    "path":"/?"
}*/

}