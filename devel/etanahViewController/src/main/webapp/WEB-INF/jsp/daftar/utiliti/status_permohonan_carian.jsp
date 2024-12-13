<%-- 
    Document   : status_permohonan_carian
    Created on : May 8, 2013, 6:15:42 PM
    Author     : apool
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:messages />
<s:errors />

<s:form action="/daftar/kesinambungan" >
    <br>
    <fieldset class="aras1">

        <legend>Maklumat Carian</legend>
        <br>
        
        <p>
            <label for="idPermohonan">ID Carian :</label>
            ${actionBean.pc.idCarian}
        </p>

          <p><label for="kodUrusan">Kod Urusan/Urusan :</label>
              ${actionBean.pc.urusan.kod} -
              ${actionBean.pc.urusan.nama}
          </p>

        <p><label for="tarikhDaftar">Tarikh Carian :</label>
            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.pc.infoAudit.tarikhMasuk}"/>     <fmt:formatDate pattern="hh:mm aa" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
        </p>
        
       
        <p><label for="penyerah">Penyerah :</label>
            ${actionBean.pc.penyerahNama == null ? "-" : actionBean.pc.penyerahNama}
        </p>
        
        <p><label>Kerani Kemasukan :</label>
            ${actionBean.pc.infoAudit.dimasukOleh.nama == null ? "-" : actionBean.pc.infoAudit.dimasukOleh.nama}
        </p>
        <br>
    </fieldset>
    <br>

    <fieldset class="aras1">
        <legend>
            Maklumat Hakmilik
        </legend>
        <p><div class="content" align="center">

            <display:table class="tablecloth" name="${actionBean.senaraiHakmilik}"  cellpadding="0" cellspacing="0" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column   title="ID Hakmilik" property="idHakmilik"/>
               <display:column property="noLot" title="No Lot" />
               <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.luas}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
              <display:column property="daerah.nama" title="Daerah" class="daerah"/>
               <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
            </display:table>
            &nbsp;
        </div>
        </p>
        
    </fieldset>
    <br>
    <div  align="center"><p><s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/></p></div>
         
</s:form>
</body>
</html>