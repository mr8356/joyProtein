package com.codekat.joyprotein.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AminoOrderDTO extends ItemOrderDTO{
    private int weight;
    private String tasteCode;
}
