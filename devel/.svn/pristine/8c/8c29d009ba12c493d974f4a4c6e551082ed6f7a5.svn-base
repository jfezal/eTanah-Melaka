<%--
    Document   : laporan_pemantauan
    Created on : Oct 7, 2011, 5:49:02 PM
    Author     : sitifariza.hanim
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

<s:form beanclass="etanah.view.penguatkuasaan.LaporanPemantauanActionBean" name="form2">
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
        
        <c:if test="${actionBean.kodNegeri eq '05'}">
                var cell4 = row.insertCell(3);
                cell4.setAttribute("id", "newRow");
                var imgUpload = document.createElement("img");
                imgUpload.setAttribute("src", "${pageContext.request.contextPath}/pub/images/icons/upload.png");
                imgUpload.setAttribute("name","gambar"+(rowcount-1));
                imgUpload.setAttribute("id","gambar"+(rowcount-1));
                imgUpload.setAttribute("alt","upload");
                imgUpload.setAttribute("onclick","javascript:muatNaikForm("+(rowcount-1)+")");
                imgUpload.setAttribute("height", "30");
                imgUpload.setAttribute("width", "30");
                imgUpload.setAttribute("onmouseover", "this.style.cursor='pointer'");
                cell4.appendChild(imgUpload);
        </c:if>
        
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
                                var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_pemantauan?deleteLaporan&idKandungan='
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
                        }
                    }

                }catch(e){
                    alert("Error in regenerateBill : "+e);
                }
            }


            function removeDiari(idKandungan){

                if(confirm('Adakah anda pasti untuk hapus?')) {
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_pemantauan?deleteLaporan&idKandungan='+idKandungan;
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

            function muatNaikForm(txt,idKand) {
                var folderId = "${actionBean.permohonan.folderDokumen.folderId}";
                var dokumenId = "";
                var idPermohonan = "${actionBean.permohonan.idPermohonan}";
                var dokumenKod = "LPM";
                var idHakmilik = "";
                var idRujukan = idKand;
                var idLaporan = "${actionBean.kertas.idKertas}";
                var textLaporan = $("#kandungan"+txt).val();
                
                var left = (screen.width/2)-(1000/2);
                var top = (screen.height/2)-(150/2);
                var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId + '&dokumenId='
                    + dokumenId + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idLaporan=' + idLaporan + 
                    '&idHakmilik=' + idHakmilik + '&idRujukan=' + idRujukan + '&textLaporanPemantauan=' + textLaporan;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
                
            }
            
            function doViewReport(v) {
                var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
            }
            
            function removeImej(idImej, idDokumen) {
                if(confirm('Adakah anda pasti untuk hapus?')) {
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_pemantauan?deleteSelected&idImej='+idImej+'&idDokumen='+idDokumen;
                    $.get(url,
                    function(data){
                        $("#DIVSenaraiLaporan").replaceWith($('#DIVSenaraiLaporan', $(data)));
                    },'html');
                }
            }
           
            function refreshLaporanPemantauan(){
                var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_pemantauan?refreshpage';
                $.get(url,
                function(data){
                    $("#DIVSenaraiLaporan").replaceWith($('#DIVSenaraiLaporan', $(data)));
                },'html');
            }
    </script>
    <s:messages/>
    <s:errors/>
    <%--<s:hidden name="permohonan.idPermohonan" />--%>
    <s:hidden name="permohonan.idPermohonan" />
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Laporan Pemantauan</legend>
                <p>
                    <label><em>*</em>Tajuk :</label>
                    <s:textarea class="normal_text" name="tajuk" id="tajuk" cols="70" rows="10" onkeydown="limitText(this,4000);" onkeyup="limitText(this,4000);"> </s:textarea>
                </p>

                <div id="DIVSenaraiLaporan">
                    <s:hidden name="recordCount" id="recordCount"/>
                    <div class="content" align="center">
                        <table  width="70%" id="tbl" class="tablecloth">
                            <tr>
                                <th width="1%" align="center"><b>Pilih</b></th>
                                <th width="1%" align="center"><b>Bil</b></th>
                                <th width="10%" align="center"><b><em>*</em>Laporan</b></th>
                                <c:if test="${actionBean.kodNegeri eq '05'}">
                                    <th width="1%" align="center"><b>Imej</b></th>
                                </c:if>                            
                                <%--<b>Laporan :</b>--%>
                            </tr>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.senaraiKertas}" var="line">
                                <c:choose>
                                    <c:when test="${actionBean.kodNegeri eq '05'}">
                                        <tr style="font-weight:bold">
                                            <td><s:checkbox name="pilih${i}" id="pilih${i}" /></td>
                                            <td><c:out value="${i+1}"/></td>
                                            <td>
                                                <s:textarea name="kandungan${i}" id="kandungan${i}" value="${line.kandungan}" cols="100" rows="7" class="normal_text"/>
                                            </td>
                                            <td>
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                                     onclick="muatNaikForm('${i}','${line.idKandungan}');" height="30" width="30" alt="muat naik"
                                                     onmouseover="this.style.cursor='pointer';" title="Muat naik Dokumen Gambar Laporan Pemantauan"/>
                                                <c:forEach items="${line.senaraiKertasImej}" var="img">
                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                         onclick="doViewReport('${img.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen Gambar Laporan Pemantauan"/> <font size="1">${count}-${img.dokumen.tajuk}</font>
                                                    /
                                                    <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                         onclick="removeImej('${img.idImej}','${img.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                                         onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${img.dokumen.kodDokumen.nama}"/>
                                                </c:forEach>
                                            </td>
                                            <s:hidden name="idKertas${i}" id="idKertas${i}" value="${line.idKandungan}" />
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr style="font-weight:bold">
                                            <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                            <td><c:out value="${i+1}"/></td>
                                            <c:if test="${line.subtajuk eq actionBean.stageId}">
                                                <td>
                                                    <s:textarea name="kandungan${i}" id="kandungan${i}" value="${line.kandungan}" cols="100" rows="7" class="normal_text"/>
                                                </td>
                                            </c:if>
                                            <c:if test="${line.subtajuk ne actionBean.stageId}">
                                                <td>
                                                    <s:textarea name="kandungan${i}" id="kandungan${i}" value="${line.kandungan}" cols="100" rows="7" readonly="true"/>
                                                </td>
                                            </c:if>

                                            <%--llllllll ${actionBean.stageId}--%>
                                            <s:hidden name="idKertas${i}" id="idKertas${i}" value="${line.idKandungan}" />

                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>


                        </table>
                        <br/>
                        <table>
                            <tr><td align="center">
                                    <p align="center">** Sila tanda di petak Pilih untuk hapus.</p>
                                    <br/>
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>
                                    <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow1('tbl')"/>
                        </table>
                    </div>
                </div>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${view}">
        <div class="subtitle displaytag">
            <fieldset class="aras1">
                <legend>Laporan :</legend>
                <p>
                    ${actionBean.tajuk}&nbsp;
                </p>

                <div class="content" align="center"  >
                    <display:table name="${actionBean.senaraiKertas}" id="line" class="tablecloth" requestURI="/penguatkuasaan/laporan_pemantauan" pagesize="10" style="width:75%">
                        <display:column title="Bil" style="width:3%">${line_rowNum}</display:column>
                        <display:column property="kandungan" title="Laporan" style="text-align:justify"/>
                    </display:table>
                </div>
            </fieldset>
        </div>

    </c:if>


</s:form>

