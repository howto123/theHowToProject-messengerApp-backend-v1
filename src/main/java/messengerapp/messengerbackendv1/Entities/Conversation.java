package messengerapp.messengerbackendv1.Entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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
public class Conversation {
    
    @Id
    @SequenceGenerator(
        name = "conversation_sequence",
        sequenceName = "conversation_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conversation_sequence"
    )
            
    private Integer conversationId;
    private Integer partner1;
    private Integer partner2;
    
    //---------------------------------------------
    // constructors, getters & setters, toString

    public Conversation(Integer conversationId, Integer partner1, Integer partner2) {
        this.conversationId = conversationId;
        this.partner1 = partner1;
        this.partner2 = partner2;
    }

    public Conversation(Integer partner1, Integer partner2) {
        this.partner1 = partner1;
        this.partner2 = partner2;
    }

    public Conversation() {
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Integer getPartner1() {
        return partner1;
    }

    public void setPartner1(Integer partner1) {
        this.partner1 = partner1;
    }

    public Integer getPartner2() {
        return partner2;
    }

    public void setPartner2(Integer partner2) {
        this.partner2 = partner2;
    }

    @Override
    public String toString() {
        return "Conversation{" + "conversationId=" + conversationId + ", partner1=" + partner1 + ", partner2=" + partner2 + '}';
    }

}
