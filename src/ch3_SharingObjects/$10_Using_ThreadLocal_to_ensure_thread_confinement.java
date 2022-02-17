package ch3_SharingObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 10:44 PM
 */
public class $10_Using_ThreadLocal_to_ensure_thread_confinement {

    public static class ConnectionDispenser {
        static String DB_URL = "jdbc:mysql://localhost/mydatabase";

        private final ThreadLocal<Connection> connectionHolder = ThreadLocal.withInitial(() -> {
            try {
                return DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                throw new RuntimeException("Unable to acquire Connection, e");
            }
        });

        public Connection getConnection() {
            return connectionHolder.get();      // 当某个线程第一次调用get时，将触发withInitial来初始化ThreadLocal
        }
    }
}
