<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<page:applyDecorator name="default" title="Ralat Sistem">
    <script type="text/javascript">
        $(function () {
            $('.detail_error').hide();

            $('#detail').click(function () {
                if ($(this).hasClass('open')) {
                    $(this).removeClass('open');
                    $(this).addClass('close');
                    $('.detail_error').hide();
                    $(this).text('[ more detail ]');
                } else {
                    $(this).removeClass('close');
                    $(this).addClass('open');
                    $('.detail_error').show();
                    $(this).text('[ less detail ]');
                }
            });

        });
    </script>
    <br/>
    <fieldset>
        <div class="errors">
            <table>
                <tr>
                    <td><img src="${pageContext.request.contextPath}/images/warning.png"/>&nbsp;</td>
                    <td>
                        <b>Sistem Mengalami Masalah. Sila Hubungi Administrator !</b> 
                        <br/>
                        <%= new java.util.Date()%> <%= request.getServerName()%> - 
                        <b><font color="red">${mesej}</font></b>
                        Kembali ke<b><a href="${pageContext.request.contextPath}/helpdesk/main">&nbsp;Laman Utama</a></b>
                    </td>                    
                </tr>
            </table>
            <br/>
            <p class="detail_error">
                ${exception_msg}
            </p>
        </div>
    </fieldset>

    <!--
    ${exception_msg}
    -->
</page:applyDecorator>