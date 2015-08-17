package vn.com.onesoft.bigfox.server.io.core.message.sc;

/*
 * Author: QuanPH
 * Copyright @ 2015 by OneSoft.,JSC
 * 
 */



import vn.com.onesoft.bigfox.server.io.core.annotat.messageions.Message;
import vn.com.onesoft.bigfox.server.io.core.annotat.messageions.Property;
import vn.com.onesoft.bigfox.server.io.core.message.base.MessageOut;
import vn.com.onesoft.bigfox.server.io.core.message.tags.CoreTags;

/**
 *
 * @author QuanPH
 */
@Message(tag = CoreTags.SC_INIT_SESSION, name = "SC_INIT_SESSION", isCore = true)
public class SCInitSession extends MessageOut {

    @Property(name = "sessionStatus")
    public int sessionStatus;

    public static final int START_NEW_SESSION = 0x01;
    public static final int CONTINUE_OLD_SESSION = 0x02;
    
    public SCInitSession(int sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
    
    
    
}