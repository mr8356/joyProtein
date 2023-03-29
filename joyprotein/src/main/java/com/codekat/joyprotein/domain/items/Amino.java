package com.codekat.joyprotein.domain.items;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("amino")
@Getter
@Setter
public class Amino extends Item{
    private int weight; // 단위 : g 
    private String tasteCode;
}
