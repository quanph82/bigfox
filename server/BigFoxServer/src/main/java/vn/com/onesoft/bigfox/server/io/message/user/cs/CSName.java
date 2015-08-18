/*
 * Author: QuanPH
 * Copyright @ 2015 by OneSoft.,JSC
 * 
 */

package vn.com.onesoft.bigfox.server.io.message.user.cs;

import com.google.common.collect.MapMaker;
import io.netty.channel.Channel;
import java.util.Map;
import vn.com.onesoft.bigfox.server.io.core.session.BFSessionManager;
import vn.com.onesoft.bigfox.server.io.core.session.IBFSession;
import vn.com.onesoft.bigfox.server.io.messaannotationsons.Property;
import vn.com.onesoft.bigfox.server.io.message.annotations.Message;
import vn.com.onesoft.bigfox.server.io.message.base.MessageIn;
import vn.com.onesoft.bigfox.server.io.message.user.tags.Tags;

/**
 *
 * @author QuanPH
 */
@Message(tag = Tags.CS_NAME, name = "CS_NAME")
public class CSName extends MessageIn {

    @Property(name = "msg")
    private String msg;

    public static Map<IBFSession, String> mapSessionToName = new MapMaker().makeMap();

    @Override
    public void execute(Channel channel) {
        IBFSession session = BFSessionManager.getInstance().getSessionByChannel(channel);
        mapSessionToName.put(session, msg);
    }



}