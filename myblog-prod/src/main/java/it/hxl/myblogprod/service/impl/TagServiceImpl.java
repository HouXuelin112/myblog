package it.hxl.myblogprod.service.impl;

import it.hxl.myblogprod.entity.Tag;
import it.hxl.myblogprod.mapper.TagMapper;
import it.hxl.myblogprod.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagService")
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> findAllTags() {
        return tagMapper.findAllTags();
    }
}
