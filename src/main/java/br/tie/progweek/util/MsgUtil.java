package br.tie.progweek.util;

import br.tie.progweek.dto.MsgDTO;

public class MsgUtil {
    private static final String SUCCESS = "alert-success";
    private static final String WARNING = "alert-warning";
    private static final String DANGER = "alert-danger";

    public static MsgDTO success(String title, String text) {
        return new MsgDTO(SUCCESS, title, text);
    }

    public static MsgDTO warning(String title, String text) {
        return new MsgDTO(WARNING, title, text);
    }

    public static MsgDTO danger(String title, String text) {
        return new MsgDTO(DANGER, title, text);
    }
}
