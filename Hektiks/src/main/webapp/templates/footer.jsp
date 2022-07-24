<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Logger.consoleLog(Logger.JSP, "FOOTER.JSP"); %>
<!-- footer start -->
<div class="separator" id="telegram-separator">
    <div class="separator-content" \>
        <img src="<%= request.getContextPath() %>/assets/images/icons/telegram.svg" alt="telegram">
        <div style="text-align: center; padding: 0 1.2rem;">
            <h2 class="hs-5">Non perderti nessuna offerta o promozione!</h2>
            <p class="text" style="color: var(--light-grey); margin-top: 0.2rem">Sii il primo a ricevere offerte e
                promozioni!</p>
        </div>
        <a href="https://t.me/WarehouseDealsItalia" target="_blank" class="btn">Unisciti</a>
    </div>
</div>
<footer>
    <div class="footer-container">
        <div class="footer-main-content">
            <h1 class="hs-4">Seguici su GitHub</h1>
            <br>
            <a title="Angelo Nazzaro" href="https://github.com/sl1mSha4dey" target="_blank" class="github">
                <img src="<%= request.getContextPath() %>/assets/images/angelo_nazzaro_github.jpg"
                     alt="angelo nazzaro github">
            </a>
            <a title="Francesco Granozio" href="https://github.com/Francesco-Granozio" target="_blank" class="github">
                <img src="<%= request.getContextPath() %>/assets/images/francesco_granozio_github.jpg"
                     alt="francesco granozio github">
            </a>
        </div>
        <div class="bottom-line">
            <p class="text" style="padding-top: 1rem">Copyright &copy; Hektiks 2022 ~ developed with <span
                    style="color: red">&#10083;</span> by Angelo Nazzaro & Francesco Granozio</p>
        </div>
    </div>
</footer>


<script src="<%= request.getContextPath() %>/assets/libs/jquery/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/libs/awesomeNotifications/index.var.js"></script>
<script src="https://kit.fontawesome.com/bedafbbe2b.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script>
    const notifier = new AWN({position: "top-right"});
</script>
<script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
<!-- footer end -->
</body>
</html>
