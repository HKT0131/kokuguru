//　自分が格納されているフォルダ名
package control;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import beans.Shop;
import dao.ShopDAO;

public class ShopManager {

    // 属性
    private Connection connection = null;

    // 引数を持たないコンストラクタ
    public ShopManager() {
    }

    // 追加
    // 引数はStudentオブジェクト
    public void registShop(Shop shop) {

        // StudentDAOオブジェクト生成
        ShopDAO shopDAO = new ShopDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = shopDAO.createConnection();

        // StudentオブジェクトをDataBaseに登録する
        shopDAO.registShop(shop, this.connection);

        // DataBaseとの接続を切断する
        shopDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

    }

    // 全件取得
    public List<Shop> showShop() {

        // StudentDAOオブジェクト生成
        ShopDAO shopDAO = new ShopDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = shopDAO.createConnection();

        List<Shop> shopList = new ArrayList<>();
        shopList = shopDAO.showShop(this.connection);

        // DataBaseとの接続を切断する
        shopDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return shopList;
    }

}
