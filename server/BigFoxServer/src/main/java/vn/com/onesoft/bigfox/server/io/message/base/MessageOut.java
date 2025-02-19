/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.onesoft.bigfox.server.io.message.base;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import vn.com.onesoft.bigfox.server.io.message.annotations.Message;

/**
 *
 * @author Quan
 */
public abstract class MessageOut extends BaseMessage {

    private ByteArrayOutputStream byteArrayOutput;
    private DataOutputStream out;

    public MessageOut() {
        byteArrayOutput = new ByteArrayOutputStream();
        out = new DataOutputStream(byteArrayOutput);
    }

    public byte[] toBytes() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            Message m = getClass().getAnnotation(Message.class);
            if (m != null) {
                tag = m.tag();
            }
            out.writeInt(0);
            out.writeInt(tag);
            out.writeInt(getMSequence());
            out.writeInt(getSSequence());
            out.writeInt(getStatus());
            out.writeInt(getCheckSum());
            BigFoxUtils.write(this, out);
            byte[] data = baos.toByteArray();

            length = data.length; // 4 byte length, 4 byte tag
            data[0] = (byte) ((length >> 24) & 0x00ff);
            data[1] = (byte) ((length >> 16) & 0x00ff);
            data[2] = (byte) ((length >> 8) & 0x00ff);
            data[3] = (byte) ((length) & 0x00ff);

            return data;
        } catch (Exception ex) {
BFLogger.getInstance().error(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public MessageOut clone() {
        return this;
    }

    public MessageOut setCoreErrorStatus(int coreErrorStatus){
        this.setCoreErrorStatus1(coreErrorStatus);
        return this;
    }
    
}
