package com.platform.rp.services.employee.external.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.BsEmployeeExtRewardDAO;
import com.platform.rp.services.employee.core.dao.ItRecordCashLogDAO;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtRewardEntity;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.employee.core.dao.entity.ItRecordCashLogEntity;
import com.platform.rp.services.employee.external.service.IExBsEmployeeInfoService;
import com.platform.rp.services.employee.external.service.IExItRecordCashLogService;
import com.platform.rp.services.employee.external.vo.EmployeeIncomeBaseInfoVo;
import com.platform.rp.services.wechat.service.IWxPromotionService;
import com.platform.rp.util.Constant;

@Service
public class ExItRecordCashLogServiceImpl implements IExItRecordCashLogService {
	private Log log = LogFactory.getLog(ExItRecordCashLogServiceImpl.class);

	@Autowired
	private ItRecordCashLogDAO<ItRecordCashLogEntity> dao;
	
	@Autowired
	private BsEmployeeExtRewardDAO<BsEmployeeExtRewardEntity> empExtRewardDao;
	
	@Autowired
	IExBsEmployeeInfoService bsEmployeeInfoService;
	
    @Autowired
    IWxPromotionService wxPromotionService;
    
    

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page getPage(Page page, Map params) throws Exception {
		try {
			params.put("start", page.getStart());
//			params.put("end", page.getEnd());
			params.put("pageSize", page.getPageSize());
			// 查询总数
			int count = dao.count(params);
			List<ItRecordCashLogEntity> itRecordCashLogList = dao.getPage(params);
			
			if((itRecordCashLogList != null) && (itRecordCashLogList.size() > 0))
			{
				for(ItRecordCashLogEntity vo : itRecordCashLogList)
				{
					Date createTime = vo.getCreateTime();
					if(createTime != null)
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						vo.setCreateTimeStr(sdf.format(createTime));
					}
				}
			}
			
			page.setResult(itRecordCashLogList);
			page.setTotalCount(count);
			
			return page;
		} catch (Exception e) {
			log.error("业务处理失败" + e.getMessage());
			throw e;
		}
	}

	public static Map<Long, Integer> mapEmp=new HashMap<Long, Integer>();
	//线程处理
	final Lock lock= new ReentrantLock();
	
	@Override
	public  int add(ItRecordCashLogEntity vo) {
		try{
			
	        try{
	        	lock.lock();
	        	
	        	//判断钱够不够
	        	EmployeeIncomeBaseInfoVo empIncomeBaseInfoVo = empExtRewardDao.getEmployeeIncomeBaseInfo(vo.getEmpId());
				if(empIncomeBaseInfoVo != null)
				{
					if(vo.getCash().compareTo(empIncomeBaseInfoVo.getRemainMoney()) > 0)
					{
						return 1;
					}
				}				
		       
		        //
		        if(mapEmp.containsKey(vo.getEmpId())){
		        	throw new Exception("不能连续提现");
		        }
		        
		        mapEmp.put(vo.getEmpId(), 1);	
	        }finally{	        	
	        	lock.unlock();
	        }
				
			//打款
			BigDecimal big = vo.getCash();
			//big = big.multiply(new BigDecimal(100)).add(big.multiply(new BigDecimal(-0.03)));
			//收取手续费 3%
			big = big.add(big.multiply(new BigDecimal(-0.03))).multiply(new BigDecimal(100)).setScale(0,RoundingMode.HALF_UP);
			String orderId=UUID.randomUUID().toString().replace("-", "");
			log.info("员工编号："+vo.getEmpId()+":生成企业付款订单号："+orderId);
			
			BsEmployeeInfoEntity empEntity=bsEmployeeInfoService.getEmployeeInfoByEmpId(vo.getEmpId());
			Boolean bool = wxPromotionService.promationPay(orderId, empEntity.getWxAccount(), 
					big.intValue()+"", "众赏之下，人人勇夫");
			if(bool){
				vo.setStatus(Constant.PAYING);				
			}else{
				vo.setStatus(Constant.PAYORDER);
			}
			vo.setPaymentNo(orderId);
			//同步更新bs_employee_ext_reward
			sync(vo);
			
			dao.save(vo);
		}catch(Exception e){
			log.error("提现错误",e);
		}finally{
			mapEmp.remove(vo.getEmpId());
		}
		return 0;
	}
	
	
	//提现成功后同步更新bs_employee_ext_reward
	private void sync(ItRecordCashLogEntity vo)
	{
		//从数据库中取出该员工所有打赏记录
		List<BsEmployeeExtRewardEntity> empExtRewardList = empExtRewardDao.getEmployeeReward(0, vo.getEmpId(), 1);
		
		if((empExtRewardList != null) && (empExtRewardList.size() > 0))
		{
			BigDecimal willCash = vo.getCash();   //本次准备提现的金额
			for(BsEmployeeExtRewardEntity entity : empExtRewardList)
			{
				//提现次数增加1
				entity.setCashCount(entity.getCashCount() + 1);
				
				//判断本条记录对应的可提现金额（totalMoney - cashMoney）是否大于提现金额
				BigDecimal remainMoney = entity.getTotalMoney().subtract(entity.getCashMoney());
				if(remainMoney.compareTo(willCash) >= 0)   //大于等于提现金额，从本条记录扣除提现金额后退出循环
				{
					//更新本条记录的已提现金额
					entity.setCashMoney(entity.getCashMoney().add(willCash));
//					entity.setUpdateTime(new Date());
					empExtRewardDao.update(entity);
					
					break;
				}
				else   //小于提现金额，处理完毕后继续从下一条数据中将剩余数额提现
				{
					//更新本条记录的已提现金额
					entity.setCashMoney(entity.getCashMoney().add(remainMoney));
					empExtRewardDao.update(entity);
					
					willCash = willCash.subtract(remainMoney);
				}
			}
		}
	}

	@Override
	public void update(ItRecordCashLogEntity vo) {
		dao.update(vo);
	}
	
	
}
