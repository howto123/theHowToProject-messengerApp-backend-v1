/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package messengerapp.messengerbackendv1.Entities;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lüku
 */

@Repository
public interface TextMessageRepository
        extends JpaRepository<TextMessage, Integer> {
    
    @Query(value = "SELECT m FROM TextMessage m WHERE m.conversationId = ?1")
    List<TextMessage> getMessagesByConversationID(Integer conversationId);
    
    @Query(
            value = "SELECT * FROM text_message WHERE conversation_id = ?1 ORDER BY message_id DESC LIMIT 1",
            nativeQuery = true)
    TextMessage findLastByConversationId(Integer conversationId);
    
}