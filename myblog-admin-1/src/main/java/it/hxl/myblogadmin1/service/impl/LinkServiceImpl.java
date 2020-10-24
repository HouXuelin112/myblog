package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.Link;
import it.hxl.myblogadmin1.mapper.LinkMapper;
import it.hxl.myblogadmin1.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public int getLinkCount() {
        return linkMapper.getLinkCount();
    }

    @Override
    public List<Link> findLinksByPage(int pageSize, int currentPage) {
        return linkMapper.findLinksByPage(pageSize, currentPage-1);
    }

    @Override
    public Link findLinkById(int id) {
        return linkMapper.findLinkById(id);
    }

    @Override
    public int deleteLinkById(int id) {
        return linkMapper.deleteLinkById(id);
    }

    @Override
    public int deleteMultiLinks(String ids) {
        return linkMapper.deleteMultiLinks(ids);
    }

    @Override
    public int insertLink(Link link) {
        return linkMapper.insertLink(link);
    }

    @Override
    public int updateLinks(Link link) {
        return linkMapper.updateLinks(link);
    }

    @Override
    public int updateStatusById(int id) {
        return linkMapper.updateStatusById(id);
    }

    @Override
    public int getHandleCounts() {
        return linkMapper.getHandleCounts();
    }
}
