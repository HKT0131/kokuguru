//--------------------------------
//	RegistInfo.java
//--------------------------------
//　自分が格納されているフォルダ名
package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
/*
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/
import javax.servlet.http.*;

import beans.Review;
import control.ReviewManager;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/Review")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class ReviewRegist extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/KokuGuru/Login");
        }
        else{
            // requestオブジェクトの文字エンコーディングの設定
            request.setCharacterEncoding("UTF-8");
            // requestオブジェクトから登録情報の取り出し
            int shopId = Integer.parseInt(request.getParameter("shopId"));
            String opinion = request.getParameter("opinion");

            // studentオブジェクトに情報を格納
            Review review = new Review(shopId, opinion);

            ReviewManager reviewManager = new ReviewManager();

            reviewManager.registReview(review);
            // 成功画面を表示する
            // System.out.println("OK牧場");
            response.sendRedirect("/KokuGuru/Shop");
        }
    }
}
