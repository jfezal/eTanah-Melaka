<%--
    Document   : search_IdMohonOSebab
    Created on : Sep 27, 2010, 5:44:54 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carian Id Permohonan Sebelum atau Nama Projek</title>
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
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
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

            function test(){
                <%--alert(index);--%>
                <%--var namaProjekVal = 'namaProjek';--%>
               <%--alert(namaProjekVal);--%>
                <%--var index=document.getElementById('index').value;--%>
                opener.document.getElementById('idSblm').value = $("#selectedIdMohon").val();
                opener.document.getElementById('namaProjek').value = $("#selectedNama").val();
                self.opener.hideMaklumatPemohon();
                self.close();
            }

        function closeWindow() {
          //uncomment to open a new window and close this parent window without warning
          //var newwin=window.open("popUp.htm",'popup','');
          if(navigator.appName=="Microsoft Internet Explorer") {
          this.focus();self.close(); }
          else { window.open('','eTanah',''); window.close(); }
        }


           function uppercase(){
             var sebabProjek = document.getElementById("sebabProjek").value;
             document.getElementById("sebabProjek").value = sebabProjek.toUpperCase();

              var idMohon = document.getElementById("idMohon").value;
             document.getElementById("idMohon").value = idMohon.toUpperCase();
        }

        function selectRadio(obj){
            document.getElementById("selectedIdMohon").value=obj.id;
            document.getElementById("selectedNama").value=obj.value;
        }

          $(document).ready(function() {
                maximizeWindow();
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            });

    </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
           <s:form beanclass="etanah.view.stripes.pengambilan.AduanNoTabActionBean">
                <%-- <s:hidden name="idHakmilik" />--%>
                <fieldset class="aras1">
                    <legend>
                        Kemasukan Untuk Carian Id Permohonan Terdahulu atau Nama Projek
                    </legend>
                    <s:hidden id="selectedIdMohon" name="selectedIdMohon" />
                    <s:hidden id="selectedNama" name="selectedNama" />

                    <p>
                        <%--<s:hidden name="index" id="index" />--%>
                    </p>

                    <table border="0" cellspacing="5" width="80%">
                    <tr>
                        <td align="left" width="30%" valign="top"><label style="; clear: right"  >Maklumat permohonan yang terdahulu :</label></td>
                        <td><s:text name="id" id="idMohon" onkeyup="javascript:uppercase();" size="30"/></td>
                    </tr>
                    <%--Maklumat permohonan yang hendak ditarik balik :--%>
                    <tr>
                        <td><label>Nama Projek :</label></td>
                        <td><s:text name="sebabProjek" id="sebabProjek"  onkeyup="javascript:uppercase();" size="30"/></td>
                    </tr>
                    <tr>
                        <td><label>&nbsp;</label></td>
                        <td><s:submit name="cariIdSebelumOSebab" value="Cari" class="btn"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/></td>
                    </tr>
                    </table>
                </fieldset>
            </div>
            <br>

            <div class="subtitle">

               <%-- <c:if test="${actionBean.kodSyaratNyata != null}">--%>

                <fieldset class="aras1">
                    <legend>senarai ID Permohonan Beserta Nama Projek</legend><br />
                    <p>
                        <display:table style="width:97%" class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" pagesize="3" cellpadding="1" cellspacing="1" requestURI="/pengambilan/penarikanBalik" id="line">

                            <display:column> <s:radio name="radio_" id="${line.idPermohonan}" value="${line.sebab}" style="width:15px" onclick="javascript:selectRadio(this)"/></display:column>
                            <iframe scrolling="yes">
                            <display:column title="Id Permohonan" property="idPermohonan" style="width:20%;vertical-align:top;"/>
                            <display:column title="Nama Projek" property="sebab" style="text-transform:uppercase;width:90%;vertical-align:top;" />
                            <display:column title="Hakmilik" style="width:60%;vertical-align:top;">
                                <%--<c:set var="i" value="1"/>
                                <c:forEach items="${line.senaraiHakmilik}" var="senarai">
                                    ${i})${senarai.hakmilik.idHakmilik}<br/>
                                    <c:set var="i" value="${i+1}"/>
                                </c:forEach>--%>
                                <display:table name="${line.senaraiHakmilik}" id="line1">
                                    <display:column title="IdHakmilik">
                                        ${line1.hakmilik.idHakmilik}

                                    </display:column>
                                </display:table>
                            </display:column>
                            </iframe>
                            </display:table>
                    </p>
                    <c:if test="${fn:length(actionBean.listIdMohon) > 0}" >
                        <p><label>&nbsp;</label>
                            <s:button name="simpanIdMohonOSebab" value="OK" id="simpanIdMohonOSebab" class="btn" onclick="javascript:test();"/>
                        </p>
                    </c:if>
                </fieldset>

            </s:form>
        </div>
    </body>
</html>

