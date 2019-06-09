package se.pensionsmyndigheten.icc.test.orderprocessor.bean;

import java.util.UUID;

public class FileNameBean {

	public String getUniqueFileName() {
		return UUID.randomUUID().toString();
	}
}
