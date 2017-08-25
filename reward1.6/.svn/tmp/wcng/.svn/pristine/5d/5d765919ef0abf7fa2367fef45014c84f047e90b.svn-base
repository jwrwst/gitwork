package com.platform.rp.services.store.external.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;
import com.platform.rp.util.response.result.RestfulResult;

@Path("/external/storeInfo")
@Produces(MediaType.APPLICATION_JSON)
public interface IExBsStoreInfoRest {

	@POST
	@Path("save")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult save(BsStoreInfoEntity entity);
	
	@POST
	@Path("saveStore")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult saveStore(BsStoreInfoEntity entity);
	
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult update(BsStoreInfoEntity entity);
	
	@GET
	@Path("getStoreInfo")
	public RestfulResult getStoreInfo(@QueryParam("storeId")long storeId) ;
	
	@GET
	@Path("getStoreExtReward")
	public RestfulResult getStoreExtReward(@QueryParam("storeId")long storeId) ;
	
	@POST
	@Path("updateStoreExtReward")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult updateStoreExtReward(BsStoreInfoEntity entity);
	
	@POST
	@Path("saveManagerStore")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult saveManagerStore(BsStoreInfoEntity entity);

	/**
	 * 门店绑定
	 * @param entity
	 * @return
	 */
	@POST
	@Path("saveDividedStore")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult saveDividedStore(BsStoreInfoEntity entity);
	/**
	 * 商户绑定
	 * @param entity
	 * @return
	 */
	@POST
	@Path("saveMerManage")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult saveMerManage(BsStoreInfoEntity entity);
	@GET
	@Path("deleteStore")
	public RestfulResult deleteStore(@QueryParam("storeId")long storeId,@QueryParam("orgCode")String orgCode) ;

	@POST
	@Path("updateStatus")
	public RestfulResult updateStatus(Map<String,Object> params);
	
	@GET
	@Path("getCreateStoreNum")
	public RestfulResult getCountByOpenIdAndStoreCode(@QueryParam("openId")String openId) ;
	
}
