<%--
    Document   : carian_aduan
    Created on : 31-Mar-2011, 01:09:17
    Author     : nordiyana
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
            $("#selectedIdHM").val(obj.value);
            <%--document.getElementById("selectedIdMohon").value=obj.id;--%>
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
           <s:form beanclass="etanah.view.stripes.pengambilan.KemasukanAduanActionBean">
                <%-- <s:hidden name="idHakmilik" />--%>
                <fieldset class="aras1">
                    <legend>
                        Kemasukan Untuk Carian Id Hakmilik
                    </legend>
                    <s:hidden id="selectedIdHM" name="selectedIdHM" />

                    <p>
                        <%--<s:hidden name="index" id="index" />--%>
                    </p>

                    <table border="0" cellspacing="5" width="80%">
                    <tr>
                        <td align="left" width="30%" valign="top"><label style="; clear: right"  >Id Hakmilik :</label></td>
                        <td><s:text name="idHakmilik" id="idHakmilik" onkeyup="javascript:uppercase();" size="30"/></td>
                    </tr>
                    <%--Maklumat permohonan yang hendak ditarik balik :--%>
                   <%-- <tr>
                        <td><label>Nama Projek :</label></td>
                        <td><s:text name="sebabProjek" id="sebabProjek"  onkeyup="javascript:uppercase();" size="30"/></td>
                    </tr>--%>
                    <tr>
                        <td><label>&nbsp;</label></td>
                        <td><s:submit name="HakmilikPopup" value="Cari" class="btn"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/></td>
                    </tr>
                    </table>
                </fieldset>
            </div>
            <br>

            <div class="subtitle">


               <%-- <c:if test="${actionBean.kodSyaratNyata != null}">--%>

                <fieldset class="aras1">
                    <legend>Senarai Permohonan Beserta Nama Projek</legend><br />
                    <div  align="center">


                            <display:table style="width:70%" class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" pagesize="3" cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanaduan" id="line">
                                <display:column> <s:radio name="radio_" id="${line.id}" value="${line.id}" style="width:15px" onclick="javascript:selectRadio(this)"/></display:column>
                                <display:column title="Id Permohonan" property="permohonan.idPermohonan" style="vertical-align:top;"/>
                                <display:column title="Nama Projek" property="permohonan.sebab" style="text-transform:uppercase;vertical-align:top;" />
                                <display:column title="Tarikh Masuk" style="vertical-align:top;">
                                    <fmt:formatDate value="${line.permohonan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/>
                                </display:column>
                            </display:table>
                        <c:if test="${fn:length(actionBean.senaraiHakmilikPermohonan) > 0}">
                            <%--<s:submit name="cariHakmilikUrusan" value="OK" id="cariHakmilikUrusan" class="btn" />--%>
                            <s:button name="cariHakmilikUrusan" id="cariHakmilikUrusan" value="OK" class="btn" onclick="save(this.name, this.form);"/>

                        </c:if>
                    </div>
                    <%--<p>
                        <display:table style="width:97%" class="tablecloth" name="${actionBean.hakmilikMHList}" pagesize="3" cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanaduan" id="line">
                            <display:column> <s:radio name="radio_" id="${line.idPermohonan}" value="${line.sebab}" style="width:15px" onclick="javascript:selectRadio(this)"/></display:column>
                            <display:column title="Id Permohonan Terdahulu" property="line.permohonan.idPermohonan" style="width:20%;vertical-align:top;"/>
                            <display:column title="Nama Projek" style="text-transform:uppercase;width:90%;vertical-align:top;" />
                            <display:column title="Hakmilik" style="width:60%;vertical-align:top;">
                                <c:set var="i" value="1"/>
                                <c:forEach items="${line.senaraiHakmilik}" var="senarai">
                                    ${i})${senarai.hakmilik.idHakmilik}<br/>
                                    <c:set var="i" value="${i+1}"/>
                                </c:forEach>
                                <display:table name="${line.senaraiHakmilik}" id="line1">
                                    <display:column title="IdHakmilik">
                                        ${line1.hakmilik.idHakmilik}

                                    </display:column>
                                </display:table>
                            </display:column>
                            </display:table>
                    </p>
                    <c:if test="${fn:length(actionBean.hakmilikMHList) > 0}" >
                        <p><label>&nbsp;</label>
                            <s:button name="cariHakmilikUrusan" value="OK" id="simpanIdMohonOSebab" class="btn" onclick="javascript:test();"/>
                        </p>
                    </c:if>--%>
                </fieldset>

            </s:form>
        </div>
    </body>
</html>

