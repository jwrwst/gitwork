package com.platform.rp.services.common.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.platform.rp.services.employee.core.dao.ItRecordCashLogDAO;
import com.platform.rp.services.employee.core.dao.entity.ItRecordCashLogEntity;
import com.platform.rp.services.wechat.service.IWxPromotionService;
import com.platform.rp.util.Constant;
import com.platform.rp.util.Properties;
import com.platform.rp.util.StringUtils;

/**
 * 企业付款
 * @author tangjun
 * 
 * 2016年4月15日
 */
public class RewardTransferTask {
	
	private Log log = LogFactory.getLog(RewardTransferTask.class);
	
	@Autowired
	ItRecordCashLogDAO<ItRecordCashLogEntity> cashLogDAO;
	
    @Autowired
    IWxPromotionService wxPromotionService;
    
    @Autowired
    Properties properties;
    
	private RewardTransferTask rewardTransferTask = null;
	
	
	private RewardTransferTask() {

	}

	/**
	 * 
	 * @return
	 */
	public synchronized RewardTransferTask getInstance() {
		if (null == rewardTransferTask) {
			rewardTransferTask = new RewardTransferTask();
		}
		return rewardTransferTask;
	}
	/**
     * 定时任务
     */
	public void getRewardTransferInfo() {
		//避免两台机器同时执行
		if(properties.isExecuteTask.equals("0")){
			return;
		}
		
		try{
			log.info("企业定时付款====开始");
			
			List<ItRecordCashLogEntity> rewardInfo = cashLogDAO.getList();
			for (int i = 0; i < rewardInfo.size(); i++) {
				ItRecordCashLogEntity rewardInfoEntity = rewardInfo.get(i);
	
				BigDecimal big = rewardInfoEntity.getCash();
				big=big.multiply(new BigDecimal(100));
				
				String orderId=rewardInfoEntity.getPaymentNo();
				if(StringUtils.isEmpty(orderId)){
					orderId=UUID.randomUUID().toString().replace("-", "");
				}
				Boolean bool = wxPromotionService.promationPay(orderId,
						rewardInfoEntity.getOpenId(), 
						big.intValue()+"", "众赏企业付款——众赏之下，人人勇夫");
				
				//修改数据库状态
				rewardInfoEntity.setStatus(Constant.PAYING);
				rewardInfoEntity.setUpdateTime(new Date());
				rewardInfoEntity.setPaymentNo(orderId);
				
				//判断微信返回状态
				if(bool){
					cashLogDAO.update(rewardInfoEntity);
				}
			}
		}catch(Exception e){
			log.error("企业定时付款",e);
		}
	}
}
