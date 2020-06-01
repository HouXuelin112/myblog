package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.mapper.LinkMapper;
import it.hxl.myblogadmin1.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public int getLinkCount() {
        return linkMapper.getLinkCount();
    }
}
