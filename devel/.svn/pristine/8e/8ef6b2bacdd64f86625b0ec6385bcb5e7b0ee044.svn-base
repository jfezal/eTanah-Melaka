<%--
    Document   : carian_kodsyaratnyata
    Created on : Dec 10, 2009, 6:47:58 PM
    Author     : khairil #modified By fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carian Kod Syarat Nyata</title>
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
                     var detail = $('input:radio[name=radio_]:checked').val();
                    var strKodSyarat =$('#inputName').val();
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
                                     opener.document.getElementById(strKodSyarat).value = s;
                                    $.prompt.goToState('state1');

                                 return false;
                             }
                         },
                         state1: {
                             html:'Kod Syarat Nyata \''+s+'\' telah dipilih.',
                             submit:function(v,m,f){
                               self.close();
                             }
                         }

                     };

                     $.prompt(msgBox);



                         }


        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.stripes.nota.NotaDaftarActionBean">
                <s:hidden name="kodSekatan" id="kodSekatan" />
                <s:hidden name="inputName" id="inputName"/>
                <fieldset class="aras1">
                    <legend>
                        Carian Kod Syarat Nyata
                    </legend>

                    <p>

                        <label>Kod Syarat Nyata :</label>
                        <s:text name="kodSyaratNyata" id="kodSyaratNyata"/>
                    </p>
                    <p>

                        <label>Syarat :</label>
                        <s:text name="urusan" id="urusan"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cariKodSyaratNyata" value="Cari" class="btn"/>
                    </p>
                </fieldset>
            </div>
            <br>

            <div class="subtitle">
                <%--  <c:if test="${fn:length(actionBean.listHakmilik) > 0}">--%>
                <%--<c:if test="${actionBean.kodSyaratNyata != null}">--%>

                    <fieldset class="aras1">
                        <legend></legend>
                        <p>
                            <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSyaratNyata}" pagesize="15" cellpadding="0" cellspacing="0" requestURI="/daftar/nota/nota_daftar?cariKodSyaratNyata" id="line">
                                <display:column> <s:radio name="radio_" id="radio_" value="${line.syarat}" onclick="pickPut('${line.kodSyarat}')"/></display:column>
                                <display:column title="Kod" property="kodSyarat" />

                                <display:column title="Syarat" property="syarat" />
                            </display:table>
                        </p>
                        <%--<c:if test="${fn:length(actionBean.listKodSyaratNyata) > 0}" >--%>
                            <p><label>&nbsp;</label>
                                <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
                            </p>
                        <%--</c:if>--%>
                    </fieldset>

                <%--</c:if>--%>

            </s:form>
        </div>
    </body>
</html>
