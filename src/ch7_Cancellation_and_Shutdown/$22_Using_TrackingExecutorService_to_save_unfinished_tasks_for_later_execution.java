package ch7_Cancellation_and_Shutdown;

import net.jcip.annotations.GuardedBy;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 8:38 PM
 */
public class $22_Using_TrackingExecutorService_to_save_unfinished_tasks_for_later_execution {

    public static abstract class WebCrawler {
        private volatile $21_ExecutorService_that_keeps_track_of_cancelled_tasks_after_shutdown.TrackingExecutor exec;
        @GuardedBy("this")
        private final Set<URL> urlsToCrawl = new HashSet<>();

        private final ConcurrentMap<URL, Boolean> seen = new ConcurrentHashMap<>();
        private static final long TIMEOUT = 500;
        private static final TimeUnit UNIT = MILLISECONDS;

        public WebCrawler(URL startUrl) {
            urlsToCrawl.add(startUrl);
        }

        public synchronized void start() {
            exec = new $21_ExecutorService_that_keeps_track_of_cancelled_tasks_after_shutdown.TrackingExecutor(Executors.newCachedThreadPool());
            for (URL url : urlsToCrawl) submitCrawlTask(url);
            urlsToCrawl.clear();
        }

        public synchronized void stop() throws InterruptedException {
            try {
                saveUncrawled(exec.shutdownNow());
                if (exec.awaitTermination(TIMEOUT, UNIT))
                    saveUncrawled(exec.getCancelledTasks());
            } finally {
                exec = null;
            }
        }

        protected abstract List<URL> processPage(URL url);

        private void saveUncrawled(List<Runnable> uncrawled) {
            for (Runnable task : uncrawled)
                urlsToCrawl.add(((CrawlTask) task).getPage());
        }

        private void submitCrawlTask(URL u) {
            exec.execute(new CrawlTask(u));
        }

        private class CrawlTask implements Runnable {
            private final URL url;

            CrawlTask(URL url) {
                this.url = url;
            }

            private int count = 1;

            boolean alreadyCrawled() {
                return seen.putIfAbsent(url, true) != null;
            }

            void markUncrawled() {
                seen.remove(url);
                System.out.printf("marking %s uncrawled%n", url);
            }

            public void run() {
                for (URL link : processPage(url)) {
                    if (Thread.currentThread().isInterrupted())
                        return;
                    submitCrawlTask(link);
                }
            }

            public URL getPage() {
                return url;
            }
        }
    }
}
