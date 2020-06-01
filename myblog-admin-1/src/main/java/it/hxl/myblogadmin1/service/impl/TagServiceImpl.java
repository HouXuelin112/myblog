package it.hxl.myblogadmin1.service.impl;


import it.hxl.myblogadmin1.entity.Tag;
import it.hxl.myblogadmin1.mapper.TagMapper;
import it.hxl.myblogadmin1.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagService")
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public int deleteTag(int id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public int insertTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }

    @Override
    public List<Tag> findAllTags() {
        return tagMapper.findAllTags();
    }

    @Override
    public Tag findTagById(int id) {
        return tagMapper.findTagById(id);
    }
}
