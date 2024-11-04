package com.example.telegram_bot_message_holiday_day_is_today.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "user_actions")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;

    @Column(name = "botcommand")
    private String botCommand;

    @Column(name = "called_at")
    private LocalDateTime localDateTime;
}
