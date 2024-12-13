<%-- 
    Document   : carianKodSekolah.jsp
    Created on : 27 September 2010, 1:41:17 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function pilih(){
        var len = $(".radio").length;
        for(var i=1; i<=len; i++){
            if($('#radio'+i).is(':checked')){
                var kodSekolah = $('#radio'+i).val();
                var url = "${pageContext.request.contextPath}/hasil/maklumat_tambah_SBM?simpanKodSekolahBantuan&kod="+kodSekolah;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    self.opener.refreshPageSekolah();
                    self.close();
                },'html');
            }
        }
    }
</script>
                
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.hasil.MaklumatTambahRemSBMActionBean" id="carianKodSekolahBantuan">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Kod Sekolah Bantuan</legend>
            <p>
                <label>Kod :</label>
                <s:text name="searchKod"  value="" size="25" maxlength="8" id="searchKod" class="searchKod"/><%-- onchange="this.value = this.value.toUpperCase();"/>--%>
            </p>
            <p>
                <label>Nama :</label>
                <s:text name="searchNama" value="" size="40" maxlength="60" id="searchNama" class="searchNama" onchange="this.value = this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="negeri" id="negeri" style="width:175px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                &nbsp;
            </p>
            <p align="center">
                <s:submit name="cari" value="Cari" class="btn"/>&nbsp;
                <s:button class="btn" name="reset" value="Isi Semula" onclick="clearText('carianKodSekolahBantuan');"/>&nbsp;
                <s:button name="" value="Tutup" class="btn" onclick="self.close();"/>
            </p>
        </fieldset>
    </div><br>
    <div class="subtitle displaytag">
        <fieldset class="aras1">
            <legend>Maklumat Kod Sekolah Bantuan</legend>
            <p align="center">
                <s:button name="" value="Pilih" class="btn" onclick="pilih();" /> &nbsp;
                <%--<s:button name="" value="Tutup" class="btn" onclick="self.close();"/>--%>
            </p>
            <center>
                <display:table name="${actionBean.senaraiRujukanList}" class="tablecloth" id="row" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/maklumat_tambah_SBM?cari">
                    <display:column><s:radio name="radioKod" id="radio${row_rowNum}" value="${row.kod}" class="radio" onmouseover="this.style.cursor='pointer';"/></display:column>
                    <display:column title="Bil.">${row_rowNum}</display:column>
                    <display:column title="Kod" property="kod"/>
                    <display:column title="Nama" property="nama"/>
                    <display:column title="Alamat" class="alamat">
                        ${row.alamat.alamat1}<c:if test="${row.alamat.alamat2 ne null}">,</c:if>
                        ${row.alamat.alamat2}<c:if test="${row.alamat.alamat3 ne null}">,</c:if>
                        ${row.alamat.alamat3}<c:if test="${row.alamat.alamat4 ne null}">,</c:if>
                        ${row.alamat.alamat4}<c:if test="${row.alamat.poskod ne null}">,</c:if>
                        ${row.alamat.poskod}<c:if test="${row.alamat.negeri ne null}">,</c:if>
                        ${row.alamat.negeri.nama}
                    </display:column>
                    <display:column title="No. Tel" class="">
                        ${row.noTel1}<c:if test="${row.noTel2 ne null}">,</c:if>
                        ${row.noTel2}
                    </display:column>
                    <display:column title="No. Fax" property="nofax"/>
                    <display:column title="Email" property="email"/>
                </display:table>
            </center>
        </fieldset>
    </div>
</s:form>
