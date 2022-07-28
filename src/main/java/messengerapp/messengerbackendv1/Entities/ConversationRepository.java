/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package messengerapp.messengerbackendv1.Entities;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lüku
 */

@Repository
public interface ConversationRepository
        extends JpaRepository<Conversation, Integer> {
    
    
    @Query(value = "SELECT c FROM Conversation c WHERE c.partner1 = ?1 AND c.partner2 = ?2")
    List<Conversation> getConversationByConversationPartners(
        Integer partner1, Integer partner2);
   
    @Query(value = "SELECT c FROM Conversation c WHERE c.partner1 = ?1 OR c.partner2 = ?1")
    List<Conversation> getConversationByAppUserId(
        Integer appUserId);
    
}