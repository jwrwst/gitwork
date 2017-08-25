package com.platform.rp.framework.rest.jersey.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentInjector;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProviderContext;

/**
 * 
 * jersey服务器端，自定义json与pojo互转提供者
 * 
 */
@SuppressWarnings("unchecked")
public class CustomerJacksonProviderProxy implements MessageBodyReader<Object>, MessageBodyWriter<Object> {
	private static final Logger logger = Logger.getLogger(CustomerJacksonProviderProxy.class);
	private CustomerJacksonJsonProvider pojoProvider = new CustomerJacksonJsonProvider();

	private String encoding = "UTF-8";

	@Provider
	@Consumes({ MediaType.APPLICATION_JSON, "text/json" })
	@Produces({ MediaType.APPLICATION_JSON, "text/json" })
	public class CustomerJacksonJsonProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {

		String threadInfo = " ， 当前线程ID:" + Thread.currentThread().getId();

		/**
		 * json转pojo
		 * 
		 */
		@Override
		public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
				InputStream entityStream) throws IOException {
			try {
				if (type == null)
					return null;

				if (entityStream == null) {
					logger.warn("服务器端打印" + threadInfo + "，输入流为null！");
					try {
						return type.newInstance();
					} catch (Exception e1) {
						logger.warn("服务器端打印" + threadInfo + "，入口参数构造未成功！", e1);
						return null;
					}
				}

				List<String> jsonStrList = IOUtils.readLines(entityStream, getEncoding());
				if (CollectionUtils.isEmpty(jsonStrList)) {
					logger.warn("服务器端打印" + threadInfo + "，请求json串为空串！");
					try {
						return type.newInstance();
					} catch (Exception e1) {
						logger.warn("服务器端打印" + threadInfo + "，入口参数构造未成功！", e1);
						return null;
					}
				}

				String jsonStr = "";
				for (String jsonStrTemp : jsonStrList)
					jsonStr += jsonStrTemp;
				logger.info("服务器端打印" + threadInfo + "，请求json串 ：" + jsonStr);

				if (StringUtils.isBlank(jsonStr) || "\"{}\"".equals(jsonStr)) {
					logger.warn("服务器端打印" + threadInfo + "，请求json串内容为空串或请求对象内容为空！ ");
					try {
						return type.newInstance();
					} catch (Exception e1) {
						logger.warn("服务器端打印" + threadInfo + "，入口参数构造未成功！", e1);
						return null;
					}
				}

				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(jsonStr, type);
			} catch (Exception e) {
				logger.error("服务器端打印" + threadInfo + "，json串格式不合法！", e);
				throw new IOException(e.fillInStackTrace());
			}
		}

		/**
		 * pojo转json
		 * 
		 */
		@Override
		public void writeTo(Object value, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
				MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException {
			if (entityStream == null || value == null)
				return;

			OutputStreamWriter outputStreamWriter = null;
			try {
				ObjectMapper mapper = new ObjectMapper();
				String jsonStr = new String(mapper.writeValueAsBytes(value), getEncoding());
				logger.info("服务器端打印" + threadInfo + "，返回json串 ：" + jsonStr);

				outputStreamWriter = new OutputStreamWriter(entityStream, getEncoding());
				mapper.writeValue(outputStreamWriter, jsonStr);
			} catch (Exception e) {
				logger.error("服务器端打印" + threadInfo + "，构造json串出错！", e);
				throw new IOException(e.fillInStackTrace());
			} finally {
				// IOUtils.closeQuietly(outputStreamWriter);
			}
		}

		@Override
		public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
			return true;
		}

		@Override
		public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
			return true;
		}

		@Override
		public long getSize(Object value, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
			return -1;
		}
	}

	private static class ProvidersInjectableProviderContext implements InjectableProviderContext {

		@SuppressWarnings("unused")
		final Providers p;

		@SuppressWarnings("rawtypes")
		final Injectable i;

		@SuppressWarnings("rawtypes")
		private ProvidersInjectableProviderContext(final Providers p) {
			this.p = p;
			this.i = new Injectable() {

				@Override
				public Object getValue() {
					return p;
				}
			};
		}

		@Override
		public boolean isAnnotationRegistered(Class<? extends Annotation> ac, Class<?> cc) {
			return ac == Context.class;
		}

		@Override
		public boolean isInjectableProviderRegistered(Class<? extends Annotation> ac, Class<?> cc, ComponentScope s) {
			return isAnnotationRegistered(ac, cc);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public <A extends Annotation, C> Injectable getInjectable(Class<? extends Annotation> ac, ComponentContext ic, A a, C c, ComponentScope s) {
			return (c == Providers.class) ? i : null;
		}

		@SuppressWarnings("rawtypes")
		@Override
		public <A extends Annotation, C> Injectable getInjectable(Class<? extends Annotation> ac, ComponentContext ic, A a, C c, List<ComponentScope> ls) {
			return (c == Providers.class) ? i : null;
		}

		@Override
		public <A extends Annotation, C> InjectableScopePair getInjectableWithScope(Class<? extends Annotation> ac, ComponentContext ic, A a, C c,
				List<ComponentScope> ls) {
			return (c == Providers.class) ? new InjectableScopePair(i, ls.get(0)) : null;
		}

	}

	@Context
	public void setProviders(Providers p) {
		new ComponentInjector<CustomerJacksonJsonProvider>(new ProvidersInjectableProviderContext(p), CustomerJacksonJsonProvider.class).inject(pojoProvider);
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return pojoProvider.isReadable(type, genericType, annotations, mediaType);
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
			InputStream entityStream) throws IOException, WebApplicationException {
		return pojoProvider.readFrom(type, genericType, annotations, mediaType, httpHeaders, entityStream);
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return pojoProvider.isWriteable(type, genericType, annotations, mediaType);
	}

	@Override
	public long getSize(Object t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return pojoProvider.getSize(t, type, genericType, annotations, mediaType);
	}

	@Override
	public void writeTo(Object t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException, WebApplicationException {
		pojoProvider.writeTo(t, type, genericType, annotations, mediaType, httpHeaders, entityStream);
	}

	/**
	 * @return encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding
	 *            要设置的 encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
