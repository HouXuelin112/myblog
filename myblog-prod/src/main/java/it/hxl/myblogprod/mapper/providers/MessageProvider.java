package it.hxl.myblogprod.mapper.providers;

import it.hxl.myblogprod.entity.Message;
import org.apache.ibatis.jdbc.SQL;

public class MessageProvider {

    public String insertMessage(Message message){
        return new SQL(){
            {
                INSERT_INTO("message");
                if(message.getUser() != null && message.getUser().getId() != 0){
                    VALUES("user_id", "#{user.id}");
                }
                if (message.getMessageContent() != null){
                    VALUES("message_content", "#{messageContent}");
                }
                VALUES("parent_id", "#{parentId}");
                if (message.getIssueDate() != null){
                    VALUES("issueDate", "#{issueDate}");
                }
                if (message.getToUser() != null & message.getToUser().getId() != 0){
                    VALUES("to_user_id", "#{toUser.id}");
                }
            }
        }.toString();
    }

}
