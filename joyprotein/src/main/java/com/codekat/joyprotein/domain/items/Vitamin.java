package com.codekat.joyprotein.domain.items;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("vitamin")
@Getter
@Setter
public class Vitamin extends Item {
    private int units;
}
