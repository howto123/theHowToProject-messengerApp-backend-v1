package messengerapp.messengerbackendv1.Entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Lüku
 */


@Entity
@Table
public class TextMessage {
    
    @Id
    @SequenceGenerator(
        name = "textMesssage_sequence",
        sequenceName = "textMessage_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "textMessage_sequence"
    )
            
    private Integer messageId;
    private String messageContent;
    private Integer senderId;
    private Integer receiverId;
    private Integer conversationId;
    private Date timeOfCreation;

    
    // constructors, getters & setters, toString

    public TextMessage(Integer messageId, String messageContent, Integer senderId, Integer receiverId, Integer conversationId, Date timeOfCreation) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.conversationId = conversationId;
        this.timeOfCreation = timeOfCreation;
    }

    public TextMessage(String messageContent, Integer senderId, Integer receiverId) {
        this.messageContent = messageContent;
        this.senderId = senderId;
        this.receiverId = receiverId;
        }

    public TextMessage() {
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Date getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(Date timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    @Override
    public String toString() {
        return "TextMessage{" + "messageId=" + messageId + ", messageContent=" + messageContent + ", senderId=" + senderId + ", receiverId=" + receiverId + ", conversationId=" + conversationId + ", timeOfCreation=" + timeOfCreation + '}';
    }
    
    
    
}
