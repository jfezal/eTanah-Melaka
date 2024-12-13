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
        var url = '${pageContext.request.contextPath}/penguatkuasaan/serahan_barang_rampasan?addBarangOperasi&idOp='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
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
        var url = '${pageContext.request.contextPath}/penguatkuasaan/serahan_barang_rampasan?refreshPageBarangOperasi';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function viewRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/serahan_barang_rampasan?editBarangOperasi&idBarangOperasi='+id+'&status=view';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
    
    function editRecord(id,idOp){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/serahan_barang_rampasan?editBarangOperasi&idBarangOperasi='+id+'&idOp='+idOp+'&status=edit';
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
            <legend>
                Maklumat Barangan Semasa Operasi
            </legend>
            <c:if test="${edit}">
                <div class="instr-fieldset"><br>
                    Klik butang tambah untuk masukkan maklumat barang operasi
                </div>
                <br>
                <div class="content" align="center">
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="10%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><u><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></u></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                    </td>
                                </tr>

                            </table>
                        </display:column>
                        <display:column title="Maklumat Barang Operasi">
                            <c:set value="1" var="count"/>
                            <c:if test="${fn:length(line.senaraiBarangOperasi) ne 0}">
                                <table width="100%" cellpadding="1">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="1%" align="center"><b>Nama Barang</b></th>
                                        <th  width="1%" align="center"><b>Model</b></th>
                                        <th  width="1%" align="center"><b>Kuantiti</b></th>
                                        <th  width="5%" align="center" colspan="2"><b>Tindakan</b></th>
                                    </tr>
                                    <c:forEach items="${line.senaraiBarangOperasi}" var="barang">
                                        <tr>
                                            <td width="5%">${count}</td>
                                            <td width="50%"><u><a class="popup" onclick="viewRecord(${barang.idBarangOperasi})">${barang.nama}</a></u></td>
                                            <td width="50%">${barang.model}</td>
                                            <td width="50%">
                                                <c:if test="${barang.kntt eq null}">Tiada data</c:if>
                                                <c:if test="${barang.kntt ne null}">${barang.kntt}</c:if>
                                            </td>
                                            <td width="5%">
                                                <div align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Kemasikini untuk Barang ${barang.nama}" onclick="editRecord('${barang.idBarangOperasi}','${line.idOperasi}')"/>
                                                </div>
                                            </td>
                                            <td width="5%">
                                                <div align="center">
                                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Hapus untuk Barang ${barang.idBarangOperasi}" onclick="deleteRecord('${barang.idBarangOperasi}');"/>
                                                </div>
                                            </td>


                                        </tr>

                                        <c:set value="${count+1}" var="count"/>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </display:column>
                        <display:column title="Tambah Barang">
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord('${line.idOperasi}');"/>
                        </display:column>



                    </display:table>
                    <br>

                </div>
            </c:if>

            <c:if test="${view}">
                <br>
                <div class="content" align="center">
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="10%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><u><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></u></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                    </td>
                                </tr>

                            </table>
                        </display:column>
                        <display:column title="Maklumat Barang Operasi">
                            <c:set value="1" var="count"/>
                            <c:if test="${fn:length(line.senaraiBarangOperasi) ne 0}">
                                <table width="100%" cellpadding="1">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="1%" align="center"><b>Nama Barang</b></th>
                                        <th  width="1%" align="center"><b>Model</b></th>
                                        <th  width="1%" align="center"><b>Kuantiti</b></th>
                                    </tr>
                                    <c:forEach items="${line.senaraiBarangOperasi}" var="barang">
                                        <tr>
                                            <td width="5%">${count}</td>
                                            <td width="50%"><u><a class="popup" onclick="viewRecord(${barang.idBarangOperasi})">${barang.nama}</a></u></td>
                                            <td width="50%">${barang.model}</td>
                                            <td width="50%">
                                                <c:if test="${barang.kntt eq null}">Tiada data</c:if>
                                                <c:if test="${barang.kntt ne null}">${barang.kntt}</c:if>
                                            </td>
                                        </tr>

                                        <c:set value="${count+1}" var="count"/>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </display:column>

                    </display:table>
                    <br>
                </div>
            </c:if>


        </fieldset>
    </div>
</s:form>

