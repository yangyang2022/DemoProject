package com.yangyang.model;

import javax.persistence.*;

@Entity
@Table(name = "t_message")
public class Message {

    private Long id;
    private String text;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
