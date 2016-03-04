package com.ostd.labs.jms.bean.account;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.log4j.Logger;

/**
 * @author pkiryukhin
 */
public class JmsMessageConsumer implements MessageListener {
    private static final Logger LOG = Logger.getLogger(JmsMessageConsumer.class);
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            LOG.info("Consumed message: " + msg.getText());


        } catch (JMSException e) {
            e.printStackTrace();

        }
    }
}