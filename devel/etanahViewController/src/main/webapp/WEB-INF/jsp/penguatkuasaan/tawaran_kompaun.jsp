<%--
    Document   : tawaran_kompaun
    Created on : May 13, 2011, 11:52:22 AM
    Author     : latifah.iskak
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
<c:if test="${multipleOp}">
    <style type="text/css">
        .tablecloth{
            padding:0px;
            width:90%;
        }

        .infoLP{
            float: right;
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;

        }

        .infoHeader{
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;
            text-align: center;

        }

    </style>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        var bilOp =  ${fn:length(actionBean.listOp)}; //list op
        //start: for senarai saksi
        for (var p = 0; p < bilOp; p++){
            //            var bil =  document.getElementById('totalSaksi'+p).value;
            //alert(bil);
            //            if(bil != "0"){
            //                for (var i = 0; i < bil; i++){
            //                    var pilihStatus = document.getElementById('pilihStatus'+p+i).value;
            //                    if(pilihStatus != ""){
            //                        if(pilihStatus == "Y")
            //                            document.getElementById("pilih"+p+i).checked = true;
            //                    }else{
            //                        document.getElementById("pilih"+p+i).checked = false;
            //                    }
            //                }
            //            }
               
                 
            //            if(bil == 0){
            //                document.getElementById("tempohKompaun"+p).disabled = true;
            //            }
            //end for senarai saksi
            var bilKompaunIP =  document.getElementById('totalKompaunIP'+p).value;
            //alert(bilKompaunIP);
            var total = 0;
            if(bilKompaunIP != "0"){
                for (var i = 0; i < bilKompaunIP; i++){
                    var amaunIP = document.getElementById('amaunIP'+p+i).value;
                    if(amaunIP !=""){
                        //alert(amount);
                        total += parseFloat(amaunIP);
                        document.getElementById('jumKompaun'+p).value=parseFloat(total).toFixed(2);
                    }
                }
            }
            
        }
             
        totalAmount();
    });

    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?popup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=950,height=600");
    }

    function tambahMuktamadKompaun(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?popupMuktamadKompaun", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=950,height=800");
    }

    function tambahMuktamadPelupusan(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?popupMuktamadPelupusan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=950,height=800");
    }

    function removeSingle(idKompaun)
    {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?deleteSingle&idKompaun='
                +idKompaun;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function refreshPageKompaun(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function refreshPageMuktamad(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?refreshPageMuktamadkompaunMelaka';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function refreshPageBayaranPelupusan(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?refreshPageBayaranPelupusan';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    
    function viewSyorKompaun(id,oks){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?viewSyorKompaunDetail&idKompaun='+id+'&oks='+oks;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function viewMuktamadKompaun(id,oks){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?viewMuktamadKompaunMelaka&idKompaun='+id+'&oks='+oks;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function viewMuktamadBayaranPelupusan(id,oks){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?viewMuktamadBayaranPelupusan&idKompaun='+id+'&oks='+oks;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function addRecord(id){
        //alert(id);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?addRecord&idOp="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }
         
    function addRecordIP(id){
        //alert(id);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?addRecordIP&idOp="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function refreshPageMuktamadkompaunMelakaOp(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?refreshPageMuktamadkompaunMelakaOp';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function viewMuktamadKompaunOp(id,oks,idOp){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?viewMuktamadKompaunOp&idKompaun='+id+'&oks='+oks+'&idOp='+idOp;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function refreshListSaksiMultipleOp(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?refreshpage';
        $.get(url,
        function(data){
            $("#senaraiSaksiLuarDiv").replaceWith($('#senaraiSaksiLuarDiv', $(data)));
        },'html');
    }

    function removeSaksiMultipleOp(idSaksi){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?deleteSaksi&idSaksi='+idSaksi;
            $.get(url,
            function(data){
                $("#senaraiSaksiLuarDiv").replaceWith($('#senaraiSaksiLuarDiv', $(data)));
            },'html');
        }
    }

    function totalAmount(){
        var total = 0;
        var bilOp =  ${fn:length(actionBean.listOp)}; //list op
        for (var p = 0; p < bilOp; p++){
            //            var bil =  document.getElementById('totalSaksi'+p).value;
            //
            //
            //            for (var i = 0; i < bil; i++){
            //                var amount = document.getElementById('kompaunSaksi'+p+i).value;
            //                if(amount !=""){
            //                    //alert(amount);
            //                    total += parseFloat(amount);
            //                    document.getElementById('kompaunSaksi'+p+i).value= parseFloat(amount).toFixed(2);
            //                    document.getElementById('jumCaraBayar'+p).value=parseFloat(total).toFixed(2);
            //                }
            //            }
            
        }

    }
         
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
        
    }
         
         
    function validateForm(){
        var bilOp =  ${fn:length(actionBean.listOp)}; //list op
        //alert("bilOp : "+bilOp);

        var bilSaksi= 0;

        for (var i = 0; i < bilOp; i++){
            //check for list barang
            var listSaksi = document.getElementById('totalSaksi'+i).value;
            var tempohKompaun = document.getElementById('tempohKompaun'+i).value;
            if(listSaksi != "0"){
                //alert(listSaksi);
                for (var b = 0; b < listSaksi; b++){
                    //alert("p[id operasi] : "+i +". b[row] :"+b);
                    var pilihCheckBox = document.getElementById('pilih'+i+b).checked;
                    var amount = document.getElementById('kompaunSaksi'+i+b).value;
                    //alert("pilih barang untuk id op "+i+" :"+pilihBarangDakwa);

    <%--if( pilihCheckBox == false && amount!=""){
        alert("Sila tanda di dalam kotak pilih sebelum simpan kompaun yang dikehendaki");
        $('#pilih'+i).focus();
        return false;
    }--%>
                        if( pilihCheckBox == true && amount==""){
                            alert("Sila masukkan amaun kompaun");
                            $('#amountSyor'+i).focus();
                            return false;
                        }

                     
                        if( pilihCheckBox == true && amount!="" && tempohKompaun == ""){
                            alert("Sila masukkan tempoh kompaun");
                            $('#tempohKompaun'+i).focus();
                            return false;
                        }

                        if( pilihCheckBox == true){
                            bilSaksi++;
                        }

                    }  
                }
                
            }

            //alert("bilBarang : "+bilBarang);


    <%-- if(bilSaksi==0 ){
         alert("Sila pilih saksi yang hendak dikenakan kompaun");
         return false;
     }

    --%>
            return true;

        }
        
        function viewRecordOP(id){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
        }

</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatKompaunActionBean" name="form1">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <%--This is syor kompaun with barang rampasan for N9--%>
            <c:if test="${syorKompaunNS}">
                <div class="content" align="center">
                    <legend>
                        Maklumat Syor Kompaun
                    </legend>
                    <c:if test="${actionBean.rowCount ne 0}">
                        <div class="content" align="left">
                            <p>Klik butang tambah untuk masukkan maklumat tawaran kompaun</p>
                        </div>
                    </c:if>
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">
                            ${line_rowNum}
                        </display:column>
                        <display:column title="No.Siri">
                            <a class="popup" onclick="viewSyorKompaun('${line.idKompaun}','${line.orangKenaSyak.idOrangKenaSyak}')">${line.noRujukan}</a>
                        </display:column>
                        <display:column title="Tempoh (Hari)" property="tempohHari"></display:column>
                        <display:column title="Diserahkan Kepada">${line.isuKepada}</display:column>
                        <display:column title="No.KP">
                            <c:if test="${line.noPengenalan ne null}">
                                ${line.noPengenalan}
                            </c:if>
                            <c:if test="${line.noPengenalan eq null}">
                                Tiada data
                            </c:if>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${line.idKompaun}');" />
                            </div>
                        </display:column>

                    </display:table>

                    <br>
                    <c:if test="${actionBean.rowCount eq 0}">
                        
                    </c:if>
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>
                </div>
            </c:if>

            <%--This is muktamad kompaun with barang rampasan for Melaka since Melaka don't have syor kompaun--%>
            <c:if test="${muktamadKompaunMLK}">
                <legend>
                    Maklumat Muktamad Kompaun
                </legend>
                <div class="content" align="center">
                    <c:if test="${actionBean.rowCount ne 0}">
                        <div class="content" align="left">
                            <p>Klik butang tambah untuk masukkan maklumat tawaran kompaun</p>
                        </div>
                    </c:if>
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">
                            ${line_rowNum}
                        </display:column>
                        <display:column title="No.Siri">
                            <a class="popup" onclick="viewMuktamadKompaun('${line.idKompaun}','${line.orangKenaSyak.idOrangKenaSyak}')">${line.noRujukan}</a>
                        </display:column>
                        <display:column title="Tempoh (Hari)" property="tempohHari"></display:column>
                        <display:column title="Diserahkan Kepada">${line.isuKepada}</display:column>
                        <display:column title="No.KP">
                            <c:if test="${line.noPengenalan ne null}">
                                ${line.noPengenalan}
                            </c:if>
                            <c:if test="${line.noPengenalan eq null}">
                                Tiada data
                            </c:if>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${line.idKompaun}');" />
                            </div>
                        </display:column>

                    </display:table>

                    <br>
                    <c:if test="${actionBean.rowCount ne 0}">
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahMuktamadKompaun();"/>
                    </c:if>

                </div>
            </c:if>

            <%--This is bayaran pelupusan with barang rampasan for Melaka--%>
            <c:if test="${bayaranPelupusan}">
                <legend>
                    Maklumat Muktamad Bayaran Pelupusan
                </legend>
                <div class="content" align="center">
                    <c:if test="${actionBean.rowCount ne 0}">
                        <div class="instr-fieldset">
                            <font color="red">MAKLUMAN :</font> Klik butang tambah untuk masukkan maklumat bayaran pelupusan
                        </div>&nbsp;
                    </c:if>
                    <c:if test="${actionBean.rowCount eq 0}">
                        <div class="instr-fieldset">
                            <font color="red">MAKLUMAN :</font> Kesemua maklumat bayaran pelupusan sudah dimasukkan
                        </div>&nbsp;
                    </c:if>
                    <display:table class="tablecloth" name="${actionBean.senaraiBayaranPelupusan}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">
                            ${line_rowNum}
                        </display:column>
                        <display:column title="No.Siri">
                            <a class="popup" onclick="viewMuktamadBayaranPelupusan('${line.idKompaun}','${line.orangKenaSyak.idOrangKenaSyak}')">${line.noRujukan}</a>
                        </display:column>
                        <display:column title="Tempoh (Hari)" property="tempohHari"></display:column>
                        <display:column title="Diserahkan Kepada">${line.isuKepada}</display:column>
                        <display:column title="No.KP">
                            <c:if test="${line.noPengenalan ne null}">
                                ${line.noPengenalan}
                            </c:if>
                            <c:if test="${line.noPengenalan eq null}">
                                Tiada data
                            </c:if>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${line.idKompaun}');" />
                            </div>
                        </display:column>

                    </display:table>

                    <br>
                    <c:if test="${actionBean.rowCount ne 0}">
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahMuktamadPelupusan();"/>
                    </c:if>

                </div>
            </c:if>

            <c:if test="${multipleOp}">
                <legend>
                    Maklumat Muktamad Kompaun
                </legend>
                <br>
                <div class="instr-fieldset">
                    <font color="red">MAKLUMAN :</font> Klik butang tambah untuk masukkan maklumat tawaran kompaun
                </div>&nbsp;
                <br>
                <div class="content" align="center">
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true" style="width:50px;">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi" style="width:300px;">
                            <table width="10%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><u><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></u></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                    </td>
                                </tr>

                            </table>
                        </display:column>
                        <display:column title="Maklumat Kompaun" style="width:500px;">
                            <c:set value="0" var="j"/>
                            <c:set var="jumKompaunPerRow" value="0"/>
                            <c:forEach items="${actionBean.senaraiKompaunIP}" var="k">
                                <c:if test="${k.orangKenaSyak.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                    <c:set var="jumKompaunPerRow" value="${fn:length(actionBean.senaraiKompaunIP)}"/>
                                </c:if>
                            </c:forEach>
                            <input type="hidden" name="totalKompaunIP${line_rowNum-1}" value="${jumKompaunPerRow}" id="totalKompaunIP${line_rowNum-1}">
                            <table width="100%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>No.Siri</b></th>
                                    <th  width="1%" align="center"><b>Diserahkan Kepada</b></th>
                                    <th  width="5%" align="center"><b>No.pengenalan</b></th>
                                    <th  width="5%" align="center"><b>Hapus</b></th>
                                    <th  width="5%" align="center"><b>Amaun (RM)</b></th>
                                </tr>
                                <c:forEach items="${actionBean.senaraiKompaun}" var="kompaun">
                                    <c:if test="${kompaun.orangKenaSyak.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                        <tr>
                                            <td width="5%">${j+1}</td>
                                            <td width="5%"><u><a class="popup" onclick="viewMuktamadKompaunOp('${kompaun.idKompaun}','${kompaun.orangKenaSyak.idOrangKenaSyak}','${line.idOperasi}')">${kompaun.noRujukan}</a></u></td>
                                            <td width="50%">${kompaun.orangKenaSyak.nama}</td>
                                            <td width="50%">
                                                <c:if test="${kompaun.noPengenalan ne null}">
                                                    ${kompaun.noPengenalan}
                                                </c:if>
                                                <c:if test="${kompaun.noPengenalan eq null}">
                                                    Tiada data
                                                </c:if>
                                            </td>
                                            <td width="30%">
                                                <div align="center">
                                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${kompaun.idKompaun}');" />
                                                </div>
                                            </td>
                                            <td width="30%">
                                                ${kompaun.amaun}
                                                <input type="hidden" name="amaunIP${line_rowNum-1}${j}" value="${kompaun.amaun}" id="amaunIP${line_rowNum-1}${j}">
                                            </td>
                                        </tr>
                                        <c:set value="${j+1}" var="j"/>
                                    </c:if>
                                </c:forEach>
                                <tr>
                                    <td colspan="5" align="right">Jumlah Bayaran(RM):</td>
                                    <td><input name="jumKompaun${line_rowNum-1}" value="0.00" id="jumKompaun${line_rowNum-1}" size="12" class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                </tr>
                            </table>

                        </display:column>
                        <display:column title="Tambah Kompaun" style="width:150px;">
                            <c:if test="${!actionBean.idIP}">
                                <c:set value="0" var="k"/>
                                <c:forEach items="${actionBean.senaraiBarangRampasan}" var="barang">
                                    <c:if test="${line.idOperasi eq barang.operasi.idOperasi}">
                                        <c:if test="${barang.kompaun.idKompaun eq null}">
                                            <c:set value="${k+1}" var="k"/>
                                        </c:if>
                                    </c:if>

                                </c:forEach>
                                <c:if test="${k ne 0}">
                                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord('${line.idOperasi}');"/>
                                </c:if>
                            </c:if>
                            <c:if test="${actionBean.idIP}">
                                <c:if test="${actionBean.idOpBasedOnIdIP eq line.idOperasi}">
                                    <c:if test="${fn:length(actionBean.senaraiOKSForKompaun) ne 0}">
                                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecordIP('${actionBean.idOpBasedOnIdIP}');"/>
                                    </c:if>
                                </c:if>
                            </c:if>

                        </display:column>


                    </display:table>
                    <br>
                </div>
                <%---
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425'}">
                                    <p>
                                        <b>Senarai saksi luar </b>&nbsp;
                                    </p><br>

                    <div class="instr-fieldset">
                        <font color="red">MAKLUMAN :</font> Klik butang simpan untuk menyimpan maklumat kompaun untuk saksi luar
                    </div>&nbsp;
                    <div class="content" align="center">
                        <div id="senaraiSaksiLuarDiv">
                            <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:90%;">
                                <display:column title="Bil" sortable="true" style="width:3%;">${line_rowNum}</display:column>
                                <display:column title="Maklumat Laporan Operasi" style="width:23%">
                                    <table width="10%" cellpadding="1">
                                        <tr>
                                            <td width="3%"><font class="infoLP">Id Operasi :</font></td>
                                            <td width="3%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                        </tr>
                                        <tr>
                                            <td width="5%"><font class="infoLP">Tarikh laporan :</font></td>
                                            <td width="5%">
                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                        </tr>
                                        <tr>
                                            <td width="5%"><font class="infoLP">Masa laporan :</font></td>
                                            <td width="5%">
                                                <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                                <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                                <c:if test="${time eq 'AM'}">PAGI</c:if>
                                                <c:if test="${time eq 'PM'}">PETANG</c:if>
                                            </td>
                                        </tr>

                                    </table>
                                </display:column>
                                <display:column title="Senarai Saksi Luar" style="width:60%;">
                                    <c:set var="jumSaksi" value="0"/>
                                    <c:forEach items="${actionBean.senaraiPermohonanSaksi}" var="saksiLuar">
                                        <c:if test="${saksiLuar.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                            <c:set var="jumSaksi" value="${fn:length(actionBean.senaraiPermohonanSaksi)}"/>
                                        </c:if>
                                    </c:forEach>
                                    <input type="hidden" name="totalSaksi${line_rowNum-1}" value="${jumSaksi}" id="totalSaksi${line_rowNum-1}">
                                    <p>
                                        <label>Tempoh (Hari) :</label>
                                        <c:set var="tempohPerRow" value=""/>
                                        <c:forEach items="${actionBean.senaraiPermohonanSaksi}" var="saksiLuar">
                                            <c:choose>
                                                <c:when test="${fn:length(saksiLuar.senaraiKompaun) ne 0}">
                                                    <c:forEach items="${saksiLuar.senaraiKompaun}" var="kompaun">
                                                        <c:if test="${kompaun.saksi.idSaksi eq saksiLuar.idSaksi && saksiLuar.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                                            <c:set var="tempohPerRow" value="${kompaun.tempohHari}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                        <input type="text" id="tempohKompaun${line_rowNum-1}" name="tempohKompaun${line_rowNum-1}" value="${tempohPerRow}" size="10" maxlength="14">

                                    </p>
                                    <c:set value="0" var="count"/>

                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="1%" align="center"><b>Pilih</b></th>
                                            <th  width="10%" align="center"><b>No.Pengenalan</b></th>
                                            <th  width="20%" align="center"><b>Nama Saksi</b></th>
                                            <th  width="30%" align="center"><b>Alamat</b></th>
                                            <th  width="30%" align="center"><b>Muktamad Kompaun</b></th>
                                        </tr>
                                        <c:forEach items="${actionBean.senaraiPermohonanSaksi}" var="saksiLuar">
                                            <c:if test="${saksiLuar.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                                <tr>
                                                    <td width="1%">${count+1}</td>
                                                    <td>
                                                        <s:checkbox name="pilih${line_rowNum-1}${count}" id="pilih${line_rowNum-1}${count}" value="${saksiLuar.idSaksi}"/>
                                                        <input type="hidden" name="pilihStatus${line_rowNum-1}${count}" value="${saksiLuar.statusDakwaanKompaun}" id="pilihStatus${line_rowNum-1}${count}">
                                                        <input type="hidden" name="pilihTemp${line_rowNum-1}${count}" value="${saksiLuar.idSaksi}" id="pilihTemp${line_rowNum-1}${count}">
                                                    </td>
                                                    <td width="10%">${saksiLuar.noPengenalan}</td>
                                                    <td width="20%"><a class="popup" onclick="viewSaksi(${saksiLuar.idSaksi})">${saksiLuar.nama}</a></td>
                                                    <td width="30%">
                                                        <font style="text-transform: uppercase">
                                                            <c:if test="${saksiLuar.alamat1 ne null}">${saksiLuar.alamat1}</c:if>
                                                            <c:if test="${saksiLuar.alamat2 ne null}">${saksiLuar.alamat2}</c:if>
                                                            <c:if test="${saksiLuar.alamat3 ne null}">${saksiLuar.alamat3}</c:if>
                                                            <c:if test="${saksiLuar.alamat4 ne null}">${saksiLuar.alamat4}</c:if>
                                                            <c:if test="${saksiLuar.poskod ne null}">${saksiLuar.poskod}</c:if>
                                                            <c:if test="${saksiLuar.negeri ne null}">${saksiLuar.negeri.nama}</c:if>
                                                        </font>
                                                    </td>
                                                    <td width="10%">RM &nbsp;
                                                        <c:choose>
                                                            <c:when test="${fn:length(saksiLuar.senaraiKompaun) ne 0}">
                                                                <c:forEach items="${saksiLuar.senaraiKompaun}" var="kompaun">
                                                                    <c:if test="${kompaun.saksi.idSaksi eq saksiLuar.idSaksi}">
                                                                        <s:text name="kompaunSaksi${line_rowNum-1}${count}" value="${kompaun.amaun}" formatPattern="0.00" id="kompaunSaksi${line_rowNum-1}${count}" onblur="totalAmount();" size="10" maxlength="14"  onkeyup="validateNumber(this,this.value);"/>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="text" id="kompaunSaksi${line_rowNum-1}${count}" name="kompaunSaksi${line_rowNum-1}${count}" value="" size="10" maxlength="14" onblur="totalAmount();">
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>

                                                </tr>
                                            </c:if>

                                            <c:set value="${count+1}" var="count"/>

                                        </c:forEach>
                                        <tr>
                                            <td colspan="5" align="right">Jumlah Bayaran(RM):</td>
                                            <td><input name="jumCaraBayar${line_rowNum-1}" value="0.00" id="jumCaraBayar${line_rowNum-1}" size="12" class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                        </tr>

                                    </table>
                                </display:column>
                            </display:table>


                            <br/>
                            <p align="center">
                                <s:button class="btn" name="simpanKompaunSaksi" value="Simpan" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>

                            </p>
                        </div>
                    </div
                </c:if> --%>

            </c:if>
        </fieldset>
    </div>
</s:form>
