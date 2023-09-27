package com.ntn.wedhotrots.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ntn.wedhotrots.pojo.Comments;
import com.ntn.wedhotrots.pojo.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReplyDTO {
    private User idAdvisor;
    private Comments idComment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date ngayreply;
    private String content;
}
