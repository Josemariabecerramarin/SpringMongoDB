package com.example.rest_web_service.Resource;

import com.example.rest_web_service.Controller.UserController;
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
    @GetMapping
    public List<User> user(){
        return userController.getAllUser();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable("id") Long id){
        return userController.getId(id);
    }

    @GetMapping("{id}/email")
    public Map<String, String> findByEmail(@PathVariable("id") Long id){
        return Collections.singletonMap("email", userController.getId(id).getEmail());
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        user.setId(secuenceGenerator.generateSequence(User.SEQUENCE_NAME));
        userController.addUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userController.deleteUser(id);
    }

    @PutMapping("{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id){
        userController.updateUser(user, id);
    }

    @PatchMapping("{id}")
    public void updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
        try {
            userController.applyPatchToUser(patch, id);
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