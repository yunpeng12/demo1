package com.imooc.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		new Thread(() -> {
			while(true) {
				if(!StringUtils.isEmpty(mockQueue.getCompleteOrder())) {
					String completeOrder = mockQueue.getCompleteOrder();
					logger.info("返回订单处理结果:"+completeOrder);
					deferredResultHolder.getMap().get(completeOrder).setResult("place order success");
					mockQueue.setCompleteOrder(null);
				}else {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} ).start();
	}
	
}
