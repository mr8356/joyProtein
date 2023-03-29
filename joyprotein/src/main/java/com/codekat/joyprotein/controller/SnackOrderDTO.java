package com.codekat.joyprotein.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnackOrderDTO extends ItemOrderDTO{
    private int option;
    private String tasteCode;
}
