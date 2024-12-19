<%--
    Document   : carianNsekatan
    Edited on : Jan 5, 2010, 4:04:17 PM
    Modified by     : mohd.fairul
--%>

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

<%--<html>--%>
    <%--<head>--%>
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
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>

        <script type="text/javascript">
            function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                },'html');

            }

            function pickPut(s){
       var strKodSekatan =$('#inputName').val();
               <%--alert(strKodSekatan);--%>
                var detail = $('input:radio[name=radio_]:checked').val();

                var txt = '<p class=\"jqismooth\">Anda Telah Memilih</p>'+
                    'Kod Syarat :<br\>' +s+
                    '<br\><br\>Maklumat Kod Syarat Nyata :<br\>' + detail;


                var msgBox = {
                    state0: {
                        html:txt,
                        buttons: { 'Teruskan': true, 'Pilih Semula': false },
                        focus: 1,
                        submit:function(v,m,f){
                            if(!v) return true;
                            else
                                opener.document.getElementById(strKodSekatan).value = s;
                            $.prompt.goToState('state1');

                            return false;
                        }
                    },
                    state1: {
                        html:'Kod Sekatan Kepentingan \''+s+'\' berjaya dipilih.',
                        submit:function(v,m,f){
                            self.close();
                        }
                    }

                };

                $.prompt(msgBox);



            }


        </script>


    <%--</head>--%>
    <%--<body>--%>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.stripes.nota.NotaDaftarActionBean">
                <%-- <s:text disabled="true" name="syarat" id="syarat"/>--%>
                <s:hidden name="inputName" id="inputName"/>
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
                <%--<c:if test="${actionBean.kodSekatan != null}">--%>

                    <fieldset class="aras1">
                        <legend>Carian Kod Sekatan</legend>

                        <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSekatan}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/daftar/nota/nota_daftar?cariKodSekatan" id="line">
                            <display:column> <s:radio name="radio_" id="radio_" value="${line.sekatan}" onclick="pickPut('${line.kodSekatan}')"/></display:column>
                            <display:column title="kod" property="kodSekatan" />
                            <display:column title="Sekatan" property="sekatan" />
                        </display:table>
                        <%--<c:if test="${fn:length(actionBean.listKodSekatan) > 0}" >--%>
                            <p><label>&nbsp;</label>
                                <s:button name="simpanKodSekatan" value="Tutup" id="simpanKodSekatan" class="btn" onclick="javascript:window.close();"/>
                            </p>
                        <%--</c:if>--%>

                    </fieldset>

                <%--</c:if>--%>

            </s:form>
        </div>
<%--    </body>
</html>--%>
