package com.platform.rp.framework.rest.jersey.servlet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Path;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ClassUtils;

import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.WebApplication;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

/**
 * 客户化jersey控制器
 * 
 * 
 */
public class CustomerSpringServlet extends SpringServlet {
	private static final long serialVersionUID = 1L;

	private Set<String> paths = new HashSet<String>();

	/**
	 * @return paths
	 */
	private Set<String> getPaths() {
		return paths;
	}

	/**
	 * @param paths
	 *            要设置的 paths
	 */
	private void addPath(String path) {
		this.paths.add(path);
	}

	@Override
	protected void initiate(ResourceConfig rc, WebApplication wa) {
		super.initiate(rc, wa);

		try {
			Set<Class<?>> classes = rc.getClasses();
			if (CollectionUtils.isEmpty(classes))
				return;

			outer: for (Class<?> classTemp : classes) {
				@SuppressWarnings("unchecked")
				List<Class<?>> interfaces = ClassUtils.getAllInterfaces(classTemp);
				if (CollectionUtils.isEmpty(interfaces))
					continue;

				for (Class<?> interfaceByClass : interfaces) {
					Path annotationByPath = interfaceByClass.getAnnotation(Path.class);
					if (annotationByPath == null)
						continue;

					String path = annotationByPath.value();
					path = path.substring(1) + "/";
					System.out.println(path);
					addPath(path);
					continue outer;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}