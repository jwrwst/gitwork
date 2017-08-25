package com.platform.rp.services.template.external.common.directivemodel;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.google.gson.Gson;
import com.platform.rp.common.application.ApplicationContextUtil;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.SimpleDate;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 自定义 freemarker 宏
 * 
 * @author tangjun create date : 2015年1月12日
 */
@SuppressWarnings("rawtypes")
public class CoreDirectiveModel implements TemplateDirectiveModel {

    private Logger log = Logger.getLogger(CoreDirectiveModel.class);

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
        Object sn = MDC.get("sn");
        if (sn == null) {
            sn = UUID.randomUUID().toString();
            MDC.put("sn", "唯一标识:" + sn + " ");
        }

        long startTime = System.currentTimeMillis();

        try {
            if (!params.containsKey("service")) {
                throw new TemplateException("自定义宏异常：没有传递业务处理类参数.....", env);
            }
            // 转为通用类型
            Map map = unwrap(params);

            log.info("模版内请求参数打印：" + new Gson().toJson(map, Map.class));

            // 获取处理类
            String service = (String) map.get("service");
            // 获取处理方法
            String method = (String) map.get("method");
            // 执行处理业务
            if (!ApplicationContextUtil.containsBean(service)) {
                throw new TemplateException("自定义宏异常：业务处理类不存在......", env);
            }
            Object objService = ApplicationContextUtil.getBean(service);

            Method cls = objService.getClass().getDeclaredMethod(method,
                    Map.class);
            Object obj = cls.invoke(objService, map);

            if (body != null) {
                log.info("后台返回模版结果打印：" + new Gson().toJson(obj));

                env.setVariable("mc_obj", BeansWrapper.BEANS_WRAPPER.wrap(obj));
                body.render(env.getOut());
            }
        } catch (Exception e) {
            log.error(e);
            body.render(env.getOut());
        } finally {
            long mistiming = System.currentTimeMillis() - startTime;
            log.info("模版请求执行时间差（毫秒数）：" + mistiming);
        }
    }

    /**
     * 模板对象转普通对象
     * 
     * @param params
     * @return
     * @throws TemplateModelException
     */
    @SuppressWarnings("unchecked")
    private Map unwrap(Map params) throws TemplateModelException {
        Map map = new HashMap();
        for (Object ent : params.keySet()) {
            if (params.get(ent) instanceof SimpleScalar) {
                map.put(ent, ((SimpleScalar) params.get(ent)).getAsString());
            }
            if (params.get(ent) instanceof SimpleNumber) {
                map.put(ent, ((SimpleNumber) params.get(ent)).getAsNumber());
            }
            if (params.get(ent) instanceof SimpleDate) {
                map.put(ent, ((SimpleDate) params.get(ent)).getAsDate());
            }
            if (params.get(ent) instanceof SimpleSequence) {
                map.put(ent, ((SimpleSequence) params.get(ent)).toList());
            }
        }
        return map;
    }

}
