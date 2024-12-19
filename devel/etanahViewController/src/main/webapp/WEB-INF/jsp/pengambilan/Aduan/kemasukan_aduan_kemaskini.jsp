<%--
    Document   : kemasukan_aduan_details
    Created on : May 28, 2013, 6:18:22 PM
    Author     : Admin
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">

    $(document).ready(function() {

        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});


        }); //END OF READY FUNCTION



        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
  function validation() {
     var count1=$("#rowCount1").val();
    for(var i=1;i<=count1;i++){
        var kandungan1= $("#kandungan1"+i).val();
        if(kandungan1 == ""){
            alert('Sila pilih " 2.1 Latar Belakang " terlebih dahulu.');
            $("#kandungan1"+i).focus();
            return false;
        }
    }

    var count2=$("#rowCount2").val();
    for(var i=1;i<=count2;i++){
        var kandungan2= $("#kandungan2"+i).val();
        if(kandungan2 == ""){
            alert('Sila pilih " 3. Perihal Tanah " terlebih dahulu.');
            $("#kandungan2"+i).focus();
            return false;
        }
    }

    <%--var count3=$("#rowCount3").val();
    for(var i=1;i<=count3;i++){
        var kandungan3= $("#kandungan3"+i).val();
        if(kandungan3 == ""){
            alert('Sila pilih " 4. PANDANGAN PENTADBIR TANAH " terlebih dahulu.');
            $("#kandungan3"+i).focus();
            return false;
        }
    }--%>

    var count4=$("#rowCount4").val();
    for(var i=1;i<=count4;i++){
        var kandungan4= $("#kandungan4"+i).val();
        if(kandungan4 == ""){
            alert('Sila pilih " 4. PERAKUAN PENTADBIR TANAH " terlebih dahulu.');
            $("#kandungan4"+i).focus();
            return false;
        }
    }

    <%--var count5=$("#rowCount5").val();
    for(var i=1;i<=count5;i++){
        var kandungan5= $("#kandungan5"+i).val();
        if(kandungan5 == ""){
            alert('Sila pilih " 5. PERAKUAN PENGARAH TANAH DAN GALIAN " terlebih dahulu.');
            $("#kandungan5"+i).focus();
            return false;
        }
    }--%>

    if($("#kertasBil").val() == ""){
            $("#kertasBil").val("Tiada Data");
    }

    if($("#masa").val() == ""){
            $("#masa").val("Tiada Data");
    }

    if($("#tarikhmesyuarat").val() == ""){
            $("#tarikhmesyuarat").val("Tiada Data");
    }

    if($("#tempat").val() == ""){
            $("#tempat").val("Tiada Data");
    }

    if($("#syorPtg").val() == ""){
            $("#syorPtg").val("Tiada Data");
    }

    if($("#jam").val() == ""){
    alert('Sila pilih " JAM " terlebih dahulu.');
    $("#jam").focus();
    return false;
    }
    if($("#minit").val() == ""){
        alert('Sila pilih " MINIT " terlebih dahulu.');
        $("#minit").focus();
        return false;
    }
    if($("#pagiPetang").val() == ""){
        alert('Sila pilih " PAGI/PETANG " terlebih dahulu.');
        $("#pagiPetang").focus();
        return false;
    }
    return true;
}

