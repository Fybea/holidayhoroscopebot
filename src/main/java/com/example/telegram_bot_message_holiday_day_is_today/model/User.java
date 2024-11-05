package com.example.telegram_bot_message_holiday_day_is_today.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "users")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Action> actions;

    @Column(name = "userid")
    private long userId;

    @Column(name = "username")
    @NotEmpty
    private String username;

    @Column(name = "firstname")
    @NotEmpty
    private String firstName;

    @Column(name = "lastname")
    @Nullable
    private String lastName;

    @Column(name = "languagecode")
    private String languageCode;
}
