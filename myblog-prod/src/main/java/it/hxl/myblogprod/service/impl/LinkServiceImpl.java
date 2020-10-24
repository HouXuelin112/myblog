package it.hxl.myblogprod.service.impl;

import it.hxl.myblogprod.entity.Link;
import it.hxl.myblogprod.mapper.LinkMapper;
import it.hxl.myblogprod.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public List<Link> findAllPassLinks() {
        return linkMapper.findAllPassLinks();
    }

    @Override
    public int insertLink(Link link) {
        if (linkMapper.findLinkByName(link.getAddr()) != null){
            throw new RuntimeException("该链接已存在或正在审核，如有任何问题，请与管理员联系");
        }
        return linkMapper.insertLink(link);
    }
}
