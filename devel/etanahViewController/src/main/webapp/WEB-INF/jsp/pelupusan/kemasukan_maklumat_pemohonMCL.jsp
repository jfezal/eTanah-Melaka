
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
    function addPemohon(){
        var len = $('.nama').length;
        <%--var a = document.insert.test ;--%>
                <%--var ckd = $('#chkbox'+i).is(":checked");--%>
            <%--var a = document.insert.test.value ;--%>
        for(var i=1; i<=len; i++){
            
            if($('#radbox'+i).is(":checked")){              
                    var url = '${pageContext.request.contextPath}/pelupusan/pemohonMCL?simpanPemohonPopup&idPihak='+ $('#radbox'+i).val();
                $.get(url,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                });
            }
         <%--  else
            {
             alert("Sila pilih pemohon");
             break ;
              
            }--%>
        }
    }

</script>
<%--<style type="text/css">
    input.error { background-color: yellow; }
</style>--%>


    <s:form beanclass="etanah.view.stripes.pelupusan.CatitTanahMCL_ActionBean" name="insert">
        <fieldset class="aras1">
            <legend>Senarai Tuan Tanah</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPihakList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/pemohonMCL">
                   
                        <display:column title="Pilih">
                            <s:radio name="radiobox" id="radbox${line_rowNum}" value="${line.pihak.idPihak}"/>
                            
                            <s:hidden name="test" id="test1" value="${line.pihak.idPihak}"/>
                        </display:column>
                        
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Nama" class="nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column property="pihak.bangsa.nama" title="Bangsa"/>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                    <display:column title="Tarikh Pemilikan Tanah">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                    </display:column>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                </display:table>

            </div>
                 
                    <p>
                        <label>&nbsp;</label>
                        <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addPemohon();"/>&nbsp;
                    </p>              
            
        </fieldset>
    </s:form>