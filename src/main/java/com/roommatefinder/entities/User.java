package com.roommatefinder.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Builder.Default
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> outgoingMessages = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "recipient")
    private List<Message> incomingMessages = new ArrayList<>();

    public void sendMessage(User recipient, Message message) {
        this.outgoingMessages.add(message);
        message.setSender(this);
        recipient.incomingMessages.add(message);
        message.setRecipient(recipient);
    }

}