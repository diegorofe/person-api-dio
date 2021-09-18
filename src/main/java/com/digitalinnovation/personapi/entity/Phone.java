package com.digitalinnovation.personapi.entity;

//lombok: anotações que evita criar getters serter, construdotres
import com.digitalinnovation.personapi.entity.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder //padrão de projetos
@Data //getter and seter
@AllArgsConstructor //construtor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType type;

    @Column(nullable = false)
    private String number;

}
