package com.ntn.wedhotrots.service.impl;

import com.ntn.wedhotrots.DTO.ReplyDTO;
import com.ntn.wedhotrots.pojo.Comments;
import com.ntn.wedhotrots.pojo.Reply;
import com.ntn.wedhotrots.repository.CommentRepository;
import com.ntn.wedhotrots.repository.ReplyRepository;
import com.ntn.wedhotrots.repository.UserRepository;
import com.ntn.wedhotrots.service.ReplyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyRepository replyRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean addRep(ReplyDTO r) {
        try {
            Reply r1 = new Reply();
            r1.setIdComment(r.getIdComment());
            r1.setIdAdvisor(r.getIdAdvisor());
            r1.setContent(r.getContent());
            r1.setNgayreply(new Date());
            replyRepo.save(r1);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
