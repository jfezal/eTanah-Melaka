

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
        document.form1.rowCount.value = 1;
        var table = document.getElementById(tableID);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"2." +(rowCount+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan"+(rowCount+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan"+(rowCount+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        document.form1.rowCount.value=rowCount +1;
    }
    function deleteRow()
    {
        var i = document.form1.rowCount.value;
        var x= document.getElementById('dataTable').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable').deleteRow(i-1);
        document.form1.rowCount.value = i -1;
          
        var url = '${pageContext.request.contextPath}/kertas_mmkn?deleteSingle&idKandungan='
            +idKandungan;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
     function validateForm(){

        if(document.form1.tajuk.value=="")
        {
            alert("Sila Masukkan Tajuk");
            return false;
        }
        if(document.form1.tujuan.value=="")
        {
            alert("Sila Masukkan Tujuan");
            return false;
        }

        if(document.form1.tadbir.value=="")
        {
            alert("Sila Masukkan Perakuan Pentadbir Tanah");
            return false;
        }
        if(document.form1.syor.value=="")
        {
            alert("Sila Masukkan Syor Pengarah Unit Perancang Ekonomi Negeri");
            return false;
        }
        if(document.form1.keputusan.value=="")
        {
            alert("Sila Masukkan Keputusan Jawatankuasa Khas Pengambilan Tanah Negeri Melaka");
            return false;
        }
        if(document.form1.pengarah.value=="")
        {
            alert("Sila Masukkan Perakuan Pengarah Tanah Dan Galian, Negeri Melaka");
            return false;
        }
        return true;
       
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.KertasMMKNActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <c:if test="${form}">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend></legend>
                <div class="content" align="center">
                    <%--<s:hidden name="rowCount" value="1"/>--%>
                    <%--<s:hidden name="kira" value="1"/>--%>
                    <table border="0" width="80%">

                        <tr><td align="center"><b>DRAF KERTAS MMKN NEGERI MELAKA</b></td></tr><br>
                        <tr><td align="center"><b>KERTAS BIL ${actionBean.permohonanKertas.idKertas}/2010</b></td></tr><br>
                        <tr><td align="center"><b>TARIKH : <fmt:formatDate value="${actionBean.permohonanKertas.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy" /></b></td></tr><br>
                        <%--<tr><td align="center"><b>RISALAT MMKN :</b>MMKN</td></tr><br>
                        <tr><td align="center"><b>NO.RUJ PTG :</b><s:text name="kertasbil"  size="12" />/2010</td></tr><br>--%>

                        <tr><td><b>TAJUK</b></td></tr>

                        <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="150" name="permohonanKertas.tajuk" id="tajuk"/></font></td></tr><br>

                        <tr><td><b>1. TUJUAN</b></td></tr>

                        <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="150" name="permohonanKertasKandungan1.kandungan" id="tujuan"></s:textarea></font></td></tr><br>

                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                        <tr><td align="right"><s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow('dataTable')" />
                                <s:button name="latarBelakang" value="Hapus" class="btn" onclick="deleteRow()" />
                                <table id ="dataTable">
                                    <c:set var="i" value="1" />
                                    <c:if test="${actionBean.senaraiKertasKandungan eq null}">
                                        <tr><td><b>2.1</b></td>
                                            <td><s:textarea name="kandungan1" id="kandungan1" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();"/></td></tr></c:if>
                                        <c:forEach items="${actionBean.senaraiKertasKandungan}" var="line">
                                            <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan${i}" id="kandungan${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount" value="${i-1}"/>
                                </table>
                        <tr><td><b>3. PERIHAL PERMOHONAN</b></td></tr>
                        <tr><td>Ringkasan Permohonan:-</td></tr>
                        <tr><td>
                                <table border="0" width="96%">
                                    <tr>
                                        <td id="tdLabel"><font color="black">Pemohon :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Perihal Tanah :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Lot/PT :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Mukim :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Lokasi :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Kegunaan Tanah :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>

                                </table>
                            </td></tr><br>

                        <tr><td><b><font style="text-transform: uppercase">3. PERAKUAN PENTADBIR TANAH ALOR GAJAH</font></b></td></tr>
                        <tr><td><s:textarea name="permohonanKertasKandungan3.kandungan" rows="5" cols="150" id="tadbir"></s:textarea></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">4. SYOR PENGARAH UNIT PERANCANG EKONOMI NEGERI <%--${actionBean.hakmilik.daerah.nama}--%></font></b></td></tr>
                        <tr><td><s:textarea name="permohonanKertasKandungan4.kandungan" rows="5" cols="150" id="syor"><%--${actionBean.permohonanKertasKandungan4.kandungan}--%></s:textarea></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">5. KEPUTUSAN JAWATANKUASA KHAS PENGAMBILAN TANAH NEGERI MELAKA&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="permohonanKertasKandungan5.kandungan" rows="5" cols="150" id="keputusan"/></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">6. PERAKUAN PENGARAH TANAH DAN GALIAN, NEGERI MELAKA&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="permohonanKertasKandungan6.kandungan" rows="5" cols="150" id="pengarah"/></td></tr><br>

                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>
    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend></legend>
                <div class="content" align="center">

                    <table border="0" width="80%">

                        <tr><td align="center"><b>DRAF KERTAS MMKN NEGERI MELAKA</b></td></tr><br>
                        <tr><td align="center"><b>KERTAS BIL ${actionBean.permohonanKertas.idKertas}/2010/2010</b></td></tr><br>
                        <tr><td align="center"><b>TARIKH : <fmt:formatDate value="${actionBean.permohonanKertas.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy" /></b></td></tr><br>

                        <tr><td><b>TAJUK</b></td></tr>

                        <tr><td><font style="text-transform: uppercase"></font></td></tr><br>

                        <tr><td><b>1. TUJUAN</b></td></tr>

                        <tr><td><font style="text-transform: uppercase">${actionBean.permohonanKertasKandungan1.kandungan}</font></td></tr><br>

                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                        <tr><td align="left">
                                <table id ="dataTable">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan}" var="line">
                                        <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr><td style="display:none" >${line.idKandungan}</td><td style="font-weight:bold"><c:out value="${line.subtajuk}"/></td>
                                            <td><%--<s:textarea name="kandungan${i}" id="kandungan${i}" rows="5" cols="150">${line.kandungan}</s:textarea>--%><c:out value="${line.kandungan}"/>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount" value="${i-1}"/>
                                </table>&emsp;
                        <tr><td><b>3. PERIHAL PERMOHONAN </b></td></tr>
                        <tr><td>Ringkasan Permohonan:-</td></tr>
                        <tr><td>
                                <table border="0" width="96%">
                                    <tr>
                                        <td id="tdLabel"><font color="black">Pemohon :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Perihal Tanah :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Lot/PT :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Mukim :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Lokasi :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Kegunaan Tanah :</font></td>
                                        <td id="tdDisplay">&nbsp;</td>
                                    </tr>

                                </table>
                            </td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">3. PERAKUAN PENTADBIR TANAH ALOR GAJAH</font></b></td></tr>
                        <tr><td>${actionBean.permohonanKertasKandungan3.kandungan}</td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">4. SYOR PENGARAH UNIT PERANCANG EKONOMI NEGERI <%--${actionBean.hakmilik.daerah.nama}--%></font></b></td></tr>
                        <tr><td>${actionBean.permohonanKertasKandungan4.kandungan}</td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">5. KEPUTUSAN JAWATANKUASA KHAS PENGAMBILAN TANAH NEGERI MELAKA&nbsp;</font></b></td></tr>
                        <tr><td></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">6. PERAKUAN PENGARAH TANAH DAN GALIAN, NEGERI MELAKA&nbsp;</font></b></td></tr>
                        <tr><td></td></tr><br>
                    </table>
                </div>
            </fieldset>
        </div>

    </c:if>
</s:form>
