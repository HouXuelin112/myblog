package it.hxl.myblogprod.dao;

import it.hxl.myblogprod.entity.Tag;

import java.util.List;

public interface TagDao {

    /**
     * 查找所有标签
     * @return
     */
    List<Tag> findAllTags();

}
