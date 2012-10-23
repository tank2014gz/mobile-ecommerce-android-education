package com.google.mcommerce.sample.android.chapter06.handler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.mcommerce.sample.android.chapter06.util.ThreadUtils;

public class WorkerThreadRunnable implements Runnable {
	Handler mainThreadHandler = null;
	int count = 0;

	public WorkerThreadRunnable(Handler h) {
		mainThreadHandler = h;
	}

	public static String tag = "TestRunnable";

	public void run() {
		Log.d(tag, "start execution");
		informStart();
		for (int i = 1; i <= 10; i++) {
			ThreadUtils.sleepForInSecs(1);
			informMiddle(i);
		}
		informFinish();
	}

	public void informMiddle(int count) {
		Message m = this.mainThreadHandler.obtainMessage();
		m.setData(ThreadUtils.getStringAsABundle("done:" + count));
		this.mainThreadHandler.sendMessage(m);
	}

	public void informStart() {
		Message m = this.mainThreadHandler.obtainMessage();
		m.setData(ThreadUtils.getStringAsABundle("starting run"));
		this.mainThreadHandler.sendMessage(m);
	}

	public void informFinish() {
		Message m = this.mainThreadHandler.obtainMessage();
		m.setData(ThreadUtils.getStringAsABundle("Finishing run"));
		this.mainThreadHandler.sendMessage(m);
	}
}
