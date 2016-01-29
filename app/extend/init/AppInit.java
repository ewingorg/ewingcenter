package extend.init;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jws.Init;
import jws.Jws;
import jws.Logger;
import extend.job.JobClusterManager;
import extend.mq.MQReceiverTool;

/**
 * 程序启动初始化类
 * 
 * @author tanson lam
 * @createDate 2014年9月12日
 * 
 */
public class AppInit implements Init {
	private static boolean activemqOpen = Boolean.valueOf(Jws.configuration
			.getProperty("activemq.open", "true"));
	private static boolean jobOpen = Boolean.valueOf(Jws.configuration
			.getProperty("job.open", "true"));
	private static ExecutorService threadExecutor = Executors
			.newFixedThreadPool(3);

	public void init() {
		threadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					if (activemqOpen)
						MQReceiverTool.getInstance().startListeners();
				} catch (Exception e) {
					Logger.error(e, "fail initialize the mq server.");
				}

			}

		});
		threadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					if (jobOpen)
						JobClusterManager.getInstance().startUp();
				} catch (Exception e) {
					Logger.error(e, "fail initialize the cluster job server.");
				}

			}

		});

	}

}
