<%-- 
    Document   : carian
    Created on : Apr 14, 2010, 2:45:55 PM
    Author     : abdul.hakim
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<div align="center">
     <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Cetakan Semula (Carian Persendirian Atau Carian Rasmi Sahaja)</font>
            </div></td></tr>
        </table>
</div>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.stripes.hasil.CarianActionBean" id="carian">
<stripes:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
                <p>
                    <label>Nombor Resit : </label>
                    <stripes:text name="dokumenKewangan.idDokumenKewangan" size="30" id="resit" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>ID Perserahan/ ID Permohonan : </label>
                    <stripes:text name="permohonan.idPermohonan" size="30" id="idPermohonan" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="search" value="Cari" class="btn" onclick="return chck()" id="nxt"/>
                    <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('carian');"/>
                </p>
            <br>
        </fieldset>
    </div>
    <p></p>
    <c:if test="${actionBean.flag}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilik}" id="row">
                        <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                        <display:column title="Tarikh Carian" property="permohonanCarian.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss aa}"/>
                        <display:column title="Jenis Carian" property="permohonanCarian.urusan.kodPerserahan.nama"/>
                        <display:column title="KewDOK" property="permohonanCarian.resit.idDokumenKewangan"/>
                        <display:column title="ID Hakmilik" property="idHakmilik" style="width:250px"/>
                        <%--<display:column title="Cetak Sijil Carian">
                            <stripes:button name="" value="Cetak" class="btn" onclick="catakSijil(this.form, '${row.idHakmilik}')" id="ctkSijil"/>
                        </display:column>--%>
                        <display:column title="Cetak Resit">
                            <stripes:button name="" value="Cetak" class="btn" onclick="cetakResit(this.form, '${row.permohonanCarian.resit.idDokumenKewangan}')" id="ctkResit"/>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
</stripes:form>

<script type="text/javascript">
    function chck(){
        var resit = document.getElementById('resit');
        var id = document.getElementById('idPermohonan');
        if ((resit.value == "")&&(id.value == "")){
            alert("Sila Masukkan Nombor Resit atau ID Perserahan/ ID Permohonan untuk membuat carian.");
            return false;
        }
    }

    function catakSijil(f, id){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/carian?cetakSijil&"+queryString+"&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,scrollbars=1,resizable=1,location=0,menubar=0,width=900,height=600");
    }

    function cetakResit(f,id){
        alert(id);
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/carian?cetakReceipt&"+queryString+"&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,scrollbars=1,resizable=1,location=0,menubar=0,width=900,height=600");
    }
</script>