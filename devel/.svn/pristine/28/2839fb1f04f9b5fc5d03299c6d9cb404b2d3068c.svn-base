<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
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
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function processKelompok(f) {
        var q = $(f).formSerialize();
        var len = $('.nama').length;
        var param = '';
        var DELIM = ',';


        for (var i = 1; i <= len; i++) {
            var ckd = $('#rm_mp_' + i).is(":checked");
            if (ckd) {
                param = param + DELIM + $('#rm_mp_' + i).val();
            }
        }

        if (param == '') {
            alert('Tiada Pihak yang Dipilih.');
            return;
        }
        var frm = document.form1;
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?simpanPihakKongsi&idGroup=' + param;
        frm.action = url;
        frm.submit();
    }

    function removeKumpulan(idPemohon) {
        if (confirm('Adakah anda pasti?')) {
            var frm = document.form1;
            var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?deleteKumpulan&idPihakKongsi=' + idPemohon;
            frm.action = url;
            frm.submit();

        }
    }
    function deleteKumpulanBaru(idHakmilikPihakBerkepentingan) {
        if (confirm('Adakah anda pasti?')) {
            var frm = document.form1;
            var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?deleteKumpulanBaru&idPihakKongsi=' + idHakmilikPihakBerkepentingan;
            frm.action = url;
            frm.submit();

        }
    }

    function viewPihakTerlibat(idPihakKongsi) {
        window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?viewPihakKumpulan&idPihakKongsi=" + idPihakKongsi, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
    }


</script>    

<s:form beanclass="etanah.view.daftar.PihakKepentinganAction" name="form1">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Pihak Yang Memiliki Syer Bersama Tanpa Pihak Kongsi
            </legend>
            <br>
            <div align="center">
                <c:if test = "${actionBean.permohonan.kodUrusan.kod ne 'BETPB'}">
                    <display:table class="tablecloth" name="${actionBean.senaraiPermohonanPihakKongsi}"
                                   cellpadding="0" cellspacing="0" id="line">
                        <display:column title="">
                            <div align="center">
                                <s:checkbox name="checkbox" id="rm_mp_${line_rowNum}" value="${line.idPermohonanPihak}" class="rm_mp_"/>
                            </div>
                        </display:column>
                        <display:column title="Bil"><div align="right">${line_rowNum}</div></display:column>
                        <display:column title="Nama" class="nama">
                            ${line.nama}
                        </display:column>
                        <display:column title="No Pengenalan">
                            ${line.noPengenalan}
                        </display:column>
                        <display:column title="Id Hakmilik">
                            ${line.hakmilik.idHakmilik}
                        </display:column>                        
                        <display:column title="Syer" style="width:5%;">
                            <div align="center">
                                <c:if test = "${line.jumlahPembilang ne null}" >
                                    ${line.jumlahPembilang}/${line.jumlahPenyebut}
                                </c:if>
                            </div>
                        </display:column>
                    </display:table> 


                </c:if>
                <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'BETPB'}">
                    <display:table class="tablecloth" name="${actionBean.senaraiPihakBerkepentingan}"
                                   cellpadding="0" cellspacing="0" id="line">
                        <display:column title="">
                            <div align="center">
                                <s:checkbox name="checkbox" id="rm_mp_${line_rowNum}" value="${line.idHakmilikPihakBerkepentingan}" class="rm_mp_"/>
                            </div>
                        </display:column>
                        <display:column title="Bil"><div align="right">${line_rowNum}</div></display:column>
                        <display:column title="Nama" class="nama">
                            ${line.nama}
                        </display:column>
                        <display:column title="Jenis Pihak Kepentingan" class="nama">
                            ${line.jenis.nama}
                        </display:column>
                        <display:column title="No Pengenalan">
                            ${line.noPengenalan}
                        </display:column>
                        <display:column title="Syer" style="width:5%;">
                            <div align="center">
                                <c:if test = "${line.jumlahPembilang ne null}" >
                                    ${line.jumlahPembilang}/${line.jumlahPenyebut}
                                </c:if>
                            </div>
                        </display:column>
                        <display:column title="Aktif">
                            <div align="center">
                                ${line.aktif} <c:if test = "${line.idPermohonan eq actionBean.permohonan.idPermohonan}">(Baru)</c:if> 
                            </div>
                        </display:column>
                        <display:column title="Status Kongsi">
                            <div align="center">
                                <c:if test = "${line.pihakKongsiBersama eq null}">TIDAK</c:if><c:if test = "${line.pihakKongsiBersama ne null}">YA</c:if> 
                            </div>
                        </display:column>                        
                    </display:table> 

<%--                    <display:table class="tablecloth" name="${actionBean.senaraiPihakBerkepentingan}"
                                   cellpadding="0" cellspacing="0" id="line">
                        <display:column title="">
                            <div align="center">
                                <s:checkbox name="checkbox" id="rm_mp_${line_rowNum}" value="${line.idHakmilikPihakBerkepentingan}" class="rm_mp_"/>
                            </div>
                        </display:column>
                        <display:column title="Bil"><div align="right">${line_rowNum}</div></display:column>
                        <display:column title="Nama" class="nama">
                            ${line.nama}
                        </display:column>
                        <display:column title="Jenis Pihak Kepentingan" class="nama">
                            ${line.jenis.nama}
                        </display:column>
                        <display:column title="No Pengenalan">
                            ${line.noPengenalan}
                        </display:column>
                        <display:column title="Syer" style="width:5%;">
                            <div align="center">
                                <c:if test = "${line.jumlahPembilang ne null}" >
                                    ${line.jumlahPembilang}/${line.jumlahPenyebut}
                                </c:if>
                            </div>
                        </display:column>
                        <display:column title="Hapus Kumpulan">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="deleteKumpulanBaru('${line.idHakmilikPihakBerkepentingan}')" onmouseover="this.style.cursor = 'pointer';">
                            </div>
                        </display:column>
                    </display:table> --%>
                </c:if>
                <div  align="center">
                    <br>
                    <p>                
                        <s:button name="" value="Kelompokkan" class="btn" onclick="processKelompok(this.form);"/>                        
                    </p>
                </div>    
            </div>
            <br/>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>
                Kumpulan Pihak Kongsi
            </legend>
            <br>
            <div align="center">                
                <display:table class="tablecloth" name="${actionBean.uniqueId}"
                               cellpadding="0" cellspacing="0" id="line">                  
                    <display:column   title="Kumpulan" >
                        <a href="#" onclick="viewPihakTerlibat(${line});
        return false;">${line}</a>
                    </display:column> 
                    <display:column title="Hapus Kumpulan">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removeKumpulan('${line}')" onmouseover="this.style.cursor = 'pointer';">
                        </div>
                    </display:column>
                </display:table> 
                <div  align="center">
                    <br>
                    <p>                
                        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
                    </p>
                </div>    
            </div>
            <br/>
        </fieldset>
    </div>
    <div id="perincianPihak">
    </div>                    
</s:form>
