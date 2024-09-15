package com.riwi.template.application.dtos.reponses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class UserResponse {
    private Long id;
    private String name;
}
