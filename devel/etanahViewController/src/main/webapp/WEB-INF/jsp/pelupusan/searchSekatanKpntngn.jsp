<%-- 
    Document   : searhSekatanKpntngn
    Created on : Oct 28, 2010, 8:56:54 AM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
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
        function masuk(){

            var index=document.getElementById('index').value;

             opener.document.getElementById('kodSktn'+index).value = $("#selectedKod").val();
           <%--  opener.document.getElementById('syaratNyata'+index).value = $("#selectedNama").val();--%>
             self.close();


        }
        function uppercase(){
            <%-- alert("test");--%>
             var syaratNyata2 = document.getElementById("syaratNyata2").value;
             document.getElementById("syaratNyata2").value = syaratNyata2.toUpperCase();
        }

         function selectRadio(obj){

            document.getElementById("selectedKod").value=obj.id;
            <%--document.getElementById("selectedNama").value=obj.value;--%>

        }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.SuratKelulusanActionBean">
    <fieldset class="aras1">
        <legend>Carian Sekatan</legend>
         <s:hidden id="selectedKod" name="selectedKod" />
        <div class="subtitle">
            <p>
                <s:hidden name="index" id="index" />
            </p>
            <p>
                <label>Kod Sekatan</label>
                <s:text name="kodSekatanKepentingan" id="kodSekatanKepentingan"/>
            </p>
            <p>
                <label>Nama Sekatan Kepentingan</label>
                <s:text name="sekatKpntgn2" id="sekatKpntgn2"/>
            </p>
            <p>
                <label>&nbsp;</label>
                 <s:submit name="cariKodSekatan" value="Cari" class="btn"/>
                 <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
            </p>
        </div>

    </fieldset>
        <fieldset class="aras1">
                    <legend></legend>
                    <p>
                        <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSekatan}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pelupusan/surat_kelulusan" id="line">
                            <display:column> <s:radio name="radio_" id="${line.kod}" value="${line.sekatan}"   onclick="javascript:selectRadio(this)"/></display:column>
                            <display:column title="Kod Sekatan" property="kod"/>
                            <display:column title="Nama Sekatan" property="sekatan"/>
                            </display:table>
                    </p>
                    <c:if test="${fn:length(actionBean.listKodSekatan) > 0}" >
                        <p><label>&nbsp;</label>
                            <s:button name="simpanKodSekatan" value="Simpan" id="simpanKodSyaratNyata" class="btn" onclick="javascript:masuk();"/>
                        </p>
                    </c:if>
                </fieldset>
    
</s:form>