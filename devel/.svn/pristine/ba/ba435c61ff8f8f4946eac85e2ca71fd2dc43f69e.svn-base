<%-- 
    Document   : auditCapaianDokumen_1
    Created on : Oct 11, 2011, 2:57:48 PM
    Author     : amir.muhaimin
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    function validateForm(f) {

        if(f.elements['idkodDok'].value == '') {
            alert('Sila masukkan Kod Dokumen.');
            f.elements['idkodDok'].focus();
            return false;
        }
        else return true;
    }
    
    function chck(){
        var resit = document.getElementById('kodDok');
        var id = document.getElementById('keterangan');
        if ((resit.value == "")&&(id.value == "")){
            alert("Sila Masukkan Kod Dokumen atau Keterangan untuk membuat carian.");
            return false;
        }
        else if ((resit.value != "")&&(id.value != "")){
            alert("Sila Masukkan Kod Dokumen atau Keterangan untuk membuat carian.");
            return false;
        }
        else return true;
    }
    
    function pilihPenyerah1(){
        alert("Testing:::");
        return;
    }
    
    function memilihPenyerah(){
        var url;
        //        alert('aa');
        var e= $('#sizeKod').val();
        //        alert(e);
        var cnt=0;
        var data = new Array() ;
        for(cnt=0;cnt<e;cnt++)
        {   
            //                 var counter = cnt+1;
            //                 var radioId = 'pilihPenyerah'+counter;
            //                 alert(radioId);
            if(e==1){
                if(document.CarianKodDokumenForm.pilihPenyerah.checked){
                    opener.document.getElementById('idDok').value = document.CarianKodDokumenForm.pilihPenyerah.value;
                    //                            alert(document.CarianKodDokumenForm.pilihPenyerah.value);
                    self.close();
                }  
            }else{
                if(document.CarianKodDokumenForm.pilihPenyerah[cnt].checked){
                    opener.document.getElementById('idDok').value = document.CarianKodDokumenForm.pilihPenyerah[cnt].value;
                    //                            alert(document.CarianKodDokumenForm.pilihPenyerah[cnt].value);
                    self.close();
                }  
            }
                         
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.CarianKodDokumenActionBean" name="CarianKodDokumenForm" id="CarianKodDokumen">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Kod Dokumen</legend>
            <p>
            <font color="Red">Perhatian :</font> Sila masukkan salah satu ruangan kosong sahaja.
            </p>
            <p>
                <label>Kod Dokumen :</label>
                <s:text name="idkodDok" id="kodDok"/>
            </p>
            <p>
                <label>&nbsp;</label> atau
            </p>
            <p>
                <label>Keterangan :</label>
                <s:text name="keteranganDok" id="keterangan"/>
            </p>
            <p><label>Jumlah Paparan :</label>
                <s:select name = "paparan" value = "${actionBean.paparan}">
                    <%--<s:option></s:option>--%>
                    <s:option value="5">5</s:option>
                    <s:option value="10">10</s:option>
                    <s:option value="15">15</s:option>
                    <s:option value="20">20</s:option>
                    <s:option value="25">25</s:option>
                    <s:option value="30">30</s:option>
                    <s:option value="35">35</s:option>
                    <s:option value="40">40</s:option>
                    <s:option value="45">45</s:option>
                    <s:option value="50">50</s:option>
                    <s:option value="100">100</s:option>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <%--<s:submit name="search" value="Cari" class="btn" onclick="return chck(this.form)"/>--%>
                <s:submit name="search" value="Cari" class="btn"/>
                <s:button name="" value="Tutup" class="btn" onclick="self.close();"/>
            </p>
        </fieldset>
    </div>

    <c:if test="${actionBean.flag}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listKodDok}" id="row" >
                        <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                        <display:column title="Pilih"><s:radio name="__pilihPenyerah" class="penyerah" id="pilihPenyerah" value="${row.kod}"/></display:column>
                        <%--<display:column title="Pilih"><s:radio name="radio_" id="${row.kod}" value="${row.kod}"   onclick="javascript:selectRadio(this)"/></display:column>--%>
                        <display:column title="Kod Dokumen" property="kod"/>
                        <display:column title="Keterangan" property="nama"/>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.flag1}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listKodKeterangan}" id="row" pagesize="${actionBean.paparan}" requestURI="${pageContext.request.contextPath}/uam/carianKodDokumen">
                        <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                        <display:column title="Pilih"><s:radio name="__pilihPenyerah" class="penyerah" id="pilihPenyerah" value="${row.kod}"/></display:column>
                        <%--<display:column title="Pilih"><s:radio name="radio_" id="${row.kod}" value="${row.kod}"   onclick="javascript:selectRadio(this)"/></display:column>--%>
                        <display:column title="Kod Dokumen" property="kod"/>
                        <display:column title="Keterangan" property="nama"/>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.flag2}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listAll}" id="row" pagesize="${actionBean.paparan}" requestURI="${pageContext.request.contextPath}/uam/carianKodDokumen">
                        <display:column title="Bil." sortable="true" style="verticle-align:baseline"><div align="center">${row_rowNum}.</div></display:column>
                        <display:column title="Pilih"><s:radio name="__pilihPenyerah" class="penyerah" id="pilihPenyerah" value="${row.kod}"/></display:column>
                        <%--<display:column title="Pilih"><s:radio name="radio_" id="${row.kod}" value="${row.kod}"   onclick="javascript:selectRadio(this)"/></display:column>--%>
                        <display:column title="Kod Dokumen" property="kod"/>
                        <display:column title="Keterangan" property="nama"/>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${!simpan}">
        <p>
            <label>&nbsp;</label><s:button name="_pilih" value="Pilih" class="btn" onclick="memilihPenyerah();"/>
        </p>
    </c:if>
</s:form>