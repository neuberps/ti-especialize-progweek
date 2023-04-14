package br.tie.progweek.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MsgDTO implements Serializable {

    private String cssClass;
    private String title;
    private String text;

}
