package it.hxl.myblogprod.service;

import it.hxl.myblogprod.entity.Link;

import java.util.List;

public interface LinkService {

    /**
     * 查找所有审核通过的链接
     * @return
     */
    List<Link> findAllPassLinks();

    int insertLink(Link link);

}
