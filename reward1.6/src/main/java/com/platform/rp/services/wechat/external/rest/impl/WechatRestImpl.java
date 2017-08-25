package com.platform.rp.services.wechat.external.rest.impl;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.framework.mvcface.vo.ResponseResult;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.employee.external.service.IExBsEmployeeInfoService;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItQrcodeInfoEntity;
import com.platform.rp.services.qrcodeinfo.external.service.IExItQrcodeInfoService;
import com.platform.rp.services.store.core.dao.entity.ItRewardInfoEntity;
import com.platform.rp.services.store.external.service.IExItRewardInfoService;
import com.platform.rp.services.wechat.common.StringMagusUtils;
import com.platform.rp.services.wechat.external.common.alipay.config.AlipayConfig;
import com.platform.rp.services.wechat.external.common.alipay.util.AlipaySubmit;
import com.platform.rp.services.wechat.external.common.alipay.util.UtilDate;
import com.platform.rp.services.wechat.external.common.wxPay.CommonUtil;
import com.platform.rp.services.wechat.external.common.wxv3Pay.pay.Wx3Pay;
import com.platform.rp.services.wechat.external.common.wxv3Pay.pay.WxPayDto;
import com.platform.rp.services.wechat.external.rest.IWechatRest;
import com.platform.rp.services.wechat.external.vo.LoginVo;
import com.platform.rp.services.wechat.service.IWeixinTokenService;
import com.platform.rp.util.Constant;
import com.platform.rp.util.DateUtil;
import com.platform.rp.util.FileUtil;
import com.platform.rp.util.HttpsClient;
import com.platform.rp.util.Properties;
import com.platform.rp.util.StringUtils;
import com.platform.rp.util.Tools;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

/**
 * 微信调用
 * 
 * @author tangjun
 * 
 *         2015年6月12日
 * 
 */
@Controller
public class WechatRestImpl extends BaseController implements IWechatRest {

	private static final Logger logger = Logger.getLogger(WechatRestImpl.class);

	@Context
	HttpServletResponse response;

	@Context
	HttpServletRequest request;
	
	@Resource(name="weixinTokenServiceImpl")
	private IWeixinTokenService weixinTokenService;
	
	@Autowired
	IExItRewardInfoService exItRewardInfoService;
	
	@Autowired
	IExBsEmployeeInfoService bsEmployeeInfoService;
	
    @Autowired
    private IExItQrcodeInfoService exItQrcodeInfoService;
    
    @Autowired
	IExBsMerchantsInfoService bsMerchantsInfoService;
	  
