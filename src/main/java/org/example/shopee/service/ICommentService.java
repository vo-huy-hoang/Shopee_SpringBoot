package org.example.shopee.service;

import org.example.shopee.model.Comment;

import java.util.List;

public interface ICommentService {
    void save(Comment comment);
    List<Comment> findAll();
}
