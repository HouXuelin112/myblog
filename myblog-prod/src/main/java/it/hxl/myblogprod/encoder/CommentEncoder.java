package it.hxl.myblogprod.encoder;

import com.alibaba.fastjson.JSON;
import it.hxl.myblogprod.entity.Comments;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class CommentEncoder implements Encoder.Text<Comments> {
    @Override
    public String encode(Comments object) throws EncodeException {
        return JSON.toJSONString(object);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
