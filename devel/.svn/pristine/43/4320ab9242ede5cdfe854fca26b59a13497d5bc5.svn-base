<%-- 
    Document   : borang_siasatan
    Created on : Jan 20, 2010, 10:40:35 AM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    .infoLP{
        float: right;
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;

    }
</style>
<script type="text/javascript">
    function validateForm(){

        if($('#draf').val()=="")
        {
            alert("Sila Isi Ringkasan Draf Kertas Siasatan");
            $('#draf').focus();
            return false;
        }
        
        if($('#kronologiKes').val()=="")
        {
            alert("Sila isi kronologi kes");
            $('#kronologiKes').focus();
            return false;
        }

     
        return true;
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?refreshpage';
        $.get(url,
        function(data){
            $("#lampiranDiv").replaceWith($('#lampiranDiv', $(data)));
        },'html');
    }

    function refreshUploadLampiran(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?refreshDiv';
        $.get(url,
        function(data){
            $("#uploadLampiranDiv").replaceWith($('#uploadLampiranDiv', $(data)));
        },'html');
    }

    function refreshListSaksi(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?refreshpage';
        $.get(url,
        function(data){
            $("#senaraiPasukanDiv").replaceWith($('#senaraiPasukanDiv', $(data)));
        },'html');
    }

    function test(f) {
        $(f).clearForm();
    }
    function muatNaikForm1(folderId, dokumenId, idPermohonan, dokumenKod, idKertas) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId
            + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idKertas=' + idKertas + '&idDokumen=' + dokumenId;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function muatNaikForm(folderId, idPermohonan, dokumenKod) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId+ '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function removeImej(idImej,idKertas) {
        var url = '${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?deleteSelected&idImej='+idImej+'&idKertas='+idKertas;
        $.get(url,
        function(data){
            $('#page_div').html(data);
            refreshPage();
        },'html');
    }

    function popup(idBarang){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?viewBarangDetail&idBarang='+idBarang;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function viewOKS(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?viewOrangKenaSyak&idOrangKenaSyak='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function popupSaksi(h){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?editSaksi&idSaksi='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=890,height=500, left=" + left + ",top=" + top);
    }

    function tambahBaru(){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?saksiPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=500, left=" + left + ",top=" + top);
    }

    function removeSaksi(idSaksi){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?deleteSaksi&idSaksi='+idSaksi;
            $.get(url,
            function(data){
                $("#senaraiPasukanDiv").replaceWith($('#senaraiPasukanDiv', $(data)));
            },'html');}
    }

    function viewSaksi(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?viewSaksi&idSaksi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }


    function simpanSaksi(){
        if($('#pasukan').val()=="")
        {
            alert("Sila pilih maklumat pasukan dahulu.");
            $('#pasukan').focus();
            return false;
        }
        
        var idPasukan = $('#pasukan').val();

        $.get('${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?simpanSaksiPasukan&idPasukan='+idPasukan,
        function(html){
            $("#senaraiPasukanSaksiDiv").replaceWith($('#senaraiPasukanSaksiDiv', $(html)));

        }, 'html');


    }

    function checkExisting()
    {
        var idPasukan = document.getElementById("pasukan").value;

        if(idPasukan != ""){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?checkExistingRecordSaksi&idPasukan='+idPasukan,
            function(data){
                if(data == "Exist"){
                    alert("Rekod saksi ini telah wujud. Sila pilih saksi yang lain.");
                    document.getElementById("pasukan").value = "";
                }

            }, 'html');

        }
    }

    function removeSaksiPasukan(idPasukan){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?deleteSaksi&idPasukan='+idPasukan;
            $.get(url,
            function(data){
                $("#senaraiPasukanSaksiDiv").replaceWith($('#senaraiPasukanSaksiDiv', $(data)));
            },'html');}
    }

    function removeSaksiPasukanMultipleOp(idPasukan){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?deleteSaksi&idPasukan='+idPasukan;
            $.get(url,
            function(data){
                $("#senaraiSaksiOpDiv").replaceWith($('#senaraiSaksiOpDiv', $(data)));
            },'html');}
    }

    function addRow(tableID) {

        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell0 = row.insertCell(0);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan1"+(rowCount+1);
        element1.rows = 5;
        element1.cols = 128;
        element1.name ="kandungan1"+(rowCount+1);
        element1.id ="kandungan1"+(rowCount+1);
 
        var element2 = document.createElement("input");
        element2.setAttribute("name","idKand"+(rowCount+1));
        element2.setAttribute("id","idKand"+(rowCount+1));
        element2.setAttribute("value","noID");
        element2.setAttribute("type","hidden");
        cell0.appendChild(element1);
        cell0.appendChild(element2);

        document.getElementById("rowCount").value=rowCount+1;
        var e =document.createTextNode(' ');
        var e1 = document.createElement("INPUT");
        e1.t = "button"+(rowCount+1);
        e1.setAttribute("type","button");
        e1.className="btn";
        e1.value="Hapus";
        e1.id =(rowCount+1);
        e1.onclick=function(){deleteRow(tableID,(e1.id));};

        cell0.appendChild(e);
        cell0.appendChild(e1);
    }

    function deleteRow(tableID,elementId)
    {
        //alert(elementId);
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var rowId = "idKand"+elementId;
            var id =  document.getElementById(rowId).value;
            if(id != "noID"){
                var url = '${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?deleteSingle&idKandungan=' +id;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }else{
                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;
                document.getElementById(tableID).deleteRow(elementId-1);
                document.getElementById("rowCount").value =rowCount-1;
            }
        }

        //regenerateBill(tableID);
    }

    function regenerateBill(tableID){

        try{
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            for(var i=1;i<rowCount;i++){
                //  alert("id:"+(table.rows[i].cells[2].childNodes[0].id));
                //  alert(table.rows[i].cells[0].childNodes[0].data);
                //  table.rows[i].cells[0].childNodes[0].data= "4."+(i+1);
                table.rows[i].cells[2].childNodes[0].id= i+1;
            }
        }catch(e){
            alert(e);
        }
    }

    function removeRP(idImej) {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?deleteRakamanPercakapan&idImej='+idImej;
            $.get(url,
            function(data){
                $("#uploadRP").replaceWith($('#uploadRP', $(data)));
            },'html');
        }
    }

    function muatNaikForm(folderId, idPermohonan, dokumenKod) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId+ '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubrefreshUploadLampiranar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function refreshUploadRP(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?refreshpage';
        $.get(url,
        function(data){
            $("#uploadRP").replaceWith($('#uploadRP', $(data)));
        },'html');
    }

    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function simpanSaksiOp(rowOp){

        //alert("rowOp : "+rowOp);
        if($('#pasukan'+rowOp).val()=="")
        {
            alert("Sila pilih maklumat pasukan dahulu.");
            $('#pasukan'+rowOp).focus();
            return false;
        }

        var idPasukan = $('#pasukan'+rowOp).val();

        $.get('${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?simpanSaksiPasukan&idPasukan='+idPasukan,
        function(html){
            $("#senaraiSaksiOpDiv").replaceWith($('#senaraiSaksiOpDiv', $(html)));

        }, 'html');


    }

    function checkExistingMultipleOp(rowOp)
    {
        var idPasukan = document.getElementById("pasukan"+rowOp).value;
        //alert(idPasukan);

        if(idPasukan != ""){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?checkExistingRecordSaksi&idPasukan='+idPasukan,
            function(data){
                if(data == "Exist"){
                    alert("Rekod saksi ini telah wujud. Sila pilih saksi yang lain.");
                    document.getElementById("pasukan"+rowOp).value = "";
                }

            }, 'html');

        }
    }

    function addRecordSaksi(id){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?saksiPopup&idOp="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=500, left=" + left + ",top=" + top);
    }

    function editRecordSaksi(h,idOp){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?editSaksi&idSaksi='+h+"&idOp="+idOp;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }
    
    function refreshListSaksiMultipleOp(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/draf_siasatan?refreshpage';
        $.get(url,
        function(data){
            $("#senaraiSaksiLuarDiv").replaceWith($('#senaraiSaksiLuarDiv', $(data)));
            $("#senaraiPasukanDiv").replaceWith($('#senaraiPasukanDiv', $(data)));
        },'html');
    }
    
    function removeSaksiMultipleOp(idSaksi){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?deleteSaksi&idSaksi='+idSaksi;
            $.get(url,
            function(data){
                $("#senaraiSaksiLuarDiv").replaceWith($('#senaraiSaksiLuarDiv', $(data)));
            },'html');}
    }
    
    function tambahBaruSaksi(){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?saksiPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=500, left=" + left + ",top=" + top);
    }


 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.DraftSiasatanActionBean" name="form1">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Kertas Siasatan
            </legend>
            <div class="content" >
                <div align="center">
                    <font color="black" style="text-transform: uppercase"><h3 style=" ">SULIT</h3>
                        KERAJAAN NEGERI ${actionBean.kodNegeri.nama}
                        <table width="20%">
                            <hr>
                        </table>
                        HARTA KERAJAAN NEGERI ${actionBean.kodNegeri.nama}</font>
                </div>
                <p>&nbsp;</p>
                <c:if test="${edit}">
                    <p>
                        <b>1.&emsp;Negeri : </b> <font style="text-transform: uppercase">${actionBean.kodNegeri.nama}</font> &nbsp;
                    </p>
                    <p>
                        &emsp;&emsp;<b>Daerah : </b> <font style="text-transform: uppercase">${actionBean.permohonan.cawangan.daerah.nama}</font>&nbsp;
                    </p>
                    <p>
                        &emsp;&emsp;<b>Kertas Siasatan Bil:</b>
                        <font style="text-transform: uppercase">
                            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                                ${actionBean.idPermohonanAsal}
                            </c:if>
                            <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                                ${actionBean.idPermohonan}
                            </c:if>
                        </font>&nbsp;
                    </p>
                    <p>&nbsp;</p>
                    <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                            <p>
                                <b> 2.&emsp;MAKLUMAT PEGAWAI PENYIASAT</b>
                            </p>
                            <div class="content" align="center">
                                <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:70%;">
                                    <display:column title="Bil" sortable="true" style="width:5%;">${line_rowNum}</display:column>
                                    <display:column title="Maklumat Laporan Operasi" style="width:30%;">
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
                                    <display:column title="Pegawai Penyiasat" style="width:70%;">
                                        <c:set var="statusPerRow" value="none"/>
                                        <c:forEach items="${actionBean.listPegawaiSiasat}" var="p">
                                            <c:if test="${p.statusPeranan eq 'K' && p.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                                <c:set var="statusPerRow" value="exist"/>
                                                <table width="20%" cellpadding="1">
                                                    <tr>
                                                        <td width="20%"><font class="infoLP">Nama Ketua Penyiasat:</font></td>
                                                        <td width="20%"> 
                                                            ${p.nama}&nbsp;
                                                            <input type="hidden" name="namaKetua${line_rowNum-1}" id="namaKetua${line_rowNum-1}" value="${p.nama}">
                                                            <input type="hidden" name="idPenggunaKetua${line_rowNum-1}" id="idPenggunaKetua${line_rowNum-1}" value="${p.namaJabatan}">
                                                        </td>
                                                    </tr>
                                                </table>
                                            </c:if>
                                        </c:forEach>


                                        <c:if test="${statusPerRow eq 'exist'}"> 
                                            <table width="20%" cellpadding="1">
                                                <tr>
                                                    <td width="20%"><font class="infoLP">Nama Pembantu Penyiasat:</font></td>
                                                    <td width="20%">&nbsp; </td>
                                                </tr>
                                                <c:set value="1" var="i"/>
                                                <c:forEach items="${actionBean.listPegawaiSiasat}" var="p">
                                                    <c:if test="${p.statusPeranan eq 'P' && p.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                                        <tr>
                                                            <td width="20%">&nbsp; </td>
                                                            <td width="20%" colspan="2">${i}) ${p.nama}&nbsp; </td>
                                                        </tr>
                                                        <c:set var="i" value="${i+1}" />
                                                    </c:if>
                                                </c:forEach>
                                            </table>
                                        </c:if> 

                                    </display:column>
                                </display:table>
                            </div>

                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                            <p>
                                <b> 2.&emsp;Pegawai Penyiasat :</b>
                                <font style="text-transform: uppercase"> <c:if test="${actionBean.pegawaiSiasat.nama eq null}">-</c:if>${actionBean.pegawaiSiasat.nama}</font>&nbsp;
                            </p>
                            <p>
                                &emsp;&emsp;<b>No. Pengenalan : </b> <font style="text-transform: uppercase">${actionBean.noPengenalanKetua}</font>&nbsp;
                            </p>
                        </c:if>


                    </c:if>
                    <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                        <p>
                            <b> 2.&emsp;Pegawai Penyiasat :</b>
                            <font style="text-transform: uppercase"> <c:if test="${actionBean.pegawaiSiasat.nama eq null}">-</c:if>${actionBean.pegawaiSiasat.nama}</font>&nbsp;
                        </p>
                        <p>
                            &emsp;&emsp;<b>No. Pengenalan : </b> <font style="text-transform: uppercase">${actionBean.noPengenalanKetua}</font>&nbsp;
                        </p>
                    </c:if>

                    <p>&nbsp;</p>


                    <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                            <p>
                                <b> 3.&emsp;LAPORAN POLIS </b>
                            </p>
                            <div class="content" align="center">
                                <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:70%;">
                                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                    <display:column title="Maklumat Laporan Operasi">
                                        <table width="20%" cellpadding="1">
                                            <tr>
                                                <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                                <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
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
                                    <display:column title="Senarai Laporan Polis">
                                        <c:set value="1" var="count"/>
                                        <c:if test="${fn:length(line.senaraiAgensi) ne 0}">
                                            <table width="100%" cellpadding="1">
                                                <tr>
                                                    <th  width="1%" align="center"><b>Bil</b></th>
                                                    <th  width="1%" align="center"><b>No Laporan Polis dan Balai</b></th>
                                                    <th  width="1%" align="center"><b>Tarikh</b></th>

                                                    <th  width="5%" align="center"><b>Papar</b></th>

                                                </tr>
                                                <c:forEach items="${line.senaraiAgensi}" var="agensi">
                                                    <tr>
                                                        <td width="5%">${count}</td>
                                                        <td width="20%"> ${agensi.noRujukan}&nbsp; </td>
                                                        <td width="20%"><fmt:formatDate pattern="dd/MM/yyyy" value="${agensi.tarikhRujukan}"/></td>
                                                        <td width="30%">
                                                            <c:set value="1" var="count"/>
                                                            <c:forEach items="${actionBean.listDokumen}" var="kf">

                                                                <c:if test="${kf.dokumen.dalamanNilai1 eq agensi.idOperasiAgensi}">
                                                                    <p align="left">

                                                                        <c:if test="${kf.dokumen.kodDokumen.kod eq 'LP'}">
                                                                            <c:if test="${kf.dokumen.namaFizikal != null}">
                                                                                <br>
                                                                                -
                                                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                                     onclick="doViewReport('${kf.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${kf.dokumen.kodDokumen.nama}"/>
                                                                                ${count}-${kf.dokumen.tajuk}/
                                                                                <c:set value="${count+1}" var="count"/>
                                                                            </c:if>
                                                                        </c:if>
                                                                    </p>
                                                                </c:if>
                                                            </c:forEach>
                                                        </td>
                                                    </tr>
                                                    <c:set value="${count+1}" var="count"/>
                                                </c:forEach>
                                            </table>
                                        </c:if>
                                    </display:column>
                                </display:table>


                            </div>
                        </c:if>
                    </c:if>


                    <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                            <p>
                                <b>3.&emsp;Balai Polis Dan Bil Aduan :</b>
                                <font style="text-transform: uppercase"><c:if test="${actionBean.permohonanRujukanLuar1.noRujukan eq null}">-</c:if>${actionBean.permohonanRujukanLuar1.noRujukan}</font>&nbsp;
                            </p>
                            <p>
                                &emsp;&emsp;<b>Haribulan dan Jam Aduan :</b>
                                <font style="text-transform: uppercase"><c:if test="${actionBean.permohonanRujukanLuar1.tarikhRujukan eq null}">-</c:if><fmt:formatDate value="${actionBean.permohonanRujukanLuar1.tarikhRujukan}" pattern="dd/MM/yyyy hh:mm aaa" /></font>&nbsp;
                            </p>
                            <p>
                                &emsp;&emsp;<b>Tempat Kejadian :</b>
                                <font style="text-transform: uppercase"><c:if test="${actionBean.aduanLokasi.lokasi eq null}">-</c:if>${actionBean.aduanLokasi.lokasi}</font> &nbsp;
                            </p>
                            <%--<p>
                                &emsp;&emsp;<b>Jenis Kesalahan :</b>
                                <font style="text-transform: uppercase"><c:if test="${actionBean.permohonan.kodUrusan.nama eq null}">-</c:if>${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
                            </p>--%>


                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                        <p>
                            <b>3.&emsp;Balai Polis Dan Bil Aduan :</b>
                            <font style="text-transform: uppercase"><c:if test="${actionBean.permohonanRujukanLuar1.noRujukan eq null}">-</c:if>${actionBean.permohonanRujukanLuar1.noRujukan}</font>&nbsp;
                        </p>
                        <p>
                            &emsp;&emsp;<b>Haribulan dan Jam Aduan :</b>
                            <font style="text-transform: uppercase"><c:if test="${actionBean.permohonanRujukanLuar1.tarikhRujukan eq null}">-</c:if><fmt:formatDate value="${actionBean.permohonanRujukanLuar1.tarikhRujukan}" pattern="dd/MM/yyyy hh:mm aaa" /></font>&nbsp;
                        </p>
                        <p>
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq '429'}">
                                    &emsp;&emsp;<b>Tempat Aduan :</b>
                                </c:when>
                                <c:otherwise>
                                    &emsp;&emsp;<b>Tempat Kejadian :</b>
                                </c:otherwise>
                            </c:choose>

                            <font style="text-transform: uppercase"><c:if test="${actionBean.aduanLokasi.lokasi eq null}">-</c:if>${actionBean.aduanLokasi.lokasi}</font> &nbsp;
                        </p>

                    </c:if>
                    <p>
                        &emsp;&emsp;<b>Jenis Kesalahan :</b>
                        <font style="text-transform: uppercase"><c:if test="${actionBean.permohonan.kodUrusan.nama eq null}">-</c:if>${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
                    </p>
                    <p>&nbsp;</p>
                    <p>
                        <b>4.&emsp;KETERANGAN RINGKAS : </b>
                    </p>
                    <p>
                        <s:textarea name="permohonanKertasKandungan.kandungan" rows="5" cols="150" id="draf"  class="normal_text"/>&nbsp;
                    </p>
                    <p>&nbsp;</p>

                    <c:if test="${actionBean.kodNegeri.kod eq '04'}">

                        <p>&nbsp;</p>
                        <p>
                            <b>5.&emsp;KRONOLOGI KES : </b>
                        </p>
                        <p>
                            <s:textarea name="kronologiKes" rows="5" cols="150" id="kronologiKes"/>&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable1')"/>&nbsp;
                        </p>
                        <p>&nbsp;</p>
                        <table id ="dataTable1">
                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.senaraiKronologiKes}" var="line">

                                <tr>
                                    <td>
                                        <p> 
                                            <input type="hidden" id="idKand${i}" value="${line.idKandungan}">
                                            <s:textarea name="kandungan1${i}" id="kandungan1${i}"  rows="5" cols="150" value="${line.kandungan}"/>
                                            <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable1',this.id)"/>
                                        </p>

                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />

                            </c:forEach>

                        </table>

                        <s:hidden name="rowCount" value="${i-1}" id="rowCount"/>


                    </c:if>


                    <%--  <p>
                          <b>5. &emsp;Haribulan saman diserah kepada dituduh :</b>
                          <c:if test="${actionBean.notis.tarikhHantar eq null}">-</c:if><fmt:formatDate value="${actionBean.notis.tarikhHantar}" pattern="dd/MM/yyyy" />&nbsp;
                      </p>--%>
                    <p>&nbsp;</p>
                    <p>
                        <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                            <b>5. &emsp;BARANG KES       </b>
                            &nbsp;
                        </c:if>
                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <b>6. &emsp;BARANG KES       </b>
                            &nbsp;
                        </c:if>

                    <div class="content" align="center">
                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                                <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0"  style="width:60%;">
                                    <display:column title="Bil" sortable="true" style="width:3%;">${line_rowNum}</display:column>
                                    <display:column title="Maklumat Laporan Operasi" style="width:30%;">
                                        <table width="10%" cellpadding="1">
                                            <tr>
                                                <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                                <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
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
                                    <display:column title="Maklumat Barang Rampasan" style="width:50%;">
                                        <c:set value="1" var="count"/>

                                        <table width="100%" cellpadding="1">
                                            <tr>
                                                <th  width="1%" align="center"><b>Bil</b></th>
                                                <th  width="1%" align="center"><b>Item</b></th>
                                                <th  width="5%" align="center"><b>Papar</b></th>

                                            </tr>
                                            <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                                <c:if test="${barang.aduanOrangKenaSyak.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan}">
                                                    <tr>
                                                        <td width="5%">${count}</td>
                                                        <td width="50%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a>&nbsp;&nbsp;${barang.nomborPendaftaran}</td>
                                                        <td width="30%">
                                                            <c:if test="${barang.imej.namaFizikal != null}">
                                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                     onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/>
                                                            </c:if>
                                                        </td>

                                                    </tr>
                                                    <c:set value="${count+1}" var="count"/>
                                                </c:if>

                                            </c:forEach>
                                        </table>
                                        <%-- <b>Jumlah : ${fn:length(line.senaraiBarangRampasan)}</b>--%>
                                    </display:column>
                                </display:table>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                                <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line">
                                    <display:column title="Bil">${line_rowNum}</display:column>
                                    <display:column title="Barang yang Dirampas"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a>&nbsp;&nbsp;${line.nomborPendaftaran}</display:column>
                                    <display:column title="Kuantiti">
                                        <c:if test="${line.kodKategoriItemRampasan.kod eq 'K'}">
                                            1
                                        </c:if>
                                        <c:if test="${line.kodKategoriItemRampasan.kod eq 'B'}">
                                            ${line.kuantiti}
                                        </c:if>
                                    </display:column>
                                    <display:column title="Tempat Simpanan" property="tempatSimpanan"></display:column>
                                    <display:column title="Catatan" property="catatan"></display:column>
                                    <display:column title="Status" property="status.nama" />

                                </display:table>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                            <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column title="Barang yang Dirampas"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a>&nbsp;&nbsp;${line.nomborPendaftaran}</display:column>
                                <display:column title="Kuantiti">
                                    <c:if test="${line.kodKategoriItemRampasan.kod eq 'K'}">
                                        1
                                    </c:if>
                                    <c:if test="${line.kodKategoriItemRampasan.kod eq 'B'}">
                                        ${line.kuantiti}
                                    </c:if>
                                </display:column>
                                <display:column title="Tempat Simpanan" property="tempatSimpanan"></display:column>
                                <display:column title="Catatan" property="catatan"></display:column>
                                <display:column title="Status" property="status.nama" />

                            </display:table>
                        </c:if>
                    </div>


                    <p>&nbsp;</p>
                    <p>
                        <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                            <b> 6.&emsp;ORANG KENA SYAK       </b>&nbsp;
                        </c:if>
                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <b> 7.&emsp;ORANG KENA SYAK       </b>&nbsp;
                        </c:if>

                    </p>

                    <div align="center">
                        <p>
                            &emsp;&emsp;<b>Tuduhan ( Sebut Seksyen ) :</b>
                            <font style="text-transform: uppercase"><c:if test="${actionBean.permohonan.kodUrusan.nama eq null}">-</c:if>${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;

                        </p>
                        <p>&nbsp;</p>
                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                                <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:60%;">
                                    <display:column title="Bil" sortable="true" style="width:3%;">${line_rowNum}</display:column>
                                    <display:column title="Maklumat Laporan Operasi" style="width:30%;">
                                        <table width="10%" cellpadding="1">
                                            <tr>
                                                <td width="10%"><font class="infoLP">Id Operasi :</font></td>
                                                <td width="10%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                            </tr>
                                            <tr>
                                                <td width="10%"><font class="infoLP">Tarikh laporan :</font></td>
                                                <td width="10%">
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                            </tr>
                                            <tr>
                                                <td width="10%"><font class="infoLP">Masa laporan :</font></td>
                                                <td width="10%">
                                                    <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                                    <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                                    <c:if test="${time eq 'AM'}">PAGI</c:if>
                                                    <c:if test="${time eq 'PM'}">PETANG</c:if>
                                                </td>
                                            </tr>

                                        </table>
                                    </display:column>
                                    <display:column title="Maklumat Orang Disyaki" style="width:50%;">
                                        <c:set value="1" var="count"/>

                                        <table width="100%" cellpadding="1">
                                            <tr>
                                                <th  width="1%" align="center"><b>Bil</b></th>
                                                <th  width="1%" align="center"><b>Nama</b></th>
                                                <th  width="5%" align="center"><b>Papar</b></th>

                                            </tr>
                                            <c:forEach items="${line.senaraiAduanOrangKenaSyak}" var="oks">
                                                <c:if test="${oks.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan && oks.statusOrangKenaSyak eq null}">
                                                    <tr>
                                                        <td width="5%">${count}</td>
                                                        <td width="50%"><a class="popup" onclick="viewOKS(${oks.idOrangKenaSyak})">${oks.nama}</a></td>

                                                        <td width="30%">
                                                            <c:if test="${oks.dokumen.namaFizikal != null}">
                                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                     onclick="doViewReport('${oks.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${oks.dokumen.kodDokumen.nama}"/> 
                                                            </c:if>

                                                        </td>

                                                    </tr>
                                                    <c:set value="${count+1}" var="count"/>
                                                </c:if>

                                            </c:forEach>
                                        </table>
                                    </display:column>

                                </display:table>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A' && actionBean.permohonan.kodUrusan.kod ne '426'}">
                                <display:table class="tablecloth" name="${actionBean.senaraiAduanOrangKenaSyak}" cellpadding="0" cellspacing="0" id="line" >
                                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                    <display:column title="No.Pengenalan"><a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line.noPengenalan}</a></display:column>
                                    <display:column title="Nama" property="nama"></display:column>
                                    <display:column title="Alamat">${line.alamat.alamat1}
                                        <c:if test="${line.alamat.alamat2 ne null}"> , </c:if>
                                        ${line.alamat.alamat2}
                                        <c:if test="${line.alamat.alamat3 ne null}"> , </c:if>
                                        ${line.alamat.alamat3}
                                        <c:if test="${line.alamat.alamat4 ne null}"> , </c:if>
                                        ${line.alamat.alamat4}
                                        ${line.alamat.poskod}
                                        ${line.alamat.negeri.nama}
                                    </display:column>
                                    <display:column title="Keterangan" property="keterangan"></display:column>
                                </display:table>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                            <display:table class="tablecloth" name="${actionBean.senaraiAduanOrangKenaSyak}" cellpadding="0" cellspacing="0" id="line" >
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column title="No.Pengenalan"><a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line.noPengenalan}</a></display:column>
                                <display:column title="Nama" property="nama"></display:column>
                                <display:column title="Alamat">${line.alamat.alamat1}
                                    <c:if test="${line.alamat.alamat2 ne null}"> , </c:if>
                                    ${line.alamat.alamat2}
                                    <c:if test="${line.alamat.alamat3 ne null}"> , </c:if>
                                    ${line.alamat.alamat3}
                                    <c:if test="${line.alamat.alamat4 ne null}"> , </c:if>
                                    ${line.alamat.alamat4}
                                    ${line.alamat.poskod}
                                    ${line.alamat.negeri.nama}
                                </display:column>
                                <display:column title="Keterangan" property="keterangan"></display:column>
                            </display:table>
                        </c:if>
                    </div>
                    <p>&nbsp;</p>

                    <p>&nbsp;</p>
                    <p>
                        <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                            <b> 7.&emsp;SAKSI       </b>&nbsp;
                        </c:if>
                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <c:if test="${actionBean.kodNegeri.kod ne '04' && actionBean.kodNegeri.kod ne '05'}">
                                <b> 8.&emsp;SAKSI       </b>&nbsp;

                            </c:if>
                        </c:if>

                    </p>
                    <div class="content" align="center">
                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <c:if test="${actionBean.kodNegeri.kod ne '04' && actionBean.kodNegeri.kod ne '05'}">
                                <div class="instr-fieldset">
                                    <font color="red">Makluman : </font>Sila klik butang simpan untuk masukkan maklumat saksi dari senarai saksi dalaman.
                                </div>&nbsp;<br/>
                            </c:if>

                            <%--- 
                           <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425'}">
                               <div id="senaraiSaksiOpDiv">
                                   <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:90%;">
                                       <display:column title="Bil" sortable="true" style="width:3%;">${line_rowNum}</display:column>
                                       <display:column title="Maklumat Laporan Operasi" style="width:23%;">
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
                                        <display:column title="Senarai Saksi Dalaman" style="width:55%;">
                                            <c:set value="1" var="count"/>

                                            <table width="100%" cellpadding="1">
                                                <tr>
                                                    <th  width="1%" align="center"><b>Bil</b></th>
                                                    <th  width="1%" align="center"><b>No.Pengenalan</b></th>
                                                    <th  width="1%" align="center"><b>Nama Saksi</b></th>
                                                    <th  width="5%" align="center"><b>Peranan</b></th>
                                                    <th  width="4%" align="center"><b>Kad Kuasa</b></th>
                                                    <th  width="5%" align="center"><b>Jawatan</b></th>
                                                    <th  width="3%" align="center"><b>Hapus</b></th>

                                                </tr>

                                                <c:forEach items="${actionBean.senaraiPasukanSaksi}" var="saksi">
                                                    <c:if test="${line.idOperasi eq saksi.idOperasiPenguatkuasaan.idOperasi}">
                                                        <c:set var="statusSaksiPerRow" value="exist"/>
                                                        <tr>
                                                            <td width="1%">${count}</td>
                                                            <td width="5%">${saksi.noKp}</td>
                                                            <td width="5%">${saksi.nama}</td>
                                                            <td width="5%">${saksi.kodPerananOperasi.nama}</td>
                                                            <td width="4%">${saksi.kadKuasa}</td>
                                                            <td width="5%">${saksi.tempatKerja}</td>
                                                            <td width="3%">
                                                                <div align="center">
                                                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSaksiPasukanMultipleOp('${saksi.idOperasiPenguatkuasaanPasukan}');"/>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <c:set value="${count+1}" var="count"/>
                                                    </c:if>
                                                </c:forEach>
                                            </table>
                                        </display:column>
                                        <display:column title="Tambah Saksi" style="width:90px;">
                                            <c:set var="statusSaksiPerRow" value="none"/>
                                            <c:forEach items="${actionBean.senaraiPasukan}" var="pasukan">
                                                <c:if test="${line.idOperasi eq pasukan.idOperasiPenguatkuasaan.idOperasi}">
                                                    <c:set var="statusSaksiPerRow" value="exist"/>
                                                </c:if>

                                            </c:forEach>
                                            <c:if test="${statusSaksiPerRow eq 'exist'}">
                                                <p>
                                                    <s:select name="pasukan${line_rowNum-1}" id="pasukan${line_rowNum-1}" value="pasukan" onchange="checkExistingMultipleOp('${line_rowNum-1}')" style="width:230px;">
                                                        <s:option value=""> Sila Pilih </s:option>
                                                        <c:forEach items="${actionBean.senaraiPasukan}" var="pasukan">
                                                            <c:if test="${line.idOperasi eq pasukan.idOperasiPenguatkuasaan.idOperasi}">
                                                                <s:option value="${pasukan.idOperasiPenguatkuasaanPasukan}">${pasukan.nama}</s:option>
                                                            </c:if>

                                                        </c:forEach>
                                                    </s:select>&nbsp;
                                                    <s:button class="btn" onclick="if(simpanSaksiOp('${line_rowNum-1}'))doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                                                </p>
                                            </c:if>

                                        </display:column>
                                    </display:table>
                                </div>

                                <p>&nbsp;</p>

                                &nbsp;
                                                              <p>
                                                                    <b>Senarai saksi luar </b>&nbsp;
                                                                </p><br>
                                
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
                                        <c:set value="1" var="count"/>

                                        <table width="100%" cellpadding="1">
                                            <tr>
                                                <th  width="1%" align="center"><b>Bil</b></th>
                                                <th  width="10%" align="center"><b>No.Pengenalan</b></th>
                                                <th  width="20%" align="center"><b>Nama Saksi</b></th>
                                                <th  width="30%" align="center"><b>Alamat</b></th>
                                                <th  width="10%" colspan="2" align="center"><b>Tindakan</b></th>

                                            </tr>
                                        <c:forEach items="${actionBean.senaraiPermohonanSaksi}" var="saksiLuar">
                                            <c:if test="${line.idOperasi eq saksiLuar.operasiPenguatkuasaan.idOperasi}">
                                                <tr>
                                                    <td width="1%">${count}</td>
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
                                        <td width="5%">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSaksiMultipleOp('${saksiLuar.idSaksi}');"/>
                                            </div>
                                        </td>
                                        <td width="5%">
                                            <div align="center">
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupSaksi('${saksiLuar.idSaksi}')"/>
                                            </div>
                                        </td>

                                    </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:forEach>
                                    </table>
                                    </display:column>

                                    <display:column title="Tambah Saksi" style="width:10%;">
                                        <c:set var="statusSaksiLuarPerRow" value="none"/>
                                        <c:forEach items="${actionBean.senaraiPasukan}" var="pasukan">
                                            <c:if test="${line.idOperasi eq pasukan.idOperasiPenguatkuasaan.idOperasi}">
                                                <c:set var="statusSaksiLuarPerRow" value="exist"/>
                                            </c:if>

                                        </c:forEach>
                                        <c:if test="${statusSaksiLuarPerRow eq 'exist'}">
                                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecordSaksi(${line.idOperasi});"/>
                                        </c:if>
                                    </display:column>
                                </display:table>
                            </div>

                            </c:if>---%>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A' && actionBean.permohonan.kodUrusan.kod ne '426'}">
                                <p>
                                    &emsp;&emsp;<b>Senarai Saksi Dalaman 1:</b>
                                    <s:select name="pasukan" id="pasukan" value="pasukan" onchange="checkExisting()">
                                        <s:option value=""> Sila Pilih </s:option>
                                        <c:forEach items="${actionBean.senaraiPasukan}" var="line">
                                            <s:option value="${line.idOperasiPenguatkuasaanPasukan}">${line.nama} - ${line.kodPerananOperasi.nama}
                                            </s:option>
                                        </c:forEach>
                                    </s:select>&nbsp;
                                    <%--<s:button class="btn" onclick="if(simpanSaksi(this.value))doSubmit(this.form, this.name,'page_div')" name="simpan" value="Simpan"/>--%>
                                    <s:button class="btn" onclick="if(simpanSaksi())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                                </p>

                                &nbsp;
                                <div id="senaraiPasukanSaksiDiv">
                                    <display:table class="tablecloth" name="${actionBean.senaraiPasukanSaksi}" cellpadding="0" cellspacing="0" id="line" >
                                        <display:column title="Bil">${line_rowNum}</display:column>
                                        <display:column property="noKp" title="No.Pengenalan"></display:column>
                                        <display:column property="nama" title="Nama Saksi" style="text-transform: uppercase"></display:column>
                                        <display:column property="kodPerananOperasi.nama" title="Peranan" style="text-transform: uppercase"></display:column>
                                        <display:column property="kadKuasa" title="Kad Kuasa" style="text-transform: uppercase"></display:column>
                                        <display:column property="tempatKerja" title="Jawatan" style="text-transform: uppercase"></display:column>

                                        <display:column title="Hapus">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSaksiPasukan('${line.idOperasiPenguatkuasaanPasukan}');"/>
                                            </div>
                                        </display:column>
                                    </display:table>
                                </div>

                                <p>&nbsp;</p>

                                &nbsp;
                                <p>
                                    <b>Senarai saksi luar </b>&nbsp;
                                </p><br>

                                <div id="senaraiPasukanDiv">
                                    <display:table class="tablecloth" name="${actionBean.senaraiPermohonanSaksi}" cellpadding="0" cellspacing="0" id="line" >
                                        <display:column title="Bil">${line_rowNum}</display:column>
                                        <display:column property="noPengenalan" title="No.Pengenalan"></display:column>
                                        <display:column property="nama" title="Nama Saksi" style="text-transform: uppercase"></display:column>
                                        <display:column title="Alamat" style="text-transform: uppercase">${line.alamat1}
                                            <c:if test="${line.alamat2 ne null}"> , </c:if>
                                            ${line.alamat2}
                                            <c:if test="${line.alamat3 ne null}"> , </c:if>
                                            ${line.alamat3}
                                            <c:if test="${line.alamat4 ne null}"> , </c:if>
                                            ${line.alamat4}
                                            ${line.poskod}
                                            ${line.negeri.nama}
                                        </display:column>
                                        <display:column title="Hapus">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSaksi('${line.idSaksi}');"/>
                                            </div>
                                        </display:column>

                                        <display:column title="Kemaskini">
                                            <div align="center">
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupSaksi('${line.idSaksi}')"/>
                                            </div>
                                        </display:column>

                                    </display:table>
                                </div>
                                <table>
                                    <tr>
                                        <td align="right">
                                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>
                                        </td>
                                    </tr>
                                </table>
                            </c:if>

                        </c:if>

                        <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                            <p>&nbsp;</p>

                            &nbsp;
                            <p>
                                <b>Senarai saksi luar</b>&nbsp;
                            </p><br>

                            <div id="senaraiPasukanDiv">
                                <display:table class="tablecloth" name="${actionBean.senaraiPermohonanSaksi}" cellpadding="0" cellspacing="0" id="line" >
                                    <display:column title="Bil">${line_rowNum}</display:column>
                                    <display:column property="noPengenalan" title="No.Pengenalan"></display:column>
                                    <display:column property="nama" title="Nama Saksi" style="text-transform: uppercase"></display:column>
                                    <display:column title="Alamat" style="text-transform: uppercase">${line.alamat1}
                                        <c:if test="${line.alamat2 ne null}"> , </c:if>
                                        ${line.alamat2}
                                        <c:if test="${line.alamat3 ne null}"> , </c:if>
                                        ${line.alamat3}
                                        <c:if test="${line.alamat4 ne null}"> , </c:if>
                                        ${line.alamat4}
                                        ${line.poskod}
                                        ${line.negeri.nama}
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSaksi('${line.idSaksi}');"/>
                                        </div>
                                    </display:column>

                                    <display:column title="Kemaskini">
                                        <div align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupSaksi('${line.idSaksi}')"/>
                                        </div>
                                    </display:column>

                                </display:table>
                            </div>
                            <table>
                                <tr>
                                    <td align="right">
                                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaruSaksi();"/>
                                    </td>
                                </tr>
                            </table>
                        </c:if>

                        <br>
                    </div>

                    <p>&nbsp;</p>

                    <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                        <p>&nbsp;</p>

                        <p>
                            <c:set value="0" var="bil"/>
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                                    <c:set value="8" var="bil"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set value="9" var="bil"/>
                                </c:otherwise>
                            </c:choose>
                            <b> ${bil}.&emsp;RAKAMAN PERCAKAPAN  :       </b>

                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}',
                                     '${actionBean.permohonan.idPermohonan}','RP');return false;" height="30" width="30" alt="Muat Naik"
                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                        </p>

                        <div id="uploadRP">
                            <table>
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                    <tr>
                                        <td>

                                            <c:if test="${senarai.dokumen.kodDokumen.kod eq 'RP'}">
                                                <c:if test="${senarai.dokumen.namaFizikal != null}">
                                                    <br>
                                                    -
                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                         onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                                    <font size="2px;">${senarai.dokumen.tajuk}&nbsp;</font>/
                                                    <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                         onclick="removeRP('${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                                         onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen"/>
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <c:set value="${count+1}" var="count"/>
                                </c:forEach>
                            </table>
                        </div>
                        <p>&nbsp;</p>

                    </c:if>

                    <p>&nbsp;</p>
                    <p>
                        <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                            <b> 8.&emsp;MAKLUMAT PENGADU       </b>&nbsp;
                        </c:if>
                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <c:set value="0" var="bil"/>
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                                    <c:set value="9" var="bil"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set value="10" var="bil"/>
                                </c:otherwise>
                            </c:choose>
                            <b> ${bil}.&emsp;MAKLUMAT PENGADU       </b>&nbsp;
                        </c:if>

                    </p>


                    <div class="subtitle">
                        <fieldset class="aras1">

                            <p>
                                <label>Nama :</label>
                                ${actionBean.permohonan.penyerahNama}&nbsp;
                            </p>
                            <p>
                                <label>Jenis Pengenalan :</label>
                                <font style="text-transform: uppercase">${actionBean.permohonan.penyerahJenisPengenalan.nama}&nbsp;</font>
                            </p>
                            <p>
                                <label>No.Pengenalan :</label>
                                ${actionBean.permohonan.penyerahNoPengenalan}&nbsp;
                            </p>

                            <p>
                                <label>Alamat :</label>
                                ${actionBean.permohonan.penyerahAlamat1}&nbsp;
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.permohonan.penyerahAlamat2}&nbsp;
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.permohonan.penyerahAlamat3}&nbsp;
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.permohonan.penyerahAlamat4}&nbsp;
                            </p>
                            <p>
                                <label>Poskod :</label>
                                ${actionBean.permohonan.penyerahPoskod}&nbsp;
                            </p>
                            <p>
                                <label>Negeri :</label>
                                ${actionBean.permohonan.penyerahNegeri.nama}&nbsp;
                            </p>
                            <p>
                                <label>No.Telefon :</label>
                                ${actionBean.permohonan.penyerahNoTelefon1}&nbsp;
                            </p>
                            <p>
                                <label>Email :</label>
                                ${actionBean.permohonan.penyerahEmail}&nbsp;
                            </p>
                        </fieldset>
                    </div>
                    <p>&nbsp;</p>
                    <p>
                        <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                            <b> 9.&emsp;LAMPIRAN :       </b>
                        </c:if>
                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <c:set value="0" var="bil"/>
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                                    <c:set value="10" var="bil"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set value="11" var="bil"/>
                                </c:otherwise>
                            </c:choose>
                            <b> ${bil}.&emsp;LAMPIRAN :-      </b>&nbsp;
                        </c:if>
                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                             onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}', '${actionBean.permohonanKertas.dokumen.idDokumen}',
                                 '${actionBean.permohonan.idPermohonan}','KS','${actionBean.permohonanKertas.idKertas}');return false;" height="30" width="30" alt="Muat Naik"
                             onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>


                    </p>
                    <div id="uploadLampiranDiv">
                        <table align="left">
                            <tr>
                                <td>
                                    <c:set value="1" var="count"/>
                                    <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                        <c:if test="${senarai.dokumen.kodDokumen.kod eq 'KS'}">
                                            <br>
                                            ${count}  -
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                            (${actionBean.permohonanKertas.dokumen.tajuk})/
                                            <c:if test="${senarai.dokumen.namaFizikal != null}">
                                                <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                     onclick="removeImej('${dokumen.dokumen.idDokumen}','${actionBean.permohonanKertas.idKertas}');" height="15" width="15" alt="Hapus"
                                                     onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${dokumen.dokumen.kodDokumen.nama}"/>
                                            </c:if>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:forEach>

                                </td>

                            </tr>
                        </table>
                    </div>
                </div>
            </fieldset>
            <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>

            <p align="right">
                <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
            </p>
        </c:if>

        <!-- ******************************** view ******************************** -->
        <c:if test="${view}">
            <p>
                <b>1.&emsp;Negeri : </b> ${actionBean.kodNegeri.nama}  &nbsp;
            </p>
            <p>
                &emsp;&emsp;<b>Daerah : </b>
                ${actionBean.permohonan.cawangan.daerah.nama}&nbsp;
            </p>
            <p>
                &emsp;&emsp;<b>Kertas Siasatan Bil:</b>
                ${actionBean.idPermohonan}&nbsp;
            </p>
            <p>&nbsp;</p>
            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                    <p>
                        <b> 2.&emsp;MAKLUMAT PEGAWAI PENYIASAT</b>
                    </p>
                    <div class="content" align="center">
                        <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:70%;">
                            <display:column title="Bil" sortable="true" style="width:5%;">${line_rowNum}</display:column>
                            <display:column title="Maklumat Laporan Operasi" style="width:30%;">
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
                            <display:column title="Pegawai Penyiasat" style="width:60%;">
                                <%--- <c:forEach items="${actionBean.senaraiPasukan}" var="pasukan">
                                    <c:if test="${line.idOperasi eq pasukan.idOperasiPenguatkuasaan.idOperasi && pasukan.kodPerananOperasi.kod eq 'K'}">
                                        <table width="20%" cellpadding="1">
                                            <tr>
                                                <td width="3%"><font class="infoLP">Nama :</font></td>
                                                <td width="10%">${pasukan.nama}</td>
                                            </tr>
                                            <tr>
                                                <td width="7%"><font class="infoLP">No. Pengenalan :</font></td>
                                                <td width="10%">${pasukan.noKp}</td>
                                            </tr>
                                        </table>
                                    </c:if>
                                </c:forEach>---%>
                                <c:set var="statusPerRow" value="none"/>
                                <c:forEach items="${actionBean.listPegawaiSiasat}" var="p">
                                    <c:if test="${p.statusPeranan eq 'K' && p.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                        <c:set var="statusPerRow" value="exist"/>
                                        <table width="20%" cellpadding="1">
                                            <tr>
                                                <td width="20%"><font class="infoLP">Nama Ketua Penyiasat:</font></td>
                                                <td width="20%"> 
                                                    ${p.nama}&nbsp;
                                                    <input type="hidden" name="namaKetua${line_rowNum-1}" id="namaKetua${line_rowNum-1}" value="${p.nama}">
                                                    <input type="hidden" name="idPenggunaKetua${line_rowNum-1}" id="idPenggunaKetua${line_rowNum-1}" value="${p.namaJabatan}">
                                                </td>
                                            </tr>
                                        </table>
                                    </c:if>
                                </c:forEach>


                                <c:if test="${statusPerRow eq 'exist'}"> 
                                    <table width="20%" cellpadding="1">
                                        <tr>
                                            <td width="20%"><font class="infoLP">Nama Pembantu Penyiasat:</font></td>
                                            <td width="20%">&nbsp; </td>
                                        </tr>
                                        <c:set value="1" var="i"/>
                                        <c:forEach items="${actionBean.listPegawaiSiasat}" var="p">
                                            <c:if test="${p.statusPeranan eq 'P' && p.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                                <tr>
                                                    <td width="20%">&nbsp; </td>
                                                    <td width="20%" colspan="2">${i}) ${p.nama}&nbsp; </td>
                                                </tr>
                                                <c:set var="i" value="${i+1}" />
                                            </c:if>
                                        </c:forEach>
                                    </table>
                                </c:if> 

                            </display:column>
                        </display:table>
                    </div>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                    <p>
                        <b> 2.&emsp;Pegawai Penyiasat :</b>
                        <font style="text-transform: uppercase"> <c:if test="${actionBean.pegawaiSiasat.nama eq null}">-</c:if>${actionBean.pegawaiSiasat.nama}</font>&nbsp;
                    </p>
                    <p>
                        &emsp;&emsp;<b>No. Pengenalan : </b> <font style="text-transform: uppercase">${actionBean.noPengenalanKetua}</font>&nbsp;
                    </p>
                </c:if>


            </c:if>
            <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                <p>
                    <b> 2.&emsp;Pegawai Penyiasat :</b>
                    <font style="text-transform: uppercase"> <c:if test="${actionBean.pegawaiSiasat.nama eq null}">-</c:if>${actionBean.pegawaiSiasat.nama}</font>&nbsp;
                </p>
                <p>
                    &emsp;&emsp;<b>No. Pengenalan : </b> <font style="text-transform: uppercase">${actionBean.noPengenalanKetua}</font>&nbsp;
                </p>
            </c:if>
            <p>&nbsp;</p>


            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                    <p>
                        <b> 3.&emsp;LAPORAN POLIS </b>
                    </p>
                    <div class="content" align="center">

                        <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:70%;">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Maklumat Laporan Operasi">
                                <table width="20%" cellpadding="1">
                                    <tr>
                                        <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                        <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
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
                            <display:column title="Senarai Laporan Polis">
                                <c:set value="1" var="count"/>
                                <c:if test="${fn:length(line.senaraiAgensi) ne 0}">
                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="1%" align="center"><b>No Laporan Polis dan Balai</b></th>
                                            <th  width="1%" align="center"><b>Tarikh</b></th>

                                            <th  width="5%" align="center"><b>Papar</b></th>

                                        </tr>
                                        <c:forEach items="${line.senaraiAgensi}" var="agensi">
                                            <tr>
                                                <td width="5%">${count}</td>
                                                <td width="20%"> ${agensi.noRujukan}&nbsp; </td>
                                                <td width="20%"><fmt:formatDate pattern="dd/MM/yyyy" value="${agensi.tarikhRujukan}"/></td>
                                                <td width="30%">
                                                    <c:set value="1" var="count"/>
                                                    <c:forEach items="${actionBean.listDokumen}" var="kf">

                                                        <c:if test="${kf.dokumen.dalamanNilai1 eq agensi.idOperasiAgensi}">
                                                            <p align="left">

                                                                <c:if test="${kf.dokumen.kodDokumen.kod eq 'LP'}">
                                                                    <c:if test="${kf.dokumen.namaFizikal != null}">
                                                                        <br>
                                                                        -
                                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                             onclick="doViewReport('${kf.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${kf.dokumen.kodDokumen.nama}"/>
                                                                        ${count}-${kf.dokumen.tajuk}/
                                                                        <c:set value="${count+1}" var="count"/>
                                                                    </c:if>
                                                                </c:if>
                                                            </p>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                            </tr>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:forEach>
                                    </table>
                                </c:if>
                            </display:column>
                        </display:table>
                    </div>
                </c:if>
            </c:if>

            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                    <p>
                        <b>3.&emsp;Balai Polis Dan Bil Aduan :</b>
                        <c:if test="${actionBean.permohonanRujukanLuar1.noRujukan eq null}">-</c:if>${actionBean.permohonanRujukanLuar1.noRujukan}&nbsp;
                    </p>
                    <p>
                        &emsp;&emsp;<b>Haribulan dan Jam Aduan :</b>
                        <font style="text-transform: uppercase"><c:if test="${actionBean.permohonanRujukanLuar1.tarikhRujukan eq null}">-</c:if><fmt:formatDate value="${actionBean.permohonanRujukanLuar1.tarikhRujukan}" pattern="dd/MM/yyyy hh:mm aaa" /></font>&nbsp;
                    </p>
                    <p>
                        &emsp;&emsp;<b>Tempat Kejadian :</b>
                        <c:if test="${actionBean.aduanLokasi.lokasi eq null}">-</c:if>${actionBean.aduanLokasi.lokasi}&nbsp;
                    </p>
                    <p>
                        &emsp;&emsp;<b>Jenis Kesalahan :</b>
                        <font style="text-transform: uppercase"><c:if test="${actionBean.permohonan.kodUrusan.nama eq null}">-</c:if>${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
                    </p>
                </c:if>
            </c:if>
            <c:if test="${actionBean.kodNegeri.kod eq '05'}">

                <p>
                    <b>3.&emsp;Balai Polis Dan Bil Aduan :</b>
                    <c:if test="${actionBean.permohonanRujukanLuar1.noRujukan eq null}">-</c:if>${actionBean.permohonanRujukanLuar1.noRujukan}&nbsp;
                </p>
                <p>
                    &emsp;&emsp;<b>Haribulan dan Jam Aduan :</b>
                    <font style="text-transform: uppercase"><c:if test="${actionBean.permohonanRujukanLuar1.tarikhRujukan eq null}">-</c:if><fmt:formatDate value="${actionBean.permohonanRujukanLuar1.tarikhRujukan}" pattern="dd/MM/yyyy hh:mm aaa" /></font>&nbsp;
                </p>
                <p>
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq '429'}">
                            &emsp;&emsp;<b>Tempat Aduan :</b>
                        </c:when>
                        <c:otherwise>
                            &emsp;&emsp;<b>Tempat Kejadian :</b>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${actionBean.aduanLokasi.lokasi eq null}">-</c:if>${actionBean.aduanLokasi.lokasi}&nbsp;
                </p>
                <p>
                    &emsp;&emsp;<b>Jenis Kesalahan :</b>
                    <font style="text-transform: uppercase"><c:if test="${actionBean.permohonan.kodUrusan.nama eq null}">-</c:if>${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
                </p>
            </c:if>

            <p>&nbsp;</p>
            <p>
                <b>4.&emsp;KETERANGAN RINGKAS : </b>
                <c:if test="${actionBean.permohonanKertasKandungan.kandungan eq null}">-</c:if> ${actionBean.permohonanKertasKandungan.kandungan}&nbsp;
            </p>
            <p>&nbsp;</p>
            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                <p>
                    <b>5.&emsp;KRONOLOGI KES      </b>
                    &nbsp;
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listAllKronologiKes}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Catatan" property="kandungan"/>
                        <display:column title="Tarikh & Masa"><fmt:formatDate pattern="dd/MM/yyyy hh:mm aaa" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                    </display:table>
                </div>
                <p>&nbsp;</p>
            </c:if>

            <p>
                <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                    <b>5.&emsp;BARANG KES      </b>
                    &nbsp;
                </c:if>
                <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                    <b>6.&emsp;BARANG KES      </b>
                    &nbsp;
                </c:if>

            <div class="content" align="center">
                <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                        <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0"  style="width:60%;">
                            <display:column title="Bil" sortable="true" style="width:3%;">${line_rowNum}</display:column>
                            <display:column title="Maklumat Laporan Operasi" style="width:30%;">
                                <table width="10%" cellpadding="1">
                                    <tr>
                                        <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                        <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
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
                            <display:column title="Maklumat Barang Rampasan" style="width:50%;">
                                <c:set value="1" var="count"/>

                                <table width="100%" cellpadding="1">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="1%" align="center"><b>Item</b></th>
                                        <th  width="5%" align="center"><b>Papar</b></th>

                                    </tr>
                                    <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                        <c:if test="${barang.aduanOrangKenaSyak.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan}">
                                            <tr>
                                                <td width="5%">${count}</td>
                                                <td width="50%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                                <td width="30%">
                                                    <c:if test="${barang.imej.namaFizikal != null}">
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                             onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/>
                                                    </c:if>
                                                </td>

                                            </tr>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:forEach>
                                </table>
                                <%-- <b>Jumlah : ${fn:length(line.senaraiBarangRampasan)}</b>--%>
                            </display:column>
                        </display:table>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                        <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column title="Barang yang Dirampas"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a></display:column>
                            <display:column title="Kuantiti">
                                <c:if test="${line.kodKategoriItemRampasan.kod eq 'K'}">
                                    1
                                </c:if>
                                <c:if test="${line.kodKategoriItemRampasan.kod eq 'B'}">
                                    ${line.kuantiti}
                                </c:if>
                            </display:column>
                            <display:column title="Tempat Simpanan" property="tempatSimpanan"></display:column>
                            <display:column title="Catatan" property="catatan"></display:column>
                            <display:column title="Status" property="status.nama" />

                        </display:table>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                    <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Barang yang Dirampas"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a></display:column>
                        <display:column title="Kuantiti">
                            <c:if test="${line.kodKategoriItemRampasan.kod eq 'K'}">
                                1
                            </c:if>
                            <c:if test="${line.kodKategoriItemRampasan.kod eq 'B'}">
                                ${line.kuantiti}
                            </c:if>
                        </display:column>
                        <display:column title="Tempat Simpanan" property="tempatSimpanan"></display:column>
                        <display:column title="Catatan" property="catatan"></display:column>
                        <display:column title="Status" property="status.nama" />

                    </display:table>
                </c:if>
            </div>

            <p>&nbsp;</p>
            <p>
                <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                    <b>6.&emsp;ORANG KENA SYAK       </b>&nbsp;
                </c:if>
                <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                    <b>7.&emsp;ORANG KENA SYAK       </b>&nbsp;
                </c:if>

            </p>

            <div align="center">
                <p>
                    &emsp;&emsp;<b>Tuduhan ( Sebut Seksyen ) :</b>
                    <font style="text-transform: uppercase"><c:if test="${actionBean.permohonan.kodUrusan.nama eq null}">-</c:if>${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;

                </p>
                <p>&nbsp;</p>
                <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                        <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:60%;">
                            <display:column title="Bil" sortable="true" style="width:3%;">${line_rowNum}</display:column>
                            <display:column title="Maklumat Laporan Operasi" style="width:30%;">
                                <table width="10%" cellpadding="1">
                                    <tr>
                                        <td width="10%"><font class="infoLP">Id Operasi :</font></td>
                                        <td width="10%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                    </tr>
                                    <tr>
                                        <td width="10%"><font class="infoLP">Tarikh laporan :</font></td>
                                        <td width="10%">
                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                    </tr>
                                    <tr>
                                        <td width="10%"><font class="infoLP">Masa laporan :</font></td>
                                        <td width="10%">
                                            <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                            <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                            <c:if test="${time eq 'AM'}">PAGI</c:if>
                                            <c:if test="${time eq 'PM'}">PETANG</c:if>
                                        </td>
                                    </tr>

                                </table>
                            </display:column>
                            <display:column title="Maklumat Orang Disyaki" style="width:50%;">
                                <c:set value="1" var="count"/>
                                <table width="100%" cellpadding="1">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="1%" align="center"><b>Nama</b></th>
                                        <th  width="5%" align="center"><b>Papar</b></th>
                                    </tr>
                                    <c:forEach items="${line.senaraiAduanOrangKenaSyak}" var="oks">
                                        <c:if test="${oks.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan && oks.statusOrangKenaSyak eq null}">
                                            <tr>
                                                <td width="5%">${count}</td>
                                                <td width="50%"><a class="popup" onclick="viewOKS(${oks.idOrangKenaSyak})">${oks.nama}</a></td>

                                                <td width="30%">
                                                    <c:if test="${oks.dokumen.namaFizikal != null}">
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                             onclick="doViewReport('${oks.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${oks.dokumen.kodDokumen.nama}"/>
                                                    </c:if>

                                                </td>
                                            </tr>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:forEach>
                                </table>
                            </display:column>

                        </display:table>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                        <display:table class="tablecloth" name="${actionBean.senaraiAduanOrangKenaSyak}" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="No.Pengenalan"><a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line.noPengenalan}</a></display:column>
                            <display:column title="Nama" property="nama"></display:column>
                            <display:column title="Alamat">${line.alamat.alamat1}
                                <c:if test="${line.alamat.alamat2 ne null}"> , </c:if>
                                ${line.alamat.alamat2}
                                <c:if test="${line.alamat.alamat3 ne null}"> , </c:if>
                                ${line.alamat.alamat3}
                                <c:if test="${line.alamat.alamat4 ne null}"> , </c:if>
                                ${line.alamat.alamat4}
                                ${line.alamat.poskod}
                                ${line.alamat.negeri.nama}
                            </display:column>
                            <display:column title="Keterangan" property="keterangan"></display:column>
                        </display:table>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                    <display:table class="tablecloth" name="${actionBean.senaraiAduanOrangKenaSyak}" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No.Pengenalan"><a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line.noPengenalan}</a></display:column>
                        <display:column title="Nama" property="nama"></display:column>
                        <display:column title="Alamat">${line.alamat.alamat1}
                            <c:if test="${line.alamat.alamat2 ne null}"> , </c:if>
                            ${line.alamat.alamat2}
                            <c:if test="${line.alamat.alamat3 ne null}"> , </c:if>
                            ${line.alamat.alamat3}
                            <c:if test="${line.alamat.alamat4 ne null}"> , </c:if>
                            ${line.alamat.alamat4}
                            ${line.alamat.poskod}
                            ${line.alamat.negeri.nama}
                        </display:column>
                        <display:column title="Keterangan" property="keterangan"></display:column>
                    </display:table>
                </c:if>
            </div>
            <p>&nbsp;</p>

            <p>&nbsp;</p>
            <p>
                <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                    <b>7.&emsp;SAKSI       </b>&nbsp;
                </c:if>
                <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                        <b>8.&emsp;SAKSI       </b>&nbsp;
                    </c:if>
                </c:if>
            </p>
            <div class="content" align="center">
                <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                    &nbsp;
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                        <p>
                            <b>Senarai saksi dalaman</b>&nbsp;
                        </p> 
                    </c:if>

                    <%--- 
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425'}">
                        <div id="senaraiSaksiOpDiv">
                            <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:90%;">
                                <display:column title="Bil" sortable="true" style="width:3%;">${line_rowNum}</display:column>
                                <display:column title="Maklumat Laporan Operasi" style="width:23%;">
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
                                <display:column title="Senarai Saksi Dalaman" style="width:55%;">
                                    <c:set value="1" var="count"/>

                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="1%" align="center"><b>No.Pengenalan</b></th>
                                            <th  width="1%" align="center"><b>Nama Saksi</b></th>
                                            <th  width="5%" align="center"><b>Peranan</b></th>
                                            <th  width="4%" align="center"><b>Kad Kuasa</b></th>
                                            <th  width="5%" align="center"><b>Jawatan</b></th>
                                        </tr>
                                        <c:forEach items="${actionBean.senaraiPasukanSaksi}" var="saksi">
                                            <c:if test="${line.idOperasi eq saksi.idOperasiPenguatkuasaan.idOperasi}">
                                                <tr>
                                                    <td width="1%">${count}</td>
                                                    <td width="5%">${saksi.noKp}</td>
                                                    <td width="5%">${saksi.nama}</td>
                                                    <td width="5%">${saksi.kodPerananOperasi.nama}</td>
                                                    <td width="4%">${saksi.kadKuasa}</td>
                                                    <td width="5%">${saksi.tempatKerja}</td>
                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:forEach>
                                    </table>
                                </display:column>
                            </display:table>
                        </div>


                        <p>&nbsp;</p>

                        &nbsp;
                        
                        
                        <p>
                            <b>Senarai saksi luar </b>&nbsp;
                        </p><br>

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
                                    <c:set value="1" var="count"/>

                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="10%" align="center"><b>No.Pengenalan</b></th>
                                            <th  width="20%" align="center"><b>Nama Saksi</b></th>
                                            <th  width="30%" align="center"><b>Alamat</b></th>
                                        </tr>
                                        <c:forEach items="${actionBean.senaraiPermohonanSaksi}" var="saksiLuar">
                                            <c:if test="${line.idOperasi eq saksiLuar.operasiPenguatkuasaan.idOperasi}">
                                                <tr>
                                                    <td width="1%">${count}</td>
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
                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:forEach>
                                    </table>
                                </display:column>
                            </display:table>
                        </div>
                        


                    </c:if> ---%>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A' && actionBean.permohonan.kodUrusan.kod ne '426'}">
                        &nbsp;
                        <div id="senaraiPasukanSaksiDiv">
                            <display:table class="tablecloth" name="${actionBean.senaraiPasukanSaksi}" cellpadding="0" cellspacing="0" id="line" >
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column property="noKp" title="No.Pengenalan"></display:column>
                                <display:column property="nama" title="Nama Saksi" style="text-transform: uppercase"></display:column>
                                <display:column property="kodPerananOperasi.nama" title="Peranan" style="text-transform: uppercase"></display:column>
                                <display:column property="kadKuasa" title="Kad Kuasa" style="text-transform: uppercase"></display:column>
                                <display:column property="tempatKerja" title="Jawatan" style="text-transform: uppercase"></display:column>
                            </display:table>
                        </div>

                        <br>
                        &nbsp;
                        <p>
                            <b>Senarai saksi luar </b>&nbsp;
                        </p>
                        <display:table class="tablecloth" name="${actionBean.senaraiPermohonanSaksi}" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column title="No.Pengenalan"><a class="popup" onclick="viewSaksi(${line.idSaksi})">${line.noPengenalan}</a></display:column>
                            <display:column property="nama" title="Nama Saksi" style="text-transform: uppercase"></display:column>
                            <display:column title="Alamat" style="text-transform: uppercase">${line.alamat1}
                                <c:if test="${line.alamat2 ne null}"> , </c:if>
                                ${line.alamat2}
                                <c:if test="${line.alamat3 ne null}"> , </c:if>
                                ${line.alamat3}
                                <c:if test="${line.alamat4 ne null}"> , </c:if>
                                ${line.alamat4}
                                ${line.poskod}
                                ${line.negeri.nama}
                            </display:column>

                        </display:table>

                    </c:if>

                </c:if>

                <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                    <br>
                    &nbsp;
                    <p>
                        <b>Senarai saksi luar </b>&nbsp;
                    </p>
                    <display:table class="tablecloth" name="${actionBean.senaraiPermohonanSaksi}" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="No.Pengenalan"><a class="popup" onclick="viewSaksi(${line.idSaksi})">${line.noPengenalan}</a></display:column>
                        <display:column property="nama" title="Nama Saksi" style="text-transform: uppercase"></display:column>
                        <display:column title="Alamat" style="text-transform: uppercase">${line.alamat1}
                            <c:if test="${line.alamat2 ne null}"> , </c:if>
                            ${line.alamat2}
                            <c:if test="${line.alamat3 ne null}"> , </c:if>
                            ${line.alamat3}
                            <c:if test="${line.alamat4 ne null}"> , </c:if>
                            ${line.alamat4}
                            ${line.poskod}
                            ${line.negeri.nama}
                        </display:column>

                    </display:table>
                </c:if>


                <br>
            </div>

            <p>&nbsp;</p>

            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                <p>
                    <c:set value="0" var="bil"/>
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                            <c:set value="8" var="bil"/>
                        </c:when>
                        <c:otherwise>
                            <c:set value="9" var="bil"/>
                        </c:otherwise>
                    </c:choose>
                    <b> ${bil}.&emsp;RAKAMAN PERCAKAPAN  :       </b>

                    <c:set value="1" var="count"/>
                    <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                        <c:if test="${senarai.dokumen.kodDokumen.kod eq 'RP'}">
                            <c:if test="${senarai.dokumen.namaFizikal != null}">
                                <br>
                                ${count}  -
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                ${senarai.dokumen.tajuk}
                                <c:set value="${count+1}" var="count"/>
                            </c:if>
                        </c:if>
                    </c:forEach>

                    <c:forEach items="${actionBean.senaraiDokumen}" var="k">
                        <c:if test="${k.dokumen.kodDokumen.kod eq 'RP'}">
                            <c:if test="${k.dokumen.namaFizikal != null}">
                                <br>
                                ${count}  -
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${k.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${k.dokumen.kodDokumen.nama}"/>
                                ${k.dokumen.tajuk}
                                <c:set value="${count+1}" var="count"/>
                            </c:if>

                        </c:if>
                    </c:forEach>
                </p>
            </c:if>


            <p>&nbsp;</p>
            <p>
                <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                    <b>8.&emsp;MAKLUMAT PENGADU       </b>&nbsp;
                </c:if>
                <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                    <c:set value="0" var="bil"/>
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                            <c:set value="9" var="bil"/>
                        </c:when>
                        <c:otherwise>
                            <c:set value="10" var="bil"/>
                        </c:otherwise>
                    </c:choose>
                    <b> ${bil}.&emsp;MAKLUMAT PENGADU       </b>&nbsp;
                </c:if>
            </p>
            <div class="subtitle">
                <fieldset class="aras1">

                    <p>
                        <label>Nama :</label>
                        ${actionBean.permohonan.penyerahNama}&nbsp;
                    </p>
                    <p>
                        <label>Jenis Pengenalan :</label>
                        <font style="text-transform: uppercase">${actionBean.permohonan.penyerahJenisPengenalan.nama}&nbsp;</font>

                    </p>
                    <p>
                        <label>No.Pengenalan :</label>
                        ${actionBean.permohonan.penyerahNoPengenalan}&nbsp;
                    </p>

                    <p>
                        <label>Alamat :</label>
                        ${actionBean.permohonan.penyerahAlamat1}&nbsp;
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.permohonan.penyerahAlamat2}&nbsp;
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.permohonan.penyerahAlamat3}&nbsp;
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.permohonan.penyerahAlamat4}&nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        ${actionBean.permohonan.penyerahPoskod}&nbsp;
                    </p>
                    <p>
                        <label>Negeri :</label>
                        ${actionBean.permohonan.penyerahNegeri.nama}&nbsp;
                    </p>
                    <p>
                        <label>No.Telefon :</label>
                        ${actionBean.permohonan.penyerahNoTelefon1}&nbsp;
                    </p>
                    <p>
                        <label>Email :</label>
                        ${actionBean.permohonan.penyerahEmail}&nbsp;
                    </p>
                </fieldset>
            </div>
            <p>&nbsp;</p>

            <p>
                <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                    <b>9.&emsp;LAMPIRAN :-      </b>&nbsp;
                </c:if>
                <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                    <c:set value="0" var="bil"/>
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                            <c:set value="10" var="bil"/>
                        </c:when>
                        <c:otherwise>
                            <c:set value="11" var="bil"/>
                        </c:otherwise>
                    </c:choose>
                    <b> ${bil}.&emsp;LAMPIRAN :-</b>&nbsp;
                </c:if>

                <c:set value="1" var="count"/>
                <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                    <c:if test="${senarai.dokumen.kodDokumen.kod eq 'KS'}">
                        <br>
                        ${count}  -
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                             onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                        (${actionBean.permohonanKertas.dokumen.tajuk})
                        <c:set value="${count+1}" var="count"/>
                    </c:if>
                </c:forEach>

                <c:forEach items="${actionBean.senaraiDokumen}" var="k">
                    <br>
                    <c:if test="${k.dokumen.kodDokumen.kod eq 'KS'}">
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                             onclick="doViewReport('${k.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${k.dokumen.kodDokumen.nama}"/>
                        (${actionBean.permohonanKertas.dokumen.tajuk})
                        <c:set value="${count+1}" var="count"/>
                    </c:if>
                </c:forEach>
            </p>
        </c:if>
    </div>
</s:form>