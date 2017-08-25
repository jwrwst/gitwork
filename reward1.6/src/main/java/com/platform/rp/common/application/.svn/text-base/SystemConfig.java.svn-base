/**
 * 
 */
package com.platform.rp.common.application;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * <pre>
 * Created Date： 2015年7月8日 下午2:42:48
 * 
 * Updator：
 * 
 * Updated Date：
 * 
 * Decription：
 * 		管理系统各类参数信息
 * Version： 1.0.1
 * </pre>
 */
public class SystemConfig {

	private static String configFilePath = null;
	private static ConcurrentMap<String, String> SELF_SERVING_MAP = new ConcurrentHashMap<String, String>();

	private SystemConfig() {
	}

	public static String getConfig(String key) {
		if (SELF_SERVING_MAP.isEmpty())
			reload();

		return SELF_SERVING_MAP.get(key);
	}

	@SuppressWarnings("unchecked")
	public static void reload() {
		SAXBuilder builder = new SAXBuilder();
		Document doc;
		try {
			doc = builder.build(new File(configFilePath));

			Element root = doc.getRootElement();

			List<Element> elemts = root.getChildren();
			List<Element> items = null;
			for (Element elem : elemts) {
				items = elem.getChildren("item");
				if (!items.isEmpty()) {
					for (Element el : items) {
						SELF_SERVING_MAP.put(elem.getName() + "." + el.getAttributeValue("key"),
								el.getAttributeValue("value"));
					}
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initConfigFilePath(String path) {
		System.out.println("[SystemConfig.initConfigFilePath]加载文件路劲：" + path);
		configFilePath = path;
	}
}
