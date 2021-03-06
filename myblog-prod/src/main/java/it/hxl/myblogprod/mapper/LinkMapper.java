package it.hxl.myblogprod.mapper;

import it.hxl.myblogprod.entity.Link;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LinkMapper {

    @Select("select * from links where status=1")
    List<Link>  findAllPassLinks();

    @Select("select top 1 * from links where addr=#{addr}")
    Link findLinkByName(@Param("addr") String addr);

    @Insert("insert into links(icon, name, addr, description, status) values(#{icon}, #{name}, #{addr}, #{description}, #{status})")
    @Transactional
    int insertLink(Link link);

}
