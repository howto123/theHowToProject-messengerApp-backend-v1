/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package messengerapp.messengerbackendv1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lüku
 */

@RestController
@CrossOrigin
@RequestMapping(path = "/appUserController")
public class AppUserController {
    
    private final MessengerService messengerService;

    @Autowired
    public AppUserController(MessengerService messengerService) {
        this.messengerService = messengerService;
    }
    
    @PostMapping
    public String createAccount(@RequestBody String json){
        // reads parameters, calls service function
        // returns newly created id or error info as String
        
        // read request, assign variables name, password
        String name="", password="";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(json);
            name = jsonNode.get("name").asText();
            password = jsonNode.get("password").asText();
        } catch (JsonProcessingException ex) {}

        // add account to database, return new user id or error info String
        Integer userIdIfSuccess = messengerService.createAccount(name, password);
        if(userIdIfSuccess==-1){return "account name already exists";}
        return userIdIfSuccess.toString();
    }
    
    @GetMapping(path = "/all")
    String getUsersAndNames(){
        //returns ids and names of all users in the database (arrays in arraylist as string)
        
        // create return string
        String string = "no accounts in database yet";
        
        // call service function
        ArrayList<String[]> userAndNameList = messengerService.getUsersAndNames();
        
        // transform result in desired format for response body:
        // string of arraylist of array of size 2
        ObjectMapper mapper = new ObjectMapper();
        try{
        string = mapper.writeValueAsString(userAndNameList);
        }catch(JsonProcessingException e){}
        
        System.out.println(string);
        return string;
    }
    
    @PostMapping(path = "/login")
    String checkLogin(@RequestBody String json){
        
        String name="", password="";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(json);
            name = jsonNode.get("name").asText();
            password = jsonNode.get("password").asText();
            System.out.println("Request body is: " + jsonNode.toString());
        } catch (JsonProcessingException ex) {}

        // check if given parameters correspond to data in database,
        // return new user id or error info String
        Integer userIdIfSuccess = messengerService.checkLogin(name, password);
        if(userIdIfSuccess==-1){return "account does not exist";}
        if(userIdIfSuccess==-2){return "wrong password";}
        return userIdIfSuccess.toString();
    }
    
 
}
