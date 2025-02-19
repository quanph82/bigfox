/*
 * Author: HuongNS
 * Copyright @ 2015 by OneSoft.,JSC
 * 
 */
package vn.com.onesoft.bigfox.io.message.core.cs;

import vn.com.onesoft.bigfox.io.core.session.ConnectionManager;
import vn.com.onesoft.bigfox.io.core.session.BFUtils;
import vn.com.onesoft.bigfox.io.message.annotations.Message;
import vn.com.onesoft.bigfox.io.message.annotations.Property;
import vn.com.onesoft.bigfox.io.message.base.BFConfig;
import vn.com.onesoft.bigfox.io.message.base.MessageOut;
import vn.com.onesoft.bigfox.io.message.core.objects.ClientInfo;
import vn.com.onesoft.bigfox.io.message.core.tags.CoreTags;

/**
 *
 * @author HuongNS
 */
@Message(tag = CoreTags.CS_CLIENT_INFO, name = "CS_CLIENT_INFO", isCore = true)
public class CSClientInfo extends MessageOut {

    @Property(name = "clientInfo")
    private ClientInfo clientInfo;

    public CSClientInfo() {
        clientInfo = new ClientInfo();
        clientInfo.device = ClientInfo.DEVICE_DESKTOP;
        clientInfo.zone = "BigFoxServerChatExample";
//        clientInfo.zone = "classes";
        clientInfo.imei = "";

        if (ConnectionManager.getInstance().sessionId.length() == 0) {
            ConnectionManager.getInstance().sessionId = BFUtils.genRandomString(10);
        }
        clientInfo.sessionId = ConnectionManager.getInstance().sessionId;
        clientInfo.version = BFConfig.getInstance().getVersion();
        clientInfo.metadata = "";
    }
}
