<%-- 
    Document   : senarai_urusan_gadaian
    Created on : Dec 16, 2009, 3:34:25 PM
    Author     : farah.shafilla
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<!--s:form  beanclass="etanah.view.stripes.common.UrusanGadaianActionBean"-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function remove(val){
            var url = '${pageContext.request.contextPath}/daftar/urusan_gadaian?delete&id='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
</script>

<s:form beanclass="etanah.view.daftar.UrusanGadaian">
<br>

<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Sila Pilih Keutamaan Urusan Penangguhan
        </legend>
      <div class="content" align="center">
 <display:table class="tablecloth" name="${actionBean.hakmilikUrusanList}" pagesize="10" cellpadding="0" cellspacing="0"
                              requestURI="${pageContext.request.contextPath}/common/pihak" id="line">
                     <display:column title="Pilih">
                       <s:checkbox name="chkbox" id="chkbox${line_rowNum}" value="${line.idUrusan}" class="checkbox"/></display:column>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idPerserahan" title="ID Perserahan"/>
                    <display:column property="kodUrusan.kod" title="Urusan"/>
                    <display:column property="kodUrusan.nama" title="Jenis Perserahan"/>
                    
    </display:table>
     <c:if test="${fn:length(actionBean.hakmilikUrusanList) > 0}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="saveMelepasGadaian" id="add" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
  </div>
    </fieldset>
     <fieldset class="aras1">
            <legend>
                Senarai Permohonan Tangguh Gadaian
            </legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiMau}" pagesize="10" cellpadding="0" cellspacing="0"
                              requestURI="${pageContext.request.contextPath}/common/pihak" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="permohonan.idPermohonan" title="ID Perserahan"/>
                    <display:column property="urusan.kodUrusan.kod" title="Urusan"/>
                    <display:column property="urusan.kodUrusan.nama" title="Jenis Perserahan"/>                    
                    <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="remove('${line.idAtasUrusan}')">
                            </div>
                        </display:column>
                </display:table>
            </div>
        </fieldset>
</div>
   
     
</s:form>