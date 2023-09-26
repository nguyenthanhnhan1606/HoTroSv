package com.ntn.wedhotrots.service;

import com.ntn.wedhotrots.pojo.Comments;

import java.util.List;

public interface CommentService {
    List<Comments> getAllsComment();
    List<Comments> getCommentByNews(int id);
    boolean addComment(Comments comments);
}