    @Resource(name="systemProperties")
    private Properties properties;

    
	@Override
	public String getWxParam(String url) {
		Map<String,String> responseMap = new TreeMap<String, String>();
		String status = "";
		String message ="";
		
		String referer = url;
		try {		

			message ="成功";
			status = "0";
			String noncestr = StringMagusUtils.stringGen(10);
			String timestamp = System.currentTimeMillis()/1000+"";
			String jsTicket = weixinTokenService.getJsTicket(false);
			responseMap.put("noncestr", noncestr);
			responseMap.put("timestamp", timestamp);
			responseMap.put("jsapi_ticket", jsTicket);
			responseMap.put("url", referer);
			//sha1加密
			String signature = StringMagusUtils.shaHex(responseMap);
			responseMap.put("signature", signature);
			responseMap.put("appId", properties.appId);
	

			responseMap.put("status", status);
			responseMap.put("message", message);
			
			
			return StringMagusUtils.objToGsonstr(responseMap);
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	/**
	 * 微信初始化
	 * {empId:1,storeId:1,customerId:1,openId:111,userName:"aaaa"}
	 */
	@Override
	public RestfulResult getWxPayInit(Map<String, String> param) {
		String empId = param.get("empId");
		String storeId= param.get("storeId");
		String customerId = param.get("customerId");
		String openId= param.get("openId");
		String userName = param.get("userName");
		String rewardAmount = param.get("rewardMoney");
		try {
			if("".equals(empId) || null == empId||"".equals(storeId) || null == storeId||"".equals(customerId) || null == customerId){
				throw new Exception("参数输入不正确。。。。");
	        }
			logger.info(new BigDecimal(rewardAmount).multiply(new BigDecimal(100)).intValue()+"");
			WxPayDto tpWxPay = new WxPayDto();
			String orderId = "wx_"+CommonUtil.CreateNoncestr();
			tpWxPay.setOrderId(orderId);
			tpWxPay.setOpenId(openId);
			tpWxPay.setTotalFee(new BigDecimal(rewardAmount).multiply(new BigDecimal(100)).intValue()+"");
			Wx3Pay pay = new Wx3Pay(properties);
			String url = pay.getPackage(tpWxPay,userName);
			Map<String,Object> map =new HashMap<String, Object>();
        	map.put("json", url);
        	map.put("orderId", orderId);
        	logger.info(JSONObject.fromObject(map).toString());
        	//保存打赏记录
        	Date dt=new Date();        	
        	ItRewardInfoEntity entity=new ItRewardInfoEntity();
        	entity.setCreatedtime(dt);
        	entity.setCustomerId(customerId);
        	entity.setEmpId(Long.valueOf(empId));
        	entity.setStoreId(Long.valueOf(storeId));
        	entity.setOrderNum(orderId);
        	entity.setRewardAmount(Double.valueOf(rewardAmount));
        	entity.setStatus(Constant.PAYORDER);
        	entity.setRewardType(1);
        	//处理日结时间
        	try{
	        	String[] startAndEnd=new String[2];
	        	bsMerchantsInfoService.getStartAndEndTime(Long.valueOf(storeId), startAndEnd);
	        	int check=DateUtil.InTime(dt, startAndEnd[0], startAndEnd[0],"day");
	        	if(check==0){        	
	        		entity.setBusdate(Integer.parseInt(DateUtil.format(dt, "yyyyMMdd")));
	        	}else if(check == 1){
	        		dt.setDate(dt.getDate()-1);
	        		entity.setBusdate(Integer.parseInt(DateUtil.format(dt, "yyyyMMdd")));
	        	}
        	}catch(Exception e){
        		logger.error("设置日结日期错误",e);
        	}
        	
        	exItRewardInfoService.add(entity);
        	
        	return RestfulResultProvider.buildSuccessResult(new ResultData(map));
		} catch (Exception e) {
			logger.error("获取微信支付地址异常！", e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
	

    /**
     * 支付宝初始化
     */
	@Override
	public String getAliPayInit(String empId,String storeCode,String customerKey,String jobNumber,String userPhone) {
		String wapPayRequestUrl = null;
		try {
            if("".equals(empId) || null == empId||"".equals(storeCode) || 
            null == storeCode||"".equals(customerKey) || null == customerKey||"".equals(jobNumber) 
            || null == jobNumber||"".equals(userPhone) || null == userPhone){
            	return sendResponseResult(new ResponseResult(CommonCode.ERROR));
            }
			// 支付宝网关地址
			String ALIPAY_GATEWAY_NEW = properties.alipayGateway;
			// 返回格式
			String format = "xml";
			// 必填，不需要修改
			// 返回格式
			String v = "2.0";
			// 必填，不需要修改
			// 请求号
			String req_id = UtilDate.getOrderNum();
			// 必填，须保证每次请求都是唯一
			// req_data详细信息
			// 充值服务器异步通知页面路径
			String notify_url =properties.alipayNotifyUrl;
			// 需http://格式的完整路径，不能加?id=123这类自定义参数
			// 页面跳转同步通知页面路径
			String call_back_url = "";
	/*		if (2 == clientType) {
				call_back_url = getShortAddress(parameterDelegator
						.getProperty("com.funguide.caterpaygateway.alipay.recharge.wapcallbackurl"));
			} else {*/
				//call_back_url = properties.shortUrl+"/web/qrcode?text="+properties.shortUrl+"/"+qrCode;
			   String url ="data="+jobNumber+"&userPhone="+userPhone;
				try {
					String   mytext   =   java.net.URLEncoder.encode(url,   "utf-8");
					call_back_url   =   properties.host+"/wapCaterLogin.xhtml?"+mytext;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}     
			   
			   //call_back_url = properties.shortUrl+"/"+qrCode;
				/*if(!CommonHelper.isEmpty(wapRecharge.getBackUrl())){
					call_back_url = wapRecharge.getBackUrl();
				}*/
			//}
			// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
			// 操作中断返回地址
			String merchant_url = call_back_url;
			// 用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数
			// 卖家支付宝帐户//////////////
			//String seller_email = tCaterPayChannel.getMerchantNo();
			String seller_email = properties.alipaySellerEmail;
			
			// 必填
			// log.info("收款账号:" + seller_email);
			// 商户订单号
			String out_trade_no = "aliy_"+CommonUtil.CreateNoncestr();
			// 商户网站订单系统中唯一订单号，必填

			// 订单名称
			String subject = "打赏";
			// 必填
			// 付款金额(测试时为0.01)
			String total_fee = "";
			/*if ("true"
					.equals(parameterDelegator
							.getProperty("com.funguide.caterpaygateway.alipay.testpay"))) {
				total_fee = "0.01";
			} else {*/
				//total_fee = amount / 100d + "";
			 int total = 101;
				total_fee = total/100d +"";
			/*}*/

			// 必填
			// 请求业务参数详细
			String req_dataToken = "<direct_trade_create_req><notify_url>"
					+ notify_url + "</notify_url><call_back_url>"
					+ call_back_url + "</call_back_url><seller_account_name>"
					+ seller_email + "</seller_account_name><out_trade_no>"
					+ out_trade_no + "</out_trade_no><subject>" + subject
					+ "</subject><total_fee>" + total_fee
					+ "</total_fee><merchant_url>" + merchant_url
					+ "</merchant_url></direct_trade_create_req>";
			// 必填
			// ////////////////////////////////////////////////////////////////////////////////
			logger.info("req_dataToken:" + req_dataToken);
			// 把请求参数打包成数组
			Map<String, String> sParaTempToken = new HashMap<String, String>();
			sParaTempToken.put("service", "alipay.wap.trade.create.direct");
			sParaTempToken.put("partner", AlipayConfig.partner);
			sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
			sParaTempToken.put("sec_id", AlipayConfig.sign_type);
			//sParaTempToken.put("sec_id", "0001");
			
			sParaTempToken.put("format", format);
			sParaTempToken.put("v", v);
			sParaTempToken.put("payment_type", "1");
			//sParaTempToken.put("seller_id", AlipayConfig.ali_public_key);
			sParaTempToken.put("req_id", req_id);
			sParaTempToken.put("temp", "temp");
			sParaTempToken.put("req_data", req_dataToken);

			
			// 建立请求
			String sHtmlTextToken = AlipaySubmit.buildRequest(
					ALIPAY_GATEWAY_NEW, "", "", sParaTempToken);

			// URLDECODE返回的信息
			sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,
					AlipayConfig.input_charset);
			// 获取token
			/*logger.info("sHtmlTextToken:" + sHtmlTextToken);
			String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
			logger.info("request_token:" + request_token);*/
			int s = sHtmlTextToken.indexOf("<request_token>")+15;
			int a = sHtmlTextToken.indexOf("</request_token>");
			String request_token = sHtmlTextToken.substring(sHtmlTextToken.indexOf("<request_token>")+15, sHtmlTextToken.indexOf("</request_token>"));

			// /////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////
			// 业务详细
			String req_data = "<auth_and_execute_req><request_token>"
					+ request_token + "</request_token></auth_and_execute_req>";
			// 必填
			// 把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("sec_id", AlipayConfig.sign_type);
			sParaTempToken.put("seller_id", AlipayConfig.key);
			sParaTemp.put("format", format);
			sParaTemp.put("v", v);
			sParaTemp.put("req_data", req_data);
			// 建立请求
			wapPayRequestUrl = AlipaySubmit.getRequestURL(ALIPAY_GATEWAY_NEW,
					sParaTemp);
			logger.info(wapPayRequestUrl);
			if(null !=wapPayRequestUrl){
				//保存打赏记录
	        
			}
			Map<String,Object> map =new HashMap<String, Object>();
			map.put("wapPayRequestUrl", wapPayRequestUrl);
			return sendResponseResult(new ResponseResult(CommonCode.SUCCESS,map));
		} catch (Exception ex) {
			logger.error("获取支付宝支付地址异常！", ex);
			return sendResponseResult(new ResponseResult(CommonCode.ERROR));
		}
	}
	
	
	/**
	 * 客户端请求入口
	 */
	public void goBaseLogin() {		
		try {
			String state = request.getParameter("state");	
			String data =request.getParameter("data");
			if(StringUtils.isEmpty(data)){data="";}
			String userAgent=request.getHeader("User-Agent");
			
			String scope = "snsapi_userinfo";
			if("3".equals(state)){
				ItQrcodeInfoEntity entity = exItQrcodeInfoService.getByQrcode(data, 0);
				if(entity!=null&&entity.getState()==Constant.USER){
					scope = "snsapi_base";
				}
			}
			//如果不是微信平台则直接进入
//			if(userAgent!=null && (userAgent.indexOf("MicroMessenger")<0)){		
			if(false){
				LoginVo vo = new LoginVo();
				vo.setData(data);
				vo.setState(state);
				request.getSession().setAttribute("loginVo", vo);
				super.sendRedirect(properties.host+properties.getMenuInstance().get("url"+state)+"?state="+state+"&data="+data);
			}else{
				String redirect_url = properties.host
						+ "/rs/wechat/oauthuserInfo?data="+data;
				
				String url = properties.wxAuthoriz;
				url += "?appid=" + properties.appId + "&redirect_uri="
						+ URLEncoder.encode(redirect_url, "UTF-8")
						+ "&response_type=code&scope="+scope+"&state=" + state
						+ "#wechat_redirect";
				logger.info("第三方主动发起授权请求url:" + url);
				request.getSession().setAttribute("reqUrl", properties.host+ "/rs/wechat/goBaseLogin?state="+state+"&data="+data);
				
				response.sendRedirect(url);
			}
		} catch (Exception e) {
			logger.error("GO oauthuserInfo", e);
		}
	}
	
	private void reloadLogin(){
		try {
			Object count=request.getSession().getAttribute("reqCount");
			if(null==count){
				request.getSession().setAttribute("reqCount", 1);
			}else{
				request.getSession().setAttribute("reqCount", Integer.parseInt(count+"")+1);
			}
			
			if(null!=count && Integer.parseInt(count+"")>=5){
				response.sendRedirect(properties.host+"/views/error/openid-error.xhtml");
				return;
			}
			
			String url=(String) request.getSession().getAttribute("reqUrl");
			response.sendRedirect(url);
		} catch (Exception e) {
			logger.error("再次定向：",e);
		}
		
	}
	
	public void oAuthUserInfo() {
		// 获得微信转发过来的code
		logger.info("QueryUrl:" + request.getQueryString());
		String code = StringUtils.isBlank(request.getParameter("code"))?request.getParameter("CODE"):request.getParameter("code");
		String state = request.getParameter("state");	
		String data =request.getParameter("data");
		LoginVo vo = new LoginVo();
		try {				
			//如果不能从微信获取到CODE
			if (StringUtils.isEmpty(code)) {
				reloadLogin();
				return ;
				//throw new Exception();
			}
			
			// 用code换取openid			
			 String url =properties.wxOauthurl +"?appid=" + properties.appId + "&secret="
					+ properties.secret + "&code=" + code
					+ "&grant_type=authorization_code";			
			String jsonStr = HttpsClient.sendHttpsPost(url, "");						
			Map<String, String> gmap = StringMagusUtils.gsonstrToMap(jsonStr);
			String openId = gmap.get("openid");
			String accessToken = gmap.get("access_token");				
			logger.info("code换取的：" + jsonStr);
			if(StringUtils.isEmpty(openId)){
				reloadLogin();
				return;
			}
			request.getSession().removeAttribute("reqCount");
			
			try{
				String scope=gmap.get("scope");
				if("snsapi_userinfo".equals(scope)){
					//获取用户信息
					String userinfoUrl = properties.wxUserinfo+"?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
					String userinfoStr = HttpsClient.sendHttpsPost(userinfoUrl, "");
					logger.info("用户信息："+userinfoStr);
					JSONObject userinfoMap=JSONObject.fromObject(userinfoStr);
					
					vo.setCity(userinfoMap.optString("city"));
					vo.setProvince(userinfoMap.optString("province"));
					vo.setCountry(userinfoMap.optString("country"));
					vo.setNickname(userinfoMap.optString("nickname"));
					vo.setSex(userinfoMap.optString("sex"));
					vo.setHeadimgurl(userinfoMap.optString("headimgurl"));
					vo.setUnionid(userinfoMap.optString("unionid"));
				}
			}catch(Exception e){logger.error("获取用户信息失败",e);}
			//设置对象
			vo.setOpenId(openId);
			vo.setAccessToken(accessToken);				
			vo.setData(data);
			vo.setState(state);

		} catch (Exception e) {
			logger.error("oAuthUserInfo", e);
		}
		
		try {
//			String sign = DESUtil.encode(JSONObject.fromObject(vo).toString(), KeyConstant.USERKEY);
//			sign = URLEncoder.encode(sign,"utf-8");
			request.getSession().setAttribute("loginVo", vo);
			super.sendRedirect(properties.host+properties.getMenuInstance().get("url"+state)+"?state="+state+"&data="+data);			
		} catch (Exception e) {
			logger.error("转向地址出错：",e);
		}
	}
	

	public String uploadImg(String serverId,long empId){
		try {
			String accessToken=weixinTokenService.getWeixinToken(false);
			String wxImgUrl="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+accessToken+"&media_id="+serverId;
			logger.info(wxImgUrl);
			URL url = new URL(wxImgUrl);
			URLConnection con = url.openConnection();;
			InputStream inputStream=con.getInputStream();	
			
			//保存文件
			FileUtil fileUtil =new FileUtil();
			String fileapp="/uploadfile/"+Tools.getCurrentDateToString("yyyy/MM/dd", new Date())+"/";
			
			/*int lastIndex=request.getRealPath("/").indexOf("webapps");
			String webAppStr=request.getRealPath("/");
			if(lastIndex>0){
				webAppStr=request.getRealPath("/").substring(0,lastIndex+7);
			}*/
			String webAppStr = properties.uploadfile;
			
			String fileSavePath = webAppStr+fileapp;			
			String fileName="img_"+System.currentTimeMillis()+Tools.stringGen(2);
			logger.info(fileSavePath+fileName+".jpg");
			fileUtil.saveFile(inputStream, fileSavePath, fileName+".jpg");
						
			//保存员工信息
			String imgUrl=properties.host+fileapp+fileName+".jpg";			
			logger.info(imgUrl);
			if(StringUtils.isNotEmpty(empId+"")){
				BsEmployeeInfoEntity entity=new BsEmployeeInfoEntity();
				entity.setEmpId(empId);
				entity.setHeadPic(imgUrl);
				bsEmployeeInfoService.update(entity);
			}
			return "{\"imgUrl\":\""+imgUrl+"\"}";
		} catch (Exception e) {
			logger.error("获取微信服务器图片失败",e);
			return "";
		}
	}
	
	
	
}
