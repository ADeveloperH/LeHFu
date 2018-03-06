package com.jiefutong.lehfu.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class ThreadManager {

	public static ThreadPool getThreadPool(){
		return SingleInstanceHolder.INSTANCE;
	}

	private static class SingleInstanceHolder {
		private static  final ThreadPool INSTANCE = new ThreadPool(3, Runtime.getRuntime().availableProcessors() * 2 + 1, 0L);
	}
	
	public static class ThreadPool{
		private ThreadPoolExecutor threadPoolExecutor;
		private int corePoolSize;
		private int maximumPoolSize;
		private long keepAliveTime;
		public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
			super();
			this.corePoolSize = corePoolSize;
			this.maximumPoolSize = maximumPoolSize;
			this.keepAliveTime = keepAliveTime;
		}

		//执行任务的方法
		public void execute(Runnable runnable){
			if(runnable == null){
				return;
			}
			
			if(threadPoolExecutor == null || threadPoolExecutor.isShutdown()){
				threadPoolExecutor= new ThreadPoolExecutor(
						corePoolSize, 
						maximumPoolSize, 
						keepAliveTime, 
						TimeUnit.MILLISECONDS, 
						new LinkedBlockingQueue<Runnable>(), 
						Executors.defaultThreadFactory(),
						new AbortPolicy());
			}
			threadPoolExecutor.execute(runnable);
		}
	}
}
