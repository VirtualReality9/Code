package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {

    private Integer attachmentId;
    private String attachmentName;
    private String attachmentUrl;
    private Integer documentId;
    private String UUID;

}
