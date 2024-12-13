
<%--
    Document   : carian_kodsekatan
    Created on : Dec 10, 2009, 5:26:20 PM
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

         function sendValue(s){
        var selvalue = s.options[s.selectedIndex].value;
       
    window.opener.document.popupform.choice.value = selvalue;
    window.close();
    }
        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.stripes.MaklumatAsasActionBean">
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
                        <label>&nbsp;</label>
                        <s:submit name="cariKodSekatan" value="Cari" class="btn"/>
                    </p>
                </fieldset>
            </div>
            <br>

            <div class="subtitle">
                <%--  <c:if test="${fn:length(actionBean.listHakmilik) > 0}">--%>
                <c:if test="${actionBean.kodSekatan != null}">

                <fieldset class="aras1">
                    <legend>Carian Kod Sekatan</legend>

                    <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSekatan}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pendaftaran/maklumat_asas?cariKodSekatan" id="line">
                        <display:column> <s:radio name="radio_" id="radio_" value="${line.kod}" onclick="tryhaha()"/></display:column>
                        <display:column title="kod" property="kod" />
                        <display:column title="Sekatan" property="sekatan" />
                    </display:table>
                    <c:if test="${fn:length(actionBean.listKodSekatan) > 0}" >
                        <p><label>&nbsp;</label>
                            <s:button name="simpanKodSekatan" value="Simpan" id="simpanKodSekatan" class="btn"/>
                        </p>
                    </c:if>

                </fieldset>

                  </c:if>

            </s:form>
        </div>
    </body>
</html>



<form name=selectform>
<select name=selectmenu size="8">
<option value="Item A">Item A
<option value="Item B">Item B
<option value="Item C">Item C
<option value="Item D">Item D
<option value="Item E">Item E
<option value="Item F">Item F
<option value="Item G">Item G
<option value="Item H">Item H
<option value="Item I">Item I
<option value="Item J">Item J
<option value="Item K">Item K
<option value="Item L">Item L
<option value="Item M">Item M
<option value="Item M">Item N
<option value="Item O">Item O
<option value="Item P">Item P
<option value="Item Q">Item Q
<option value="Item R">Item R
<option value="Item S">Item S
<option value="Item T">Item T
<option value="Item U">Item U
<option value="Item V">Item V
<option value="Item W">Item W
<option value="Item X">Item X
<option value="Item Y">Item Y
<option value="Item Z">Item Z
</select>
<p>
    <input type=button value="Item Select Item" onClick="sendValue(this.form.selectmenu);">
</p>
</form>



<!-- Script Size:  2.14 KB -->