</script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pengambilan.AduanEditActionBean" name="form">
        <s:errors/>
        <s:messages/>
       <div class="subtitle">
         <fieldset class="aras1">
            <%--<c:if test="${edit}">--%>
            <legend>Maklumat Permohonan</legend>
                    <div class="subtitle" id="main">
                    <div class="content" align="center">
                        <%--<s:hidden name="idPihak" id="idPihak" value="pihak.idPihak"/>--%>
                        <%--${actionBean.pihak.idPihak}--%>
                        <table class="tablecloth" border="0" width="75%">
                            <tr>
                                <td>
                                    No. Perserahan/Permohonan :
                                </td>
                                <td>
                                    ${actionBean.permohonan.idPermohonan}
                                </td>
                            </tr>
                            <tr>
                                <td>Nama Permohonan :</td>
                                <td>
                                    <font>${actionBean.permohonan.kodUrusan.nama}</font>
                                </td>
                            </tr>
                            <tr>
                                <td>Tarikh Permohonan :</td>
                                <td>
                                    <fmt:formatDate value="${actionBean.hakmilikPermohonan.permohonan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Pengadu :</td>
                                <td>
                                    ${actionBean.pmohon.pihak.nama}
                                    <%--<fmt:formatDate value="" pattern="dd/MM/yyyy"/>--%>
                                </td>
                            </tr>
                        </table>

                    <br/>
                </div>
               </div>
        <br/>
        </fieldset>
         <br>
          <fieldset class="aras1">
            <legend>Maklumat Aduan keatas Permohonan :</legend>
                    <div class="subtitle" id="main">
                    <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th width="10%">Bil.</th>
                            <th width="10%">No. Permohonan/Perserahan</th>
                            <th width="10%">Status</th>
                            <th width="40%">Projek</th>
                            <th width="40%">Tarikh Bermula </th>
                        </tr>
                        <tr>
                            <td>
                                <b>1.</b>
                            </td>
                            <td>
                                ${actionBean.pSebelum.idPermohonan}
                            </td>
                            <td>
                                <c:if test="${actionBean.pSebelum.status eq 'AK'}">Aktif</c:if>
                                <c:if test="${actionBean.pSebelum.status eq 'SL'}">Selesai</c:if>
                                <c:if test="${actionBean.pSebelum.status eq 'TK'}">Tidak Aktif</c:if>
                                <c:if test="${actionBean.pSebelum.status eq 'BP'}">Batal</c:if>

                            </td>
                            <td> <font style="text-transform: uppercase"> ${actionBean.pSebelum.kodUrusan.nama}-${actionBean.pSebelum.sebab}</font></td>
                            <td><fmt:formatDate value="${actionBean.pSebelum.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/></td>
                        </tr>
                    </table>
                    </div>
                        <br>
                        Maklumat Tanah Pengadu
                        <div class="content" align="center">
                     <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanSblmList}" pagesize="20" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumat_hakmilikpengambilan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                        <display:column title="Luas">
                            <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                            <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                            <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;
                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                        </display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Luas Diambil">
                                    <c:if test="${line.luasTerlibat eq null}"><s:text name="luasTerlibat[${line_rowNum - 1}]" disabled="true" formatPattern="#,##0.0000" value="${line.luasTerlibat}"/>&nbsp;
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                    </c:if>
                                    <c:if test="${line.luasTerlibat ne null}">
                                        <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" disabled="true" formatPattern="#,##0.0000" value="${line.luasTerlibat}"/>&nbsp;
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                    </c:if>
                        </display:column>
                        <display:column title="Baki Luas">
                            <c:if test="${line.luasTerlibat ne null}">
                                <c:set value="${line.luasTerlibat}" var="a"/>
                                <c:set value="${line.hakmilik.luas}" var="b"/>
                                <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>
                            </c:if>
                            <c:if test="${line.luasTerlibat eq null}">0</c:if>
                        </display:column>
                        <display:column property="hakmilik.kegunaanTanah.nama" title="Kegunaan Tanah" />
                </display:table>
                    </div>
                    <br/>
                </div>
        <br/>
                 </fieldset>
       <br>
       <fieldset class="aras1">
            <legend>Maklumat Aduan</legend>
                    <div class="subtitle" id="main">
                    <div class="content" align="center">
                        <%--<s:hidden name="idPihak" id="idPihak" value="pihak.idPihak"/>--%>
                        <%--${actionBean.pihak.idPihak}--%>
                        <table class="tablecloth" border="0" width="75%">
                            <tr>
                                <td>
                                    Perihal Aduan :
                                </td>
                                <td>
                                    <%--${actionBean.perihal}--%>
                                    <s:textarea name="perihal" value="${actionBean.perihal}" rows="5" cols="50"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br/>
                </div>
        <br/>
                 </fieldset>
     <%-- <fieldset class="aras1">
            <legend>Maklumat Wakil</legend>
            <p>
                <label>Hubungan Dengan Pemilik :</label>
                <s:text name="permohonanPihakHubungan.kaitan" id="kaitan" size="42" maxlength="250"/>

            </p>
            <p>
                <label><em>*</em>Nama :</label>
                <s:text name="permohonanPihakHubungan.nama" id="penyerahNama" size="42" maxlength="250"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="permohonanPihakHubungan.jenisPengenalan.kod"  value=""  style="width:139px;" id="pengenalan" onchange="jenisPengenalan()">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>
            <p id="noPengenalanLain">
                <label>No.Pengenalan :</label>
                <s:text name="permohonanPihakHubungan.noPengenalan" id="penyerahNoPengenalanLain" maxlength="20"/>
                &nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                <s:text name="permohonanPihakHubungan.alamat1"  size="30" maxlength="50"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="permohonanPihakHubungan.alamat2"  size="30" maxlength="50"/>

            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="permohonanPihakHubungan.alamat3"  size="30" maxlength="50"/>

            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="permohonanPihakHubungan.alamat4"  size="30" maxlength="50"/>

                &nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="permohonanPihakHubungan.poskod" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>

            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="permohonanPihakHubungan.negeri.kod" style="width:139px;" value="">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            <p>
                <label>No.Telefon :</label>
                <s:text name="permohonanPihakHubungan.noTelefon1" id="telefon" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>

            </p>
        </fieldset>--%>
         <fieldset class="aras1">
            <%--<c:if test="${edit}">--%>
           
                        <%--<s:hidden name="idPihak" id="idPihak" value="pihak.idPihak"/>--%>
                        <%--${actionBean.pihak.idPihak}--%>
                         <div class="subtitle" id="main">
                        <%--class="content"--%>
                        Maklumat Pengadu
                        <div align="center">
                            <table class="tablecloth" border="0" width="75%">
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>No Pengenalan :</td>
                                <td>
                                    <%--onchange="clearNoPengenalan();"--%>
                                    <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan" value="${actionBean.pihak.jenisPengenalan.kod}">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                    </s:select> :
                                  <s:hidden name="pihak.jenisPengenalan.kod" value="pihak.jenisPengenalan.kod"/>
                                    <s:text name="pihak.noPengenalan" value="${actionBean.pihak.noPengenalan}" id="noPengenalan" size="20" onkeyup="dodacheck(this.value);" onblur="doUpperCase(this.id)"/>
                                    <font title="No KP Baru: 780104069871, No KP Lama: A2977884, No Syarikat: 115793-P, No Pertubuhan: 432483-U"><em>[780104069871]</em></font>
                                  <%--  <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                                    onclick="javascript:populatePenyerah(this);" />--%>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>Nama :</td>
                                <td>
                                    <%--${actionBean.pihak.nama}--%>
                                    <s:text name="pihak.nama" id="nama" size="40" value="${actionBean.pihak.nama}" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>Alamat :</td>
                                <td>
                                    <%--${actionBean.pihak.alamat1}--%>
                                    <%--permohonanPihak.alamat.alamat1--%>
                                    <s:text name="pihak.alamat1" id="alamat1" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <%--${actionBean.pihak.alamat2}--%>
                                    <%--permohonanPihak.alamat.alamat2--%>
                                    <s:text name="pihak.alamat2" id="alamat2" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <%--${actionBean.pihak.alamat3}--%>
                                    <%--permohonanPihak.alamat.alamat3--%>
                                    <s:text name="pihak.alamat3" id="alamat3" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <%--${actionBean.pihak.alamat4}--%>
                                    <%--permohonanPihak.alamat.alamat4--%>
                                    <s:text name="pihak.alamat4" id="alamat4" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>Poskod :</td>
                                <td>
                                  <%--  ${actionBean.pihak.poskod}--%>
                                  <%--permohonanPihak.alamat.poskod--%>
                                    <s:text name="pihak.poskod" size="10" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>Negeri :</td>
                                <td>
                                    <%--${actionBean.pihak.negeri.nama}--%>
                                    <%--<s:text name="permohonanPihak.negeri.nama" value="${actionBean.permohonanPihak.negeri.kod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>--%>
                                    <s:select name="kodNegeriAlamat" value="${actionBean.pihak.negeri.kod}" id="negeri">
                                        <s:option value="0">Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>No Telefon :</td>
                                <td>
                                    <%--${actionBean.pihak.noTelefon1}--%>
                                    <s:text name="pihak.noTelefon1" id="noTelefon1" size="14" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>Emel :</td>
                                <td>
                                   <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40" class="normal_text"/></td>
                            </tr>
                             <tr>
                                <td>Hubungan :</td>
                                <td>
                                   <s:text name="permohonanPihak.kaitan" id="hubungan" size="40" class="normal_text"/></td>
                            </tr>
                        </table>
                            <%--<c:if test="${actionBean.permohonanPihak.kaitan eq null}">
                        <table class="tablecloth" border="0" width="75%">
                            <tr>
                                <td><font color="red" size="4">*</font>No Pengenalan :</td>
                                <td>
                                    onchange="clearNoPengenalan();"
                                    <s:select name="pihak.jenisPengenalan.kod" disabled="true" id="jenisPengenalan" value="${actionBean.pihak.jenisPengenalan.kod}">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                    </s:select> :
                                  <s:hidden name="pihak.jenisPengenalan.kod" value="pihak.jenisPengenalan.kod"/>
                                    <s:text name="pihak.noPengenalan" disabled="true" value="${actionBean.pihak.noPengenalan}" id="noPengenalan" size="20" onkeyup="dodacheck(this.value);" onblur="doUpperCase(this.id)"/>
                                    <font title="No KP Baru: 780104069871, No KP Lama: A2977884, No Syarikat: 115793-P, No Pertubuhan: 432483-U"><em>[780104069871]</em></font>
                                    <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                                    onclick="javascript:populatePenyerah(this);" />
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Nama :</td>
                                <td>
                                    ${actionBean.pihak.nama}
                                    <s:text name="pihak.nama" id="nama" size="40" value="${actionBean.pihak.nama}" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Alamat :</td>
                                <td>
                                    ${actionBean.pihak.alamat1}
                                    <s:text name="permohonanPihak.alamat.alamat1" id="alamat1" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat2}
                                    <s:text name="permohonanPihak.alamat.alamat2" id="alamat2" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat3}
                                    <s:text name="permohonanPihak.alamat.alamat3" id="alamat3" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat4}
                                    <s:text name="permohonanPihak.alamat.alamat4" id="alamat4" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Poskod :</td>
                                <td>
                                    ${actionBean.pihak.poskod}
                                    <s:text name="permohonanPihak.alamat.poskod" size="10" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Negeri :</td>
                                <td>
                                    ${actionBean.pihak.negeri.nama}
                                    <s:text name="permohonanPihak.negeri.nama" value="${actionBean.permohonanPihak.negeri.kod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                                    <s:select name="kodNegeriAlamat" value="${actionBean.permohonanPihak.alamat.negeri.kod}" id="negeri">
                                        <s:option value="0">Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>No Telefon :</td>
                                <td>
                                    ${actionBean.pihak.noTelefon1}
                                    <s:text name="pihak.noTelefon1" id="noTelefon1" size="14" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Emel :</td>
                                <td>
                                   <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40" class="normal_text"/></td>
                            </tr>
                             <tr>
                                <td><font color="red" size="4">*</font>Hubungan :</td>
                                <td>
                                   <s:text name="permohonanPihak.kaitan" id="hubungan" size="40" class="normal_text"/></td>
                            </tr>
                        </table>
                           </c:if>--%>
                           <%-- <br
                                <c:if test="${actionBean.permohonanPihak.kaitan ne null}">
                          <div class="subtitle" id="main">
                        class="content"
                        Maklumat Tuan Tanah
                        <div align="center" id="hiddenWakil">
                        <table class="tablecloth" border="0" width="75%">
                            <tr>
                                <td><font color="red" size="4">*</font>No Pengenalan :</td>
                                <td>
                                    onchange="clearNoPengenalan();"
                                    <s:select name="pihak.jenisPengenalan.kod" disabled="true" id="jenisPengenalan" value="${actionBean.pihak.jenisPengenalan.kod}">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                    </s:select> :
                                  <s:hidden name="pihak.jenisPengenalan.kod" value="pihak.jenisPengenalan.kod"/>
                                    <s:text name="pihak.noPengenalan" disabled="true" value="${actionBean.pihak.noPengenalan}" id="noPengenalan" size="20" onkeyup="dodacheck(this.value);" onblur="doUpperCase(this.id)"/>
                                    <font title="No KP Baru: 780104069871, No KP Lama: A2977884, No Syarikat: 115793-P, No Pertubuhan: 432483-U"><em>[780104069871]</em></font>
                                    <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                                    onclick="javascript:populatePenyerah(this);" />
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Nama :</td>
                                <td>
                                    ${actionBean.pihak.nama}
                                    <s:text name="pihak.nama" id="nama" size="40" value="${actionBean.pihak.nama}" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Alamat :</td>
                                <td>
                                    ${actionBean.pihak.alamat1}
                                    <s:text name="permohonanPihak.alamat.alamat1" id="alamat1" size="40" class="normal_text" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat2}
                                    <s:text name="permohonanPihak.alamat.alamat2" id="alamat2" size="40" class="normal_text" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat3}
                                    <s:text name="permohonanPihak.alamat.alamat3" id="alamat3" size="40" class="normal_text" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat4}
                                    <s:text name="permohonanPihak.alamat.alamat4" id="alamat4" size="40" class="normal_text" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Poskod :</td>
                                <td>
                                    ${actionBean.pihak.poskod}
                                    <s:text name="permohonanPihak.alamat.poskod" size="10" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Negeri :</td>
                                <td>
                                    ${actionBean.pihak.negeri.nama}
                                    <s:text name="permohonanPihak.negeri.nama" value="${actionBean.permohonanPihak.negeri.kod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                                    <s:select name="kodNegeriAlamat" value="${actionBean.permohonanPihak.alamat.negeri.kod}" id="negeri" disabled="true">
                                        <s:option value="0">Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>No Telefon :</td>
                                <td>
                                    ${actionBean.pihak.noTelefon1}
                                    <s:text name="pihak.noTelefon1" id="noTelefon1" size="14" onkeyup="validateNumber(this,this.value);" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Emel :</td>
                                <td>
                                   <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40" class="normal_text" disabled="true"/></td>
                            </tr>
                        </table>
                            <br>
                             Maklumat Wakil
                        <table class="tablecloth" border="0" width="75%">
                            <tr>
                                <td><font color="red" size="4">*</font>No Pengenalan :</td>
                                <td>
                                    onchange="clearNoPengenalan();"
                                    <s:select name="permohonanPihak.jenisPengenalan.kod"  id="permohonanPihak.jenisPengenalan.kod" value="${actionBean.pihak.jenisPengenalan.kod}">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                    </s:select> :
                                  <s:hidden name="permohonanPihak.jenisPengenalan.kod" id="permohonanPihak.jenisPengenalan.kod" value="permohonanPihak.jenisPengenalan.kod"/>
                                    <s:text name="permohonanPihak.noPengenalan"  id="permohonanPihak.noPengenalan" value="${actionBean.permohonanPihak.noPengenalan}" size="20" onkeyup="dodacheck(this.value);" onblur="doUpperCase(this.id)"/>
                                    <font title="No KP Baru: 780104069871, No KP Lama: A2977884, No Syarikat: 115793-P, No Pertubuhan: 432483-U"><em>[780104069871]</em></font>
                                    <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                                    onclick="javascript:populatePenyerah(this);" />
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Nama :</td>
                                <td>
                                    ${actionBean.pihak.nama}
                                    <s:text name="permohonanPihak.nama" id="permohonanPihak.nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Alamat :</td>
                                <td>
                                    ${actionBean.pihak.alamat1}
                                    <s:text name="permohonanPihak.alamat.alamat1" id="permohonanPihak.alamat.alamat1" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat2}
                                    <s:text name="permohonanPihak.alamat.alamat2" id="permohonanPihak.alamat.alamat2" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat3}
                                    <s:text name="permohonanPihak.alamat.alamat3" id="permohonanPihak.alamat.alamat3" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat4}
                                    <s:text name="permohonanPihak.alamat.alamat4" id="permohonanPihak.alamat.alamat4" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Poskod :</td>
                                <td>
                                    ${actionBean.pihak.poskod}
                                    <s:text name="permohonanPihak.alamat.poskod" size="10" id="permohonanPihak.alamat.poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Negeri :</td>
                                <td>
                                    ${actionBean.pihak.negeri.nama}
                                    <s:text name="permohonanPihak.negeri.nama" value="${actionBean.permohonanPihak.negeri.kod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                                    <s:select name="kodNegeriAlamat" value="${actionBean.permohonanPihak.alamat.negeri.kod}" id="negeri">
                                        <s:option value="0">Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>No Telefon :</td>
                                <td>
                                    ${actionBean.pihak.noTelefon1}
                                    <s:text name="permohonanPihak.noTelefon1" id="permohonanPihak.noTelefon1" size="14" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Emel :</td>
                                <td>
                                   <s:text name="permohonanPihak.email" id="permohonanPihak.email" value="${actionBean.pihak.email}"size="40" class="normal_text"/></td>
                            </tr>
                             <tr>
                                <td><font color="red" size="4">*</font>Hubungan :</td>
                                <td>
                                   <s:text name="permohonanPihak.kaitan" id="permohonanPihak.kaitan" size="40" class="normal_text"/></td>
                            </tr>
                        </table>
                        </div>
                            </c:if>--%>
                 <%--</div>--%>
                       <%--<s:button name="saveMohon" id="save" value="Kemaskini" class="btn" onclick="doSubmit(this.form, this.name, 'page_div',idPihak)"/>--%>
                       <%--<s:button name="saveMohon" id="save" value="Kemaskini" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>--%>
                       <s:button name="saveMohon" id="save" value="Kemaskini" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                       <%--<s:submit name="saveMohon" value="Seterusnya" class="btn" id="next"/>--%>
                   <%-- </div>

                    <br/>
                </div>--%>
                <%--</c:if>--%>
                 </div>
                            </div>
                 </fieldset>
                 </div>
 </s:form>

</body>


