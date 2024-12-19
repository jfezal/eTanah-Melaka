<%--
    Document   : kemasukan_kemudahan
    Created on : Dec 21, 2010, 12:14:31 PM
    Author     : Zadhirul.Farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
      function savePemohon(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
                 //   refreshPage();
             },'html');
    }
  <%-- $(document).ready(function() {
          alert("222");
        var url = '${pageContext.request.contextPath}/strata/permohonanStrata?reload';
        $.ajax({
            type:"GET",
            url : url,
            success : function(data) {
               $('#folder_div',opener.document).html(data);
            }
        });
    });--%>

    //$(window).bind("beforeunload", function(e){alert('aaaa') });


   <%-- window.onbeforeunload= function (evt) {
        alert('test');
    }--%>


</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.strata.PermohonanStrataActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tambah Kemudahan</legend>
            <p>
                <label>Kemudahan : </label><s:text name="mhnStrataKemudahan" class="normal_text"/>

            </p>
            <p>
                <label>&nbsp;</label>&nbsp;
                <s:button class="btn" value="Tambah" name="tambahKemudahan" onclick="savePemohon(this.name, this.form);"/>
                <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
            </p>
        </fieldset>
    </div>
</s:form>
