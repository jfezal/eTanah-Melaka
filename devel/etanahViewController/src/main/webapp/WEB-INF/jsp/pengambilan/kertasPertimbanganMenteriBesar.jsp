<%-- 
    Document   : kertasPertimbanganMenteriBesar
    Created on : May 26, 2010, 4:13:01 PM
    Author     : MASSITA
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
        document.form2.rowCount.value = 1;
        var table = document.getElementById(tableID);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"2." +(rowCount+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan1"+(rowCount+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan1"+(rowCount+1);
        cell1.appendChild(element1);
        document.form2.rowCount.value=rowCount +1;
    }

    function deleteRow()
    {
        var i = document.form2.rowCount.value;
        var x= document.getElementById('dataTable').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable').deleteRow(i-1);
        document.form2.rowCount.value = i -1;

        var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn?deleteSingle&idKandungan='
            +idKandungan;
         alert("data di hapuskan");
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

     function lower(ustr)
    {
            var str=ustr.value;
            ustr.value=str.toLowerCase();
    }

     function sCase(){
      val=document.form2.kandungan1.value;
      result=new Array();
      result2='';
      count=0;
      endSentence=new Array();
      for (var i=1;i<val.length;i++){
      if(val.charAt(i)=='.'||val.charAt(i)=='!'||val.charAt(i)=='?'){
        endSentence[count]=val.charAt(i);
        count++
      }
    }

    var val2=val.split(/[.|?|!]/);

    if(val2[val2.length-1]=='')val2.length=val2.length-1;

    for (var j=0;j<val2.length;j++){
      val3=val2[j];

    if(val3.substring(0,1)!=' ')val2[j]=' '+val2[j];

    var temp=val2[j].split(' ');
    var incr=0;

    if(temp[0]==''){
      incr=1;
    }

    temp2=temp[incr].substring(0,1);
    temp3=temp[incr].substring(1,temp[incr].length);
    temp2=temp2.toUpperCase();
    temp3=temp3.toLowerCase();
    temp[incr]=temp2+temp3;

    for (var i=incr+1;i<temp.length;i++){
      temp2=temp[i].substring(0,1);
      temp2=temp2.toLowerCase();
      temp3=temp[i].substring(1,temp[i].length);
      temp3=temp3.toLowerCase();
      temp[i]=temp2+temp3;
    }

    if(endSentence[j]==undefined)endSentence[j]='';
      result2+=temp.join(' ')+endSentence[j];
    }

    if(result2.substring(0,1)==' ')result2=result2.substring(1,result2.length);
      document.form2.kandungan1.value=result2;
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

        var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn?deleteSingle&idKandungan='
            +idKandungan;
        alert("data di hapuskan");
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

        var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn?deleteSingle&idKandungan='
            +idKandungan;
        alert("data di hapuskan");
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.KertasPertimbanganMBActionBean" name="form2">
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
                        <div class="content" align="left">
                            <%--<table align="left">--%>
                            <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                    <u><b>KERTAS PERTIMBANGAN MENTERI BESAR</b></u><br /></td></tr>
                            <tr><td> &nbsp;</td></tr>
                            <tr><td align="left"><b><label><font color="black">PERSIDANGAN :</font></label><s:text name="kertasBil.kandungan"  size="10" style="text-align:left" />/2010</b></td></tr>
                            <tr><td align="left"><b><label><font color="black">MASA        :</font></label><s:text name="masa.kandungan"  size="30" style="text-align:left" /></b></td></tr>
                            <tr><td align="left"><b><label><font color="black">TARIKH :</font></label><s:text name="tarikhmesyuarat.kandungan" id="datepicker" class="datepicker" size="30" style="text-align:left"/></b></td></tr><br>
                            <tr><td align="left"><b><label><font color="black">TEMPAT      :</font></label><s:textarea name="tempat.kandungan" cols="50" rows="5" style="text-align:left"/></b></td></tr>
                            <%--</table>--%>
                        </div>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><font style="text-transform: uppercase"><b>PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.luas}, ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, BAGI ${actionBean.permohonan.sebab}.</b></font><br /><br /><hr /><br /></td></tr>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr><td><b>&nbsp;&nbsp;&nbsp;1.1</b></td></tr>
                            <td style="; padding:0em 1em"><s:textarea rows="5" cols="150" name="tujuan"/></td>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                         <tr><td>
                                <%--<s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow('dataTable')" />
                                <s:button name="latarBelakang" value="Hapus" class="btn" onclick="deleteRow()" />--%>
                                <%--<s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow10('dataTable')" />--%>
                                <table id ="dataTable">
                                    <c:set var="i" value="1" />
                                    <c:if test="${actionBean.senaraiKertasKandungan eq null}">
                                        <%--<s:hidden name="rowCount" value="1"/>--%>
                                        <tr><td><b>2.1</b></td>
                                            <td><font style="text-transform: lowercase"><s:textarea name="kandungan1" id="kandungan1" rows="5" cols="150"/></font></td></tr></c:if>
                                        <c:forEach items="${actionBean.senaraiKertasKandungan}" var="line">
                                            <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: lowercase"><s:textarea name="kandungan1${i}" id="kandungan1${i}" rows="5" cols="150" onkeyup="sCase()">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <%--<s:hidden name="rowCount" value="${i+1}"/>--%>
                                        <s:hidden name="rowCount" value="${i-1}"/>
                                </table>
                        <tr><td align="right"><s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow('dataTable')" />
                                                <s:button name="latarBelakang" value="Hapus" class="btn" onclick="deleteRow()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>3. PERIHAL TANAH</b></td></tr>
                        <tr><td>
                                <table id ="dataTable2">
                                    <c:set var="j" value="1" />
                                    <c:if test="${actionBean.senaraiKertasKandungan2 eq null}">
                                        <%--<s:hidden name="rowCount" value="1"/>--%>
                                        <tr><td><b>3.1</b></td>
                                            <td><s:textarea name="kandungan2" id="kandungan2" rows="5" cols="150"/></td></tr></c:if>
                                        <c:forEach items="${actionBean.senaraiKertasKandungan2}" var="line">
                                            <%--<s:hidden name="rowCount" value="${i}"/>--%><%--onkeyup="this.value=this.value.capitalize();"--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan2${j}" id="kandungan2${j}" rows="5" cols="150" onkeyup="lower(this);">${line.kandungan}</s:textarea>
                                            </td></tr>
                                            <c:set var="j" value="${j+1}" />
                                        </c:forEach>
                                        <%--<s:hidden name="rowCount2" value="${j+1}"/>--%>
                                        <s:hidden name="rowCount2" value="${j-1}"/>
                                </table>
                        <tr><td align="right"><s:button name="perihalTanah" value="Tambah" class="btn" onclick="addRow2('dataTable2')" />
                                <s:button name="perihalTanah" value="Hapus" class="btn" onclick="deleteRow2()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform: uppercase">4. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <%--<c:if test="ptd">--%>
                        <tr><td>
                        <%--<c:if test="${ptd}">--%>
                                <table id ="dataTable3">
                                    <c:set var="k" value="1" />
                                    <c:if test="${actionBean.senaraiKertasKandungan3 eq null}">
                                        <%--<s:hidden name="rowCount" value="1"/>--%>
                                        <tr><td><b>4.1</b></td>
                                            <td><s:textarea name="kandungan3" id="kandungan3" rows="5" cols="150"/></td></tr>
                                    </c:if>
                                        <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                            <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan3${k}" id="kandungan3${k}" rows="5" cols="150" onkeyup="lower(this);">${line.kandungan}</s:textarea>
                                            </td></tr>
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <%--<s:hidden name="rowCount3" value="${k+1}"/>--%>
                                        <s:hidden name="rowCount3" value="${k-1}"/>
                                  </table>
                           <tr><td align="right"><s:button name="perakuanPentadbirTanah" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                            <s:button name="perakuanPentadbirTanah" value="Hapus" class="btn" onclick="deleteRow3()" />
                            <%--</c:if>--%>

                              <tr><td>
                              <%--<c:if test="${!ptd}">
                               <table id ="dataTable3">
                                    <c:set var="k" value="1" />
                                    <c:if test="${actionBean.senaraiKertasKandungan3 eq null}">
                                        <s:hidden name="rowCount" value="1"/>
                                        <tr><td><b>4.1</b></td>
                                            <td><s:textarea name="kandungan3" id="kandungan3" rows="5" cols="150" disabled="true"/></td></tr>
                                    </c:if>
                                        <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                            <s:hidden name="rowCount" value="${i}"/>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan3${k}" id="kandungan3${k}" rows="5" cols="150" onkeyup="lower(this);" disabled="true">${line.kandungan}</s:textarea>
                                            </td></tr>
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount3" value="${k-1}"/>

                               </table>
                        <tr><td align="right"><s:button name="perakuanPentadbirTanah" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                        <s:button name="perakuanPentadbirTanah" value="Hapus" class="btn" onclick="deleteRow3()" disabled="true"/>
                        </c:if>--%>


                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform: uppercase">5. PERAKUAN PENGARAH TANAH DAN GALIAN, NEGERI MELAKA&nbsp;</font></b></td></tr>
                        <%--<c:if test="${ptg}">--%>
                            <tr><td><s:textarea name="syorPtg.kandungan" rows="5" cols="150"/></td></tr><br>
                        <%--</c:if>--%>
                        <%--<c:if test="${!ptg}">
                            <tr><td><s:textarea name="syorPtg.kandungan" rows="5" cols="150" disabled="true"/></td></tr><br>
                        </c:if>--%>
                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
            <%--<s:hidden name="idKandungan" value="${permohonanKertasKandungan.idKandungan}"/>--%>
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>

            <%--<c:if test="${ptg}">
                <s:button name="simpanPtg" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </c:if>
             <c:if test="${ptd}">
                <s:button name="simpanPtd" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </c:if>--%>
        </p>
    </c:if>
    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend></legend>
                <div class="content" align="center">
                    <table border="0" width="80%">
                        <div class="content" align="left">
                            <%--<table align="left">--%>
                            <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                    <u><b>MAJLIS MESYUARAT KERAJAAN NEGERI MELAKA</b></u><br /></td></tr>
                            <tr><td> &nbsp;</td></tr>
                            <tr><td align="left"><b><label><font color="black">PERSIDANGAN :</font></label>${actionBean.kertasBil.kandungan}/2010</b></td></tr>
                            <tr><td align="left"><b><label><font color="black">MASA        :</font></label>${actionBean.masa.kandungan}</b></td></tr>
                            <tr><td align="left"><b><label><font color="black">TARIKH :</font></label>${actionBean.tarikhmesyuarat.kandungan}</b></td></tr><br>
                            <tr><td align="left"><b><label><font color="black">TEMPAT      :</font></label>${actionBean.tempat.kandungan}</b></td></tr>
                            <%--</table>--%>
                        </div>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><font style="text-transform: uppercase"><b>PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.luas}, ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, BAGI ${actionBean.permohonan.sebab}.</b></font><br /><br /><hr /><br /></td></tr>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr><td><td style="; width: 10%"><b>1.1</b>${actionBean.tujuan}</td></tr><br>

                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                        <tr><td align="left">
                                <table id ="dataTable">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan}" var="line">
                                        <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr><td style="display:none" >${line.idKandungan}</td><td style="font-weight:bold" onkeyup="lower(this);"><c:out value="${line.subtajuk}"/></td>
                                            <td><%--<s:textarea name="kandungan${i}" id="kandungan${i}" rows="5" cols="150">${line.kandungan}</s:textarea>--%><c:out value="${line.kandungan}"/>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <%--<s:hidden name="rowCount" value="${i+1}"/>--%>
                                        <s:hidden name="rowCount" value="${i-1}"/>
                                </table>&emsp;

                        <tr><td><b>3. PERIHAL TANAH</b></td></tr>
                        <tr><td align="left">
                                <table id ="dataTable2">
                                    <c:set var="j" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan2}" var="line">
                                        <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr><font style="text-transform:lowercase"><td style="display:none" >${line.idKandungan}</td><td style="font-weight:bold" onkeyup="lower(this);"><c:out value="${line.subtajuk}"/></td>
                                            <td><%--<s:textarea name="kandungan${i}" id="kandungan${i}" rows="5" cols="150">${line.kandungan}</s:textarea>--%><c:out value="${line.kandungan}"/>
                                            </td></font></tr>
                                            <c:set var="j" value="${j+1}" />
                                        </c:forEach>
                                        <%--<s:hidden name="rowCount2" value="${j+1}"/>--%>
                                        <s:hidden name="rowCount2" value="${j-1}"/>
                                </table>&emsp;

                        <tr><td><b><font style="text-transform:uppercase">4 PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td align="left">
                                <table id ="dataTable3">
                                    <c:set var="k" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                        <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr><td style="display:none" onkeyup="lower(this);">${line.idKandungan}</td><td style="font-weight:bold"><c:out value="${line.subtajuk}"/></td>
                                            <td><%--<s:textarea name="kandungan${i}" id="kandungan${i}" rows="5" cols="150">${line.kandungan}</s:textarea>--%><c:out value="${line.kandungan}"/>
                                            </td></tr>
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <%--<s:hidden name="rowCount3" value="${k+1}"/>--%>
                                        <s:hidden name="rowCount3" value="${k-1}"/>
                                </table>&emsp;


                                <%--<tr><td><b><font style="text-transform: uppercase">4 PERAKUAN PENTADBIR TANAH MELAKA TENGAH</font></b></td></tr>
                                <tr><td>${actionBean.perakuanPentadbirTanah.kandungan}</td></tr><br>--%>
                        <tr><td><b><font style="text-transform: uppercase">5. PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></td></tr>
                        <tr><td><font style="text-transform: capitalize">${actionBean.syorPtg.kandungan}&nbsp;</font></td></tr><br>
                        <tr><td></td></tr><br>
                    </table>
                </div>
            </fieldset>
        </div>
    </c:if>
</s:form>