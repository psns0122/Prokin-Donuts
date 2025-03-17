package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * TODO:데이터베이스 설정
 *
 * 다음과 같이 config/dbinfo.properties 파일을 생성하고, 본인의 데이터베이스 설정에 맞게 YOURDATA 부분을 변경하세요.
 *
 * driver=com.mysql.cj.jdbc.Driver
 * url=jdbc:mysql://localhost:3306/YOURDATA?serverTimezone=YOURDATA
 * user=YOURDATA
 * password=YOURDATA
 *
 */

/**
 * {@code DBUtil} 클래스는 데이터베이스 연결을 관리하기 위한 유틸리티 클래스입니다.
 * 데이터베이스 연결, 드라이버 로딩 등의 기능을 제공합니다.
 */
public class DBUtil {
    /** 데이터베이스 정보가 포함된 ResourceBundle 객체 */
    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("config/dbinfo");

        try {
            Class.forName(bundle.getString("driver"));
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            e.printStackTrace();
        }
    }

    /**
     * 데이터베이스 연결 객체를 반환하는 메서드입니다.
     *
     * @return 데이터베이스 연결 객체(Connection), 연결 실패 시 {@code null} 반환
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    bundle.getString("url"),
                    bundle.getString("user"),
                    bundle.getString("password"));
        } catch (SQLException e) {
            System.out.println("연결 실패");
            return null;
        }
    }
}