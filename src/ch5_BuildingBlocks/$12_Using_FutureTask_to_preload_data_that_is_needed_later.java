package ch5_BuildingBlocks;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 4:24 PM
 */
public class $12_Using_FutureTask_to_preload_data_that_is_needed_later {

    public static class Preloader {
        ProductInfo loadProductInfo() throws DataLoadException {return null;}

        private final FutureTask<ProductInfo> future = new FutureTask<>(this::loadProductInfo);
        private final Thread thread = new Thread(future);

        public void start() { thread.start(); }

        public ProductInfo get() throws DataLoadException, InterruptedException {
            try {
                return future.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause instanceof DataLoadException)
                    throw (DataLoadException) cause;
                else
                    throw new RuntimeException(cause);
            }
        }

        interface ProductInfo {
        }
    }

    static class DataLoadException extends Exception { }
}
