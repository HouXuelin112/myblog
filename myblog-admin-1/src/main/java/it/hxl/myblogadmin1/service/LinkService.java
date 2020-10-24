package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.Link;

import java.util.List;

public interface LinkService {
    int getLinkCount();
    List<Link> findLinksByPage(int pageSize, int currentPage);
    Link findLinkById(int id);

    int deleteLinkById(int id);


    int deleteMultiLinks(String ids);

    int insertLink(Link link);

    int updateLinks(Link link);


    int updateStatusById(int id);

    int getHandleCounts();

}
