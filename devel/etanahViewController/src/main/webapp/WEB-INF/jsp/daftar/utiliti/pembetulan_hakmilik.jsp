<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
    </head>

    <body>
        <script type="text/javascript">
            $(document).ready(function() {

                $('#tmbh_hakmilik').hide();
                var len = $(".daerah").length;

                for (var i = 0; i <= len; i++) {
                    $('.idHakmilikBaru' + i).click(function() {
                        window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik=" + $(this).text(), "eTanah",
                                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
                    });
                }
            });

            function tmbhHakmilik() {
                $('#tmbh_hakmilik').show();
            }

            function edithakmilik(id, id2) {
                window.open("${pageContext.request.contextPath}/daftar/betul_hakmilik?editHakmilikPermohonan&idHakmilik=" + id + "&idPermohonan=" + id2, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
            }

            function deleteHakmilik(val, val2, f,val3) {
                form = document.form1;
                var answer = confirm("adakah anda pasti untuk Hapus?");
                if (answer) {
                    form.action = "${pageContext.request.contextPath}/daftar/betul_hakmilik?deleteHakmilik&idHakmilik=" + val + "&idPermohonan=" + val2+ "&idMh=" + val3;
                    form.submit();
                }
            }

            function popupTambahHakmilik(id) {
                var url = '${pageContext.request.contextPath}/daftar/betul_hakmilik?showCarianHakmilik&idPermohonan=' + id;
                window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,width=900,height=300");
            }

            function saveHakmilik(val, f) {
                if ($('#idHakmilikBaru').val() == "") {
                    alert('Sila Masukkan ID Hakmilik');
                }
                else {
                    f.action = f.action + '?simpanHakmilik&idPermohonan=' + val;
                    f.submit();
                }
            }

            function reload(v)
            {
                var frm = document.form1;
                var url = "${pageContext.request.contextPath}/daftar/betul_hakmilik?checkPermohonan&idPermohonan=" + v;
                frm.action = url;
                frm.submit();

            }

            function generate(v)
            {
                var frm = document.form1;
                var url = "${pageContext.request.contextPath}/daftar/betul_hakmilik?generateAkuanPenerimaan&idPermohonan=" + v;
                frm.action = url;
                frm.submit();

            }
            function generateresit(v)
            {
                var frm = document.form1;
                var url = "${pageContext.request.contextPath}/daftar/betul_hakmilik?generateResitBayaran&idPermohonan=" + v;
                frm.action = url;
                frm.submit();

            }
            function KemaskiniPenyerah(id) {
                window.open("${pageContext.request.contextPath}/daftar/betul_hakmilik?popapKemaskini&idMohon=" + id, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
            }



            function kemaskiniWakilKuasa(id, idWakil) {
                
                window.open("${pageContext.request.contextPath}/daftar/betul_hakmilik?popapKemaskiniWakilKuasa&idMohon=" + id+"&idWakil="+idWakil, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
            }

            function buang(id, idWakil, idDokumen)
            {                
                form = document.form1;
                var answer = confirm("adakah anda pasti untuk Hapus?");
                if (answer) {
                    form.action = "${pageContext.request.contextPath}/daftar/betul_hakmilik?deletewakilkuasa&idMohon=" + id+"&idWakil="+idWakil+"&idDokumen="+idDokumen;
                    form.submit();
                }

            }
            
             function TambahWakilKuasa(id) {
                window.open("${pageContext.request.contextPath}/daftar/betul_hakmilik?popapWakilKuasa&idMohon=" + id, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
            }




        </script>
        <s:messages />
        <s:errors />

        <div id="page_div">

            <s:form beanclass="etanah.view.daftar.UtilitiBetulHakmilik" name="form1">
                <s:hidden name="permohonan.idPermohonan"></s:hidden>
                    <fieldset class="aras1">

                        <legend>Baiki Perserahan Awalan</legend>
                        <br>
                        <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                        <s:text name="idPermohonan" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();" />
                    </p>

                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="checkPermohonan" value="Seterusnya" class="btn" />
                    </p>
                    <br>
                </fieldset>
                <br>
                <c:if test="${actionBean.form}">
                    <fieldset class="aras1">

                        <legend>Baiki Perserahan Hakmilik</legend>
                        <br>
                        <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                            ${actionBean.permohonan.idPermohonan}
                        </p>

                        <p><label for="kodUrusan">Kod Urusan/Urusan :</label>
                            ${actionBean.permohonan.kodUrusan.kod} -
                            ${actionBean.permohonan.kodUrusan.nama}
                        </p>

                        <p><label for="tarikhDaftar">Tarikh Perserahan/Permohonan :</label>
                            <fmt:formatDate pattern="dd/MM/yyyy hh:mm aa" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
                        </p>
                        &nbsp;
                        <p><label for="penyerah">Penyerah :</label>
                            ${actionBean.permohonan.penyerahNama}
                            <a onmouseover="this.style.cursor = 'pointer';" onclick="KemaskiniPenyerah('${actionBean.permohonan.idPermohonan}');">
                                <img alt="Kemaskini Maklumat Penyerah" src='${pageContext.request.contextPath}/pub/images/edit.gif' />&nbsp;
                            </a>
                        </p>
                        <p><label for="penyerah">Alamat :</label>
                        <p>${actionBean.permohonan.penyerahAlamat1}
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            ${actionBean.permohonan.penyerahAlamat2}&nbsp;
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            ${actionBean.permohonan.penyerahAlamat3}&nbsp;
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            ${actionBean.permohonan.penyerahAlamat4}&nbsp;
                        </p>


                        </p>
                        <p><label for="penyerah">Poskod :</label>
                            ${actionBean.permohonan.penyerahPoskod}
                        </p>

                        <p><label for="keputusan">Keputusan :</label>
                            ${actionBean.permohonan.keputusan.kod == null ? "-" : actionBean.permohonan.keputusan.nama}
                        </p>


                        <p><label for="keputusan">No SA/SW/SB :</label>
                            <c:if test="${fn:length(actionBean.listNoRuj) > 0}">
                            <fieldset class="aras1">    
                                <display:table class="tablecloth" name="${actionBean.listNoRuj}" cellpadding="0" cellspacing="0" id="line">

                                    <display:column title="Bil" sortable="true">${line_rowNum}
                                    </display:column>
                                    <display:column property="noRujukan" title="Id Wakil"/>                                    
                                    <display:column title="Tambah">
                                        <center><img alt='Tambah Wakil Kuasa' border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'
                                             id='rem_${line2_rowNum}' onclick="TambahWakilKuasa('${actionBean.permohonan.idPermohonan}');" onmouseover="this.style.cursor = 'pointer';"></center>
                                    </display:column>
                                        <display:column title="Kemaskini">
                                            <center><img alt='Kemaskini Wakil Kuasa' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                             id='rem_${line2_rowNum}' onclick="kemaskiniWakilKuasa('${actionBean.permohonan.idPermohonan}','${line.noRujukan}');" onmouseover="this.style.cursor = 'pointer';"></center>
                                    </display:column>
                                    <display:column title="Hapus">                                        
                                        <center><img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line2_rowNum}' onclick="buang('${actionBean.permohonan.idPermohonan}','${line.noRujukan}','${line.idDokumen}');" onmouseover="this.style.cursor = 'pointer';"></center>
                                    </display:column>
                                </display:table>
                            </fieldset>
                        </c:if>

                        <c:if test="${fn:length(actionBean.listNoRuj) <= 0}">
                            <c:if test="${actionBean.listNoRuj == null}">
                                Tiada Kuasa Wakil
                            </c:if>
                            ${actionBean.wakilKuasa.idWakil}                
                            <a onmouseover="this.style.cursor = 'pointer';" onclick="TambahWakilKuasa('${actionBean.permohonan.idPermohonan}');">
                                <img alt="Kemaskini Wakil Kuasa" src='${pageContext.request.contextPath}/pub/images/edit.gif' />&nbsp;
                            </a>

                            </p>
                        </c:if>


                        <br>
                    </fieldset>
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Hakmilik Permohonan
                        </legend>
                        <div class="content" align="center">
                            <br>
                            Sila klik pada ID Hakmilik untuk kemaskini ID Hakmilik
                            <br>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                                           requestURI="/daftar/betul_hakmilik" id="line">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column   title="ID Hakmilik" >
                                    <a href="#" onclick="edithakmilik('${line.hakmilik.idHakmilik}', '${actionBean.permohonan.idPermohonan}');
                return false;">${line.hakmilik.idHakmilik}</a>
                                </display:column>
                                <display:column property="hakmilik.noLot" title="No Lot" />
                                <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                             id='rem_${line2_rowNum}' onclick="deleteHakmilik('${line.hakmilik.idHakmilik}', '${actionBean.permohonan.idPermohonan}', this.form,'${line.id}');" onmouseover="this.style.cursor = 'pointer';">
                                    </div>
                                </display:column>
                            </display:table>
                        </div>
                        <p>
                            <label>&nbsp;</label>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PCT'}">
                            <s:button name="popuptambahhakmilik" id="popuptambahhakmilik" value="Tambah Hakmilik" class="longbtn" onclick="tmbhHakmilik();"/>&nbsp;    
                            </c:if>
                            <s:button name="" value="Klik Untuk Refresh" class="longbtn" onmouseover='this.style.cursor="pointer";' onclick="reload('${actionBean.permohonan.idPermohonan}')"/>
                            <s:button name="generateAkuanPenerimaan" value="Jana Dokumen" class="btn" onmouseover='this.style.cursor="pointer";' onclick="generate('${actionBean.permohonan.idPermohonan}')"/>
                            <s:button name="generateResitBayaran" value="Jana Resit" class="btn" onmouseover='this.style.cursor="pointer";' onclick="generateresit('${actionBean.permohonan.idPermohonan}')"/>
                        </p>
                    </fieldset>

                </div>
                <div class="subtitle" id="tmbh_hakmilik">
                    <fieldset class="aras1">
                        <br>
                        <legend>
                            Tambah Hakmilik
                        </legend>
                        <br>
                        <p>
                            <br>
                            <label for="ID Hakmilik">ID Hakmilik :</label>
                            <s:text name="idHakmilikBaru" id="idHakmilikBaru" class="idHakmilikBaru" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            <s:button name="Simpan" value="Simpan" class="btn" onclick="saveHakmilik('${actionBean.idPermohonan}', this.form);"/>
                        </p>
                    </fieldset>
                </div>
            </body>
        </c:if>
    </s:form>
</html>