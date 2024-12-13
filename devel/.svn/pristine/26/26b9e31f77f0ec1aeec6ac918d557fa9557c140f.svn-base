<%-- 
    Document   : maklumat_barang_operasi
    Created on : Sept 18, 2012, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<style type="text/css">

    .tablecloth{
        padding:0px;
        width:90%;
    }

    .infoLP{
        float: right;
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;

    }
</style>


<script type="text/javascript">

    function addRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/pembeli_barang_rampasan?popupTambahBarang';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=900,scrollbars=yes");
    }

    function deleteRecord(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/serahan_barang_rampasan?deleteBarangOperasi&idBarangOperasi='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
           
        }
    }
    
    function refreshPageBarangOperasi(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/pembeli_barang_rampasan?refreshPage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function viewRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/serahan_barang_rampasan?editBarangOperasi&idBarangOperasi='+id+'&status=view';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
    
    function editRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/pembeli_barang_rampasan?popupEditBarang&id='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }

    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }




</script>
<s:form beanclass="etanah.view.penguatkuasaan.SerahanBarangRampasanActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${edit}">
                <legend>
                    Maklumat Pembeli Barang Rampasan
                </legend>
                <div class="instr-fieldset"><br>
                    Klik butang tambah untuk masukkan pembeli barang rampasan
                </div>
                <br>

                <div class="content" align="center">


                    <br>

                    <display:table name="${actionBean.senaraiPembeli}" id="k" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${k_rowNum}</display:column>
                        <display:column title="Nama">
                            ${k.nama}
                        </display:column>
                        <display:column title="No.Pengenalan">
                            ${k.noPengenalan}
                        </display:column>
                        <display:column title="Barang Dijual">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.listOp}" var="l">
                                <c:forEach items="${l.senaraiBarangRampasan}" var="b">
                                    <c:forEach items="${actionBean.senaraiJualanBarang}" var="j">
                                        <c:if test="${j.pemohon.idPemohon eq k.idPemohon}">
                                            <c:if test="${b.idBarang eq j.rampasan.idBarang}">
                                                ${count}) ${j.rampasan.item} <br>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                </c:forEach>
                            </c:forEach>
                        </display:column>
                        <display:column title="Amaun Dijual (RM)">
                            <c:set value="1" var="count"/>
                            <c:set value="0.00" var="countTotal"/>
                            <c:forEach items="${actionBean.listOp}" var="l">
                                <c:forEach items="${l.senaraiBarangRampasan}" var="b">
                                    <c:forEach items="${actionBean.senaraiJualanBarang}" var="j">
                                        <c:if test="${j.pemohon.idPemohon eq k.idPemohon}">
                                            <c:if test="${b.idBarang eq j.rampasan.idBarang}">
                                                ${count}) <fmt:formatNumber pattern="#,##0.00" value="${j.amaun}"/><br>
                                                <c:set value="${countTotal+j.amaun}" var="countTotal"/>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                </c:forEach>
                            </c:forEach>
                            <b>Jumlah (RM): ${countTotal}</b>
                        </display:column>
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editRecord('${k.idPemohon}')"/>
                            </div>
                        </display:column>
                    </display:table>
                    <br>        
                    <c:set value="0" var="countAvailable"/>
                    <c:forEach items="${actionBean.listOp}" var="l">
                        <c:forEach items="${l.senaraiBarangRampasan}" var="b">
                            <c:if test="${b.statusJual != 'Y'}">
                                <c:set value="${countAvailable+1}" var="countAvailable"/>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord();"/>

                </div>
            </c:if>

            <c:if test="${view}">
                <br>
                <div class="content" align="center">

                    <br>
                </div>
            </c:if>

            <c:if test="${viewBayaran}">
                <br>
                <legend>
                    Maklumat Pembeli Barang Rampasan
                </legend>
                <div class="content" align="center">

                    <display:table name="${actionBean.senaraiPembeli}" id="k" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${k_rowNum}</display:column>
                        <display:column title="Nama">
                            ${k.nama}<br>
                            <c:if test="${k.noPengenalan ne null}">
                                (${k.noPengenalan})
                            </c:if>
                        </display:column>
                        <display:column title="Barang Dijual">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.listOp}" var="l">
                                <c:forEach items="${l.senaraiBarangRampasan}" var="b">
                                    <c:forEach items="${actionBean.senaraiJualanBarang}" var="j">
                                        <c:if test="${j.pemohon.idPemohon eq k.idPemohon}">
                                            <c:if test="${b.idBarang eq j.rampasan.idBarang}">
                                                ${count}) ${j.rampasan.item} <br>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                </c:forEach>
                            </c:forEach>
                        </display:column>
                        <display:column title="Amaun Dijual (RM)">
                            <c:set value="1" var="count"/>
                            <c:set value="0.00" var="countTotal"/>
                            <c:forEach items="${actionBean.listOp}" var="l">
                                <c:forEach items="${l.senaraiBarangRampasan}" var="b">
                                    <c:forEach items="${actionBean.senaraiJualanBarang}" var="j">
                                        <c:if test="${j.pemohon.idPemohon eq k.idPemohon}">
                                            <c:if test="${b.idBarang eq j.rampasan.idBarang}">
                                                ${count}) <fmt:formatNumber pattern="#,##0.00" value="${j.amaun}"/><br>
                                                <c:set value="${countTotal+j.amaun}" var="countTotal"/>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                </c:forEach>
                            </c:forEach>
                        </display:column>
                        <display:column title="Status Bayaran">
                            <c:set value="1" var="countBarang"/>
                            <c:forEach items="${actionBean.senaraiJualanBarang}" var="p">
                                <c:if test="${p.pemohon.idPemohon eq k.idPemohon}">
                                    <c:if test="${p.idDokumenKewangan eq null}">
                                        ${countBarang}) <font color="red">Belum Dibayar</font>
                                        <c:set value="${countBarang+1}" var="countBarang"/><br>
                                    </c:if>
                                    <c:if test="${p.idDokumenKewangan ne null}">
                                        ${countBarang}) Sudah Dibayar
                                        <c:set value="${countBarang+1}" var="countBarang"/><br>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="No.Resit">
                            <c:set value="1" var="countBarang"/>
                            <c:forEach items="${actionBean.senaraiJualanBarang}" var="p">
                                <c:if test="${p.pemohon.idPemohon eq k.idPemohon}">
                                    <c:if test="${p.idDokumenKewangan ne null}">
                                        ${countBarang}) ${p.idDokumenKewangan}<br>
                                        <c:set value="${countBarang+1}" var="countBarang"/>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Tarikh Bayaran">
                            <c:set value="1" var="countBarang"/>
                            <c:forEach items="${actionBean.senaraiJualanBarang}" var="p">
                                <c:if test="${p.pemohon.idPemohon eq k.idPemohon}">
                                    <c:if test="${p.idDokumenKewangan ne null}">
                                        ${countBarang}) <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aaa" value="${p.tarikhBayaran}"/><br>
                                        <c:set value="${countBarang+1}" var="countBarang"/>
                                    </c:if>
                                </c:if>
                            </c:forEach>

                        </display:column>
                    </display:table>


                </div>
            </c:if>


        </fieldset>
    </div>
</s:form>

