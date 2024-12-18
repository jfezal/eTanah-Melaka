<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }


        var status = $('#statusTanah').val();
        //        alert("status :"+status);
        if(status == "H"){
            document.getElementById("tanahHakmilik").checked = true;
        }else if(status == "K"){
            document.getElementById("tanahKerajaan").checked = true;
        }else{
            status = "H";
            document.getElementById("tanahHakmilik").checked = true;
        }
        
        changeStatusTanah(status);
        
        var idMohonHakmilik = $('#idMohonHakmilik').val();
        //        refreshWholePage(idMohonHakmilik);
    });
    
          
    function changeStatusTanah(value){
        //        alert("val :"+value);
        if(value == "H"){
            //if have id hakmilik
            $('#tanahMilikDiv').hide();
            $('#maklumatHakmilikDiv').show();
            document.getElementById("tanahHakmilik").checked = true;
        }else if(value == "K"){
            //if dont have id hakmilik
            $('#tanahMilikDiv').show();
            $('#maklumatHakmilikDiv').hide();
            document.getElementById("tanahKerajaan").checked = true;
        }
               
    }
    
    function addMaklumatTanah(i,j,idValue){
        var bil =  $('#jumlahLaporanTanah').val();
        //        alert(bil);
        if(bil == 0){
            if(j != 'TK' && j != 'TH'){
                alert("Sila masukkan hakmilik/tanah kerajaan terlebih dahulu.");
                $('#tanahHakmilik').focus();
                return false;
            }
        }
        
        if($('#idLaporanTanah').val() == ""){
            if(j != 'TK' && j != 'TH'){
                alert("Sila pilih hakmilik/tanah kerajaan terlebih dahulu.");
                $('#idMohonHakmilik').focus();
                return false;  
            }
        }
        doBlockUI();
        var id = $('#idLaporanTanah').val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/mohon_lapor_tanah?addMaklumatTanah'+'&statusDisplay='+i+'&jenisKemasukan='+j+'&idLaporanTanah='+id+'&idValue='+idValue;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1000,scrollbars=yes, left=" + left + ",top=" + top);
    }
    
    function refreshFindInfo(val) {
        doBlockUI();
        var statusOpEdit = $('#statusOpEdit').val();
        var statusOpView = $('#statusOpView').val();
        var statusOpSyor = $('#statusOpSyor').val();
        var j = "";
        if(statusOpEdit == "true"){
            j = "edit";
        }
        
        if(statusOpView == "true"){
            j = "view";
        }
        
        if(statusOpSyor == "true"){
            j = "syorPPTK";
        }
        
        //        alert(j);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/mohon_lapor_tanah?reload&idLaporanTanah=' + val+'&statusOp='+j;
        //        alert(url);
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $("#maklumatLaporanTanahDiv").replaceWith($('#maklumatLaporanTanahDiv', $(data)));
                $("#infoLaporanTanahDiv").replaceWith($('#infoLaporanTanahDiv', $(data)));
                $("#totalListDiv").replaceWith($('#totalListDiv', $(data)));
                doUnBlockUI();
                var status = $('#statusTanah').val();
                changeStatusTanah(status);
            }
            
        });
        //        setTimeout($.unblockUI, 1000);
    }
    
    function muatNaikForm(folderId, dokumenId, idPermohonan, dokumenKod, idLaporan, idHakmilik, idRujukan, jenisLT) {
        doBlockUI();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId + '&dokumenId='
            + dokumenId + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idLaporan=' + idLaporan + '&idHakmilik=' + idHakmilik + '&idRujukan=' + idRujukan+ '&jenisLaporanV2=' + jenisLT;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
    
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function removeImej(idImej, idDokumen) {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/mohon_lapor_tanah?deleteSelected&idImej='+idImej+'&idDokumen='+idDokumen;
            $.get(url,
            function(data){
                $("#TanahHakmilikDiv").replaceWith($('#TanahHakmilikDiv', $(data)));
                var id = $('#idLaporanTanah').val();
                refreshFindInfo(id);
            },'html');
        }
    }
    
    function viewHakmilik(id){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah?addHakmilik&id='+id+'&statusDisplay=view';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
        
    }
    
    function removeHakmilik(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/mohon_lapor_tanah?deleteJenisHakisan&id='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }
    
    function removeSempadan(idLaporTanahSpdn){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/jenis_sempadan?deleteSempadan&idLaporTanahSpdn='+idLaporTanahSpdn;
            $.get(url,
            function(data){
                $("#senaraiSempadan").replaceWith($('#senaraiSempadan', $(data)));
                var id = $('#idLaporanTanah').val();
                refreshFindInfo(id);
            },'html');}
    }
    
    function removeBangunan(idLaporBangunan){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/jenis_bangunan?deleteBangunan&idLaporBangunan='+idLaporBangunan;
            $.get(url,
            function(data){
                $("#senaraiBangunan").replaceWith($('#senaraiBangunan', $(data)));
                var id = $('#idLaporanTanah').val();
                refreshFindInfo(id);
            },'html');}
    }
    
    function viewTanahSekeliling(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/jenis_sempadan?tanahSekelilingDetails&idLaporTanahSpdn='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=400,scrollbars=yes");
    }
    
          
</script>


