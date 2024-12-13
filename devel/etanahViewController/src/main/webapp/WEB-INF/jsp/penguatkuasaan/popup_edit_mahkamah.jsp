<%-- 
    Document   : popup_tambah_mahkamah
    Created on : July 15, 2011, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageMahkamah();
            //alert("Maklumat telah berjaya disimpan.");
            self.close();
        },'html');

    }

    function validateForm(){
        if($('#kategoriMahkamah').val() == ''){
            alert("Sila pilih kategori mahkamah");
            $('#kategoriMahkamah').focus();
            return false;
        }
        if($('#namaSidang').val() == ''){
            alert("Sila masukkan tempat mahkamah");
            $('#namaSidang').focus();
            return false;
        }
        if($('#noRujukan').val() == ''){
            alert("Sila masukkan no.rujukan");
            $('#noRujukan').focus();
            return false;
        }
        if($('#tarikhSidang').val() == ''){
            alert("Sila masukkan tarikh sidang");
            $('#tarikhSidang').focus();
            return false;
        }

        return true;
    }

    function test(f) {
        $(f).clearForm();
    }

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
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type","text");
        e2.setAttribute("name","nama"+(rowcount-1));
        e2.setAttribute("id","nama" +(rowcount-1));
        e2.setAttribute("size","30");
        e2.onchange = function(){convertText(this,"nama" +(rowcount-1));};
        cell3.appendChild(e2);

            var cell4 = row.insertCell(3);
            var e3 = document.createElement("select");
            e3.setAttribute("name","jawatan"+(rowcount-1));
            e3.setAttribute("id","jawatan" +(rowcount-1));

        //for "sila pilih"
            var option1 = document.createElement("option");
            option1.text = " Sila pilih ";
            option1.value ="";
            e3.options.add(option1);

    <c:forEach items="${listUtil.senaraiKodPerananRujukanLuar}" var="line">
            var option2 = document.createElement("option");
            var textVal2=document.createTextNode("${line.nama}");
            option2.appendChild(textVal2);
            option2.setAttribute("value","${line.kod}");
            e3.appendChild(option2);
    </c:forEach>
            cell4.appendChild(e3);

        }

        function deleteRow(tableid) {
            var idPeranan;
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    if(null != chkbox && true == chkbox.checked) {
                        idPeranan = $("#idPeranan"+(i+j-1)).val();

                        if(document.getElementById("idPeranan"+(i+j-1)) != null){

                            var url = '${pageContext.request.contextPath}/penguatkuasaan/mahkamah?deletePeranan&idPasukan='
                                +idPeranan;

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
                alert(rowCount);

                if(rowCount > 1){
                    for(var i=1;i<rowCount;i++){
                        table.rows[i].cells[1].childNodes[0].data= i;
                    }
                }

            }catch(e){
                alert("Error in regenerateBill : "+e);
            }
        }

        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }

 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MahkamahActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Mahkamah
            </legend>
            <div class="content">
                <p>
                    <label><em>*</em>Kategori Mahkamah :</label>
                    <s:select name="kategoriMahkamah" id="kategoriMahkamah" value="${actionBean.permohonanRujukanLuar.agensi.kod}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiMahkamah}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;

                </p>
                <p>
                    <label><em>*</em>Tempat Mahkamah :</label>
                    <s:text name="namaSidang" id="namaSidang" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                    <label><em>*</em>No Rujukan :</label>
                    <s:text name="noRujukan" id="noRujukan" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                    <label><em>*</em>Tarikh Sebutan/Perbicaraan :</label>
                    <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" />
                    <font color="red" size="1">cth : hh / bb / tttt</font>&nbsp;
                </p>
                <p>
                    <label>Tarikh Keputusan :</label>
                    <s:text name="tarikhRujukan" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhRujukan"/>
                    <font color="red" size="1">cth : hh / bb / tttt</font>&nbsp;
                </p>
                <p>
                    <label>Keputusan Mahkamah :</label>
                    <s:textarea name="catatan" rows="5" cols="50" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>

            </div>
            <br/><br/>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pihak Mahkamah
            </legend>
            <br/>

            <div class="content">

                <fieldset class="aras2">
                    <legend>
                        Pihak Mahkamah
                    </legend>
                    <div class="instr-fieldset">
                        Sila klik tambah untuk menambah senarai pihak mahkamah
                    </div>

                    <div align="center" >

                        <p>
                            <s:hidden name="recordCount" id="recordCount"/>
                        </p>


                        <table  width="60%" id="tbl" class="tablecloth" align="center">
                            <tr>
                                <th  width="1%" align="center"><b>Pilih</b></th>
                                <th  width="1%" align="center"><b>Bil</b></th>
                                <th  width="5%" align="center"><b>Nama</b></th>
                                <th  width="10%" align="center"><b>Jawatan</b></th>
                            </tr>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.senaraipermohonanRujukanLuarPeranan}" var="line">
                                <tr style="font-weight:bold">
                                    <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                    <td><c:out value="${i+1}"/></td>
                                    <td><s:text name="nama${i}" id="nama${i}" size="30" onchange="this.value=this.value.toUpperCase();" value="${line.nama}"/></td>
                                    <td><s:select name="jawatan${i}" id="jawatan${i}" value="${line.kodPerananRujukanLuar.kod}">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodPerananRujukanLuar}" label="nama" value="kod" sort="nama" />
                                        </s:select>
                                    </td>
                                    <s:hidden name="idPeranan${i}" id="idPeranan${i}" value="${line.idPeranan}" />
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
                    <p>&nbsp;</p>
                </fieldset>
                <br/>
                <p align="right">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:hidden name="idRujukan"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" value="Simpan" name="editRecord"  onclick="if(validateForm())save(this.name,this.form);" />
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>

            </div>
        </fieldset>
    </div>

</s:form>

