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


//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/Login")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("Login");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String mail = request.getParameter("mail");
        String address = mail.split("@")[1];
        if(address.equals("st.u-gakugei.ac.jp")){
            response.sendRedirect("/KokuGuru/Shop");
            HttpSession session = request.getSession(true);
        }
        else response.sendRedirect("/KokuGuru/Login");
    }
}
