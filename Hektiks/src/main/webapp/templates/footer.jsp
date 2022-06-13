<%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 26/04/2022
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- footer start -->
<footer>
    <div class="footer-container">
        <div class="footer-main-content">
            <h1 class="hs-4">Seguici su Github</h1>
            <br>
            <a title="Angelo Nazzaro" href="https://github.com/sl1mSha4dey" target="_blank" class="github">
                <img src="<%= request.getContextPath() %>/assets/images/angelo_nazzaro_github.jpg" alt="angelo nazzaro github">
            </a>
            <a title="Francesco Granozio" href="https://github.com/Francesco-Granozio" target="_blank" class="github">
                <img src="<%= request.getContextPath() %>/assets/images/francesco_granozio_github.jpg" alt="francesco granozio github">
            </a>
        </div>
        <div class="bottom-line">
            <p class="text" style="padding-top: 1rem">Copyright &copy; Hektiks 2022 ~ developed with <span style="color: red">&#10083;</span> by Angelo Nazzaro & Francesco Granozio</p>
        </div>
    </div>
</footer>
<script src="<%= request.getContextPath() %>/assets/libs/jquery/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/libs/awesomeNotifications/index.var.js"></script>
<script src="https://kit.fontawesome.com/bedafbbe2b.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
<!-- footer end -->
</body>
</html>
