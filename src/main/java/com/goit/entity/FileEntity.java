package com.goit.entity;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class FileEntity {
    private String id;
    private String name;
    private String extension;
    private Long creationDate;
}
