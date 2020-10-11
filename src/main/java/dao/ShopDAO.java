
//　自分が格納されているフォルダ名
package dao;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import beans.Shop;

public class ShopDAO {

    // 属性

    // データベースの接続先アドレスを静的変数として記述
    private final static String DRIVER_URL = "jdbc:mysql://localhost:3306/kokuguru?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";

    // データベース接続ドライバの名前を静的変数として記述
    // Mysql5.系
    // private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    // Mysql8.系
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    // データベースのユーザー名 （デフォルトではroot）
    private final static String USER_NAME = "root";

    // データベースのユーザーのパスワード (デフォルトでは設定なし)
    private final static String PASSWORD = "";

    // データベースとの接続を行う
    // データベースの接続情報を持ったConnectionオブジェクトを返す
    public Connection createConnection() {
        try {
            Class.forName(DRIVER_NAME);
            Connection con = DriverManager.getConnection(DRIVER_URL, USER_NAME, PASSWORD);
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Can't Find JDBC Driver.\n");
        } catch (SQLException e) {
            System.out.println("Connect Error.\n");
        }
        return null;
    }

    // Connectionオブジェクトを使って、データベースとの接続を切断する
    // 引数Connectionオブジェクト
    public void closeConnection(Connection con) {

        try {
            con.close();
        } catch (Exception ex) {
        }
    }

    // 投稿
    public void registShop(Shop shop, Connection connection) {

        try {

            // SQLコマンド
            String sql = "insert into shop_info (shop_name, shop_reason, google_map)values(?, ?, ?)";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, shop.getShopName());
            stmt.setString(2, shop.getReason());
            stmt.setString(3, shop.getLink());
            stmt.executeUpdate();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
    }

    // 全件取得
    public List<Shop> showShop(Connection connection) {

        try {
            List<Shop> shopList = new ArrayList<>();
            // SQLコマンド
            String sql = "select * from shop_info";

            // SQLのコマンドを実行する
            // 実行結果はrsに格納される
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
				Shop shop = new Shop();
                shop.setId(rs.getInt("id"));
                shop.setShopName(rs.getString("shop_name"));
                shop.setReason(rs.getString("shop_reason"));
                shop.setLink(rs.getString("google_map"));
				shopList.add(shop);
            }
            // 終了処理
            stmt.close();
            rs.close();

            return shopList;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return null;

        } finally {
        }
    }

}
