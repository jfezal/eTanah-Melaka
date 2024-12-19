<%-- 
    Document   : laporan_ringkas
    Created on : Oct 4, 2012, 10:49:47 PM
    Author     : Siti Fariza Hanim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>

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
    p{
        color:black;
        font-weight:normal;
        font-size:13px;
        padding-top:2px;
        padding-bottom:2px;
        line-height: 15pt ;
        text-align: justify;
    }
</style>

<script language="javascript" type="text/javascript">

    function validateForm(){

        var bil =  document.getElementById("recordCount").value;
        var tajuk = document.getElementById('tajuk');

        if(tajuk.value == "" ){
            alert("Sila isikan Tajuk terlebih dahulu");
            $('#tajuk').focus();
            return false;
        }

        if(document.getElementById("recordCount").value == "" ){
            alert("Sila isikan  butiran laporan terlebih dahulu");
            return false;
        }

        for (var i = 0; i < bil; i++){

            var kandungan = document.getElementById('kandungan'+i);
            if(kandungan.value == "" ){
                alert("Sila isikan butiran laporan terlebih dahulu");
                $('#kandungan'+i).focus();
                return false;
            }

        }
        
        if(document.getElementById("ulasanPPT").value == "" ){
            alert("Sila isikan ulasan PPT terlebih dahulu");
            $('#ulasanPPT').focus();
            return false;
        }
        
        return true;
    }


    function addRow (tableid){

        var table = document.getElementById (tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        document.getElementById("recordCount").value =rowcount;

        var cell1 = row.insertCell(0);
        var e1 = document.createElement("INPUT");
        e1.setAttribute("type","checkbox");
        e1.setAttribute("name","pilih" +(rowcount-1));
        cell1.appendChild(e1);

        var cell2 = row.insertCell(1);
        var bil = document.createTextNode(rowcount);
        cell2.appendChild(bil);


        var cell3 = row.insertCell(2);
        var e2 = document.createElement("textarea");
        e2.t = "kandungan"+(rowcount-1);
        e2.rows = 7;
        e2.cols = 100;
        e2.name ="kandungan"+(rowcount-1);
        e2.id ="kandungan"+(rowcount-1);
        e2.onchange = function(){convertText(this,"kandungan" +(rowcount-1));};
        cell3.appendChild(e2);

    }

    function deleteRow(r,tableid){
        alert(tableid);
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var i=r.parentNode.parentNode.rowIndex;
            document.getElementById('tbl').deleteRow(i);
        }
        regenerateBill(tableid);

    }

    function deleteRow1(tableid) {
    <%--alert(tableid);--%>
            var idKandungan;
            try {
                var table = document.getElementById(tableid);

                var rowCount = table.rows.length;
                var j=0;
                for(var i=0; i<rowCount; i++) {
    <%--alert(rowCount);--%>
                    var row = table.rows[i];
    <%--alert('how :'+ row);--%>
                    var chkbox = row.cells[0].childNodes[0];
    <%--alert('hai');--%>
                    if(null != chkbox && true == chkbox.checked) {
    <%--alert('are');--%>
                        idKandungan = $("#idKertas"+(i+j-1)).val();
    <%--alert (idKandungan);--%>
                        if(document.getElementById("idKertas"+(i+j-1)) != null){
                            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_ringkas?deleteLaporan&idKandungan='
                                +idKandungan;

                            $.get(url,
                            function(data){
                                $('#page_div').html(data);
                            },'html');
                        }

                        table.deleteRow(i);
                        rowCount--;
                        i--;
                        j++;

                    }
                }
            }catch(e) {
    <%--alert(e);--%>
            }

            regenerateBill(tableid);
        }

        function regenerateBill(tableid){
            try{
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                document.getElementById("recordCount").value =rowCount-1;

                if(rowCount > 1){
                    for(var i=1;i<rowCount;i++){
                        table.rows[i].cells[1].childNodes[0].data= i;
                        table.rows[i].cells[2].childNodes[0].name= 'kandungan'+(i-1);
                        table.rows[i].cells[2].childNodes[0].id= 'kandungan'+(i-1);
                    }
                }

            }catch(e){
                alert("Error in regenerateBill : "+e);
            }
        }


        function removeDiari(idKandungan){

            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_ringkas?deleteLaporan&idKandungan='+idKandungan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }


        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i;
        }


        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }

