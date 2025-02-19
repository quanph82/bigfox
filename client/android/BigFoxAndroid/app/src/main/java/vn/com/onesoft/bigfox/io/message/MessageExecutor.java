/*
 * Author: QuanPH
 * Copyright @ 2015 by OneSoft.,JSC
 * 
 */
package vn.com.onesoft.bigfox.io.message;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.com.onesoft.bigfox.io.core.session.BFLogger;
import vn.com.onesoft.bigfox.io.core.session.BigFox;
import vn.com.onesoft.bigfox.io.core.session.ClassFinder;
import vn.com.onesoft.bigfox.io.core.session.ConnectionManager;
import vn.com.onesoft.bigfox.io.message.annotations.Message;

/**
 * @author QuanPH
 */
public class MessageExecutor {


    private static MessageExecutor _instance = null;

    private Map<Integer, Class<?>> mapTagToCoreMessage = new HashMap<Integer, Class<?>>();
    private Map<Integer, Class<?>> mapTagToUserMessage = new HashMap<Integer, Class<?>>();

    public static MessageExecutor getInstance() {
        if (_instance == null) {
            _instance = new MessageExecutor();
            _instance.loadClasses();
        }
        return _instance;
    }

    public void execute(byte[] data) {
        DataInputStream dis = new DataInputStream(
                new ByteArrayInputStream(data));
        int tag = 0;
        int length = 0;
        try {
            length = dis.readInt();// length
            tag = dis.readInt();
            int mSequence = dis.readInt();
            int sSequence = dis.readInt();
            int status = dis.readInt();
            int checkSum = dis.readInt();
            Class<?> clazz = null;
            if ((status & 0x01) == 0x01)
                clazz = mapTagToCoreMessage.get(tag);
            else
                clazz = mapTagToUserMessage.get(tag);

            BaseMessage message = (BaseMessage) BigFox.fromBytes(clazz, dis);
            message.setmSequence(mSequence);
            message.setsSequence(sSequence);
            message.setStatus(status);
            message.setCheckSum(checkSum);
            dis.close();
            message.setTag(tag);
            message.setLength(length);
            Message m = clazz.getAnnotation(Message.class);
            BFLogger.getInstance().debug(m);
            ConnectionManager.getInstance().onMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadClasses() {
        try {
            List<Class<?>> classes = ClassFinder.find(BaseMessage.class);
            for (Class<?> clazz : classes) {
                Message m = clazz.getAnnotation(Message.class);
                if (m.name().toUpperCase().indexOf("SC") == 0) {
                    if (m.isCore())
                        mapTagToCoreMessage.put(m.tag(), clazz);
                    else
                        mapTagToUserMessage.put(m.tag(), clazz);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
