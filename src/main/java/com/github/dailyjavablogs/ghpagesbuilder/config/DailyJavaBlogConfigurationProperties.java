package com.github.dailyjavablogs.ghpagesbuilder.config;

import com.github.dailyjavablogs.ghpagesbuilder.data.model.Blog;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Component
@ConfigurationProperties(prefix = "djb")
public class DailyJavaBlogConfigurationProperties {
	private Collection<Blog> sources = new ArrayList<>();
	private FrontMatter frontMatter = new FrontMatter();
	/**
	 * The date to start including blog posts from.
	 */
	private LocalDate notBefore = LocalDate.of(2026, 1, 1);

	public Collection<Blog> getSources() {
		return sources;
	}

	public void setSources(Collection<Blog> sources) {
		this.sources = sources;
	}

	public FrontMatter getFrontMatter() {
		return frontMatter;
	}

	public void setFrontMatter(FrontMatter frontMatter) {
		this.frontMatter = frontMatter;
	}

	public LocalDate getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(final LocalDate notBefore) {
		this.notBefore = notBefore;
	}

	public static class FrontMatter {
		private String rootDir;

		public String getRootDir() {
			return rootDir;
		}

		public void setRootDir(String rootDir) {
			this.rootDir = rootDir;
		}

		public String getPostsDir() {
			return Path.of(rootDir, "posts").toAbsolutePath().toString();
		}
	}
}
