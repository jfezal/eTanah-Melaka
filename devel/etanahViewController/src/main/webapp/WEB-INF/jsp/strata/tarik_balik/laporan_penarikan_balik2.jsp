<%-- 
    Document   : laporan_penarikan_balik
    Created on : Jul 6, 2010, 11:11:02 AM
    Author     : User
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function menyimpan(index,i)
    {
        var kand;
        if(index==2){
            kandungan = document.getElementById("kandungan2"+i).value;
        }
        if(index==3){
            kandungan = document.getElementById("kandungan3"+i).value;
        }
        if(index==4){
            kandungan = document.getElementById("kandungan4"+i).value;
        }
        
        if(confirm('Adakah anda pasti untuk simpan data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/semak_penarikan_balik?simpan&index='+index+'&kandungan='+kandungan;
                +index;
alert(url);
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
  
       
       
    }
    function deleteRow(idKandungan)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/semak_penarikan_balik?deleteRow2&idKandungan='+idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }

    function addRow(index)
    {
        if(confirm('Adakah anda pasti untuk menambah data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/semak_penarikan_balik?tambahRow2&index='+index;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }
    


</script>
<%--<s:form name ="form1" beanclass="etanah.view.strata.PenarikanBalikActionBean">--%>
<s:form beanclass="etanah.view.strata.PenarikanBalikActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle" align="center">
        <fieldset class="aras1"> <legend>Kertas Pertimbangan PTG</legend>
            <p><font size="3"><b>
                        <%-- ${actionBean.tajuk}--%></b></font>
            </p>
            <br>
            <p align="center">
            <table align="left" width="713" border="0">
                <tr>
                    <td width="40"><b>1.0</b></td>
                    <td width="657"><b>MAKLUMAT PERMOHONAN</b></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><table width="653" height="52" border="0" cellpadding="0" cellspacing="10">
                            <tr>
                                <td>Nama Pemohon</td>
                                <td>: &nbsp;${actionBean.pemohon}&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="30%">Pemilik Berdaftar &nbsp;</td>
                                <td width="527">: &nbsp;${actionBean.pemilik}&nbsp;</td>
                            </tr>
                            <tr>
                                <td>No Fail</td>
                                <td>: &nbsp;${actionBean.noFail}&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="20%">Bandar/Pekan/Mukim &nbsp;</td>
                                <td width="527">: &nbsp;${actionBean.mukim}&nbsp;</td>
                            </tr>
                            <tr>
                                <td>Daerah</td>
                                <td>: &nbsp;${actionBean.daerah}&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="20%">No Hakmilik</td>
                                <td width="527">: &nbsp;${actionBean.listNoHakmilik}&nbsp;</td>
                            </tr>
                            <tr>
                                <td>No Lot</td>
                                <td>: &nbsp;${actionBean.noLot}&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="20%">Nama Skim</td>
                                <td width="527">: &nbsp;${actionBean.namaSkim}</td>
                            </tr>
                            <tr>
                                <td>Bilangan Skim</td>
                                <td>: &nbsp;1</td>
                            </tr>
                            <tr>
                                <td width="20%">Bilangan Petak</td>
                                <td width="527">: &nbsp;${actionBean.jumlahPetak}</td>
                            </tr>
                            <tr>
                                <td>Tarikh Diluluskan</td>
                                <td><c:if test="${actionBean.keputusan eq null}">: &nbsp;Tiada Data</c:if>
                                    <c:if test="${actionBean.keputusan ne null}">: &nbsp;${actionBean.keputusan}</c:if></td>
                            </tr>
                            <tr>
                                <td width="30%">Tarikh Bayaran Kelulusan</td>
                                <td width="527"><c:if test="${actionBean.bayaran eq null}">: &nbsp;Tiada Data</c:if><c:if test="${actionBean.bayaran ne null}">: &nbsp;${actionBean.bayaran}</c:if></td>
                                </tr>
                                <tr>
                                    <td width="30%">&nbsp;</td>
                                    <td width="527">&nbsp;</td>
                                </tr>
                            </table> </td>
                    </tr>
                    <tr>
                        <td><b>2.0</b></td>
                        <td><b>MASALAH/ISU YANG TIMBUL</b> </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><table width="650" border="0">
                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.listMohonKertas2}" var="line">


                                <tr>
                                    <td width="23" valign="top"><c:out value="2.${i}"/></td>
                                    <td><s:hidden name="listMohonKertas2[${i-1}].bil"/><s:hidden name="listMohonKertas2[${i-1}].subtajuk"/> <s:textarea  id="kandungan2${i}"name="listMohonKertas2[${i-1}].kandungan" readonly="TRUE" cols="90"  rows="5" class="normal_text"/></td>
                                    <td></td>
                                </tr><c:set var="i" value="${i+1}" />
                            </c:forEach>

                            <tr>
                                <td width="23" valign="top"></td>
                                <td  align="left">
                                    <%--<s:button class="btn" value="Tambah" name="add" onclick="addRowViaJS('dataTable1')"/>--%>
                                    <%--<c:if test="${edit}"> <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2')"></s:button> <s:button name="simpan2" id="save1" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"></s:button></c:if> --%>
                                    <%-- <td  align="left">
                                         <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${i-1})"></s:button></td>--%>
                                </td>
                            </tr>
                        </table></td>
                </tr>
                <c:if test="${actionBean.stage2}">
                    <tr>
                        <td><b>3.0</b></td>
                        <td><b>SYOR PENOLONG PENGARAH STRATA / PENOLONG PEGAWAI TADBIR</b> </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp; </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><center><s:radio name="syorRadio" disabled="FALSE" value="DI" id="12" ></s:radio> Disokong <s:radio name="syorRadio" value="DT" id="12" disabled="FALSE" ></s:radio> Ditolak </center>&nbsp; </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                                <td>Setelah dikaji dan diteliti, pihak pentadbiran ini mengesyorkan supaya permohonan penarikan skim tersebut ${actionBean.syorPen} </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><table width="650" border="0">
                                <c:set var="j" value="1" />
                                <c:forEach items="${actionBean.listMohonKertas3}" var="line">
                                    <tr>
                                        <td width="23" valign="top"><c:out value="3.${j}"/></td>
                                        <td><s:hidden name="listMohonKertas3[${j-1}].bil"/><s:hidden name="listMohonKertas3[${j-1}].subtajuk"/> <s:textarea id="kandungan3${j}" name="listMohonKertas3[${j-1}].kandungan" readonly="FALSE" cols="90" rows="5" class="normal_text"/></td>
                                        <td><c:if test="TRUE"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button> </c:if></td>
                                    </tr><c:set var="j" value="${j+1}" />

                                </c:forEach>
                                <tr>
                                    <td width="23" valign="top"></td>
                                    <td  align="left">
                                        <%--<s:button class="btn" value="Tambah" name="add" onclick="addRowViaJS('dataTable1')"/>--%>
                                        <c:if test="TRUE"> <s:button value="Tambah" class="btn" name="tambah"  onclick="addRow('3')"></s:button><s:button name="simpan4" id="save1" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"></s:button> </c:if><%--<td  align="left">
                                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('3',${j-1})"></s:button></td>--%>
                                    <td></td>
                                </tr>
                            </table></td>
                    </tr>

                    <c:if test="${actionBean.kodNegeri eq '04'}">
                        <tr>
                            <td><b>4.0</b></td>
                            <td><b>PERAKUAN TIMBALAN PENDAFTAR HAKMILIK GERAN TANAH</b> </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td> <br>Tuan PTG,<p>
                                    Permohonan penarikan skim oleh ${actionBean.pemohon} bagi no lot ${actionBean.noLot}, Kawasan ${actionBean.mukim}, Daerah ${actionBean.daerah}, Melaka adalah ${actionBean.syorPen} <br> <br>

                            </td>
                        </tr>
                    </c:if>
                </c:if>
            </table>
            <br>
            </p>
        </fieldset> <br> </div>
    <p align="center">

    </p>
</s:form>