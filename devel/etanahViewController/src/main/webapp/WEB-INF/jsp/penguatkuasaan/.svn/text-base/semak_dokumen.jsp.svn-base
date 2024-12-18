<%-- 
    Document   : dokumen_iringan
    Created on : Apr 14, 2011, 4:23:46 PM
    Author     : latifah.iskak
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>

<script type="text/javascript">
    function addRow (tableid){

        var table = document.getElementById (tableid);
            var rowcount = table.rows.length;
            var row = table.insertRow(rowcount);
            document.getElementById("recordCount").value =rowcount;

            //alert(rowcount);
            var cell1 = row.insertCell(0);
            var e1 = document.createElement("INPUT");
            e1.setAttribute("type","checkbox");
            e1.setAttribute("name","pilih" +(rowcount-1));
            cell1.appendChild(e1);

            var cell2 = row.insertCell(1);
            var bil = document.createTextNode(rowcount);
            cell2.appendChild(bil);

            var cell3 = row.insertCell(2);
            var e3 = document.createElement("select");
            e3.setAttribute("name","kodDokumen"+(rowcount-1));
            e3.setAttribute("id","kodDokumen" +(rowcount-1));
            e3.setAttribute("style","width:250px;");
        e3.onchange = function(){checkRecord(this.value,(rowcount-1));};

        //for "sila pilih"
            var option1 = document.createElement("option");
            option1.text = " Sila pilih ";
            option1.value ="";
            e3.options.add(option1);
    
    <c:forEach items="${listUtil.senaraiKodDokumen}" var="line">
            var option2 = document.createElement("option");
            var textVal2=document.createTextNode("${line.nama}");
            option2.appendChild(textVal2);
            option2.setAttribute("value","${line.kod}"); 
        <%--option2.setAttribute("style","width:170px");--%>
                e3.appendChild(option2);
    </c:forEach>
                
            cell3.appendChild(e3);

            var cell4 = row.insertCell(3);
            var e4 = document.createElement("textarea");
            e4.t = "catatan"+(rowcount-1);
            e4.rows = 5;
            e4.cols = 40;
            e4.name ="catatan"+(rowcount-1);
            e4.id ="catatan"+(rowcount-1);
            e4.onchange = function(){convertText(this,"catatan" +(rowcount-1));};
            cell4.appendChild(e4);


            var cell5 = row.insertCell(4);

        }

        function muatNaikForm(row) {
            //alert(row);
            var idPermohonan =  $("#idPermohonan").val();
            var folderId =  $("#folderId").val();
            var dokumenKod = $("#kodDokumen"+row).val();
            //
            //alert(dokumenKod);

            if(dokumenKod == ""){
                alert("Sila pilih jenis dokumen terlebih dahulu.");
                $('#kodDokumen'+row).focus();
            }else{
                var left = (screen.width/2)-(1000/2);
                var top = (screen.height/2)-(150/2);
                var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId+ '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idRujukan=' + 'DokumenDisemak' + '&idLaporan=' + row;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
            }
        }


        function validateForm(){
            var bil =  document.getElementById("recordCount").value;
            for (var i = 0; i < bil; i++){
                var kodDok = document.getElementById('kodDokumen'+i).value;

                if(kodDok == "" ){
                    alert("Sila pilih jenis dokumen");
                    $('#kodDokumen'+i).focus();
                    return false;
                }
            }

            return true;
        }

        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }

        function refreshPageSemakDokumen(id){
            // alert(id);
            var url = '${pageContext.request.contextPath}/penguatkuasaan/semak_dokumen?refreshpage';
            $.get(url,
            function(data){
                $("#uploadPic"+id).replaceWith($('#uploadPic'+id, $(data)));
            },'html');
        }

        function deleteRow(tableid) {
            var idSemakDokumen;
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    if(null != chkbox && true == chkbox.checked) {
                        idSemakDokumen = $("#idSemakDokumen"+(i+j-1)).val();

                        if(document.getElementById("idSemakDokumen"+(i+j-1)) != null){

                            var url = '${pageContext.request.contextPath}/penguatkuasaan/semak_dokumen?delete&idPermohonanSemakDokumen='
                                +idSemakDokumen;

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
                alert(e);
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

        function removeImej(idImej,id) {
            //alert(id);
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/semak_dokumen?deleteSelected&idImej='+idImej;
                $.get(url,
                function(data){
                    $("#semakDokumenDiv"+id).replaceWith($('#semakDokumenDiv'+id, $(data)));
                },'html');
            }
        }

        function refreshPage(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/semak_dokumen?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function refreshPageDokumenDisemak(id){
            // alert(id);
            var url = '${pageContext.request.contextPath}/penguatkuasaan/semak_dokumen?refreshpage';
            $.get(url,
            function(data){
                $("#semakDokumenDiv"+id).replaceWith($('#semakDokumenDiv'+id, $(data)));
            },'html');
        }

        function checkRecord(value,id,status){
            var bil =  document.getElementById("recordCount").value;
            
            for (var i = 0; i < bil; i++){
                var kod = document.getElementById('kodDokumen'+i).value;
                if(i != id){
                    if(value == kod){
                        alert("Jenis dokumen ini telah dipilih. Sila pilih jenis dokumen yang lain.");
                        $('#kodDokumen'+id).focus();

                        if(status == 'saved'){
                            document.getElementById("kodDokumen"+id).value = document.getElementById("kodDok"+id).value;
                        }else{
                            document.getElementById("kodDokumen"+id).value = '';
                        }
                        
                        return false;
                    }
                }
               
            }
        }
    
</script>
<s:form beanclass="etanah.view.penguatkuasaan.SemakDokumenActionBean" name="form1">
    <s:hidden name="permohonan.idPermohonan" id="idPermohonan"/>
    <s:hidden name="permohonan.folderDokumen.folderId" id="folderId"/>
    <s:errors/>
    <s:messages/>


    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Senarai Dokumen
            </legend>
            <br/>

            <div class="content">
                <p>
                <fieldset class="aras2">
                    <legend>
                        Dokumen Disemak
                    </legend>
                    <c:if test="${edit}">
                        <div class="instr-fieldset">
                            <br><font color="red">PERINGATAN:</font> Sila klik tambah untuk menambah senarai dokumen yang hendak disemak.

                        </div>

                        <div align="center" >

                            <p>
                                <s:hidden name="recordCount" id="recordCount"/>
                            </p>

                            <table  width="80%" id="tbl" class="tablecloth" align="center">
                                <tr>
                                    <th  width="1%" align="center"><b>Pilih</b></th>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="5%" align="center"><b>Jenis Dokumen</b></th>
                                    <th  width="3%" align="center"><b>Kesalahan</b></th>
                                    <th  width="10%" align="center"><b>Lampiran</b></th>
                                </tr>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.listPermohonanSemakDokumen}" var="line">
                                    <tr style="font-weight:bold">
                                        <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                        <td><c:out value="${i+1}"/></td>
                                        <s:hidden name="idSemakDokumen${i}" id="idSemakDokumen${i}" value="${line.idPermohonanSemakDokumen}" />
                                        <td><s:select name="kodDokumen${i}" id="kodDokumen${i}" value="${line.kodDokumen.kod}" style="width:250px;" onchange="checkRecord(this.value,'${i}','saved')">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodDokumen}" label="nama" value="kod" sort="nama" />
                                            </s:select>
                                            <input type="hidden" id="kodDok${i}" name="kodDok${i}" value="${line.kodDokumen.kod}">
                                        </td>
                                        <td><s:textarea name="catatan${i}" id="catatan${i}" value="${line.catatan}" cols="40" rows="5" class="normal_text" onchange="convertText(this,this.id)"/></td>
                                        <td>
                                            <div id="semakDokumenDiv${i}">
                                                <p>

                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                                         onclick="muatNaikForm('${i}');return false;" height="30" width="30" alt="Muat Naik"
                                                         onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                                                <p align="center">
                                                    <c:set value="1" var="count"/>
                                                    <c:forEach items="${actionBean.listKandunganFolder}" var="senarai">
                                                        <c:if test="${senarai.dokumen.kodDokumen.kod eq line.kodDokumen.kod}">
                                                            <c:if test="${senarai.dokumen.namaFizikal != null}">
                                                                <%-- ${count})--%>
                                                                <br>
                                                                -
                                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                                                <%--${senarai.dokumen.kodDokumen.nama} ${count} (--%>${senarai.dokumen.tajuk}<%--)--%>/
                                                                <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                     onclick="removeImej('${senarai.dokumen.idDokumen}','${i}');" height="15" width="15" alt="Hapus"
                                                                     onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                                                <c:set value="${count+1}" var="count"/>

                                                            </c:if>
                                                        </c:if>

                                                    </c:forEach>
                                                </p>
                                            </div>
                                        </td>

                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                            <br/>
                            <p align="center"><em>**</em> Sila tanda di petak Pilih untuk hapus.</p>

                            <table width="80%">
                                <tr><td align="right">

                                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                        <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow('tbl')"/>
                            </table>
                        </div>
                    </c:if>

                    <c:if test="${view}">

                        <div align="center" >

                            <p>
                                <s:hidden name="recordCount" id="recordCount"/>
                            </p>

                            <table  width="60%" id="tbl" class="tablecloth" align="center">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="5%" align="center"><b>Jenis Dokumen</b></th>
                                    <th  width="3%" align="center"><b>Kesalahan</b></th>
                                    <th  width="10%" align="center"><b>Lampiran</b></th>
                                </tr>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.listPermohonanSemakDokumen}" var="line">
                                    <tr style="font-weight:bold">
                                        <td><c:out value="${i+1}"/></td>
                                        <s:hidden name="idSemakDokumen${i}" id="idSemakDokumen${i}" value="${line.idPermohonanSemakDokumen}" />
                                        <td>${line.kodDokumen.nama} </td>
                                        <td>${line.catatan}</td>
                                        <td>
                                            <div id="semakDokumenDiv${i}">
                                                <p align="center">
                                                    <c:set value="1" var="count"/>
                                                    <c:forEach items="${actionBean.listKandunganFolder}" var="senarai">
                                                        <c:if test="${senarai.dokumen.kodDokumen.kod eq line.kodDokumen.kod}">
                                                            <c:if test="${senarai.dokumen.namaFizikal != null}">
                                                                <%-- ${count})--%>
                                                                <br>
                                                                ${count} -
                                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                                                ${senarai.dokumen.tajuk}

                                                                <c:set value="${count+1}" var="count"/>

                                                            </c:if>
                                                        </c:if>

                                                    </c:forEach>
                                                </p>
                                            </div>
                                        </td>

                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                            <br/>

                        </div>
                    </c:if>

                    <p>&nbsp;</p>
                </fieldset>
                <br/>
                <c:if test="${edit}">
                    <p align="right">
                        <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                        <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                    </p>
                </c:if>


            </div>
        </fieldset>
    </div>

</s:form>
