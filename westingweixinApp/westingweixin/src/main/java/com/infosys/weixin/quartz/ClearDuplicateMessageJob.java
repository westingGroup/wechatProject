package com.infosys.weixin.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.infosys.weixin.kit.DuplicateMessageKit;

public class ClearDuplicateMessageJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		DuplicateMessageKit.clear();
	}

}
