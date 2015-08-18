/*
 * Author: HuongNS
 * Copyright @ 2015 by OneSoft.,JSC
 * 
 */
package vn.com.onesoft.bigfox.server.io.message.core.cs;

import io.netty.channel.Channel;
import vn.com.onesoft.bigfox.server.io.message.core.tags.CoreTags;
import vn.com.onesoft.bigfox.server.io.core.objects.message.ClientInfo;
import vn.com.onesoft.bigfox.server.io.messaannotationsons.Property;
import vn.com.onesoft.bigfox.server.io.message.annotations.Message;
import vn.com.onesoft.bigfox.server.io.message.base.MessageIn;


/**
 *
 * @author HuongNS
 */
@Message(tag = CoreTags.CS_CLIENT_INFO, name = "CS_CLIENT_INFO", isCore = true)
public class CSClientInfo extends MessageIn {
    
    @Property(name = "clientInfo")
    private ClientInfo clientInfo;
    
    @Override
    public void execute(Channel channel) {

        int a = 0;
//        Main.logger.info(this.getClass().getName());
//        Player player = new Player(channel, clientInfo);
//        LiveTubeContext.mapChannelToPlayer.put(channel, player);
    }

    /**
     * @return the clientInfo
     */
    public ClientInfo getClientInfo() {
        return clientInfo;

    }
}