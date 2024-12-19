<%-- 
    Document   : RekodPembayaranCek_pop
    Created on : Oct 12, 2010, 4:57:39 PM
    Author     : Rajesh Reddy
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<script type="text/javascript">


$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
});

        
$(document).ready(function() {
    $("#close").click( function(){
        setTimeout(function(){
            self.close();
        }, 100);
    });
    });

  </script>
<STYLE>
.red {background:red}
.green {background:green}
.yellow {background:yellow}
.white {background:white}
</STYLE>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.pengambilan.RekodPembayaranCekPengambilanActionBean" >
        <s:messages/>
        <fieldset class="aras1">
            <legend> </legend>
            <display:table class="tablecloth" name="${actionBean.senaraiAmbilPampasan}" pagesize="5" cellpadding="0" cellspacing="0"
                           requestURI="/pengambilan/RekodPembayaranCekPengambilan" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column property="jumTerimaPampasan" title="Jumlah Nilai Cek(RM)"/>
                <display:column  title="Rekod Cek"  >
                    <s:hidden name="idPihak" value="${line.perbicaraanKehadiran.pihak.pihak.idPihak}"/>
                    <div align="center">
                        <c:if test="${line.jumTerimaPampasan > 0}">
                            <s:button name="a" class="green" disabled="true" value="    " />
                        </c:if>
                        <c:if test="${line.jumTerimaPampasan == 0}">
                            <s:button name="a" class="red" disabled="true" value="    " />
                        </c:if>
                    </div>
                </display:column>
                <display:column  title="Cek Diambil Tuan Tanah" >
                    <div align="center">
                        <s:checkbox name="dokDiambil[${line_rowNum-1}]" value="Y" id="dokDiambil[${line_rowNum-1}]"/>
                    </div>
                </display:column>
                <display:column  title="Tarikh Cek Diambil" >
                    <s:text name="tarikhDokDiambil[${line_rowNum-1}]" id="datepicker[${line_rowNum-1}]" class="datepicker" size="12" formatPattern="dd/MM/yyyy"/>
                </display:column>
            </display:table>
            <br/><br/>
            <div align="center">
                <s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                <s:submit name="simpan" value="Simpan" class="btn" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </div>
        </fieldset>
    </s:form>
</div>