package com.platform.rp.framework.template.freemarker.cache;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.TemplateLoader;
/**
 * 根据传输流，加载模板
 * 
 *  @author tangjun
 *  create date : 2015年3月11日
 */
public class StreamTemplateLoader implements TemplateLoader {
	private final Map<String, StreamTemplateSource> templates = new HashMap<String, StreamTemplateSource>();

	public StreamTemplateLoader(String name, Reader templateSource) {
		this.putTemplate(name, templateSource);
	}

	public void putTemplate(String name, Reader templateSource) {
		putTemplate(name, templateSource, System.currentTimeMillis());
	}

	/**
	 * Puts a template into the loader. The name can contain slashes to denote
	 * logical directory structure, but must not start with a slash. If the
	 * method is called multiple times for the same name and with different last
	 * modified time, the configuration's template cache will reload the
	 * template according to its own refresh settings (note that if the refresh
	 * is disabled in the template cache, the template will not be reloaded).
	 * Also, since the cache uses lastModified to trigger reloads, calling the
	 * method with different source and identical timestamp won't trigger
	 * reloading.
	 * 
	 * @param name
	 *            the name of the template.
	 * @param templateSource
	 *            the source code of the template.
	 * @param lastModified
	 *            the time of last modification of the template in terms of
	 *            <tt>System.currentTimeMillis()</tt>
	 */
	public void putTemplate(String name, Reader templateSource,
			long lastModified) {
		templates.put(name, new StreamTemplateSource(name, templateSource,
				lastModified));
	}

	public void closeTemplateSource(Object templateSource) {
	}

	public Object findTemplateSource(String name) {
		return templates.get(name);
	}

	public long getLastModified(Object templateSource) {
		return ((StreamTemplateSource) templateSource).lastModified;
	}

	public Reader getReader(Object templateSource, String encoding) {
		return ((StreamTemplateSource) templateSource).source;
	}

	private static class StreamTemplateSource {
		private final String name;
		private final Reader source;
		private final long lastModified;

		StreamTemplateSource(String name, Reader source, long lastModified) {
			if (name == null) {
				throw new IllegalArgumentException("name == null");
			}
			if (source == null) {
				throw new IllegalArgumentException("source == null");
			}
			if (lastModified < -1L) {
				throw new IllegalArgumentException("lastModified < -1L");
			}
			this.name = name;
			this.source = source;
			this.lastModified = lastModified;
		}

		public boolean equals(Object obj) {
			if (obj instanceof StreamTemplateSource) {
				return name.equals(((StreamTemplateSource) obj).name);
			}
			return false;
		}

		public int hashCode() {
			return name.hashCode();
		}
	}

}
