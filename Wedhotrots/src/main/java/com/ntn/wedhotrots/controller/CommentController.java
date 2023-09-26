package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.pojo.Comments;
import com.ntn.wedhotrots.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentSer;

    @GetMapping("/comment/news/{id}")
    @CrossOrigin
    public List<Comments> getCommentByNews(@PathVariable int id){
        return commentSer.getCommentByNews(id);
    }

    @GetMapping("/comment")
    @CrossOrigin
    public List<Comments> getAlls(){
        return commentSer.getAllsComment();
    }

    @PostMapping("/comment")
    @CrossOrigin
    public boolean addComment(@RequestBody Comments comments){
        return commentSer.addComment(comments);
    }
}
