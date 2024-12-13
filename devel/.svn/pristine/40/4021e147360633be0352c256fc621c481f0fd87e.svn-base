<%-- 
    Document   : baiki_hakmilik_akaun
    Created on : May 8, 2016, 11:28:48 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Baiki Hakmilik dan Akaun</title>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>     
        <script type="text/javascript">
    function clearForm(f) {
        $(f).clearForm();
    }
    
    


 function doEdit(idHakmilik){
        window.open("${pageContext.request.contextPath}/utiliti/baiki_hakmilik_akaun?kemaskiniHakmilik&idHakmilik="+idHakmilik, "eTanah",
        "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=900,height=350");
        
    }
    
    
  function doEditAkaun(noAkaun){
        window.open("${pageContext.request.contextPath}/utiliti/baiki_hakmilik_akaun?kemaskiniAkaun&noAkaun="+noAkaun, "eTanah",
        "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=900,height=350");
        
    }
    
  
            
            
            function reload(v)
    {
      url = "${pageContext.request.contextPath}/utiliti/baiki_hakmilik_akaun?reload&idHakmilik=" + v;
      $.post(url,
              function(data) {
                $('#page_div').html(data);
              }, 'html');

    }
            
    
        </script>    
    </head>
    <body>
 
        <s:errors/>
        <s:messages/>        
        <div class="subtitle">
            <s:form beanclass="etanah.view.utility.KemaskiniHakmilikAkaun" name="form1"> 
                <%--  <s:hidden name="idHakmilik" />--%>
                <fieldset class="aras1">
                    <p>
                        <label>Id Hakmilik :</label>
                        <s:text name="idHakmilik" id="idHakmilik" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cari" value="Cari" class="btn" />
                        <s:button class="btn" name="reset" value="Isi Semula" onclick="clearForm(this.form);"/>                   
                        
                    </p>
                    <br>  
                    <c:if test="${actionBean.showDetailHakmilik}">
                        <p>
                        <div class="subtitle">    
                            <fieldset class="aras1"> 
                                <legend> Maklumat Hakmilik </legend>  
                                <center><display:table style="width:30%" class="tablecloth" name="${actionBean.hakmilik}" id="line">
                                        <display:column title="Id Hakmilik" property="idHakmilik" />       
                                        <display:column title="Status" property="kodStatusHakmilik.nama" />    
                                        <display:column title="Cukai Sebenar" property="cukaiSebenar" />
                                        <display:column title="Tarikh Batal" format="{0,date,dd/MM/yyyy}" property="tarikhBatal" />
                                        <display:column title="Tarikh Daftar Asal" format="{0,date,dd/MM/yyyy}" property="tarikhDaftarAsal" />
                                        <display:column title="No versi DHDE" property="noVersiDhde" />
                                        <display:column title="No versi DHKE" property="noVersiDhke" />
                                        <display:column title="ID Dokumen DHDE" property="dhde.idDokumen" />
                                        <display:column title="ID Dokumen DHKE" property="dhke.idDokumen" />
                                        <display:column title="Kemaskini">
                                            <p align="center"><img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="doEdit('${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';">
                                            </p>
                                        </display:column>
                                    </display:table>
                            </fieldset>
                        </div>
                        </p>
                        <br>                        
                        <p>
                        <div class="subtitle">    
                            <fieldset class="aras1">
                                <legend> Maklumat Akaun </legend>  
                                <center><display:table style="width:30%" class="tablecloth" name="${actionBean.listAkaun}" excludedParams="popup" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/utiliti/baiki_hakmilik_akaun" id="line">
                                        <display:column title="No akaun" property="noAkaun" />       
                                        <display:column title="Status" property="status.nama" />    
                                        <display:column title="Kemaskini">
                                            <p align="center"><img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="doEditAkaun('${line.noAkaun}');"  onmouseover="this.style.cursor = 'pointer';">
                                            </p>
                                        </display:column>
                                    </display:table>
                            </fieldset>
                        </div>
                        </p>
                        <br>
                        <p>
                        <label>&nbsp;</label>
                        <s:submit name="reload" value="Klik Untuk Refresh" class="longbtn" onmouseover='this.style.cursor="pointer";' onclick="reload('${actionBean.hakmilik.idHakmilik}')"/>
                        </p>
                    </c:if>
                </fieldset>
            </s:form>
        </div>
    </body>
</html>