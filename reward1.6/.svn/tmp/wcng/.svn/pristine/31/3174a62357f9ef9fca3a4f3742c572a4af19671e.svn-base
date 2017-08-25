package com.platform.rp.services.wechat.external.action;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.xml.sax.InputSource;

import com.opensymphony.xwork2.ActionSupport;
import com.platform.rp.framework.timer.IteratorTimer;
import com.platform.rp.framework.timer.face.IOperatorTimer;
import com.platform.rp.services.employee.external.service.IExEmployeeHomepageService;
import com.platform.rp.services.store.core.dao.entity.ItRewardInfoEntity;
import com.platform.rp.services.store.external.service.IExItRewardInfoService;
import com.platform.rp.services.wechat.external.common.wxv3Pay.pay.WxPayResult;
import com.platform.rp.util.Constant;

/**
 * 页面控制
 * 
 * @author tangjun
 *
 * 2015年6月12日
 *
 */
@Controller
@ParentPackage("default")
public class PayCallBackAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(PayCallBackAction.class);

    @Autowired
    IExEmployeeHomepageService employeeHomepageService ;
    
    @Autowired
    IExItRewardInfoService exItRewardInfoService;
    
    /**支付宝回调*/
   // @ResponseBody
    @Action("/alipay/*")
    public void alipay() {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response =  ServletActionContext.getResponse();
    	Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		// 获取支付宝POST过来反馈信息
         String xml="";
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			if("notify_data".equals(name)){
				xml = valueStr;
			}
		}

		try {
			Map map = new HashMap();
			Document document = DocumentHelper.parseText(xml);
			Element nodeElement = document.getRootElement();
			List node = nodeElement.elements();
			for (Iterator it = node.iterator(); it.hasNext();) {
			Element elm = (Element) it.next();
			map.put(elm.getName(), elm.getText());
			elm = null;
			}
			// 商户订单号
			String out_trade_no = (String) map.get("out_trade_no");

			// 交易状态
			String trade_status = (String)map.get("trade_status");
			//付款时间
			String pay_date  = (String)map.get("gmt_payment");
			String total_fee  = (String)map.get("total_fee");
			if("TRADE_SUCCESS".equals(trade_status) && "TRADE_SUCCESS" !=trade_status){
				//System.out.println("=-------------------");
				//根据订单号修改支付状态


				//支付成功 给员工转账   转账失败会有后台现在扫描支付
				//openid 转账金额 打赏客户名称  订单号
				try{
				
					//返回true  修改打赏状态为 2
					//String orderId,String openId,String amount,String desc
				}catch(Exception e){
					e.printStackTrace();
					logger.error(e);
				}
				
				response.getWriter().write("success");
				response.getWriter().close();
			}else{
				response.getWriter().write("fail");
				response.getWriter().close();
			}
		} catch (Exception e) {
			logger.error(e);
			try {
				response.getWriter().write("fail");
				response.getWriter().close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				logger.error(e1);
			}			
		}

    }
   /* 
    *  v2
    */
   /* @Action("/wxNotify/*")
    public void wxNotify() {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response =  ServletActionContext.getResponse();
    	Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		// 获取支付宝POST过来反馈信息
         Map map = new HashMap();
         String xml="";
         System.out.println("=======================");
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			 System.out.println("=======================1");
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			if("notify_data".equals(name)){
				xml = valueStr;
			}
			System.out.println("name="+name+":"+valueStr);
			map.put(name, valueStr);
		}

		try {
			// 商户订单号
			String out_trade_no = (String) map.get("out_trade_no");
			//System.out.println("out_trade_no="+out_trade_no);

			// 交易状态
			String trade_status = (String)map.get("trade_state");
			//付款时间
			String pay_date  = (String)map.get("time_end");
			//System.out.println("pay_date="+pay_date);
			String total_fee  = (String)map.get("total_fee");
			System.out.println("0".equals(trade_status));
			System.out.println("0"==trade_status);
			System.out.println(trade_status);
            if("0".equals(trade_status)){
				//根据订单号修改支付状态
				ItRewardInfoEntity entity = new ItRewardInfoEntity();
				entity.setOrderNum(out_trade_no);
				entity.setPaytime(pay_date);
				entity.setStatus(1);
				int  amount = Integer.parseInt(total_fee)  ;
				total_fee = amount/100d+"";
				entity.setRewardAmount(total_fee);
				ItRewardInfoEntity rewardInfoEntity = rewardInfoService.updateRewardInfoEntity(entity);// 修改员工信息
				//发送感谢信及保存 感谢信日志 
				System.out.println("+++++++++++++++++=================++++++++++++");
				saveiGratitudeSend(out_trade_no,rewardInfoEntity);
				System.out.println("+++++++++================================+++++++++++++++++");
				response.getWriter().write("success");
				response.getWriter().close();
				System.out.println("=-----------------------------");
		    }else{
		    	response.getWriter().write("fail");
				response.getWriter().close();
		    }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("=----------------------------+++++++++++++++++++++++-");
			try {
				response.getWriter().write("fail");
				response.getWriter().close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}

    }*/
    /**
     * v3
     */
    @Action("/wxNotify/*")
    public void wxNotify() {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response =  ServletActionContext.getResponse();
    	//把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
    			System.out.print("微信支付回调数据开始");
    			//示例报文
//    			String xml = "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
    			String inputLine;
    			String notityXml = "";
    			String resXml = "";

    			try {
    				while ((inputLine = request.getReader().readLine()) != null) {
    					notityXml += inputLine;
    				}
    				request.getReader().close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}

    			logger.error("接收到的报文：" + notityXml);
    			
    			Map m = parseXmlToList2(notityXml);
    			WxPayResult wpr = new WxPayResult();
    			wpr.setAppid(m.get("appid").toString());
    			//wpr.setBankType(m.get("bank_type").toString());
    			//wpr.setCashFee(m.get("cash_fee").toString());
    			//wpr.setFeeType(m.get("fee_type").toString());
    			wpr.setIsSubscribe(m.get("is_subscribe")+"");
    			wpr.setMchId(m.get("mch_id").toString());
    			//wpr.setNonceStr(m.get("nonce_str").toString());
    			wpr.setOpenid(m.get("openid").toString());
    			wpr.setOutTradeNo(m.get("out_trade_no").toString());
    			wpr.setResultCode(m.get("result_code").toString());
    			wpr.setReturnCode(m.get("return_code").toString());
    			//wpr.setSign(m.get("sign").toString());
    			wpr.setTimeEnd(m.get("time_end").toString());
    			wpr.setTotalFee(m.get("total_fee").toString());
    			//wpr.setTradeType(m.get("trade_type").toString());
    			//wpr.setTransactionId(m.get("transaction_id").toString());
    			
    			if("SUCCESS".equals(wpr.getResultCode())){
    				// 商户订单号
    				String out_trade_no = (String) wpr.getOutTradeNo();
    				//System.out.println("out_trade_no="+out_trade_no);

    				//付款时间
    				String pay_date  = (String)wpr.getTimeEnd();
    				//System.out.println("pay_date="+pay_date);
    				String total_fee  = (String)wpr.getTotalFee();
    				try {
    					//根据订单号修改支付状态
    					ItRewardInfoEntity entity = exItRewardInfoService.getRewardInfoByOrderNum(out_trade_no);
    					synchronized (this) {
							if(Constant.PAYORDER == entity.getStatus()){
								entity.setOrderNum(out_trade_no);
		    					entity.setPaytime(pay_date);
		    					entity.setStatus(Constant.PAYING);
		    					String total_fee1 = total_fee;
		    					int  amount = Integer.parseInt(total_fee)  ;
		    					double total_fee2 = amount/100d;
		    					entity.setRewardAmount(total_fee2);
		    					
		    					employeeHomepageService.addReward(entity);
							}
    					}
    					
    					//发送微信通知
    					try{
    						//saveiGratitudeSend(out_trade_no,rewardInfoEntity);
    					}catch(Exception e){
    						logger.error("发送感谢信失败==",e);
    					}
    					//支付成功 给员工转账   转账失败会有后台现在扫描支付
    					//openid 转账金额 打赏客户名称  订单号 					
    				
    					
        				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
        				+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
    				} catch (Exception e) {
						logger.error("支付返回处理订单",e);
						resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
	    	    				+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
					}
    			}else{
    				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
    				+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
    			}
    			logger.info(resXml);
    			try {
    			BufferedOutputStream out = new BufferedOutputStream(
    					response.getOutputStream());
					out.write(resXml.getBytes());
	    			out.flush();
	    			out.close();
    			} catch (IOException e) {
					logger.error("返回支付回调失败",e);
				}

    }
    
    /**
	 * description: 解析微信通知xml
	 * 
	 * @param xml
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	
	private static Map parseXmlToList2(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			org.jdom.Document doc = (org.jdom.Document) sb.build(source);
			org.jdom.Element root = doc.getRootElement();// 指向根节点
			List<org.jdom.Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (org.jdom.Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	
    
    public void saveiGratitudeSend(String out_trade_no,ItRewardInfoEntity rewardInfoEntity) throws Exception{

	    	IteratorTimer.iterator(new IOperatorTimer() {
					@Override
					public boolean operator() {
						try{
							
				    		return true;				    		
						}catch(Exception e){
							logger.error(e);
							return false;
						}
					}
			});   	

    }

    public static void main(String[] args) {
    	
    	try {
		//int s =	SingletonClient.getClient().sendSMS(new String[] {"18126005521"}, "【海底捞】"+a+"-回复TD退订", "",5);

    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
