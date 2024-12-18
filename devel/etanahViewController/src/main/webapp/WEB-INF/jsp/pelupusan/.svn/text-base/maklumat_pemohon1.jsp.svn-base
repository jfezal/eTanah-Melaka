<%-- 
    Document   : maklumat_pemohon1
    Created on : Feb 11, 2010, 12:15:37 PM
    Author     : muhammad.amin
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

<script type="text/javascript">

    $(document).ready(function() {
        var len = $(".bpm").length;
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }

    });


    $('#content1').hide();

    $('a').click(function(){

        $('#content1').show('slow');

    });

    function refreshMaklumatPemohon(){
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?refreshMaklumatPemohon';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function addPemohon(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showTambahPemohon", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }
    function addPemohonBatuan(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showTambahPemohonBatuan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }
    function addPemohonTanahAdat(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showTambahPemohonTanahAdat", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }
    function addPemohonTanah(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showTambahPemohonTanah", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }

    function addPemohonSuami_Isteri(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showTambahLatarbelakangPemohon", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }

    function addIbuBapaPemohon(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showMaklumatIbuBapa", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }
    
    function addSaudaraPemohon(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showMaklumatSaudara", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }

    function addPemohonAnak(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showMaklumatAnak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }
    function addPemohonLembaga_Pengarah(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showMaklumatAhliLembaga", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }
    function removePemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?deletePemohon&idPemohon='+val;
            $.get(url,function(data){$('#page_div').html(data);},'html');
        }
    }
    function removePemohonTanah(val,val2){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?deletePemohonTanah&idTanah='+val+'&idPemohon='+val2;
            $.get(url,function(data){$('#page_div').html(data);},'html');
        }
    }
    
    function removePemohonHbgn(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?deletePemohonHbgn&idPemohonHbgn='+val;
            $.get(url,function(data){$('#page_div').html(data);},'html');
        }
    }

    function removePengarah(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?deletePihakPengarah&idPengarah='+val;
            $.get(url,function(data){$('#page_div').html(data);},'html');
        }
    }

    function kemaskini(i){
        var d = $('.x'+i).val();
        
        var idMohon = document.getElementById("idPermohonan").value;
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showEditPemohon&idPihak="+d+"&idPermohonan="+idMohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600, scrollbars=yes");
    }
    function kemaskiniBatuan(i){
        var d = $('.x'+i).val();
        
        var idMohon = document.getElementById("idPermohonan").value;
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showEditPemohonBatuan&idPihak="+d+"&idPermohonan="+idMohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600, scrollbars=yes");
    }
    function kemaskiniTanahAdat(i){
        var d = $('.x'+i).val();
        
        var idMohon = document.getElementById("idPermohonan").value;
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showEditPemohonTanahAdat&idPihak="+d+"&idPermohonan="+idMohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600, scrollbars=yes");
    }
    function kemaskini1(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showEditSuamiIsteri&idHubungan="+i, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function kemaskiniAnak(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showEditAnak&idHubungan="+i, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function kemaskinilembaga(i){
    <%-- var d = $('.x'+i).val();--%>
            window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showEditlembaga&idPengarah="+i, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }

        function kemaskiniIbuBapa(i){
            var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showEditIbuBapa&idHubungan="+i, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }
        
        function kemaskiniSaudara(i){
            var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showEditSaudara&idHubungan="+i, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohon1ActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Pemohon</legend>
            <s:hidden name="idPermohonan" id="idPermohonan"/>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1">

                    <display:column title="Bil">
                        ${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Alamat" >${line.pihak.suratAlamat1}
                        <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat2}
                        <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat3}
                        <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat4}</display:column>
                    <display:column property="pihak.suratPoskod" title="Poskod" />
                    <display:column property="pihak.suratNegeri.nama" title="Negeri" />
                    <c:if test="${edit eq true}">
                        <display:column title="Kemaskini">
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'PBPTD' || actionBean.p.kodUrusan.kod eq 'PBPTG' || actionBean.p.kodUrusan.kod eq 'PPBB'}">
                                <a href="#" onclick="kemaskiniBatuan('${line_rowNum -1},${idPermohonan}');return false;">Kemaskini</a>

                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'PBPTD' && actionBean.p.kodUrusan.kod ne 'PBPTG' && actionBean.p.kodUrusan.kod ne 'PPBB' && actionBean.p.kodUrusan.kod ne 'PTMTA'}">
                                <div align="center">
                                    <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png' onclick="kemaskiniTanahAdat('${line_rowNum -1},${idPermohonan}')"/>
                                </div>
                                <%--<a href="#" onclick="kemaskini('${line_rowNum -1},${idPermohonan}');return false;">Kemaskini</a>--%>
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'PTMTA'}">
                                <div align="center">
                                    <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png' onclick="kemaskiniTanahAdat('${line_rowNum -1},${idPermohonan}')"/>
                                </div>
                                <%--<a href="#" onclick="kemaskiniTanahAdat('${line_rowNum -1},${idPermohonan}');return false;">Kemaskini</a>--%>
                            </c:if>    

                        </display:column>    
                        <c:if test="${actionBean.p.kodUrusan.kod ne 'BMBT'}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')">
                                </div>
                            </display:column>
                        </c:if>
                    </c:if>
                </display:table>
                <%--<c:if test="${edit eq true}">--%>
                <tr><td>
                        <c:if test="${line.pihak eq null}">
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'PBPTD' || actionBean.p.kodUrusan.kod eq 'PBPTG' || actionBean.p.kodUrusan.kod eq 'PPBB'}">
                                <s:button class="btn" value="Tambah" name="newBatuan" id="newBatuan" onclick="addPemohonBatuan();"/>

                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'PBPTD' && actionBean.p.kodUrusan.kod ne 'PBPTG' && actionBean.p.kodUrusan.kod ne 'PPBB' && actionBean.p.kodUrusan.kod ne 'PTMTA'}">
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addPemohon();"/>
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'PTMTA'}">
                                <s:button class="btn" value="Tambah" name="newBatuan" id="newBatuan" onclick="addPemohonTanahAdat();"/>

                            </c:if>
                            &nbsp;
                        </c:if>
                    </td></tr>
            </div>
            <%--</c:if>--%>

            <br>
            <p align="center"><font color="blue"><b>Tanah Yang Dimiliki Pemohon</b></font></p>
            <br>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikList}"  cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1" id="line2">
                    <display:column title="No" sortable="true">${line2_rowNum}</display:column>
                    <display:column property="idHakmilik" title="ID Hakmilik" class="popup hakmilik${line2_rowNum}"/>
                    <%--<display:column property="noLot" title="No Lot" />--%>
                    <display:column title="No Lot"><fmt:formatNumber pattern="#" value="${line2.noLot}"/> </display:column>
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line2.luas}"/>&nbsp;${line2.kodUnitLuas.nama}</display:column>
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" class="bpm"/>
                </display:table>
            </div>
            <%--TANAH LUAR NEGERI--%>
            <c:if test="${actionBean.p.kodUrusan.kod ne 'PBPTD' && actionBean.p.kodUrusan.kod ne 'PBPTG' && actionBean.p.kodUrusan.kod ne 'PPBB'}">
                <br>
                <p align="center"><font color="blue"><b>Tanah Yang Dimiliki Pemohon di Luar Negeri</b></font></p>
                <br>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.pemohonLuarNegeri1}" cellpadding="0" cellspacing="0" id="line99"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1">

                        <display:column title="No.">${line_rowNum}
                        </display:column>
                        <%--<display:column property="noLot" title="No Lot"/>--%>
                        <display:column title="No Lot"> <fmt:formatNumber pattern="#" value="${line99.noLot}"/> </display:column>
                        <display:column property="bandarPekanMukim" title="Mukim" />
                        <display:column title="Daerah" >${line99.daerah}</display:column>
                        <display:column property="negeri.nama" title="Negeri" />
                        <display:column property="jenisGeran" title="Jenis Geran" />
                        <display:column property="luas" title="Luas" />
                        <display:column property="pengusahaan" title="Usaha" />
                        <c:if test="${edit eq true}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePemohonTanah('${line99.idTanah}','${line99.pemohon.idPemohon}')">
                                </div>
                            </display:column>
                        </c:if>
                    </display:table>
                    <c:if test="${edit eq true}">
                        <tr>
                            <td>
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addPemohonTanah();"/>
                            </td>
                        </tr>
                    </c:if>
                </div>
                <%--END OF LUAR NEGERI--%>
                <br/>
            </c:if>

            <%--Code Changed by rohan--%>

            <c:if test="${line.pihak ne null}">

                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'|| actionBean.pihak.jenisPengenalan.kod eq 'L'|| actionBean.pihak.jenisPengenalan.kod eq 'P'||actionBean.pihak.jenisPengenalan.kod eq 'T'||actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'N'||actionBean.pihak.jenisPengenalan.kod eq 'F'}">
                    <c:if test="${actionBean.pemohon.statusKahwin eq 'Berkahwin' }">
                        <legend>Butir-butir Suami/Isteri</legend>

                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.pemohonHubunganList}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1">

                                <display:column title="Bil">${line_rowNum}
                                    <s:hidden name="" class="${line_rowNum -1}" value="${line.idHubungan}"/>
                                </display:column>
                                <display:column property="nama" title="Nama"/>
                                <display:column property="noPengenalan" title="Nombor Pengenalan" />

                                <display:column title="Alamat" >${line.alamat1}
                                    <c:if test="${line.alamat2 ne null}"> , </c:if>
                                    ${line.alamat2}
                                    <c:if test="${line.alamat3 ne null}"> , </c:if>
                                    ${line.alamat3}
                                    <c:if test="${line.alamat4 ne null}"> , </c:if>
                                    ${line.alamat4}</display:column>
                                <display:column property="poskod" title="Poskod" />
                                <display:column property="negeri.nama" title="Negeri" />
                                <c:if test="${edit eq true}">
                                    <display:column title="Kemaskini"><a href="#" onclick="kemaskini1('${line.idHubungan}');return false;">Kemaskini</a></display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removePemohonHbgn('${line.idHubungan}')">
                                        </div>

                                    </display:column>
                                </c:if>
                            </display:table>
                            <c:if test="${edit eq true}">
                                <%-- value : ${actionBean.pemohon.pihak.kodJantina}--%>
                                <c:if test="${actionBean.pemohon.pihak.kodJantina eq '1'}">
                                    <c:if test="${fn:length(actionBean.pemohonHubunganList) < 4}">
                                        <tr><td>
                                                <s:button class="btn" value="Tambah" name="new1" id="new1"  size="60" onclick="addPemohonSuami_Isteri();"/>&nbsp;
                                            </td></tr>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${actionBean.pemohon.pihak.kodJantina eq '2'}">
                                        <c:if test="${fn:length(actionBean.pemohonHubunganList) < 1}">
                                        <tr><td>
                                                <s:button class="btn" value="Tambah" name="new1" id="new1"  size="60" onclick="addPemohonSuami_Isteri();"/>&nbsp;
                                            </td></tr>
                                        </c:if>
                                    </c:if>
                                </c:if>
                        </div>

                        <br>
                        <p align="center">
                            <font color="blue"><b>Tanah Yang Dimiliki Suami/Isteri</b></font>
                        </p>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.hakmilikList}"  cellpadding="0" cellspacing="0"
                                           requestURI="/common/maklumat_hakmilik_permohonan" id="line2">
                                <display:column title="No" sortable="true">${line2_rowNum}</display:column>
                                <display:column property="idHakmilik" title="ID Hakmilik" class="popup hakmilik${line2_rowNum}"/>
                                <%--<display:column property="noLot" title="No Lot" />--%>
                                <display:column title="No Lot"><fmt:formatNumber pattern="#" value="${line2.noLot}"/> </display:column>
                                <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line2.luas}"/>&nbsp;${line2.kodUnitLuas.nama}</display:column>
                                <display:column property="daerah.nama" title="Daerah" class="daerah"/>
                                <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"/>
                            </display:table>
                        </div>
                        <br/>
                        <%--Added by Shazwan 13 June 2011--%>

                    </c:if>
                    <c:if test="${actionBean.pemohon.statusKahwin eq 'Janda' || actionBean.pemohon.statusKahwin eq 'Duda' || actionBean.pemohon.statusKahwin eq 'Berkahwin' || actionBean.pemohon.statusKahwin eq 'Bujang'}">

                        <legend>Butir-butir Ibu/Bapa</legend>

                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.pemohonHubunganList2}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1">

                                <display:column title="Bil">${line_rowNum}
                                    <s:hidden name="" class="${line_rowNum -1}" value="${line.idHubungan}"/>
                                </display:column>
                                <display:column property="nama" title="Nama"/>
                                <display:column property="noPengenalan" title="Nombor Pengenalan" />

                                <display:column title="Alamat" >${line.alamat1}
                                    <c:if test="${line.alamat2 ne null}"> , </c:if>
                                    ${line.alamat2}
                                    <c:if test="${line.alamat3 ne null}"> , </c:if>
                                    ${line.alamat3}
                                    <c:if test="${line.alamat4 ne null}"> , </c:if>
                                    ${line.alamat4}</display:column>
                                <display:column property="poskod" title="Poskod" />
                                <display:column property="negeri.nama" title="Negeri" />
                                <c:if test="${edit eq true}">
                                    <display:column title="Kemaskini"><a href="#" onclick="kemaskiniIbuBapa('${line.idHubungan}');return false;">Kemaskini</a></display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removePemohonHbgn('${line.idHubungan}')">
                                        </div>

                                    </display:column>
                                </c:if>
                            </display:table>
                            <c:if test="${edit eq true}">
                                <c:if test="${fn:length(actionBean.pemohonHubunganList2) < 2}">
                                    <tr><td>
                                            <s:button class="btn" value="Tambah" name="new2" id="new2"  size="60" onclick="addIbuBapaPemohon();"/>&nbsp;
                                        </td></tr>
                                    </c:if>
                            </div>
                        </c:if>
                    </c:if>
                    <br/>
                    <c:if test="${actionBean.pemohon.statusKahwin eq 'Janda' || actionBean.pemohon.statusKahwin eq 'Duda' || actionBean.pemohon.statusKahwin eq 'Berkahwin'}">
                        <legend>Butir-butir Anak</legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.pemohonHubunganList1}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1">

                                <display:column title="Bil">${line_rowNum}
                                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idHubungan}"/>
                                </display:column>
                                <display:column property="nama" title="Nama Anak Pemohon"/>
                                <display:column property="noPengenalan" title="Nombor Pengenalan"/>
                                <display:column property="umur" title="Umur" />
                                <display:column title="Nama Sekolah" property="institusi"/>
                                <c:if test="${edit eq true}">
                                    <display:column title="Kemaskini"><a href="#" onclick="kemaskiniAnak('${line.idHubungan}');return false;">Kemaskini</a></display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removePemohonHbgn('${line.idHubungan}')">
                                        </div>
                                    </display:column>
                                </c:if>
                            </display:table>
                            <c:if test="${edit eq true}">
                                <tr><td>
                                        <s:button class="btn" value="Tambah" name="new2" id="new2" onclick="addPemohonAnak();"/>&nbsp;
                                    </td></tr>
                                </c:if>
                        </div>
                    </c:if>


                </c:if>

                <br/><br/>
                <%--     ${actionBean.pihak.jenisPengenalan.kod}....${actionBean.pihak}--%>
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                    <legend>Butir-butir Ahli Lembaga Pengarah</legend>
                    <div class="content" align="center">

                        <display:table class="tablecloth" name="${actionBean.pihakPengarahList}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1">

                            <display:column title="Bil" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPengarah}"/>
                            </display:column>
                            <display:column property="nama" title="Nama Ahli"/>
                            <display:column property="noPengenalan" title="Nombor Pengenalan" />
                            <display:column title="Alamat" >${line.alamat1}
                                <c:if test="${line.alamat2 ne null}"> , </c:if>
                                ${line.alamat2}
                                <c:if test="${line.alamat3 ne null}"> , </c:if>
                                ${line.alamat3}
                                <c:if test="${line.alamat4 ne null}"> , </c:if>
                                ${line.alamat4}</display:column>
                            <display:column property="poskod" title="Poskod" />
                            <display:column property="kodNegeri.nama" title="Negeri" />
                            <%--<display:column property="pihak.jumSaham" title="Jumlah Saham"/>--%>
                            <c:if test="${edit eq true}">
                                <display:column title="Kemaskini"><a href="#" onclick="kemaskinilembaga('${line.idPengarah}');return false;">Kemaskini</a></display:column>
                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onclick="removePengarah('${line.idPengarah}')">
                                    </div>
                                </display:column>
                            </c:if>
                        </display:table>
                        <c:if test="${edit eq true}">
                            <tr><td>
                                    <s:button class="btn" value="Tambah" name="new3" id="new3" onclick="addPemohonLembaga_Pengarah();"/>&nbsp;
                                </td></tr>
                            </c:if>
                    </div>
                </c:if>
                <c:if test="${actionBean.p.kodUrusan.kod eq 'PTMTA'}">
                    <legend>Butir-butir Saudara</legend>

                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.pemohonHubunganSaudara}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1">

                            <display:column title="Bil">${line_rowNum}
                                <s:hidden name="" class="${line_rowNum -1}" value="${line.idHubungan}"/>
                            </display:column>
                            <display:column property="nama" title="Nama"/>
                            <display:column property="noPengenalan" title="Nombor Pengenalan" />

                            <display:column title="Alamat" >${line.alamat1}
                                <c:if test="${line.alamat2 ne null}"> , </c:if>
                                ${line.alamat2}
                                <c:if test="${line.alamat3 ne null}"> , </c:if>
                                ${line.alamat3}
                                <c:if test="${line.alamat4 ne null}"> , </c:if>
                                ${line.alamat4}</display:column>
                            <display:column property="poskod" title="Poskod" />
                            <display:column property="negeri.nama" title="Negeri" />
                            <c:if test="${edit eq true}">
                                <display:column title="Kemaskini"><a href="#" onclick="kemaskiniSaudara('${line.idHubungan}');return false;">Kemaskini</a></display:column>
                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onclick="removePemohonHbgn('${line.idHubungan}')">
                                    </div>

                                </display:column>
                            </c:if>
                        </display:table>
                        <c:if test="${edit eq true}">

                            <tr><td>
                                    <s:button class="btn" value="Tambah" name="new2" id="new2"  size="60" onclick="addSaudaraPemohon();"/>&nbsp;
                                </td></tr>
                            </c:if>

                    </div>
                </c:if>
            </c:if>
        </fieldset>
    </div>
</s:form>


