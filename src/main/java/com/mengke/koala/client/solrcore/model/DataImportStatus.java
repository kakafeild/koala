package com.mengke.koala.client.solrcore.model;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-20
 */
public class DataImportStatus {

	private String status;

	private int requests;

	private int fetched;

	private int skipped;

	private int processed;

	private boolean success;

	private String dataImportStatus;

	private String elapsedTime;

	private String timeTaken;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}

	public int getFetched() {
		return fetched;
	}

	public void setFetched(int fetched) {
		this.fetched = fetched;
	}

	public int getSkipped() {
		return skipped;
	}

	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}

	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getDataImportStatus() {
		return dataImportStatus;
	}

	public void setDataImportStatus(String dataImportStatus) {
		this.dataImportStatus = dataImportStatus;
	}

	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}
}
