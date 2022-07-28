/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package messengerapp.messengerbackendv1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import messengerapp.messengerbackendv1.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(path = "/messageController")
public class MessageController {
    
    // Attribute
    private final MessengerService messengerService;

    // Constructor
    @Autowired
    public MessageController(MessengerService messengerService) {
        this.messengerService = messengerService;
    }
    
    // Methods
    @PostMapping(path = "/sendMessage")
    public String sendMessage(@RequestBody String json){
        
        // read request body into variables
        String messageContent="";
        Integer senderId=-1, receiverId=-1;
        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(json);
            messageContent = jsonNode.get("message").asText();
            senderId = jsonNode.get("senderId").asInt();
            receiverId = jsonNode.get("receiverId").asInt();
        } catch (JsonProcessingException ex) {}

        // create TextMessage object, call messengerService's function. no error code for now
        TextMessage message = new TextMessage(messageContent, senderId, receiverId);
        messengerService.addNewMessage(message);
                
        return "1";
        
    }
        
    @PostMapping(path = "/showSpecificChat")
    public String showSpecificChat(@RequestBody String json){
    
        // read request body into variables
        Integer partner1 = -1, partner2 = -1;
        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(json);
            partner1 = jsonNode.get("partner1").asInt();
            partner2 = jsonNode.get("partner2").asInt();
        } catch (JsonProcessingException ex) {}

        
        // make messengerService's method call
        List specificChatList =
                messengerService.showSpecificChat(partner1, partner2);
        
        
        // convert returned list into json String
        String string = "";
        ObjectMapper mapper = new ObjectMapper();
        try{
        string = mapper.writeValueAsString(specificChatList);
        }catch(JsonProcessingException e){}
        
        System.out.println(string);
        
        return string;
    }
    
    @PostMapping(path = "/showAllChats")
    public String showAllChats(@RequestBody String json){
        
        // read request body into variables
        int identity = -1;
        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(json);
            identity = jsonNode.get("identity").asInt();
        } catch (JsonProcessingException ex) {}
        
        
        // make messengerService's method call
        List allChatsList =
                messengerService.showAllChats(identity);
        
        
        // convert returned list into json String
        String string = "";
        try{
        string = objectMapper.writeValueAsString(allChatsList);
        }catch(JsonProcessingException e){}
        
        return string;
    }

}
