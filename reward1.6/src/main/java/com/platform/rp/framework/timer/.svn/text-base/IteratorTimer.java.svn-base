package com.platform.rp.framework.timer;

import org.apache.log4j.Logger;

import com.platform.rp.framework.rest.jersey.client.impl.FastRestClient;
import com.platform.rp.framework.threadPool.ThreadPoolProvider;
import com.platform.rp.framework.timer.face.IOperatorTimer;


/**
 * 迭代定时器 
 * 
 */
public class IteratorTimer {  
	
	private static final Logger logger = Logger.getLogger(FastRestClient.class);
	
	public static void iterator(final IOperatorTimer operatorTimer){
		try {
			ThreadPoolProvider.getInstance().execute(new Runnable() {
				int size = 20;
				@Override
				public void run() {	
					int i = 0;
					while(i<size){
						boolean bool = operatorTimer.operator();
						if(bool){
							i = 20 ; 
							break;
						}
						//两分钟执行一次
						try {
							Thread.sleep(2*i*60*1000);
							i++;
						} catch (InterruptedException e) {
							logger.error("迭代定时休眠失败：",e);
						}
					}
					
				}
			});
						
		} catch (Exception e) {
			logger.error("迭代定时线程池处理失败：",e);
		}
	}

}
