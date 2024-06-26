package org.example.shopee.service.impl;

import org.example.shopee.model.Comment;
import org.example.shopee.repository.ICommentRepository;
import org.example.shopee.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }
    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
