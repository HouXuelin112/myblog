package it.hxl.myblogprod.service;

import it.hxl.myblogprod.entity.Tag;

import java.util.List;

public interface TagService {
    /**
     * 查找所有tag并返回
     * @return
     */
    List<Tag> findAllTags();

}
