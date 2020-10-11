//　自分が格納されているフォルダ名
package control;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import beans.Review;
import dao.ReviewDAO;

public class ReviewManager {

    // 属性
    private Connection connection = null;

    // 引数を持たないコンストラクタ
    public ReviewManager() {
    }

    // 追加
    // 引数はStudentオブジェクト
    public void registReview(Review review) {

        // StudentDAOオブジェクト生成
        ReviewDAO reviewDAO = new ReviewDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = reviewDAO.createConnection();

        // StudentオブジェクトをDataBaseに登録する
        reviewDAO.registReview(review, this.connection);

        // DataBaseとの接続を切断する
        reviewDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

    }

    // 全件取得
    public List<Review> showReview() {

        // StudentDAOオブジェクト生成
        ReviewDAO reviewDAO = new ReviewDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = reviewDAO.createConnection();

        List<Review> reviewList = new ArrayList<>();
        reviewList = reviewDAO.showReview(this.connection);

        // DataBaseとの接続を切断する
        reviewDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return reviewList;
    }

}
