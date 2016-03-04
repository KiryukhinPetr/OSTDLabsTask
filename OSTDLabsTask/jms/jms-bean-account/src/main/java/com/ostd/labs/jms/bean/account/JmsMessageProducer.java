package com.ostd.labs.jms.bean.account;
import java.util.List;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
/**
 * @author pkiryukhin
 */

@Service
public class JmsMessageProducer {
    @Value("${reply.to}")
    private String replyTo;

    private JmsTemplate jmsTemplate;
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    /**
     * send text to default destination
     * @param text
     */
    public void send(final String text) {

        this.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(text);
                //set ReplyTo header of Message, pretty much like the concept of email.
                message.setJMSReplyTo(new ActiveMQQueue(replyTo));
                return message;
            }
        });
    }

    /**
     * Simplify the send by using convertAndSend
     * @param text
     */
    public void sendText(final String text) {
        this.jmsTemplate.convertAndSend(text);
    }

    /**
     * Send text message to a specified destination
     * @param text
     */
    public void send(final Destination dest, final String text) {

        this.jmsTemplate.send(dest,new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(text);
                return message;
            }
        });
    }
}
