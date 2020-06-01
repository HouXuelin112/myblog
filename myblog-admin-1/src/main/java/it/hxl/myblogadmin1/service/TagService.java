package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.Tag;

import java.util.List;

public interface TagService {

    int deleteTag(int id);

    int updateTag(Tag tag);

    int insertTag(Tag tag);

    List<Tag> findAllTags();

    Tag findTagById(int id);

}
