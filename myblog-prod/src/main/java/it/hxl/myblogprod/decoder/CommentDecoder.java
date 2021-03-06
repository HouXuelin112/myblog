package it.hxl.myblogprod.decoder;

import com.alibaba.fastjson.JSON;
import it.hxl.myblogprod.entity.Comments;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class CommentDecoder implements Decoder.Text<Comments> {
    @Override
    public Comments decode(String s) throws DecodeException {
        return JSON.parseObject(s, Comments.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
