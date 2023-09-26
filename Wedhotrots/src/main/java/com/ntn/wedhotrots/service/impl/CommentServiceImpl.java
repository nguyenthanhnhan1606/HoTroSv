package com.ntn.wedhotrots.service.impl;

import com.ntn.wedhotrots.pojo.Comments;
import com.ntn.wedhotrots.repository.CommentRepository;
import com.ntn.wedhotrots.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepo;


    @Override
    public List<Comments> getAllsComment() {
        return commentRepo.findAll();
    }

    @Override
    public List<Comments> getCommentByNews(int id) {
        return commentRepo.getCommentsByIdNews(id);
    }

    @Override
    public boolean addComment(Comments comments) {
        try{
            Comments c = new Comments();
            c.setContent(comments.getContent());
            c.setIdNews(comments.getIdNews());
            c.setNgaybinhluan(new Date());
            c.setIdUser(comments.getIdUser());
            commentRepo.save(c);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
