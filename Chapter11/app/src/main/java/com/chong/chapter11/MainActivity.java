package com.chong.chapter11;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scheduleThreads();

    }

    private void scheduleThreads() {
        runAsyncTask();
        runIntentService();
        runThreadPool();
    }

    private void runThreadPool() {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
            }
        };

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        fixedThreadPool.execute(command);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(command);

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        // 2000ms后执行command
        scheduledThreadPool.schedule(command, 2000, TimeUnit.MILLISECONDS);
        // 延迟10ms后，每隔1000ms执行一次command
        scheduledThreadPool.scheduleAtFixedRate(command, 10, 1000, TimeUnit.MILLISECONDS);

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(command);
    }

    private void runIntentService() {
        Intent service = new Intent(this, LocalIntentService.class);
        service.putExtra("task_action", "com.chong.action.TASK1");
        startService(service);
        service.putExtra("task_action", "com.chong.action.TASK2");
        startService(service);
        service.putExtra("task_action", "com.chong.action.TASK3");
        startService(service);
    }

    private void runAsyncTask() {
        try {
            new DownloadFilesTask().execute(new URL("http://www.baidu.com"),
                    new URL("http://www.renyugang.cn"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalSize = 0;
            for (int i = 0; i < count; i++) {
                // totalSize += Downloader.downloadFile(urls[i]);
                publishProgress((int) ((i / (float) count) * 100));
                // Escape early if cancel() is called
                if (isCancelled())
                    break;
            }
            return totalSize;
        }

        protected void onProgressUpdate(Integer... progress) {
            // setProgressPercent(progress[0]);
            Log.d(TAG, "progress == " + progress[0]);
        }

        protected void onPostExecute(Long result) {
            // showDialog("Downloaded " + result + " bytes");
            Log.d(TAG, "Downloaded " + result + " bytes");
            Toast.makeText(MainActivity.this, "Downloaded " + result + " bytes", Toast.LENGTH_SHORT).show();
        }
    }

}
