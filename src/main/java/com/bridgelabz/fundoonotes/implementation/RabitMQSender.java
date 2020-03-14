package com.bridgelabz.fundoonotes.implementation;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.model.UserInfo;


@Service
public class RabitMQSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${javainuse.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${javainuse.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(UserInfo  info) {
		rabbitTemplate.convertAndSend(exchange, routingkey, info);
		System.out.println("Send msg = " + info);
	}
}
