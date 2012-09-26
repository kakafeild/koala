package com.mengke.koala.shared.solrcore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-18
 */
public class SolrCoreBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String coreName;

	private String schema;

	private String config;

	private boolean isDefaultCore;

	private String instanceDir;

	private String dataDir;

	private Date startTime;

	private long uptime;

	private long sizeInBytes;

	private String indexSize;

	public SolrCoreBaseInfo() {
		// Empty method body
	}

	public SolrCoreBaseInfo(String coreName) {
		this.coreName = coreName;
	}

	public String getCoreName() {
		return coreName;
	}

	public void setCoreName(String coreName) {
		this.coreName = coreName;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public boolean isDefaultCore() {
		return isDefaultCore;
	}

	public void setDefaultCore(boolean defaultCore) {
		isDefaultCore = defaultCore;
	}

	public String getInstanceDir() {
		return instanceDir;
	}

	public void setInstanceDir(String instanceDir) {
		this.instanceDir = instanceDir;
	}

	public String getDataDir() {
		return dataDir;
	}

	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getSizeInBytes() {
		return sizeInBytes;
	}

	public void setSizeInBytes(long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

	public String getIndexSize() {
		return indexSize;
	}

	public void setIndexSize(String indexSize) {
		this.indexSize = indexSize;
	}
}
