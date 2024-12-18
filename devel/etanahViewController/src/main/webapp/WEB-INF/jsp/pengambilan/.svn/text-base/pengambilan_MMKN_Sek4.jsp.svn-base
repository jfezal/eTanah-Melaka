<%--
    Document   : pengambilan_MMKN_Sek4
    Created on : Jul 30, 2010, 10:50:08 AM
    Author     : Rajesh
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
    function addRow1(tableID) {
        document.form2.rowCount1.value = 1;
        var table = document.getElementById(tableID);

        var rowCount1 = table.rows.length;
        var row = table.insertRow(rowCount1);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"2.1." +(rowCount1+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan1"+(rowCount1+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan1"+(rowCount1+1);
        cell1.appendChild(element1);
        document.form2.rowCount1.value=rowCount1 +1;
    }

    function deleteRow1()
    {
        var i = document.form2.rowCount1.value;
        var x= document.getElementById('dataTable1').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable1').deleteRow(i-1);
        document.form2.rowCount1.value = i -1;

        var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
            +idKandungan;
         
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function addRow2(tableID2) {
        document.form2.rowCount2.value = 1;
        var table = document.getElementById(tableID2);

        var rowCount2 = table.rows.length;
        var row = table.insertRow(rowCount2);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"3." +(rowCount2+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan2"+(rowCount2+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan2"+(rowCount2+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        document.form2.rowCount2.value=rowCount2 +1;
    }
    function deleteRow2()
    {
        var j = document.form2.rowCount2.value;
        var x= document.getElementById('dataTable2').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable2').deleteRow(j-1);
        document.form2.rowCount2.value = j -1;

        var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
            +idKandungan;
        
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function addRow3(tableID3) {
        document.form2.rowCount3.value = 1;
        var table = document.getElementById(tableID3);

        var rowCount3 = table.rows.length;
        var row = table.insertRow(rowCount3);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"4." +(rowCount3+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan3"+(rowCount3+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan3"+(rowCount3+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        document.form2.rowCount3.value=rowCount3 +1;
    }
    function deleteRow3()
    {
        var k = document.form2.rowCount3.value;
        var x= document.getElementById('dataTable3').rows[k-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable3').deleteRow(k-1);
        document.form2.rowCount3.value = k -1;

        var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
            +idKandungan;
        
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function addRow4(tableID4) {
        document.form2.rowCount4.value = 1;
        var table = document.getElementById(tableID4);

        var rowCount4 = table.rows.length;
        var row = table.insertRow(rowCount4);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"5." +(rowCount4+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan4"+(rowCount4+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan4"+(rowCount4+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        document.form2.rowCount4.value=rowCount4 +1;
    }
    function deleteRow4()
    {
        var k = document.form2.rowCount4.value;
        var x= document.getElementById('dataTable4').rows[k-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable4').deleteRow(k-1);
        document.form2.rowCount4.value = k -1;

        var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
            +idKandungan;
        
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function addRow5(tableID5) {
        document.form2.rowCount5.value = 1;
        var table = document.getElementById(tableID5);

        var rowCount5 = table.rows.length;
        var row = table.insertRow(rowCount5);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"7." +(rowCount5+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan5"+(rowCount5+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan5"+(rowCount5+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        document.form2.rowCount5.value=rowCount5 +1;
    }
    function deleteRow5()
    {
        var k = document.form2.rowCount5.value;
        var x= document.getElementById('dataTable5').rows[k-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable5').deleteRow(k-1);
        document.form2.rowCount5.value = k -1;

        var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
            +idKandungan;
        
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }    
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PengambilanMMKNSek4ActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <c:if test="${form}">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend></legend>
                <div class="content" align="center">
                    <table border="0" width="80%">

                        <tr><td><b><font style="text-transform: uppercase">PERMOHONAN PENGAMBILAN TANAH DI ATAS LOT
                                        <c:forEach items="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik" >
                                            <c:out value="${senaraiHakmilik.hakmilik.noLot}"/>, 
                                        </c:forEach>
                                           
                                           SELUAS ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama} , DI ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, MELAKA UNTUK TUJUAN RIZAB PARIT, DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.</font></b><br /><br /><hr /><br /></td></tr>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr><tr><tr><td colspan="2"><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea rows="5" cols="140" name="tujuan"/></font><td>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2.1 Perihal Permohonan</b></td></tr>
                        <tr><td>
                                <table id ="dataTable1">
                                    <c:set var="i" value="1" />
                                    <c:if test="${actionBean.senaraiKertasKandungan1 eq null}">
                                        <%--<s:hidden name="rowCount1" value="1"/>--%>
                                        <tr><td><b>2.1.1</b></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan1" id="kandungan1" rows="5" cols="150"/></font></td></tr></c:if>
                                        <c:forEach items="${actionBean.senaraiKertasKandungan1}" var="line">
                                            <%--<s:hidden name="rowCount1" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan1${i}" id="kandungan1${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount1" value="${i-1}"/>
                                </table>
                        <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow1('dataTable1')" />
                                <s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow1()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>3. Perihal Tanah</b></td></tr>
                        <tr><td>
                                <table id ="dataTable2">
                                    <c:set var="i" value="1" />
                                    <c:if test="${actionBean.senaraiKertasKandungan2 eq null}">
                                        <%--<s:hidden name="rowCount" value="1"/>--%>
                                        <tr><td><b>3.1</b></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan2" id="kandungan2" rows="5" cols="150"/></font></td></tr></c:if>
                                        <c:forEach items="${actionBean.senaraiKertasKandungan2}" var="line">
                                            <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan2${i}" id="kandungan2${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount2" value="${i-1}"/>
                                </table>
                        <tr><td align="right"><s:button name="perihalTanah" value="Tambah" class="btn" onclick="addRow2('dataTable2')" />
                                <s:button name="perihalTanah" value="Hapus" class="btn" onclick="deleteRow2()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">4. PANDANGAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td>
                                <table id ="dataTable3">
                                    <c:set var="k" value="1" />
                                    <c:if test="${actionBean.senaraiKertasKandungan3 eq null}">
                                        <%--<s:hidden name="rowCount" value="1"/>--%>
                                        <tr><td><b>4.1</b></td>
                                            <td><s:textarea name="kandungan3" id="kandungan3" rows="5" cols="150"/></td></tr></c:if>
                                        <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                           <%-- <s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan3${k}" id="kandungan3${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount3" value="${k-1}"/>
                                </table>
                        <tr><td align="right"><s:button name="pandanganPentadbirTanah" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                                <s:button name="pandanganPentadbirTanah" value="Hapus" class="btn" onclick="deleteRow3()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">5. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td>
                                <table id ="dataTable4">
                                    <c:set var="k" value="1" />
                                    <c:if test="${actionBean.senaraiKertasKandungan4 eq null}">
                                        <%--<s:hidden name="rowCount" value="1"/>--%>
                                        <tr><td><b>5.1</b></td>
                                            <td><s:textarea name="kandungan4" id="kandungan4" rows="5" cols="150"/></td></tr></c:if>
                                        <c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line">
                                           <%-- <s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan4${k}" id="kandungan4${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount4" value="${k-1}"/>
                                </table>
                        <tr><td align="right"><s:button name="perakuanPentadbirTanah" value="Tambah" class="btn" onclick="addRow4('dataTable4')" />
                                <s:button name="perakuanPentadbirTanah" value="Hapus" class="btn" onclick="deleteRow4()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>6. KEPUTUSAN JAWATANKUASA KHAS PENGAMBILAN TANAH NEGERI MELAKA</b></td></tr>
                        <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="ulasan1" rows="5" cols="150"/></font></td></tr><br>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>7. PERAKUAN PENGARAH TANAH DAN GALIAN</b></td></tr>
                        <tr><td>
                                <table id ="dataTable5">
                                    <c:set var="k" value="1" />
                                    <c:if test="${actionBean.senaraiKertasKandungan5 eq null}">
                                        <%--<s:hidden name="rowCount" value="1"/>--%>
                                        <tr><td><b>7.1</b></td>
                                            <td><s:textarea name="kandungan5" id="kandungan5" rows="5" cols="150"/></td></tr></c:if>
                                        <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                            <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan5${k}" id="kandungan5${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount5" value="${k-1}"/>
                                </table>
                        <tr><td align="right"><s:button name="perakuanPengarahTanah" value="Tambah" class="btn" onclick="addRow5('dataTable5')" />
                                <s:button name="perakuanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow5()" />

                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
            <%--<s:hidden name="idKandungan" value="${permohonanKertasKandungan.idKandungan}"/>--%>
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
            <%--|| if(validateForm2()) || if(validateForm3()) || if(validateForm4()) || if(validateForm5()) || if(validateForm6()) || if(validateForm7())--%>
        </p>
    </c:if>
    <%--<c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend></legend>
                <div class="content" align="center">
                    <table border="0" width="80%">
                        <div class="content" align="left">
                            <table align="left">
                            <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                    <u><b>MAJLIS MESYUARAT KERAJAAN NEGERI MELAKA</b></u><br /></td></tr>
                            <tr><td> &nbsp;</td></tr>
                            <tr><td align="left"><b><label><font color="black">PERSIDANGAN :</font></label>${actionBean.kertasBil.kandungan}/2010</b></td></tr>
                            <tr><td align="left"><b><label><font color="black">MASA        :</font></label>${actionBean.masa.kandungan}</b></td></tr>
                            <tr><td align="left"><b><label><font color="black">TARIKH :</font></label>${actionBean.tarikhmesyuarat.kandungan}</b></td></tr><br>
                            <tr><td align="left"><b><label><font color="black">TEMPAT      :</font></label>${actionBean.tempat.kandungan}</b></td></tr>
                            </table>
                        </div>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><font style="text-transform: uppercase"><b>PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.luas}, ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, BAGI ${actionBean.permohonan.sebab}.</b></font><br /><br /><hr /><br /></td></tr>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr><td><font style="text-transform:capitalize">${actionBean.tujuan}</font></td></tr><br>

                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                        <tr><td align="left">
                                <table id ="dataTable">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan}" var="line">
                                        <s:hidden name="rowCount" value="${i}"/>
                                        <tr><font style="text-transform: uppercase"><td style="display:none" >${line.idKandungan}</td><td style="font-weight:bold" onkeyup="this.value=this.value.toUpperCase();"><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan${i}" id="kandungan${i}" rows="5" cols="150">${line.kandungan}</s:textarea><c:out value="${line.kandungan}"/>
                                            </td></font></tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount1" value="${i-1}"/>
                                </table>&emsp;

                        <tr><td><b>3. PERIHAL TANAH</b></td></tr>
                        <tr><td align="left">
                                <table id ="dataTable2">
                                    <c:set var="j" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan2}" var="line">
                                        <s:hidden name="rowCount" value="${i}"/>
                                        <tr><font style="text-transform:capitalize"><td style="display:none" >${line.idKandungan}</td><td style="font-weight:bold" onkeyup="this.value=this.value.toUpperCase();"><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan${i}" id="kandungan${i}" rows="5" cols="150">${line.kandungan}</s:textarea><c:out value="${line.kandungan}"/>
                                            </td></font></tr>
                                            <c:set var="j" value="${j+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount2" value="${j-1}"/>
                                </table>&emsp;

                        <tr><td><b><font style="text-transform:uppercase">4 PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td align="left">
                                <table id ="dataTable3">
                                    <c:set var="k" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                        <s:hidden name="rowCount" value="${i}"/>
                                        <tr><td style="display:none" onkeyup="this.value=this.value.toUpperCase();"><font style="text-transform:capitalize">${line.idKandungan}</font></td><td style="font-weight:bold"><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan${i}" id="kandungan${i}" rows="5" cols="150">${line.kandungan}</s:textarea><c:out value="${line.kandungan}"/>
                                            </td></tr>
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount3" value="${k-1}"/>
                                </table>&emsp;


                                <tr><td><b><font style="text-transform: uppercase">4 PERAKUAN PENTADBIR TANAH MELAKA TENGAH</font></b></td></tr>
                                <tr><td>${actionBean.perakuanPentadbirTanah.kandungan}</td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">5. PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></td></tr>
                        <tr><td><font style="text-transform:uppercase">${actionBean.syorPtg.kandungan}&nbsp;</font></td></tr><br>
                        <tr><td></td></tr><br>
                    </table>
                </div>
            </fieldset>
        </div>
    </c:if>--%>
</s:form>
