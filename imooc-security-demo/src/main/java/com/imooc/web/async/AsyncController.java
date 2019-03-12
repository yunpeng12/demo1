/**
 * 
 */
package com.imooc.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author Administrator
 *
 */
@RestController
public class AsyncController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	@RequestMapping("/order")
	public DeferredResult<String> order() throws Exception {
		logger.info("主线程开始");
		
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		deferredResultHolder.getMap().put(orderNumber, deferredResult);
		/*Callable<String> result = new Callable<String>() {

			@Override
			public String call() throws Exception {
				logger.info("副线程开始");
				Thread.sleep(1000);
				logger.info("副线程结束");
				return "success";
			}
		};*/
		logger.info("主线程结束");
		return deferredResult;
	}
}
