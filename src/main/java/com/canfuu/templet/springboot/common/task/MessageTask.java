package com.canfuu.templet.springboot.common.task;

import com.canfuu.templet.springboot.common.util.MessageUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * In YunSuDesignWeb->com.yunsudesign.server.common.task
 * Create in 19:51 2018/4/10
 *
 * @author canfuu
 * @version v1.0
 * @descriotion 描述
 */
@Component
public class MessageTask {
	@Scheduled(cron = "0 0 0 0/24 * ?")
	public void refreshMessageQueue(){
		MessageUtil.flush();
	}
}
