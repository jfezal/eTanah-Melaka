<%-- 
    Document   : kemasukan_aduan_papar
    Created on : Jun 11, 2013, 4:58:49 PM
    Author     : Admin
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%-- carian hakmilik end--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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

</script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pengambilan.AduanActionBean" name="form">
        <s:errors/>
        <s:messages/>
       <div class="subtitle">
        <s:messages/>
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
                                    ${actionBean.perihal}
                                    <%--<s:textarea name="perihal" value="" rows="5" cols="50" readonly="true"/>--%>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br/>
                </div>
        <br/>
                 </fieldset>
         <fieldset class="aras1">
            <%--<c:if test="${edit}">--%>
            <legend>Maklumat Pengadu</legend>
             <div class="subtitle" id="main">
                 <%--class="content"--%>
                    <div align="center">
                        <s:hidden name="idPihak" id="idPihak" value="pihak.idPihak"/>
                        <%--${actionBean.pihak.idPihak}--%>
                        <table class="tablecloth" border="0" width="75%">
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>No Pengenalan :</td>
                                <td>
                                     ${actionBean.pihak.jenisPengenalan.nama} :${actionBean.pihak.noPengenalan}
                                    <%--<s:text name="pihak.noPengenalan" id="noPengenalan" size="20" onkeyup="dodacheck(this.value);" onblur="doUpperCase(this.id)"/>
                                    <font title="No KP Baru: 780104069871, No KP Lama: A2977884, No Syarikat: 115793-P, No Pertubuhan: 432483-U"><em>[780104069871]</em></font>--%>
                                  <%--  <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                                    onclick="javascript:populatePenyerah(this);" />--%>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>Nama :</td>
                                <td>
                                    ${actionBean.pihak.nama}
                                    <%--<s:text name="pihak.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>--%>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>Alamat :</td>
                                <td>
                                    ${actionBean.permohonanPihak.alamat.alamat1}
                                    <%--<s:text name="pihak.alamat1" value="${actionBean.pihak.alamat1}" id="alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>--%>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.permohonanPihak.alamat.alamat2}
                                    <%--<s:text name="pihak.alamat2" value="${actionBean.pihak.alamat2}" id="alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>--%>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.permohonanPihak.alamat.alamat3}
                                    <%--<s:text name="pihak.alamat3" value="${actionBean.pihak.alamat3}" id="alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>--%>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.permohonanPihak.alamat.alamat4}
                                    <%--<s:text name="pihak.alamat4" value="${actionBean.pihak.alamat4}" id="alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>--%>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>Poskod :</td>
                                <td>
                                    ${actionBean.permohonanPihak.alamat.poskod}
                                    <%--<s:text name="pihak.poskod" value="${actionBean.pihak.poskod}" size="10" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>--%>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>Negeri :</td>
                                <td>
                                    ${actionBean.permohonanPihak.alamat.negeri.nama}
                                    <%--<s:text name="pihak.negeri.nama" value="${actionBean.pihak.negeri.kod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>--%>
                                    <%--<s:select name="pihak.negeri.kod" id="negeri">
                                        <s:option value="0">Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                    </s:select>--%>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>No Telefon :</td>
                                <td>
                                    ${actionBean.pihak.noTelefon1}
                                    <%--<s:text name="pihak.noTelefon1" id="noTelefon1" value="${actionBean.pihak.noTelefon1}"size="" onkeyup="validateNumber(this,this.value);"/>--%>
                                </td>
                            </tr>
                            <tr>
                                <td><%--<font color="red" size="4">*</font>--%>Emel :</td>
                                <td>
                                    ${actionBean.pihak.email}
                                    <%--<s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40" class="normal_text"/> <em>[alamat_emel@gmail.com]</em>--%>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <br/>
                </div>
                <%--</c:if>--%>
                 </fieldset>
                 </div>
 </s:form>

</body>