</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLaporanRingkasActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.idPermohonan" />
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Laporan Ringkas</legend>
                <p>
                    <label><em>*</em>Tajuk :</label>
                    <s:textarea class="normal_text" name="tajuk" id="tajuk" cols="70" rows="10" onkeydown="limitText(this,4000);" onkeyup="limitText(this,4000);"> </s:textarea>
                </p>
                <p>
                    <label><em>*</em>Tarikh :</label>
                    <s:text name="tarikhLaporan" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="datepicker" />&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>
                <p>
                    <label><em>*</em>Masa :</label>
                    <s:select name="jam" id="jam" style="width:50px;">
                        <s:option value=""> Jam </s:option>
                        <c:forEach var="jam" begin="1" end="12">
                            <s:option value="${jam}">${jam}</s:option>
                        </c:forEach>
                    </s:select>
                    <s:select name="minit" id="minit" style="width:70px;">
                        <s:option value=""> Minit </s:option>
                        <c:forEach var="minit" begin="00" end="59" >
                            <c:choose>
                                <c:when test="${minit > 9}"><s:option value="${minit}">${minit}</s:option></c:when>
                                <c:otherwise><s:option value="0${minit}">0${minit}</s:option></c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </s:select>
                    <s:select name="ampm" id="ampm" style="width:80px">
                        <s:option value="">Pilih</s:option>
                        <s:option value="AM">PAGI</s:option>
                        <s:option value="PM">PETANG</s:option>
                    </s:select>
                </p>

                <s:hidden name="recordCount" id="recordCount"/>
                <div class="content" align="center">
                    <table  width="50%" id="tbl" class="tablecloth">
                        <tr>
                            <th width="1%" align="center"><b>Pilih</b></th>
                            <th width="1%" align="center"><b>Bil</b></th>
                            <th width="10%" align="center"><b><em>*</em>Laporan</b></th>
                            <%--<b>Laporan :</b>--%>
                        </tr>
                        <c:set value="0" var="i"/>
                        <c:forEach items="${actionBean.senaraiKertas}" var="line">
                            <tr style="font-weight:bold">

                                <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                <td><c:out value="${i+1}"/></td>
                                <td>
                                    <s:textarea name="kandungan${i}" id="kandungan${i}" value="${line.kandungan}" cols="100" rows="7" class="normal_text"/>
                                </td>
                                <s:hidden name="idKertas${i}" id="idKertas${i}" value="${line.idKandungan}" />
                            </tr>
                            <c:set var="i" value="${i+1}" />
                        </c:forEach>

                    </table>
                    <br/>

                    <table>
                        <tr><td align="center">
                                <p align="center">** Sila tanda di petak Pilih untuk hapus.</p>
                                <br/>
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow1('tbl')"/>
                    </table>

                </div>

                <legend>Ulasan</legend>
                <p>
                    <label><font style="text-transform: capitalize">Syor ${actionBean.pengguna.jawatan} : </font></label>
                    <s:textarea name="ulasanPPT" id="ulasanPPT" cols="70" rows="10" onchange="this.value=this.value.toUpperCase();"/>
                </p>


                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>
                </p>

            </fieldset>
        </div>
    </c:if>

    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Laporan Ringkas</legend>
                <p>
                    <label>Tajuk :</label>
                    ${actionBean.tajuk}&nbsp;
                </p>
                <p>
                    <label>Tarikh :</label>
                    <c:if test="${actionBean.kertas.tarikhSidang ne null}"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.kertas.tarikhSidang}"/>&nbsp;</c:if>
                    <c:if test="${actionBean.kertas.tarikhSidang eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Masa :</label>
                    <c:if test="${actionBean.kertas.tarikhSidang ne null}">
                        <fmt:formatDate pattern="hh:mm" value="${actionBean.kertas.tarikhSidang}"/>
                        <fmt:formatDate pattern="aaa" value="${actionBean.kertas.tarikhSidang}" var="time"/>
                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                    </c:if>
                    <c:if test="${actionBean.kertas.tarikhSidang eq null}"> Tiada Data </c:if>
                </p>
                <div class="content" align="center"  >
                    <display:table name="${actionBean.senaraiKertas}" id="line" class="tablecloth" style="width:75%">
                        <display:column title="Bil" style="width:3%">${line_rowNum}</display:column>
                        <display:column property="kandungan" title="Laporan" />
                    </display:table>
                </div>


                <br>

                <c:if test="${ulasanPPTK}">
                    <legend>Ulasan</legend>
                    <c:if test="${actionBean.mohonLaporUlasPPT ne null}">
                        <p>
                            <label>Syor ${actionBean.mohonLaporUlasPPT.kodPeranan.nama} :</label>
                        </p>
                        <c:if test="${actionBean.ulasanPPT ne null}">
                            <table>
                                <tr><p align="justify" style="text-transform: uppercase"/>
                                <td valign="top">
                                    <font size="2px;">${actionBean.ulasanPPT}&nbsp;</font></td>
                                </tr>
                            </table>
                        </c:if>
                        <c:if test="${actionBean.ulasanPPT eq null}"> Tiada Data </c:if>

                    </c:if>
                    <p>
                        <label><font style="text-transform: capitalize">Syor ${actionBean.pengguna.jawatan} : </font></label>
                        <s:textarea name="ulasanPPTK" cols="70" rows="10" onchange="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanUlasan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>

                </c:if>
                <c:if test="${!ulasanPPTK}">
                    <c:if test="${actionBean.mohonLaporUlasPPT ne null || actionBean.mohonLaporUlasPPTK ne null}"><legend>Ulasan</legend></c:if>
                    <table>
                        <c:if test="${actionBean.mohonLaporUlasPPT ne null}">
                            <tr>
                                <td><p><label>Syor ${actionBean.mohonLaporUlasPPT.kodPeranan.nama} :</label></c:if></p>
                                    <table>
                                        <td><p align="justify" style="text-transform: uppercase"><c:if test="${actionBean.ulasanPPT ne null}"> ${actionBean.ulasanPPT}&nbsp;
                                                <c:if test="${actionBean.ulasanPPT eq null}"> Tiada Data </c:if></p></td>
                                    </table>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.mohonLaporUlasPPTK ne null}">
                            <tr>
                                <td>
                                    <p>
                                        <label>Syor ${actionBean.mohonLaporUlasPPTK.kodPeranan.nama} :</label>
                                    </p>
                                    <table>
                                        <td><p align="justify" style="text-transform: uppercase"><c:if test="${actionBean.ulasanPPTK ne null}"> ${actionBean.ulasanPPTK}&nbsp; </c:if>
                                                <c:if test="${actionBean.ulasanPPTK eq null}"> Tiada Data </c:if></p></td>
                                    </table>


                                </td>
                            </tr>
                        </c:if>
                    </table>


                </c:if>

            </fieldset>
        </div>

    </c:if>


</s:form>

