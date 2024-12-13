
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
    <%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
    <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Kemasukan Terperinci Hakmilik</title>
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
    <script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Pertanyaan SB/SW/SA</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
    </head>
    <body>
        

    <stripes:form action="/daftar/pertanyaanSBSWSA">  
        <fieldset class="aras1">            
            <legend>Maklumat Surat Kebenaran</legend>
            <br>
            <table width="95%">
                <tr>
                   <th><legend><center><top><font color="black" size="2">Pemberi</font></legend></th>
                   <th><legend><center><font color="black" size="2">Penerima</font></legend></th>
                </tr>                
                <tr>
                    <td width="50%" valign="top">
                        <c:choose> 
                        <c:when test="${fn:length(actionBean.pemberiSB) > 0}">             
                            <display:table class="tablecloth" name="${actionBean.pemberiSB}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column property="wakilKuasa.idWakil" title="No Surat Kebenaran"  class="nama"/>
                                <display:column property="nama" title="Nama"  class="nama"/>
                            </display:table>                
                        </c:when>
                        <c:otherwise>             
                                <legend><center><top><font color="black" size="2">Tiada Rekod</font></legend>
                        </c:otherwise>
                        </c:choose>                     
                    </td>
                    <td width="50%" valign="top">
                        <c:choose> 
                        <c:when test="${fn:length(actionBean.penerimaSB) > 0}">         
                            <display:table class="tablecloth" name="${actionBean.penerimaSB}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}
                                </display:column>
                                <display:column property="wakilKuasa.idWakil" title="No Surat Kebenaran"  class="nama"/>
                                <display:column property="nama" title="Nama"  class="nama"/>
                            </display:table>                
                        </c:when>
                        <c:otherwise>             
                                <legend><center><top><font color="black" size="2">Tiada Rekod</font></legend>
                        </c:otherwise>
                        </c:choose>                     
                    </td>
                </tr>
              </table>      
        </fieldset>
        
        <br>
        <fieldset class="aras1">            
            <legend>Maklumat Surat Wakil Kuasa</legend>
            <br>
            <table width="95%">
                <tr>
                   <th><legend><center><top><font color="black" size="2">Pemberi</font></legend></th>
                   <th><legend><center><font color="black" size="2">Penerima</font></legend></th>
                </tr>                
                <tr>
                    <td width="50%" valign="top">
                        <c:choose> 
                        <c:when test="${fn:length(actionBean.pemberiSW) > 0}">             
                            <display:table class="tablecloth" name="${actionBean.pemberiSW}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column property="wakilKuasa.idWakil" title="No Surat Wakil Kuasa"  class="nama"/>
                                <display:column property="nama" title="Nama"  class="nama"/>
                            </display:table>                
                        </c:when>
                        <c:otherwise>             
                                <legend><center><top><font color="black" size="2">Tiada Rekod</font></legend>
                        </c:otherwise>
                        </c:choose> 
                    </td>
                    <td width="50%" valign="top">
                        <c:choose> 
                        <c:when test="${fn:length(actionBean.penerimaSW) > 0}">         
                            <display:table class="tablecloth" name="${actionBean.penerimaSW}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}
                                </display:column>
                                <display:column property="wakilKuasa.idWakil" title="No Surat Wakil Kuasa"  class="nama"/>
                                <display:column property="nama" title="Nama"  class="nama"/>
                            </display:table>                
                        </c:when> 
                        <c:otherwise>             
                                <legend><center><top><font color="black" size="2">Tiada Rekod</font></legend>
                        </c:otherwise>
                        </c:choose>                     
                    </td>
                </tr>
              </table>      
        </fieldset>
        
        <br>
        <fieldset class="aras1">            
            <legend>Maklumat Surat Amanah</legend>
            <br>
            <table width="95%">
                <tr>
                   <th><legend><center><top><font color="black" size="2">Pemberi</font></legend></th>
                   <th><legend><center><font color="black" size="2">Penerima</font></legend></th>
                </tr>                
                <tr>
                    <td width="50%" valign="top">
                        <c:choose> 
                        <c:when test="${fn:length(actionBean.pemberiSA) > 0}">             
                            <display:table class="tablecloth" name="${actionBean.pemberiSA}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column property="wakilKuasa.idWakil" title="No Surat Amanah"  class="nama"/>
                                <display:column property="nama" title="Nama"  class="nama"/>
                            </display:table>                
                        </c:when>
                        <c:otherwise>             
                                <legend><center><top><font color="black" size="2">Tiada Rekod</font></legend>
                        </c:otherwise>
                        </c:choose>                     
                    </td>
                    <td width="50%" valign="top">
                        <c:choose> 
                        <c:when test="${fn:length(actionBean.penerimaSA) > 0}">         
                            <display:table class="tablecloth" name="${actionBean.penerimaSA}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}
                                </display:column>
                                <display:column property="wakilKuasa.idWakil" title="No Surat Amanah"  class="nama"/>
                                <display:column property="nama" title="Nama"  class="nama"/>
                            </display:table>                
                        </c:when>
                        <c:otherwise>             
                                <legend><center><top><font color="black" size="2">Tiada Rekod</font></legend>
                        </c:otherwise>
                        </c:choose>                     
                    </td>
                </tr>
              </table>  
              <br>
              <br>
              <p>
              <label>&nbsp;</label>
              <stripes:submit name="back" value="Kembali" class="longbtn"/>
              </p>
        </fieldset>     
      </stripes:form>
    </body>
</html>