<s:form beanclass="etanah.view.penguatkuasaan.MohonLaporTanahActionBean" name="form">
    <div id="infoLaporanTanahDiv">
        <s:hidden name="permohonan.idPermohonan" />
        <s:hidden name="statusTanah" id="statusTanah"/>
        <s:hidden name="jenisLaporan" value="${actionBean.jenisLaporan}"/>
        <s:hidden name="laporanTanah.idLaporan" id="idLaporanTanah"/>
        <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
        <s:hidden name="laporanTanah.namaSempadanJalanraya"/>
        <input type="hidden" value="${edit}" id="statusOpEdit">
        <input type="hidden" value="${view}" id="statusOpView">
        <input type="hidden" value="${syorPPTK}" id="statusOpSyor">
        <input type="hidden" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="jumlahPermohonanHakmilik">
        <input type="hidden" value="${fn:length(actionBean.senaraiTanahMilik)}" id="jumlahTanahKerajaan">
        <input type="hidden" value="${fn:length(actionBean.listLaporanTanah)}" id="jumlahLaporanTanah">
        <s:messages/>
        <s:errors/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Laporan Tanah
            </legend>
            <div id="totalListDiv">
                <c:if test="${fn:length(actionBean.listLaporanTanah) eq 0}">
                    <br>
                    <div class="instr-fieldset">
                        <font color="red">MAKLUMAN : </font>Sila Masukkan Maklumat Tanah Kerajaan/Rizab atau Maklumat Hakmilik.
                    </div>&nbsp;
                </c:if>
                <c:if test="${fn:length(actionBean.listLaporanTanah) ne 0}">
                    <p>
                        <label>Senarai Hakmilik/Tanah Kerajaan: </label>
                        <s:select name="idMohonHakmilik" onchange="refreshFindInfo(this.value);" id="idMohonHakmilik">
                            <c:forEach items="${actionBean.listLaporanTanah}" var="item" varStatus="line">
                                <c:if test="${empty item.hakmilikPermohonan.hakmilik}">
                                    <s:option value="${item.idLaporan}" style="width:400px">
                                        ${item.hakmilikPermohonan.noLot} - ${item.hakmilikPermohonan.lot.nama}
                                    </s:option>
                                </c:if>
                                <c:if test="${not empty item.hakmilikPermohonan.hakmilik}">
                                    <s:option value="${item.idLaporan}" style="width:400px">
                                        ${item.hakmilikPermohonan.hakmilik.idHakmilik} ( ${item.hakmilikPermohonan.hakmilik.daerah.nama} - ${item.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama} )
                                    </s:option>
                                </c:if>

                            </c:forEach>
                        </s:select>
                    </p>   
                </c:if>
            </div>
            <br>

        </fieldset>
    </div>

    <div id="maklumatLaporanTanahDiv">
        <div class="subtitle">
            <fieldset class="aras1">
                <p>
                    <label>Status Tanah : </label>
                    <input type="radio" name="statusTanah" id="tanahKerajaan" value="K" onclick="changeStatusTanah('K');" />Tanah Kerajaan/Rezab
                    <input type="radio" name="statusTanah" id="tanahHakmilik" value="H" onclick="changeStatusTanah('H');" />Tanah Milik
                </p>

                <div id="tanahMilikDiv">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>Tanah Kerajaan/Rizab</legend>
                            <c:if test="${edit}">
                                <p>
                                    <a onclick="addMaklumatTanah('edit','TK','');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </p>
                            </c:if>
                            <div class="content" align="center">
                                <display:table  name="${actionBean.senaraiTanahMilik}" id="line" class="tablecloth">
                                    <display:column title="Nombor Lot/PT" >
                                        <c:if test="${line.noLot ne null}"> ${line.noLot}&nbsp; </c:if>
                                        <c:if test="${line.noLot eq null}"> Tiada </c:if>
                                    </display:column>

                                    <display:column title="Kod Lot" >
                                        <c:if test="${line.lot.nama ne null}"> ${line.lot.nama}&nbsp; </c:if>
                                        <c:if test="${line.lot.nama eq null}"> Tiada </c:if>
                                    </display:column>

                                    <display:column title="Kategori Tanah" >
                                        <c:if test="${line.kategoriTanahBaru.nama ne null}"> ${line.kategoriTanahBaru.nama}&nbsp; </c:if>
                                        <c:if test="${line.kategoriTanahBaru.nama eq null}"> Tiada </c:if>
                                    </display:column>

                                    <display:column title="Luas">
                                        <c:if test="${line.luasTerlibat ne null}"> ${line.luasTerlibat}&nbsp; ${line.kodUnitLuas.nama}</c:if>
                                        <c:if test="${line.luasTerlibat eq null}"> Tiada </c:if>
                                    </display:column>

                                    <display:column title="Bandar/Pekan/Mukim">
                                        <c:if test="${line.bandarPekanMukimBaru.nama ne null}"> ${line.bandarPekanMukimBaru.nama}</c:if>
                                        <c:if test="${line.bandarPekanMukimBaru.nama eq null}"> Tiada </c:if>
                                    </display:column>

                                    <display:column title="Kod Dun">
                                        <c:if test="${line.kodDUN.nama ne null}"> ${line.kodDUN.nama}</c:if>
                                        <c:if test="${line.kodDUN.nama eq null}"> Tiada </c:if>
                                    </display:column>

                                    <display:column title="Catatan">
                                        <c:if test="${line.catatan ne null}"> ${line.catatan}&nbsp; </c:if>
                                        <c:if test="${line.catatan eq null}"> Tiada </c:if>
                                    </display:column>
                                    <display:column title="Imej Di Atas Tanah">
                                        <c:if test="${edit}">
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                                 onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}','',
                                                     '${actionBean.permohonan.idPermohonan}','IH','${actionBean.laporanTanah.idLaporan}','${line.hakmilik.idHakmilik}','${line.id}','Y');return false;" height="30" width="30" alt="Muat Naik"
                                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen Gambar Hakmilik"/>
                                            <br>
                                        </c:if>
                                        <c:set value="1" var="count"/>
                                        <c:forEach items="${actionBean.imejLaporanList}" var="senarai">
                                            <c:if test="${senarai.dokumen.kodDokumen.kod eq 'IH' && senarai.dokumen.perihal eq line.id}">
                                                <%--<c:if test="${senarai.dokumen.namaFizikal ne null}">--%>
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen Gambar Hakmilik"/> <font size="1">${count}-${senarai.dokumen.tajuk}</font>

                                                <c:if test="${edit}">
                                                    /
                                                    <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                         onclick="removeImej('${senarai.idImej}','${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                                         onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                                </c:if>

                                                <br/>
                                                <c:set value="${count+1}" var="count"/>
                                                <%--</c:if>--%>
                                            </c:if>
                                        </c:forEach>
                                    </display:column>
                                    <c:if test="${edit}">
                                        <display:column title="Kemaskini">
                                            <div align="center">
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="addMaklumatTanah('edit','TK','${line.id}');"/>
                                            </div>
                                        </display:column>

                                        <display:column title="Hapus" >
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeHakmilik('${line.id}');"/>
                                            </div>
                                        </display:column>

                                    </c:if>

                                </display:table>
                            </div>
                        </fieldset>
                    </div>
                </div>
                <div id="maklumatHakmilikDiv">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>
                                Maklumat Hakmilik
                            </legend>
                            <c:if test="${edit}">
                                <p>
                                    <a onclick="addMaklumatTanah('edit','TH','');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </p>
                            </c:if>
                            <div id="multipleHakmilikDiv">
                                <div class="content" align="center">
                                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                                                   id="line">
                                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">
                                            <c:if test="${line.hakmilik.idHakmilik eq null}">
                                                Tiada rekod
                                            </c:if>
                                            <c:if test="${line.hakmilik.idHakmilik ne null}">
                                                <u><a class="popup" onclick="viewHakmilik(${line.id})">${line_rowNum}</a></u>
                                            </c:if>
                                        </display:column>
                                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                                        <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                                        <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                                        <c:if test="${edit}">
                                            <display:column title="Kemaskini">
                                                <div align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="addMaklumatTanah('edit','TH','${line.id}');"/>
                                                </div>
                                            </display:column>  
                                            <display:column title="Hapus">
                                                <div align="center">
                                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeHakmilik('${line.id}');"/>
                                                </div>
                                            </display:column>  
                                        </c:if>
                                    </display:table>

                                    <br>

                                    Senarai Pemilik  <br>
                                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                                        <display:column title="Nama">
                                            <c:set value="1" var="count"/>
                                            <c:forEach items="${actionBean.pihakBerkepentinganList}" var="senarai">
                                                <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                    <c:out value="${count}"/>)&nbsp;
                                                    <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                                    <c:set value="${count + 1}" var="count"/>
                                                    <br>
                                                </c:if>
                                            </c:forEach>
                                        </display:column>
                                        <display:column title="Jenis Pihak Berkepentingan">
                                            <c:set value="1" var="count"/>
                                            <c:forEach items="${actionBean.pihakBerkepentinganList}" var="senarai">
                                                <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                    <c:out value="${count}"/>)&nbsp;
                                                    <c:out value="${senarai.jenis.nama}"/><br>
                                                    <c:set value="${count + 1}" var="count"/><br>
                                                </c:if>
                                            </c:forEach>
                                        </display:column>
                                        <display:column title="Syer yang dimiliki">
                                            <c:set value="1" var="count"/>
                                            <c:forEach items="${actionBean.pihakBerkepentinganList}" var="senarai">
                                                <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                    <c:out value="${count}"/>)&nbsp;
                                                    <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                                    <c:set value="${count + 1}" var="count"/><br>
                                                </c:if>
                                            </c:forEach>
                                        </display:column>
                                        <display:column title="Tarikh Pemilikan Didaftar">
                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilik.tarikhDaftar}"/>
                                        </display:column>
                                    </display:table>
                                    <br>

                                    Senarai Pihak Berkepentingan <br>
                                    <div id="DocHakmilikDiv">
                                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                            <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                                            <display:column title="Nama">
                                                <c:set value="1" var="count"/>
                                                <c:forEach items="${actionBean.pihakKepentinganList}" var="senarai">
                                                    <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                        <c:out value="${count}"/>)&nbsp;
                                                        <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                                        <c:set value="${count + 1}" var="count"/>
                                                        <br>
                                                    </c:if>
                                                </c:forEach>
                                            </display:column>
                                            <display:column title="Jenis Pihak Berkepentingan">
                                                <c:set value="1" var="count"/>
                                                <c:forEach items="${actionBean.pihakKepentinganList}" var="senarai">
                                                    <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                        <c:out value="${count}"/>)&nbsp;
                                                        <c:out value="${senarai.jenis.nama}"/><br>
                                                        <c:set value="${count + 1}" var="count"/><br>
                                                    </c:if>
                                                </c:forEach>
                                            </display:column>
                                            <display:column title="Syer yang dimiliki">
                                                <c:set value="1" var="count"/>
                                                <c:forEach items="${actionBean.pihakKepentinganList}" var="senarai">
                                                    <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                        <c:out value="${count}"/>)&nbsp;
                                                        <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                                        <c:set value="${count + 1}" var="count"/><br>
                                                    </c:if>
                                                </c:forEach>
                                            </display:column>
                                            <display:column title="Tarikh Pemilikan Didaftar">
                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilik.tarikhDaftar}"/>
                                            </display:column>
                                        </display:table>
                                    </div>
                                    <br>

                                    Senarai Waris <br>

                                    <display:table name="${actionBean.hakmilikWarisList}" id="line2" class="tablecloth">
                                        <display:column title="Bil">
                                            ${line2_rowNum}
                                            <s:hidden name="x" class="x${line2_rowNum -1}" value=""/>
                                        </display:column>
                                        <display:column property="nama" title="Nama" />
                                        <display:column property="noPengenalan" title="No. Pengenalan" />
                                        <display:column title="Syer"> ${line2.syerPembilang }/${line2.syerPenyebut} </display:column>
                                    </display:table>

                                </div>
                            </div>

                            <div id="TanahHakmilikDiv">
                                <div class="subtitle">
                                    <fieldset class="aras1">
                                        <legend>Tanah Milik</legend>
                                        <div class="content" align="center">
                                            <display:table  name="${actionBean.hakmilikPermohonanList}" id="line" class="tablecloth">
                                                <display:column title="Jenis Hakmilik">
                                                    <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                                                    <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada </c:if>
                                                </display:column>

                                                <display:column title="Nombor Hakmilik">
                                                    <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                                                    <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada </c:if>
                                                </display:column>

                                                <display:column title="Nombor Lot/PT" >
                                                    <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                                                    <c:if test="${line.hakmilik.noLot eq null}"> Tiada </c:if>
                                                </display:column>
                                                <display:column title="Luas">
                                                    <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                                                    <c:if test="${line.hakmilik.luas eq null}"> Tiada </c:if>
                                                </display:column>
                                                <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada </c:if>
                                                </display:column>
                                                <display:column title="Syarat Nyata">
                                                    <c:if test="${line.hakmilik.syaratNyata.syarat ne null}"> ${line.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                                                    <c:if test="${line.hakmilik.syaratNyata.syarat eq null}"> Tiada </c:if>
                                                </display:column>
                                                <display:column property="hakmilik.rizab.nama" title="Rizab" >
                                                    <c:if test="${actionBean.hakmilik.rizab.nama ne null}"> ${actionBean.hakmilik.rizab.nama}&nbsp; </c:if>
                                                    <c:if test="${actionBean.hakmilik.rizab.nama eq null}"> Tiada </c:if>
                                                </display:column>

                                                <display:column title="Cukai (RM)">
                                                    <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                                                    <c:if test="${line.hakmilik.cukai eq null}"> Tiada </c:if>
                                                </display:column>
                                                <display:column title="Imej Di Atas Tanah ">
                                                    <c:if test="${edit}">
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                                             onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}','',
                                                                 '${actionBean.permohonan.idPermohonan}','IH','${actionBean.laporanTanah.idLaporan}','${line.hakmilik.idHakmilik}','','Y');return false;" height="30" width="30" alt="Muat Naik"
                                                             onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen Gambar Hakmilik"/>
                                                        <br>
                                                    </c:if>
                                                    <c:set value="1" var="count"/>
                                                    <c:forEach items="${actionBean.imejLaporanList}" var="senarai">
                                                        <c:if test="${senarai.hakmilik.idHakmilik eq line.hakmilik.idHakmilik}">
                                                            <%--<c:if test="${senarai.dokumen.namaFizikal ne null}">--%>
                                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                 onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen Gambar Hakmilik"/> <font size="1">${count}-${senarai.dokumen.tajuk}</font>

                                                            <c:if test="${edit}">
                                                                /
                                                                <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                     onclick="removeImej('${senarai.idImej}','${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                                                     onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                                            </c:if>

                                                            <br/>
                                                            <c:set value="${count+1}" var="count"/>
                                                            <%--</c:if>--%>
                                                        </c:if>
                                                    </c:forEach>
                                                </display:column>

                                            </display:table>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>

            </fieldset>
        </div>
        <!-- Info/Label for  Laporan Tanah-->
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Laporan Tanah</legend>
                <p>
                    <label>Tarikh Aduan Diterima :</label>
                    <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp;</c:if>
                    <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk eq null}"> Tiada </c:if>
                </p>
            </fieldset>
        </div>
        <!-- Info/Label for  Perihal Tanah-->
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Perihal Tanah</legend>
                <c:if test="${edit}">
                    <p>
                        <a onclick="addMaklumatTanah('edit','PT','');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                    </p> 
                </c:if>
                <br>

                <c:if test="${actionBean.statusTanah eq 'H'}"> 
                    <p>
                        <label>Keluasan :</label>
                        <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilik.luas ne null}"><fmt:formatNumber  pattern="#,##0.0000" value="${actionBean.hakmilikPermohonanList[0].hakmilik.luas}"/>
                            ${actionBean.hakmilikPermohonanList[0].hakmilik.kodUnitLuas.nama}&nbsp;</c:if>
                        <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilik.luas eq null}"> Tiada </c:if>
                    </p>
                    <p>
                        <label>Status Tanah :</label>
                        <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilik.rizab.nama ne null}"> ${actionBean.hakmilikPermohonanList[0].hakmilik.rizab.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilik.rizab.nama eq null}"> Tiada </c:if>
                    </p>
                    <p>
                        <label>Jenis Tanah :</label>
                        <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.hakmilikPermohonanList[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilik.kodTanah.nama eq null}"> Tiada </c:if>
                    </p>
                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '127' && actionBean.stageId eq 'maklum_milik_sementara'}">
                    <p>
                        <label>Tarikh Arahan PBN :</label>
                        <c:if test="${actionBean.permohonanRujukanLuar.tarikhSidang ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhSidang}" />&nbsp; </c:if>
                        <c:if test="${actionBean.permohonanRujukanLuar.tarikhSidang eq null}"> Tiada </c:if>
                    </p>
                </c:if>
                <p>
                    <label>Nombor Warta Kerajaan : </label>
                    <c:if test="${actionBean.permohonanRujukanLuar.noRujukan ne null}"> ${actionBean.permohonanRujukanLuar.noRujukan}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.noRujukan eq null}"> Tiada </c:if>
                </p>
                <p>
                    <label>Tarikh Lawatan :</label>
                    <c:if test="${actionBean.tarikhLawatan ne null}"> ${actionBean.tarikhLawatan}&nbsp; </c:if>
                    <c:if test="${actionBean.tarikhLawatan eq null}"> Tiada </c:if>

                </p>
                <p>
                    <label>Masa Lawatan :</label>
                    <c:set var="valueAmPm" value="${actionBean.ampm}"/>
                    <c:if test="${valueAmPm eq 'AM'}"> <c:set var="valueAmPm" value="PAGI"/></c:if>
                    <c:if test="${valueAmPm eq 'PM'}"> <c:set var="valueAmPm" value="PETANG"/></c:if>
                    <c:if test="${actionBean.tarikhLawatan ne null}"> ${actionBean.jam}:${actionBean.minit} ${valueAmPm}&nbsp; </c:if>
                    <c:if test="${actionBean.tarikhLawatan eq null}"> Tiada </c:if>

                </p>
                <p>
                    <label>Anggaran Luas Terhakis/Ceroboh/Dilanggar Syarat :</label>
                    <c:if test="${actionBean.laporanTanah.usahaLuas ne null}">${actionBean.laporanTanah.usahaLuas}&nbsp; ${actionBean.laporanTanah.usahaLuasUom.nama}</c:if>
                    <c:if test="${actionBean.laporanTanah.usahaLuas eq null}"> Tiada </c:if>
                </p>

                <br>
                <div class="content" align="center">
                    <%--Bersempadan--%>
                    <c:set var="idAduan" value="${actionBean.permohonan.idPermohonan}" />
                    <c:set var="kodNegeri" value="${fn:substring(idAduan, 0, 2)}" />
                    <c:if test = "${kodNegeri eq '05'}">Bersempadan</c:if>
                    <c:if test = "${kodNegeri eq '04'}">Mercu Tanda</c:if>
                    <table class="tablecloth">
                        <tr>
                            <th>Bersempadan</th><th>Nama</th><th width="120">Jarak</th>
                        </tr>
                        <tr>
                            <td>
                                Jalan Raya
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya ne null}"> ${actionBean.laporanTanah.namaSempadanJalanraya}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya eq null}"> Tiada </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanJalanraya ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanJalanraya}"/> ${actionBean.laporanTanah.jarakSempadanJalanrayaUOM.nama}</c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanJalanraya eq null}"> Tiada </c:if>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                Landasan Keretapi
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.namaSempadanKeretapi ne null}"> ${actionBean.laporanTanah.namaSempadanKeretapi}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanKeretapi eq null}"> Tiada </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanKeretapi ne null}">  <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanKeretapi}"/> ${actionBean.laporanTanah.jarakSempadanKeretapiUOM.nama}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanKeretapi eq null}"> Tiada </c:if>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                Laut
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.namaSempadanLaut ne null}"> ${actionBean.laporanTanah.namaSempadanLaut}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanLaut eq null}"> Tiada </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanLaut ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanLaut}"/> ${actionBean.laporanTanah.jarakSempadanLautUOM.nama}&nbsp;</c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanLaut eq null}"> Tiada </c:if>

                            </td>
                        </tr><tr>
                            <td>
                                Sungai
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.namaSempadanSungai ne null}"> ${actionBean.laporanTanah.namaSempadanSungai}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanSungai eq null}"> Tiada </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanSungai ne null}">   <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanSungai}"/> ${actionBean.laporanTanah.jarakSempadanSungaiUOM.nama}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanSungai eq null}"> Tiada </c:if>

                            </td>
                        </tr>
                    </table>
                </div>
                <p>
                    <label>Jenis Jalan :</label>
                    <c:if test="${actionBean.laporanTanah.jenisJalan ne null}">
                        <c:choose>
                            <c:when test="${actionBean.laporanTanah.jenisJalan eq 'JB'}">
                                Jalan Berturap&nbsp;
                            </c:when>
                            <c:when test="${actionBean.laporanTanah.jenisJalan eq 'JL'}">
                                Jalan Leterite&nbsp;
                            </c:when>
                            <c:when test="${actionBean.laporanTanah.jenisJalan eq 'JTM'}">
                                Jalan Tanah Merah&nbsp;
                            </c:when>
                            <c:when test="${actionBean.laporanTanah.jenisJalan eq 'JT'}">
                                Jalan Tanah&nbsp;
                            </c:when>
                        </c:choose>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.jenisJalan eq null}"> Tiada </c:if>
                </p>
                <p>
                    <label>Jalan Masuk :</label>
                    <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'Y'}">Ada</c:if>
                    <c:if test="${actionBean.laporanTanah.adaJalanMasuk ne 'Y'}">Tiada</c:if>
                </p>
                <p>
                    <label>Catatan :</label>
                </p>
                <%--<c:if test="${actionBean.laporanTanah.catatanJalanMasuk ne null}"> ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp; </c:if>--%>
                <c:if test="${actionBean.laporanTanah.catatanJalanMasuk ne null}">
                    <%--<s:textarea name="laporanTanah.catatanJalanMasuk" id="catatanJalanMasuk" rows="7" cols="80" readonly="true" onchange="this.value=this.value.toUpperCase();" disabled="false" />&nbsp;--%>
                    <table>
                        <tr>
                            <td valign="top">
                                <font size="2px;">${actionBean.laporanTanah.catatanJalanMasuk}&nbsp;</font></td>
                        </tr>
                    </table>
                </c:if>
                <p>
                    <c:if test="${actionBean.laporanTanah.catatanJalanMasuk eq null}"> Tiada </c:if>
                </p>

                <legend>Permohonan Manual</legend>
                <p>
                    <label>No.Fail :</label>
                    <c:if test="${actionBean.permohonanManual.noFail ne null}"> ${actionBean.permohonanManual.noFail}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanManual.noFail eq null}"> Tiada </c:if>
                </p>
            </fieldset>
        </div>

        <!-- Info/Label for  Keadaan Tanah-->
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Keadaan Tanah</legend>
                <c:if test="${edit}">
                    <p>
                        <a onclick="addMaklumatTanah('edit','KT','');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                    </p>
                </c:if>
                <p>
                    <label>Keadaan Tanah :</label>
                    <c:if test="${actionBean.laporanTanah.kecerunanTanah.nama ne null}"> ${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.kecerunanTanah.nama eq null}"> Tiada </c:if>
                </p>
                <p id="tinggi">
                    <label>Ketinggian Dari Paras Jalan (m) :</label>
                    <c:if test="${actionBean.laporanTanah.ketinggianDariJalan ne null}"> ${actionBean.laporanTanah.ketinggianDariJalan}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.ketinggianDariJalan eq null}"> Tiada </c:if>
                </p>
                <p id="cerun">
                    <label>Darjah Kecerunan :</label>
                    <c:if test="${actionBean.laporanTanah.kecerunanBukit ne null}"> ${actionBean.laporanTanah.kecerunanBukit}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.kecerunanBukit eq null}"> Tiada </c:if>
                </p>
                <p id="dalam">
                    <label>Dalam Paras Air (m) :</label>
                    <c:if test="${actionBean.laporanTanah.parasAir ne null}"> ${actionBean.laporanTanah.parasAir}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.parasAir eq null}"> Tiada </c:if>
                </p>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    <c:if test="${actionBean.laporanTanah.strukturTanah.nama ne null}"> ${actionBean.laporanTanah.strukturTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.strukturTanah.nama eq null}"> Tiada </c:if>
                </p>
                <p>
                    <label>Keadaan Atas Tanah :</label>
                </p>
                <%--<c:if test="${actionBean.laporanTanah.keadaanTanah ne null}"> ${actionBean.laporanTanah.keadaanTanah}&nbsp; </c:if>--%>
                <c:if test="${actionBean.laporanTanah.keadaanTanah ne null}">
                    <table>
                        <tr>
                            <td valign="top">
                                <font size="2px;">${actionBean.laporanTanah.keadaanTanah}&nbsp;</font></td>
                        </tr>
                    </table>
                    <%--<s:textarea name="laporanTanah.keadaanTanah" id="keadaanTanah" rows="7" cols="80" readonly="true" onchange="this.value=this.value.toUpperCase();" disabled="false" />&nbsp;--%>
                </c:if><p>
                    <c:if test="${actionBean.laporanTanah.keadaanTanah eq null}"> Tiada </c:if>
                </p>
                <br>

                <div class="content" align="center">
                    Dilintasi Oleh
                    <table class="tablecloth" align="center">
                        <tr>
                            <th>Talian Elektrik</th><th>Talian Telefon</th><th>Laluan Gas</th><th>Paip Air</th><th>Tali Air</th><th>Sungai</th><th>Parit</th>
                        </tr>
                        <tr>
                            <td>
                                <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}">Ya</c:if>
                                <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq null}">Tiada</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">Ya</c:if>
                                <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq null}">Tiada</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">Ya</c:if>
                                <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq null}">Tiada</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">Ya</c:if>
                                <c:if test="${actionBean.laporanTanah.dilintasPaip eq null}">Tiada</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">Ya</c:if>
                                <c:if test="${actionBean.laporanTanah.dilintasTaliar eq null}">Tiada</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">Ya</c:if>
                                <c:if test="${actionBean.laporanTanah.dilintasSungai eq null}">Tiada</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">Ya</c:if>
                                <c:if test="${actionBean.laporanTanah.dilintasParit eq null}">Tiada</c:if>
                            </td>
                        </tr>
                    </table>

                </div>





            </fieldset>
        </div>

        <!-- Info/Label for  Latar belakang Tanah-->
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Latar belakang Tanah</legend>
                <c:if test="${edit}">
                    <p>
                        <a onclick="addMaklumatTanah('edit','LBT','');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                    </p>  
                </c:if>
                <p>
                    <label>Diusahakan :</label>
                    <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">Ya</c:if>
                    <c:if test="${actionBean.laporanTanah.usaha ne 'Y'}">Tidak</c:if>
                </p>
                <p>
                    <label>Oleh :</label>
                    <c:if test="${actionBean.laporanTanah.diusaha ne null}"> ${actionBean.laporanTanah.diusaha}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.diusaha eq null}"> Tiada </c:if>
                </p>
                <p>
                    <label>Tarikh Mula Usaha :</label>
                    <c:if test="${actionBean.tarikhDiusahakan ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.tarikhDiusahakan}" />&nbsp;</c:if>
                    <c:if test="${actionBean.tarikhDiusahakan eq null}"> Tiada </c:if>
                </p>
                <p>
                    <label>Jenis Tanaman :</label>
                    <c:if test="${actionBean.laporanTanah.usahaTanam ne null}"> ${actionBean.laporanTanah.usahaTanam}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.usahaTanam eq null}"> Tiada </c:if>
                </p>
                <p>
                    <label>Bangunan :</label>
                    <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">Ada</c:if>
                    <c:if test="${actionBean.laporanTanah.adaBangunan ne 'Y'}">Tiada</c:if>

                </p>
                <p>
                    <label>Tahun Dibina :</label>
                    <c:if test="${actionBean.laporanTanah.bangunanTahunDibina ne null}"> ${actionBean.laporanTanah.bangunanTahunDibina}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.bangunanTahunDibina eq null}"> Tiada </c:if>
                </p>
                <p>
                    <label>Diduduki :</label>
                    <c:if test="${actionbean.laporanTanah.bangunanDidiami eq 'Y'}">Ya</c:if>
                    <c:if test="${actionbean.laporanTanah.bangunanDidiami ne 'Y'}">Tidak</c:if>

                </p>
                <p>
                    <label>Rancangan Kerajaan :</label>

                    <c:if test="${actionBean.laporanTanah.rancanganKerajaan ne null}"> ${actionBean.laporanTanah.rancanganKerajaan}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.rancanganKerajaan eq null}"> Tiada </c:if>

                </p>
            </fieldset>
        </div>

        <!-- Info/Label for  Jenis Bangunan-->
        <div class="subtitle" align="center">
            <fieldset class="aras1">
                <legend>Jenis Bangunan </legend>
                <c:if test="${edit}">
                    <p align="left">
                        <a onclick="addMaklumatTanah('edit','JB','');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                    </p>
                </c:if>
                <div id="senaraiBangunan" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiPermohonanLaporanBangunan}" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Jenis Bangunan" >
                            <c:if test="${line.jenisBangunan eq 'KK'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Kekal</a></u></c:if>
                            <c:if test="${line.jenisBangunan eq 'SK'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Separuh Kekal</a></u></c:if>
                            <c:if test="${line.jenisBangunan eq 'SM'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Sementara</a></u></c:if>
                            <c:if test="${line.jenisBangunan eq 'LL'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Lain-lain</a></u></c:if>
                        </display:column>
                        <display:column title="Ukuran">
                            ${line.ukuran}x${line.keteranganTahunBinaan}
                        </display:column>
                        <display:column property="uomUkuran.nama" title="Unit Ukuran"></display:column>
                        <display:column title="Nilai">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/>
                        </display:column>
                        <c:if test="${edit}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeBangunan('${line.idLaporBangunan}');"/>
                                </div>
                            </display:column>

                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="addMaklumatTanah('edit','JB','${line.idLaporBangunan}');"/>
                                </div>
                            </display:column>
                        </c:if>

                    </display:table>
                </div>
                &nbsp;
            </fieldset>
        </div>

        <!-- Info/Label for  Tanah Sekeliling-->
        <div class="subtitle" align="center">
            <fieldset class="aras1">
                <legend>Tanah Sekeliling</legend>
                <c:if test="${edit}">
                    <p align="left">
                        <a onclick="addMaklumatTanah('edit','TS','');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                    </p>
                </c:if>

                &nbsp;
                <br>
                <div id="senaraiSempadan">
                    <display:table class="tablecloth" name="${actionBean.senaraiLaporanTanahSempadan}" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column  title="Jenis Sempadan">
                            <c:if test="${line.jenisSempadan eq 'U'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Utara</a></u></c:if>
                            <c:if test="${line.jenisSempadan eq 'S'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Selatan</a></u></c:if>
                            <c:if test="${line.jenisSempadan eq 'T'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Timur</a></u></c:if>
                            <c:if test="${line.jenisSempadan eq 'B'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Barat</a></u></c:if>
                        </display:column>
                        <display:column property="kodKategoriTanah.nama" title="Jenis Tanah"></display:column>
                        <display:column property="hakmilik.idHakmilik" title="Hakmilik"></display:column>
                        <display:column property="noLot" title="No Lot"></display:column>
                        <display:column property="kodLot.nama" title="Kod Lot"></display:column>
                        <display:column property="catatan" title="Catatan"></display:column>
                        <display:column title="Imej Di Atas Tanah ">
                            <c:if test="${edit}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                     onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}','','${actionBean.permohonan.idPermohonan}','IH${line.jenisSempadan}','${actionBean.laporanTanah.idLaporan}','',${line.idLaporTanahSpdn},'Y');return false;" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen Gambar Hakmilik"/>
                                <br>
                                <c:set value="1" var="count"/>
                                <c:if test="${line.jenisSempadan eq 'U'}"><c:set value="${actionBean.utaraImejLaporanList}" var="imejSempadanList"/></c:if>
                                <c:if test="${line.jenisSempadan eq 'S'}"><c:set value="${actionBean.selatanImejLaporanList}" var="imejSempadanList"/></c:if>
                                <c:if test="${line.jenisSempadan eq 'T'}"><c:set value="${actionBean.timurImejLaporanList}" var="imejSempadanList"/></c:if>
                                <c:if test="${line.jenisSempadan eq 'B'}"><c:set value="${actionBean.baratImejLaporanList}" var="imejSempadanList"/></c:if>
                                <c:forEach items="${imejSempadanList}" var="senarai">
                                    <c:if test="${line.idLaporTanahSpdn eq senarai.dokumen.perihal}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.tajuk}"/> <font size="1">${count}-${senarai.dokumen.tajuk}</font>
                                        /
                                        <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                             onclick="removeImej('${senarai.idImej}','${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                             onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                        <br/>
                                        <c:set value="${count+1}" var="count"/>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${!edit}">
                                <%-- <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                      onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}','',
                                          '${actionBean.permohonan.idPermohonan}','IH${line.jenisSempadan}','${actionBean.laporanTanah.idLaporan}','',${line.idLaporTanahSpdn});return false;" height="30" width="30" alt="Muat Naik"
                                      onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen Gambar Hakmilik"/>--%>
                                <br>
                                <c:set value="1" var="count"/>
                                <c:if test="${line.jenisSempadan eq 'U'}"><c:set value="${actionBean.utaraImejLaporanList}" var="imejSempadanList"/></c:if>
                                <c:if test="${line.jenisSempadan eq 'S'}"><c:set value="${actionBean.selatanImejLaporanList}" var="imejSempadanList"/></c:if>
                                <c:if test="${line.jenisSempadan eq 'T'}"><c:set value="${actionBean.timurImejLaporanList}" var="imejSempadanList"/></c:if>
                                <c:if test="${line.jenisSempadan eq 'B'}"><c:set value="${actionBean.baratImejLaporanList}" var="imejSempadanList"/></c:if>
                                <c:forEach items="${imejSempadanList}" var="senarai">
                                    <c:if test="${line.idLaporTanahSpdn eq senarai.dokumen.perihal}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.tajuk}"/> <font size="1">${count}-${senarai.dokumen.tajuk}</font>
                                        <%--/
                                        <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                             onclick="removeImejTanahSekelilingList('${senarai.idImej}','${senarai.dokumen.idDokumen}','IH${line.jenisSempadan}');" height="15" width="15" alt="Hapus"
                                             onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>--%>
                                        <br/>
                                        <c:set value="${count+1}" var="count"/>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </display:column>
                        <c:if test="${edit}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSempadan('${line.idLaporTanahSpdn}');"/>
                                </div>
                            </display:column>

                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="addMaklumatTanah('edit','TS','${line.idLaporTanahSpdn}');"/>
                                </div>
                            </display:column>
                        </c:if>


                    </display:table>
                </div>

            </fieldset>
        </div>

        <div class="subtitle">

            <fieldset class="aras1">
                <c:if test="${edit}">
                    <legend>Ulasan</legend>
                    <c:if test="${edit}">
                        <p>
                            <a onclick="addMaklumatTanah('edit','UPPT','');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </p>  
                    </c:if>

                    <p>
                        <label><font style="text-transform: capitalize">
                                <c:if test="${edit}">
                                    Syor ${actionBean.pengguna.jawatan} : 
                                </c:if>
                                <c:if test="${view}">
                                    Syor ${actionBean.mohonLaporUlasPPT.kodPeranan.nama} :
                                </c:if>
                            </font></label>
                            <c:if test="${actionBean.mohonLaporUlasPPT.ulasan eq null}">
                            TIADA SYOR 
                        </c:if>
                        <c:if test="${actionBean.mohonLaporUlasPPT.ulasan ne null}">
                            <font style="text-transform: capitalize">${actionBean.mohonLaporUlasPPT.ulasan}</font>
                        </c:if>
                        <%--<s:textarea name="mohonLaporUlasPPT.ulasan" id="ulasanPPT" cols="70" rows="10" class="normal_text" readonly="true"/>--%>
                    </p>
                </c:if>

                <c:if test="${!edit && !syorPPTK}">
                    <c:if test="${actionBean.mohonLaporUlasPPT ne null || actionBean.mohonLaporUlasPPTK ne null}"><legend>Ulasan</legend></c:if>
                    <table>
                        <c:if test="${actionBean.mohonLaporUlasPPT ne null}">
                            <tr>
                                <td><p><label>Syor ${actionBean.mohonLaporUlasPPT.kodPeranan.nama} :</label></c:if></p>
                                    <table>
                                        <td><p align="justify" style="text-transform: uppercase"><c:if test="${actionBean.ulasanPPT ne null}"> ${actionBean.ulasanPPT}&nbsp;
                                                <c:if test="${actionBean.ulasanPPT eq null}"> Tiada </c:if></p></td>
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
                                                <c:if test="${actionBean.ulasanPPTK eq null}"> Tiada </c:if></p></td>
                                    </table>


                                </td>
                            </tr>
                        </c:if>
                    </table>


                </c:if>

                <c:if test="${syorPPTK}">
                    <legend>Ulasan</legend>
                    <p>
                        <a onclick="addMaklumatTanah('edit','UPPTK','');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                    </p>  
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
                        <c:if test="${actionBean.ulasanPPT eq null}"> Tiada </c:if>

                    </c:if>
                    <p>
                        <label><font style="text-transform: capitalize">Syor ${actionBean.pengguna.jawatan} : </font></label>
                        <c:if test="${actionBean.ulasanPPTK eq null}">
                            TIADA SYOR 
                        </c:if>
                        <c:if test="${actionBean.ulasanPPTK ne null}">
                            <font style="text-transform: capitalize">${actionBean.ulasanPPTK}</font>
                        </c:if>
                    </p>
                </c:if>

            </fieldset>
        </div>
    </div>


</s:form>
<script type="text/javascript">
    function refreshWholePage(val){
        //        alertMsg();
        //        setTimeout(alertMsg,100000); 
        //                        alert(val);

        setTimeout(function () {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/mohon_lapor_tanah?reload&idLaporanTanah=' + val;
            $.get(url,
            function(data){
                //                        alert(data);
                //            setTimeout(alertMsg,5000); 
                $("#maklumatLaporanTanahDiv").replaceWith($('#maklumatLaporanTanahDiv', $(data)));
                $("#infoLaporanTanahDiv").replaceWith($('#infoLaporanTanahDiv', $(data)));
                //                $('#page_div').html(data);
            
                var status = $('#statusTanah').val();
                changeStatusTanah(status);
            },'html');
            //            alert("This code will run last, after a 5 second delay")
        }, 10000);
  
    }
    
    function alertMsg() {
        window.reload();
        return true;
    }
    
    function refresh(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/mohon_lapor_tanah?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
</script>


