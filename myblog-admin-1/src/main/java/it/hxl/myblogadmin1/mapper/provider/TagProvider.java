package it.hxl.myblogadmin1.mapper.provider;

import it.hxl.myblogadmin1.entity.Tag;
import org.apache.ibatis.jdbc.SQL;

public class TagProvider {

    public String updateTag(Tag tag){
        return new SQL(){
            {
                UPDATE("tags");
                if (tag.getTagDescription() != null && !tag.getTagDescription().equals("")){
                    SET("tagDescription=#{tagDescription}");
                }
                if (tag.getTagName() != null && !tag.getTagName().equals("")){
                    SET("tagName=#{tagName}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }

}
