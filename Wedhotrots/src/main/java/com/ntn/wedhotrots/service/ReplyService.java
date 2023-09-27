package com.ntn.wedhotrots.service;

import com.ntn.wedhotrots.DTO.ReplyDTO;
import com.ntn.wedhotrots.pojo.Reply;
import org.springframework.stereotype.Service;

public interface ReplyService {
    boolean addRep(ReplyDTO r);
}

