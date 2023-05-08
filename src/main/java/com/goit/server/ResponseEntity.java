package com.goit.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ResponseEntity {

    private final String content;
    private final Integer status;
}
