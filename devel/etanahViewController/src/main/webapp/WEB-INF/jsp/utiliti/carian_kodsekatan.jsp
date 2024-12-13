<%-- 
    Document   : carian_kodsekatan
    Created on : Apr 15, 2010, 10:45:46 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carian Kod Sekatan</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript">
            function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
            <%--alert(url);--%>
                    $.post(url,q,
                    function(data){
                        if(data == '1')
                        {
                            alert('Sila masukan data pada label yang bertanda *. ');
                        }else{$('#page_div',opener.document).html(data);

                            self.close();}

                    },'html');

                }
                $(document).ready(function() {
                    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                    $("#simpanKodSekatan").click(function(){
                        opener.document.getElementById('sekatan').value = $('input:radio[name=radio_]:checked').val();
                        self.close();
                    });

                });
        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.daftar.utiliti.PertanyaanKod">
                <%--  <s:hidden name="idHakmilik" />--%>
                <fieldset class="aras1">
                    <legend>
                        Carian Kod Sekatan
                    </legend>

                    <p>
                        <label>Kod Sekatan :</label>
                        <s:text name="kodSekatan" id="kodSekatan"/>
                    </p>
                     <p>
                        <label>Sekatan :</label>
                        <s:text name="sekatan" id="sekatan"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cariKodSekatan" value="Cari" class="btn"/>
                    </p>
                </fieldset>
            </div>
            <br>

            <div class="subtitle">
                <%--  <c:if test="${fn:length(actionBean.listHakmilik) > 0}">--%>
               <%-- <c:if test="${actionBean.kodSekatan != null}">--%>

                <fieldset class="aras1">
                    <legend>Carian Kod Sekatan</legend>

                    <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSekatan}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/utiliti/pertanyaan_kod" id="line">
                        <%--<display:column> <s:radio name="radio_" id="radio_" value="${line.kod}"/></display:column>--%>
                        <display:column title="kod" property="kodSekatan" />
                        <display:column title="Sekatan" property="sekatan" />
                    </display:table>
                    

                </fieldset>

                  <%--</c:if>--%>

            </s:form>
        </div>
    </body>
</html>