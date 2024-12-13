<%-- 
    Document   : UtilitiPilihPeguam
    Created on : May 9, 2011, 3:02:00 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.text.*,java.util.*" session="true"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>

<script type="text/javascript">
    function addNew(v){
        window.open("${pageContext.request.contextPath}/lelong/peguam?tukarPeguam&idPermohonan=" + v, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
	
	function clearForm() {
                
        $("#idPermohonan").val('');
    }
	
	function doSearch() {
        var a = $('#idPermohonan').val();
    
        if(a == ''){
            alert('Sila masukan id Permohonan');            
            return false;
        }
        //f.action = f.action + '?' + e;
        //f.submit();
    }
	
	function refreshRekod(idMohon){
        var url = '${pageContext.request.contextPath}/lelong/peguam?find2&idPermohonan='+idMohon;
        $.get(url,
        function(data){
            $('#peguam_page').html(data);
        }, 'html');
    }
	
</script>
<div id="peguam_page">
<s:form beanclass="etanah.view.stripes.lelong.UtilitiPilihPeguamActionBean" >
    <s:messages />
    <s:errors />
    <s:hidden name="idPermohonan" value="${idPermohonan}"/>
    <s:hidden name="idPenyerah" value="${idPenyerah}"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <p>
                <label> ID Permohonan :</label>
                <s:text name="idPermohonan" id="idPermohonan" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p align="right">
                <s:submit name="find" value="Cari" class="btn" onclick=" return doSearch();"/>
                <s:submit name="reset" value="Isi Semula" class="btn" onclick="clearForm();"/>&nbsp;
            </p>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Peguam Asal
            </legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiPermohonan}"
                               pagesize="10" cellpadding="0" cellspacing="0" requestURI=""
                               id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idPermohonan" title="ID Permohonan"/>
                    <display:column property="penyerahNama" title="Nama"/>
                    <display:column title="Alamat" class="alamat">
                        <font style="text-transform: uppercase">${line.penyerahAlamat1}</font> <c:if test="${line.penyerahAlamat2 ne null}">,</c:if>
                        <font style="text-transform: uppercase">${line.penyerahAlamat2}</font> <c:if test="${line.penyerahAlamat3 ne null}">,</c:if>
                        <font style="text-transform: uppercase">${line.penyerahAlamat3}</font> <c:if test="${line.penyerahAlamat4 ne null}">,</c:if>
                        <font style="text-transform: uppercase">${line.penyerahAlamat4}</font> <c:if test="${line.penyerahPoskod ne null}">,</c:if>
                        <font style="text-transform: uppercase">${line.penyerahPoskod}</font> <c:if test="${line.penyerahNegeri.kod ne null}">,</c:if>
                        <font style="text-transform: uppercase">${line.penyerahNegeri.nama}</font>
                    </display:column>
                    <display:column property="penyerahNoTelefon1" title="No. Telefon"/>
                    <display:column property="penyerahEmail" title="Email"/>
					<display:column property="infoAudit.tarikhMasuk" title="Tarikh Masuk"format="{0,date,dd/MM/yyyy}"/>
                </display:table>
            </div>
        </fieldset>
        <c:if test="${actionBean.peguamList eq null}">                                
            <p align="right">
                <s:button name="tukarPeguam" value="Tukar Peguam" class="btn" onclick="addNew('${actionBean.permohonan.idPermohonan}');"/>
            </p>
        </c:if>
            
        <c:if test="${actionBean.peguamList ne null}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Peguam Baru 
                    </legend>

                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.peguamList}"
                                       pagesize="10" cellpadding="0" cellspacing="0" requestURI=""
                                       id="line">
                            
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column property="idPermohonan.idPermohonan" title="ID Permohonan"/>
                            <display:column property="peguam.nama" title="Nama"/>
                            <display:column title="Alamat" class="alamat">
                                <font style="text-transform: uppercase">${line.peguam.alamat1}</font> <c:if test="${line.peguam.alamat2 ne null}">,</c:if>
                                <font style="text-transform: uppercase">${line.peguam.alamat2}</font> <c:if test="${line.peguam.alamat3 ne null}">,</c:if>
                                <font style="text-transform: uppercase">${line.peguam.alamat3}</font> <c:if test="${line.peguam.alamat4 ne null}">,</c:if>
                                <font style="text-transform: uppercase">${line.peguam.alamat4}</font> <c:if test="${line.peguam.poskod ne null}">,</c:if>
                                <font style="text-transform: uppercase">${line.peguam.poskod}</font> <c:if test="${line.peguam.negeri.kod ne null}">,</c:if>
                                <font style="text-transform: uppercase">${line.peguam.negeri.nama}</font>
                            </display:column>
                            <display:column property="peguam.noTelefon1" title="No. Telefon Bimbit"/>
                            <display:column property="peguam.noTelefon2" title="No. Telefon Pejabat"/>
							<display:column property="infoAudit.tarikhMasuk" title="Tarikh Masuk"format="{0,date,dd/MM/yyyy}"/>
							<display:column title="Status">
								<c:if test="${line.aktif eq 'Y'}">Aktif</c:if><c:if test="${line.aktif eq 'T'}">Tidak Aktif</c:if>
                            </display:column>
                        </display:table>
                    </div>
                </fieldset>
		<c:if test="${actionBean.peguamList ne null}">                                
            <p align="right">
                <s:button name="tukarPeguam" value="Tukar Peguam" class="btn" onclick="addNew('${actionBean.permohonan.idPermohonan}');"/>
            </p>
        </c:if>
            </div>
        </c:if>

    </s:form>
	</div>