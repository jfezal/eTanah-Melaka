<%-- 
    Document   : pembetul_maklumatHakmilikAsal
    Created on : Dec 23, 2009, 3:19:12 PM
    Author     : mohd.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script language="javascript">
     function editPop(idH,idHA)
   {
       <%--alert("Success IDH::"+ idH+" "+"Success IDHA::"+idHA);--%>

       var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?popupAsal&idH='+idH+'&idHA='+idHA;
      window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=250,scrollbars=yes");
   }
</script>


<s:form beanclass="etanah.view.stripes.nota.pembetulanActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
      
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Asal
            </legend>
            <p style="color:red">
                *Pilih id Hakmilik Asal untuk membuat pembetulan.<br/>
              </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikAsalList}"  cellpadding="0" cellspacing="0"
                               id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="hakmilikAsal" title="ID Hakmilik Asal" />
                    <display:column title="Baiki">
                    <div align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editPop('${line.hakmilik.idHakmilik}', '${line.hakmilikAsal}')"/>
                </div>
                </display:column>
                 </display:table>
            </div>
            
        </fieldset>

    </div>
    <br>
      <br>

</s:form>
