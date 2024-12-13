<%-- 
    Document   : carian_hakmilik_asal
    Created on : 03 November 2009, 4:40:06 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head>
        <title>Carian Hakmilik Asal</title>
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
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
        <script type="text/javascript">
            function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;                
                $.post(url,q,
                function(data){
                    if(data == '1')
                    {
                        alert('Sila masukan data pada label yang bertanda *. ');
                    }else{
                        //alert($('#perincianHakmilik',opener.document).html());
                        $('#perincianHakmilik',opener.document).html(data);
                        self.close();
                    }

                },'html');

            }
            $(document).ready(function() {
                maximizeWindow();
            });
                    
            <%--function save2(){
                self.opener.addTable
            }--%>

                   
        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean">
                <s:hidden name="idHakmilik" />
                <fieldset class="aras1">
                    <legend>
                        Carian Hakmilik Asal
                    </legend>

                    <p>

                        <label for="ID Hakmilik Asal">ID Hakmilik :</label>
                        <s:text name="idAsal" id="idAsal"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="searchHakmilikAsalByIDHakmilikAsal" value="Cari" class="btn"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </fieldset>
            </div>
            <br>
            <%-- <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Senarai Hakmilik Asal
            </legend>

            --%> <%--<div class="content" align="center">
                 <display:table class="tablecloth" name="${actionBean.listHakmilik}" pagesize="4" cellpadding="0" cellspacing="0" requestURI="/pendaftaran/kemasukan_perincian_hakmilik?searchHakmilikAsalByIDHakmilikAsal" id="line">
                     <display:column> <s:checkbox name="chkbox" id="chkbox${line_rowNum}" value="${line.idHakmilik}"/></display:column>
                     <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                     <display:column property="idHakmilik" title="ID Hakmilik Asal" class="idasal${line_rowNum}"/>
                     <display:column property="tarikhDaftar" title="Tarikh mula dimiliki" format="{0,date,dd-MM-yyyy}" class="tarikhmilik${line_rowNum}"/>
                     <display:column property="nama" title="Nama" class="nama${line_rowNum}" />
                     <display:column property="noPengenalan" title="No KP" class="nokp${line_rowNum}"/>
                     <s:hidden name="idpihak" class="idpihak${line_rowNum}"></s:hidden>
                 </display:table>
             </div>--%><%--
                    <c:if test="${fn:length(actionBean.listHakmilik) > 0}">
                        <p>
                            <label>&nbsp;</label>
                            <s:button name="add" id="add" value="Tambah" class="btn"/>
                        </p>
                    </c:if>
                </fieldset>
            </form:form>
        </div>--%>

            <div class="subtitle">
                <%--  <c:if test="${fn:length(actionBean.listHakmilik) > 0}">--%>
                <c:if test="${actionBean.idAsal != null and actionBean.sejarahHakmilik.idHakmilik != null}">
                    <s:hidden name="sejarahHakmilik.idHakmilik"/>
                    <fieldset class="aras1">
                        <legend>Hakmilik Asal</legend>
                        <p><label>ID Hakmilik : </label>
                            ${actionBean.sejarahHakmilik.idHakmilik}
                        </p>
                        <p><label>Tarikh Mula dimiliki : </label>
                            <fmt:formatDate type="date"
                                            pattern="dd/MM/yyyy"
                                            value="${actionBean.sejarahHakmilik.tarikhDaftar}"/>
                        </p>
                        <p><label>&nbsp;</label>
                            <s:button name="simpanHakmilikAsal" id="simpan" value="Simpan" class="btn" onclick="save(this.name,this.form);"/>
                        </p>
                    </fieldset>

                </c:if>

                <%-- <c:if test="${fn:length(actionBean.listHakmilik) == 0 and actionBean.idAsal != null}">
                     <fieldset class="aras1">
                         <legend>Hakmilik Asal</legend>
                         <p><label>ID Hakmilik : </label>
                             <s:text name="idAsal" />
                         </p>
                         <p><label>Tarikh Mula dimiliki : </label>
                             <s:text name="sejarahHakmilik.tarikhDaftar" formatPattern="dd/MM/yyyy" class="datepicker" />
                         </p>

                        <p><label>&nbsp;</label>
                            <s:button name="simpanHakmilikAsal" value="Simpan" class="btn" onclick="save(this.name,this.form);"/>
                        </p>
                    </fieldset>
                </c:if>
                --%>        
            </s:form>
        </div>
    </body>
</html>