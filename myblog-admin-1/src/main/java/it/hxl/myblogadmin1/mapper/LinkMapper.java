package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.Link;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LinkMapper {

    @Insert("insert into links(icon, name, addr, description, status) values(#{icon}, #{name}, #{addr}, #{description}, #{status})")
    @Transactional
    int insertLink(Link link);

    @Select("select count(*) from links where status=0")
    int getHandleCounts();

    @Select("select count(*) from links")
    int getLinkCount();


    @Select("select * from (select top ${pageSize} * from links where id>=(select max(id) from (select top (${pageSize}*${lastPage}+1) * from links order by id asc) temp)) as t2 order by status")
    List<Link> findLinksByPage(@Param("pageSize") int pageSize, @Param("lastPage") int lastPage);

    @Select("select * from links where id = #{id}")
    Link findLinkById(int id);

    @Delete("delete from links where id = #{id}")
    @Transactional
    int deleteLinkById(int id);

    @Delete("delete from links where id in (${ids})")
    @Transactional
    int deleteMultiLinks(@Param("ids") String ids);

    @Update("update links set icon=#{icon}, name=#{name}, addr=#{addr}, description=#{description} where id=#{id}")
    @Transactional
    int updateLinks(Link link);

    @Update("update links set status=1 where id=#{id}")
    @Transactional
    int updateStatusById(@Param("id") int id);
}
