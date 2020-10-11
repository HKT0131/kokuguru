<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="beans.Shop" %>
<%@page import="beans.Review" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<% List<Shop> shopList= (ArrayList<Shop>) request.getAttribute("shopList"); %>
<% List<Review> reviewList= (ArrayList<Review>) request.getAttribute("reviewList"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  </head>
  <body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <nav class="navbar navbar-expand-lg navbar-light bg-warning">
      <a class="navbar-brand" href="#">国分寺グルメ掲示板</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <div class="btn" data-toggle="modal" data-target="#exampleModal">投稿する</a>
          </li>
        </ul>
      </div>
    </nav>
    <br>

    <% if(shopList.size()==0){ %>
      <h3>投稿はありません</h3>
    <%} else{ %>
    <% for(Shop shop : shopList) {%>
    <div class="card m-auto" style="width: 80%;">
      <%= shop.getLink() %>
      <div class="card-body">
        <h5 class="card-title"><%= shop.getShopName() %></h5>
        <p class="card-text"><%= shop.getReason() %></p>
        <div class="row justify-content-center text-center">
          <a href="#" class="col-3" data-toggle="modal" data-target="#ReviewModal<%= shop.getId() %>">Let's review</a>
          <a href="#" class="col-3" data-toggle="modal" data-target="#KuchikomiModal<%= shop.getId() %>">みんなの口コミ</a>
        </div>
      </div>
    </div>
    <!-- ReviewModal -->
    <form action="./Review" method="post" class="modal fade" id="ReviewModal<%= shop.getId() %>" tabindex="-1" role="dialog" aria-labelledby="ReviewModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="ReviewModalLabel">Review</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
            <div class="modal-body">
              <input type="hidden" name="shopId" value="<%= shop.getId() %>">
              <div class="form-group">
                <label><%= shop.getShopName() %></label>
                <textarea class="form-control" name="opinion" rows="3"></textarea>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary">Go</button>
            </div>
        </div>
      </div>
    </form>

    <!-- 口コミModal -->
    <div class="modal fade" id="KuchikomiModal<%= shop.getId() %>" tabindex="-1" role="dialog" aria-labelledby="KuchikomiModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header bg-light">
            <h5 class="modal-title" id="ReviewModalLabel"><%= shop.getShopName() %>の口コミ</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
            <div class="modal-body">
              <%List<Review> list = new ArrayList<>();%>
              <%for(Review review : reviewList){%>
              <%if(review.getShopId()==shop.getId()) list.add(review);%>
              <%}%>
              <% if(list.size()==0){ %>
                <h3>口コミはありません</h3>
              <%} else{ %>
              <% int cnt=1;%>
              <% for(Review review : list) {%>
              <div class="card m-auto">
                <div class="card-title bg-light text-center"><%= cnt%>コメ</div>
                <div class="card-text text-center"><%= review.getOpinion()%></div>
              </div>
              <br>
              <% cnt++;%>
              <%}%>
              <%}%>
            </div>
            <div class="modal-footer">
            </div>
        </div>
      </div>
    </div>
    <br>
    <%}%>
    <%}%>
    <br>
    
    <!-- PostModal -->
    <form action="./Shop" method="post" class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">投稿</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="shopName">店の名前</label>
              <input type="text" class="form-control" require maxlength="30" name="shopName" aria-describedby="emailHelp" placeholder="シシカリ">
            </div>
            <div class="form-group">
              <label for="reason">おすすめ理由</label>
              <textarea class="form-control" require maxlength="255" name="reason" rows="3"></textarea>
            </div>
            <div class="form-group">
              <label for="link">GoogleMapLink</label>
              <textarea class="form-control" require maxlength="500" name="link" rows="3"></textarea>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary">Go</button>
          </div>
        </div>
      </div>
    </form>
  </body>
</html>
