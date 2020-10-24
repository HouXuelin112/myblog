package it.hxl.myblogprod.handler;

import it.hxl.myblogprod.decoder.CommentDecoder;
import it.hxl.myblogprod.encoder.CommentEncoder;
import it.hxl.myblogprod.entity.Message;
import it.hxl.myblogprod.sessionmanager.ChildMessageSessionManager;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@ServerEndpoint(value = "/comments", decoders = { CommentDecoder.class }, encoders = { CommentEncoder.class })
public class ChildMessageHandler {
    private Session session;

    @OnOpen
    public void onOpen(Session ws_session) {
        // 设置session，并记录建立连接时间
        ChildMessageSessionManager.add(ws_session.getId(), ws_session);
        this.session = ws_session;
        // 通知客户端连接成功
        System.out.println("lia");
    }

    @OnMessage
    public void onMessage(String message) {
        // 做点处理返回给客户端
        System.out.println(message);
    }

    @OnError
    public void onError(Throwable t) {
        // 发生异常时，如果连接还是打开状态，则通知客户端错误信息
    }

    @OnClose
    public void onClose(Session session) {
        ChildMessageSessionManager.remove(session.getId());
        System.out.println("close: " + ChildMessageSessionManager.getEntry().size());
        // 关闭连接时，需要做的事情在该函数内完成，例如关闭数据库连接等
    }

    private void sendMessage(Session ws_session, Message message) {
        try {
            // 以同步的方式向客户端发送消息
            ws_session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            System.out.println("Method: sendMessage, Error closeing session " + e.getMessage());
        }
    }
}
