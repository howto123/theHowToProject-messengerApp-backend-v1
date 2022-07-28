/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package messengerapp.messengerbackendv1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import messengerapp.messengerbackendv1.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Here the backend logic happens. All public methods mirror the functionality that the
 * user needs to be offered.
 * @author Lüku
 */

@Service
public class MessengerService {
    
    // Attributes
    private final AppUserRepository appUserRepository;
    private final TextMessageRepository textMessageRepository;
    private final ConversationRepository conversationRepository;

    // Constructor
    @Autowired
    public MessengerService(
            AppUserRepository appUserRepository,
            TextMessageRepository textMessageRepository,
            ConversationRepository conversationRepository
            ) {
        this.appUserRepository = appUserRepository;
        this.textMessageRepository = textMessageRepository;
        this.conversationRepository = conversationRepository;
    }

    // ---------main Methods, mirror frontend methods
    Integer createAccount(String name, String password) {
        // check, if name is already taken
        List<AppUser> list = appUserRepository.getUserByName(name);
        if(list.isEmpty()){
            // if not, put data into db
            AppUser user = new AppUser(name, password);
            appUserRepository.save(user);
            List<AppUser> newList = appUserRepository.getUserByName(name);
            return newList.get(0).getUserId();
        }else{
            System.out.println("Account name is already taken.");
            return -1;
        }
    }
    
    ArrayList<String[]> getUsersAndNames() {
        List<AppUser> list = appUserRepository.findAll(Sort.by(Sort.Direction.ASC, "appUserId"));
        if(list.isEmpty()){System.out.println("There are no accounts created yet.");}
        ArrayList<String[]> userAndNameList = new ArrayList();
        for(AppUser u : list){
            String[] entry = {u.getUserId().toString(), u.getUserName()};
            userAndNameList.add(entry);
        }
        return userAndNameList;
    }
    
    
    Integer checkLogin(String name, String password) {
        System.out.println("Name: "+ name + " Password: " + password);
        
        // create empty Object
        AppUser user = new AppUser();
        
        // check if there is an account with that name in db
        List<AppUser> list = appUserRepository.getUserByName(name);
        if(list.isEmpty()){
            System.out.println("There is no account with that name.");
            return -1;
        }else{
            // if yes, check if Password is correct.
            if(list.get(0).getUserPassword().equals(password)){
                user = list.get(0);
                return user.getUserId();
            }
            
            System.out.println("Wrong Password: " + password);
            System.out.println("Wrong Password: " + list.get(0).getUserPassword());
            return -2;
            
        }
        
    }

    public void addNewMessage(TextMessage message){
        System.out.println(message);
        message.setConversationId(
                getConversationId(message.getSenderId(), message.getReceiverId())
        );
        textMessageRepository.save(message);
    }
    
    public List<String[]> showSpecificChat(Integer partner1Id, Integer partner2Id){
        
        ArrayList<String[]> specificChatList = new ArrayList();
        
        // 1) find all messages of the partners
        int conversationId = getConversationId(partner1Id, partner2Id);
        List<TextMessage> listOfTextMessages =
                textMessageRepository.getMessagesByConversationID(conversationId);
        // 3) read user name of partner 1, partner 2
        AppUser user1 = appUserRepository.getReferenceById(partner1Id);
        String name1 = user1.getUserName();
        
        
        AppUser user2 = appUserRepository.getReferenceById(partner2Id);
        String name2 = user2.getUserName();
                
        // 4) replace partners id with name
        for(TextMessage m : listOfTextMessages){
            String[] temp = new String[2];
            if(m.getSenderId() == partner1Id){temp[0] = name1;}
                else{temp[0]=name2;}
            temp[1] = m.getMessageContent();
            specificChatList.add(temp);
        }

        return specificChatList;
    }
    
    public List<String[]> showAllChats (Integer identity){
        
        List<String[]> listOfStringArrays = new ArrayList();
        
        // 1) search for identity conversation table, get a list of conversations
        List<Conversation> conversationList = conversationRepository.
                getConversationByAppUserId(identity);
        
        // 2) loop, read conversation partner and conversation id, find most  
        // recent message as well as partner name, put all these two into
        // an arraylist of arrays of size two -> like we need it eventually
        // inside the table at the front end
        
        for(Conversation c : conversationList){
            
            // take conversationId, find latest message
            Integer conversationId = c.getConversationId();
            
            TextMessage message = textMessageRepository.findLastByConversationId(conversationId);
            String lastText = message.getMessageContent();
            
            
            // take partnerId, find partner name
            Integer partnerId = c.getPartner1();
            if(Objects.equals(c.getPartner1(), identity)){partnerId = c.getPartner2();}
            
            AppUser partnerUser = appUserRepository.getReferenceById(partnerId);
            String partnerName = partnerUser.getUserName();
            
        
            // put them into listOfStringArrays
            String[] stringArray = {partnerName, lastText};
            listOfStringArrays.add(stringArray);
            
        }

        // 4) if empty: "no conversations yet"
        if(listOfStringArrays.isEmpty()){
            listOfStringArrays.add(new String[]{"(none)", "no conversations yet"});
        }
        
        return listOfStringArrays;
    }
    
    
    //----------------helper methods-------------
  
    private Integer getConversationId(Integer partner1, Integer partner2){
        // returns conversationId if conversation exists already, otherwise
        // creates new conversation and returns its id
        
        //make sure that identities are sorted: partner1 < partner2
        if(partner1>partner2){
            Integer temp = partner1;
            partner1 = partner2;
            partner2 = temp;
        }
        
        // create object to return
        Conversation conversation = new Conversation(partner1, partner2);
        
        // check it conversation among these partners already exists in db
        List<Conversation> conversationList =
                conversationRepository.getConversationByConversationPartners(
                partner1, partner2);
        if(!conversationList.isEmpty()){conversation = conversationList.get(0);}
        else{
            // create new conversatin in database, have conversationId assigned automatically
            conversationRepository.save(conversation);
            // get that created element back, including conversationId
            List<Conversation> list = conversationRepository
                .getConversationByConversationPartners(partner1, partner2);
            conversation = list.get(0);
        }
        
        return conversation.getConversationId();
    }
    
    


}
