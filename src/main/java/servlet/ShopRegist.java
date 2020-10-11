//--------------------------------
//	RegistInfo.java
//--------------------------------
//　自分が格納されているフォルダ名
package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
/*
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/
import javax.servlet.http.*;

import beans.Shop;
import beans.Review;
import control.ShopManager;
import control.ReviewManager;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/Shop")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class ShopRegist extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/KokuGuru/Login");
        }
        else{
            List<Shop> shopList=new ArrayList<>();
            ShopManager shopManager = new ShopManager();
            shopList=shopManager.showShop();
            Collections.reverse(shopList);
            List<Review> reviewList = new ArrayList<>();
            ReviewManager reviewManager = new ReviewManager();
            reviewList = reviewManager.showReview();
            Collections.reverse(reviewList);

            request.setAttribute("shopList",shopList);
            request.setAttribute("reviewList",reviewList);
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            RequestDispatcher dispatcher = request.getRequestDispatcher("/top.jsp");
            dispatcher.forward(request, response);
        }
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/KokuGuru/Login");
        }
        else{
            // requestオブジェクトから登録情報の取り出し
            String shopName = request.getParameter("shopName");
            String reason = request.getParameter("reason");
            String link = request.getParameter("link");
            link=link.split("width=")[0];
            String need = "\"width=\"100%\" height=\"50%\" frameborder=\"0\" style=\"border:0;\" allowfullscreen=\"\" aria-hidden=\"false\" tabindex=\"0\"></iframe>";
            link=link.concat(need);

            // studentオブジェクトに情報を格納
            Shop shop = new Shop();
            shop.setShopName(shopName);
            shop.setReason(reason);
            shop.setLink(link);

            ShopManager shopManager = new ShopManager();

            shopManager.registShop(shop);
            // 成功画面を表示する
            // System.out.println("OK牧場");
            response.sendRedirect("/KokuGuru/Shop");
        }
    }
}
