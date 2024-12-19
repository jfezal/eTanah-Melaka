<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>
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
  
    function addWakil(){

        var len = $('.nama').length;
        var param = '';

        for(var i=1; i<=len; i++){
            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + '&idPenerima=' + $('#chkbox'+i).val();
            }
        }
        if(param == ''){
            alert('Tiada Penerima.');
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/SuratKuasaWakil?simpanWakil'+param;

         $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
            },
            success : function(data){
                $('#page_div').html(data);
                self.close();
            }
        });
       
    }

   
</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle">
    <s:form name="form1" beanclass="etanah.view.stripes.nota.BetulSuratKuasaWakilActionBean">
        <s:errors/>
        <s:messages/>

        <div class="subtitle">
            <br>
            <fieldset class="aras1">
                <legend>
                    Maklumat Surat Kuasa Wakil
                </legend>
                <br>
                <p>
                    <label><font color="red">*</font>ID Surat Kuasa Wakil :</label>
                    <s:text name="idWakil" size="40"/>
                </p>
                <br>
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="searchWakil" value="Cari" class="btn" onmouseover="this.style.cursor='pointer';"/>

                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()" onmouseover="this.style.cursor='pointer';"/>
                </p>
                <br>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listWakilKuasaPenerima}" pagesize="10" cellpadding="0" cellspacing="0"
                                   requestURI="${pageContext.request.contextPath}/daftar/pembetulan/SuratKuasaWakil?searchWakil" id="line">
                        <display:column title="Pilih">
                            <s:checkbox name="chkbox" id="chkbox${line_rowNum}" value="${line.idPenerima}"/>
                        </display:column>
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column property="wakilKuasa.permohonan.idPermohonan" title="ID Perserahan"  class="nama"/>
                        <display:column property="nama" title="Nama"/>
                        <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan"/>
                        <display:column property="noPengenalan" title="No Pengenalan"/>
                    </display:table>
                </div>
                <br>
                 <p>
            <label>&nbsp;</label>
            <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addWakil();" onmouseover="this.style.cursor='pointer';"/>&nbsp;
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()" onmouseover="this.style.cursor='pointer';"/>
        </p>
            </fieldset>
        </div>
        <br>
       


    </s:form>
</div>