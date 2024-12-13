<%--
    Document   : Borang MMKN
    Created on : 17-Apr-2010, 6:20:51
    Author     : Massita
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>

<script language="javascript" type="text/javascript">
        function addRow(tableID) {

        var table = document.getElementById(tableID);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

         var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"2.1." +rowCount+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "latarBelakang";
         element1.rows = 5;
        element1.cols = 150;
        cell1.appendChild(element1);
    }

    function addRow1(tableID1) {

        var table = document.getElementById(tableID1);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"3.1." + rowCount+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "perakuanPentadbirTanah";
        element1.rows = 5;
        element1.cols = 150;
        cell1.appendChild(element1);

        
        <%--cell2.innerHTML += "<s:textarea name='perakuanPentadbirTanah' rows='5' cols='150'/>";
        cell2.innerHTML +="<p><input type='textarea' name='perakuanPentadbirTanah' rows='5' cols='150'>";--%>
    }

<%--function generateRow() {
var d=document.getElementById("new");
d.innerHTML+="<p><input type='text' name='food'>";
}--%>
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.permohonan_pembatalanMMKNActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            	<%--<c:set scope="request" var="senaraiPengarah"  value="${actionBean.listPengarah}"/>--%>
                <%--<c:set scope="request" var="senaraiPemohon"  value="${actionBean.listPemohon}"/>--%>

            <div class="content" align="center">
                <table  border="0" width="80%">
                    <c:if test="${edit}">
                        <tr><td align="center"><b><u>MAJLIS MESYUARAT KERAJAAN NEGERI MELAKA</u></b></td></tr><br>
                        <tr><td align="center"><b>PERSIDANGAN :<s:text name="kertasbil"  size="12" />/2010</b></td></tr><br>
                        <tr><td align="center"><b>MASA :<s:text name="" size="12" /></b></td></tr><br>
                        <tr><td align="center"><b>TARIKH :<s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /></b></td></tr><br>
                        <tr><td align="center"><b>TEMPAT :<s:text name="" size="12" /></b></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td align="center"><b><u>MAJLIS MESYUARAT KERAJAAN NEGERI MELAKA</u></b></td></tr><br>
                        <tr><td align="center"><b>PERSIDANGAN <%--${actionBean.mohon_ruj_luar.trh_sidang}--%>/2010</b></td></tr><br>
                        <tr><td align="center"><b>MASA : ${actionBean.kertasbil}/2010</b></td></tr><br>
                        <tr><td align="center"><b>TARIKH : ${actionBean.tarikhMesyuarat}</b></td></tr><br>
                        <tr><td align="center"><b>TEMPAT : <%--${actionBean.tarikhMesyuarat}--%></b></td></tr><br>
                    </c:if >
                    <c:if test="${edit}">
                        <tr><td><b><font style="text-transform: uppercase">PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ${actionBean.hakmilik.noLot} MUKIM ${actionBean.hakmilik.bandarPekanMukim.nama} DAERAH ${actionBean.hakmilik.daerah.nama} BAGI ${actionBean.permohonan.sebab}.</font></b></td></tr><br>
                    </c:if>
                     <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform: uppercase">PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ${actionBean.hakmilik.noLot} MUKIM ${actionBean.hakmilik.bandarPekanMukim.nama} DAERAH ${actionBean.hakmilik.daerah.nama} BAGI ${actionBean.permohonan.sebab}.</font></b></td></tr><br>
                        <hr>
                     </c:if><br />
                                                                         
                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${!edit}"><br />
                        <tr>
                            <td valign="left"><font style="text-transform:capitalize"><b>&nbsp;&nbsp;&nbsp;&nbsp;1.1 ${actionBean.tujuan}</b></font></td>
                        </tr><br>
                    </c:if>
                    <c:if test="${edit}">
                        <tr>
                            <td><font style="text-transform: uppercase"><s:textarea rows="5" cols="150" name="tujuan"/></font></td>
                        </tr><br>
                    </c:if>

                    <tr><td><b>2. LATAR BELAKANG</b></td></tr><br />
                    <table id="dataTable">
                        <tr>
                            <td><b> 2.1</b></td>
                            <td><s:textarea name="latarBelakang" rows="5" cols="150"/> </td>
                        </tr>
                    </table>
                    <tr>
                        <td align="right">
                           <%-- <s:button name="new" value="Tambah" id="new" class="btn" onclick="generateRow();"/>--%>
                           <s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow('dataTable')" />
                        </td>
                    </tr>
                    <br />

                    <tr><td><b>3. PERIHAL TANAH</b></td></tr><br />
                    <table id="dataTable">
                        <tr>
                            <td><b> 3.1</b></td>
                            <td><s:textarea name="latarBelakang" rows="5" cols="150"/> </td>
                        </tr>
                    </table>
                    <tr>
                        <td align="right">
                           <%-- <s:button name="new" value="Tambah" id="new" class="btn" onclick="generateRow();"/>--%>
                           <s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow('dataTable')" />
                        </td>
                    </tr>
                    <br />

                    <c:if test="${edit}">
                        <tr><td><b><font style="text-transform: uppercase">4. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr><br />
                        <table id="dataTable1">
                                <tr>
                                    <td><b>4.1</b></td>
                                    <td><s:textarea name="perakuanPentadbirTanah" rows="5" cols="150"/> </td>
                                </tr>
                        </table>

                        <tr>
                            <td align="right">
                               <%-- <s:button name="new" value="Tambah" id="new" class="btn" onclick="generateRow();"/>--%>
                               <s:button name="perakuanPentadbirTanah" value="Tambah" class="btn" onclick="addRow('dataTable')" />
                            </td>
                        </tr>

                        <tr><td><b><font style="text-transform: uppercase">5. PERAKUAN TANAH DAN GALIAN </font></b></td></tr><br />
                        <table id="dataTable1">
                                <tr>
                                    <td><b>4.1</b></td>
                                    <td><s:textarea name="perakuanTanahGalian" rows="5" cols="150"/> </td>
                                </tr>
                        </table>

                        <tr>
                            <td align="right">
                               <%-- <s:button name="new" value="Tambah" id="new" class="btn" onclick="generateRow();"/>--%>
                               <s:button name="perakuanTanahGalian" value="Tambah" class="btn" onclick="addRow('dataTable')" />
                            </td>
                        </tr>
                    </c:if>

                        <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform: uppercase">4. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize">${actionBean.pentadbirTanah}&nbsp;</font></td></tr>

                        <tr><td><b><font style="text-transform: uppercase">5. PERAKUAN PENGARAH TANAH DAN GALIAN&nbsp;</font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize">${actionBean.pengarahTanahGalian}&nbsp;</font></td></tr><br>
                        </c:if>
                    <br />
                  </table>
            </div>
        </fieldset>
    </div>
      <c:if test="${edit}">
        <p align="center">
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>
</s:form>