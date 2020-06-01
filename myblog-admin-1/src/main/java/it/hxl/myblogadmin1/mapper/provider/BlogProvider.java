package it.hxl.myblogadmin1.mapper.provider;

import it.hxl.myblogadmin1.entity.Blog;
import org.apache.ibatis.jdbc.SQL;
import sun.awt.SunHints;

public class BlogProvider {

    public String insertBlog(Blog blog){
        return new SQL(){
            {
                INSERT_INTO("blog");
                if (blog.getTitle() != null && !blog.getTitle().equals("")){
                    VALUES("title", "#{title}");
                }
                if (blog.getFilename() != null && !blog.getFilename().equals("")){
                    VALUES("filename", "#{filename}");
                }
                if (blog.getAdmin() != null){
                    VALUES("Author_id", "#{admin.id}");
                }
               VALUES("isTop", "#{isTop}");
               VALUES("isOriginal", "#{isOriginal}");
                if (blog.getTag() != null){
                    VALUES("tag_id", "#{tag.id}");
                }
                VALUES("visitCount", "#{visitCount}");
                VALUES("CommentsCount", "#{commentsCount}");
                if (blog.getDisplayImage() != null){
                    VALUES("displayImage", "#{displayImage}");
                }
                if (blog.getIssueDate() != null){
                    VALUES("issueDate", "#{issueDate}");
                }
                if (blog.getDescription() != null){
                    VALUES("description", "#{description}");
                }
            }
        }.toString();
    }

}
