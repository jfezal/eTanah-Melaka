<%-- 
    Document   : maklumat_asas_strata
    Created on : Oct 8, 2012, 2:46:08 PM
    Author     : mazurahayati.yusop
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>


<script type="text/javascript">
    function edit1(f, id1) {
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?cetak&" + queryString + "&idHakmilik=" + id1, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function cetakBil(f) {

        var form = $(f).formSerialize();
        var report = null;
        var negeri = '${actionBean.kodNegeri}';
        if (negeri == 'melaka') {
            report = 'HSLResitBilCukaiSTRMLK001.rdf';
        } else {
            report = 'HSLResitBilCukaiNS003.rdf';
        }
        var param = document.getElementById('id_hakmilik');
        $.get("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?bilCetakPenyata&idHakmilik=" + param.value,
                function (data) {
                    if (data == 'berjaya') {
    <%--window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_hakmilik="+param.value, "eTanah",
    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");--%>
                        var url = "reportName=" + report + "%26report_p_id_hakmilik=" + param.value+ "%26report_p_no_akaun=" + param.value;

                        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                    }
                });
    }

    function cetakInfoCukai(f, idHakmilik) {
        var form = $(f).formSerialize();
        var negeri = '${actionBean.kodNegeri}';
        var report = null;
        if (negeri == 'melaka') {
            report = "HSLMaklumatCukaiTanahSTR_MLK.rdf";
        } else {
            report = "HSLMaklumatCukaiTanah001.rdf";
        }
    <%--window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_hakmilik="+idHakmilik, "eTanah",
    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");--%>
        var url = "reportName=" + report + "%26report_p_id_hakmilik=" + idHakmilik+ "%26report_p_no_akaun=" + idHakmilik;
        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function refreshPagesAddress123(f) {
        var queryString = $(f).formSerialize()
        var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshpage&" + queryString;
        $.get(url,
                function (data) {
                    $('#aa').html(data);
                }, 'html');

    }
    function refreshPagesAddress(f) {
        var queryString = $(f).formSerialize()
        var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshpage&" + queryString;
        $.get(url,
                function (data) {
                    $('#aa').html(data);
                }, 'html');

    }

    function refreshT123(f) {
        var queryString = $(f).formSerialize()
        var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshTambah&" + queryString;
        $.get(url,
                function (data) {
                    $('#aa').html(data);
                }, 'html');

    }

    function refreshB123(f) {
        var queryString = $(f).formSerialize()
        var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshBaru&" + queryString;
        $.get(url,
                function (data) {
                    $('#aa').html(data);
                }, 'html');

    }

    function bayar() {
        var url = "${pageContext.request.contextPath}/hasil/kutipan_hasil?search&hakmilik=" + hakmilik;
        $.post(url, q,
                function (data) {
                    alert(data);
                    $('#ak').html(data);
                }, 'html');
    }
    function tambahPembayar(f, id1, id2) {
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?tambah&" + queryString + "&idHakmilik=" + id1 + "&noAkaun=" + id2, "eTanah",
                "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=470");
    }

    function pihakBaru(f, id1, id2) {
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?baru&" + queryString + "&idHakmilik=" + id1 + "&noAkaun=" + id2, "eTanah",
                "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=470");
    }
    
    function popupParam(nama, idhakmilik) {
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + "&reportName=" + nama + "&report_p_id_hakmilik=" + idhakmilik, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

</script>
<s:form beanclass="etanah.view.strata.PertanyaanHakmilikStrataActionBean">
    <div id="bayar">

        <div class="subtitle">

            <fieldset class="aras1">
                <legend>Maklumat Induk (Master Title)</legend>
                <p>
                    Borang 2K Indeks Daftar Strata(2K)
                    <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                         onclick="popupParam('STRpertanyaanHM2K.rdf', '${actionBean.hmInduk.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                    Borang 3K Penyata Daftar Strata(3K)
                    <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                         onclick="popupParam('STRpertanyaanHM3K.rdf', '${actionBean.hmInduk.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                </p>
                <p><label>Daerah :</label>
                    ${actionBean.hakmilik.daerah.nama}&nbsp;
                    <s:text name="report_p_id_hakmilik" value="${actionBean.hakmilik.idHakmilik}" id="id_hakmilik" style="display:none"/>
                </p>

                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
                </p>
                <p>
                    <label>Seksyen :</label>
                    <c:if test="${actionBean.hakmilik.seksyen.kod ne null}">
                        ${actionBean.hakmilik.seksyen.nama}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hakmilik.seksyen.kod eq null}">
                        - &nbsp;
                    </c:if>                   
                </p>

                <p>
                    <label>Tempat :</label>
                    <c:if test="${actionBean.hmInduk.lokasi ne null}">
                        ${actionBean.hmInduk.lokasi}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hmInduk.lokasi eq null}">
                        - &nbsp;
                    </c:if>
                </p>
                <p>
                    <label>Jenis Hakmilik :</label>
                    ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
                </p>

                <p>
                    <label>Jenis Penggunaan Tanah :</label>
                    ${actionBean.hmInduk.kategoriTanah.nama}&nbsp;
                </p>

                <p>
                    <label>Kod Lot :</label>
                    ${actionBean.hakmilik.lot.nama}&nbsp;

                </p>
                <p>
                    <label>No Lot/PT :</label>
                    ${actionBean.hakmilik.noLot}&nbsp;

                </p>

                <p>
                    <label>Tarikh Daftar :</label>
                    <fmt:formatDate type="date"
                                    pattern="dd/MM/yyyy"
                                    value="${actionBean.hmInduk.tarikhDaftar}"/>&nbsp;
                </p>

                <p>
                    <label>Tanah Rezab :</label>
                    <c:if test="${actionBean.hmInduk.rizab ne null}">
                        ${actionBean.hmInduk.rizab.nama}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hmInduk.rizab eq null}">
                        - &nbsp;
                    </c:if>
                </p>
                <p>
                    <label>Pihak Berkuasa Tempatan :</label>
                    <c:if test="${actionBean.hmInduk.pbt ne null}">
                        ${actionBean.hmInduk.pbt.nama}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hmInduk.pbt eq null}">
                        - &nbsp;
                    </c:if>
                </p>

                <p>
                    <label>Taraf Pegangan :</label>
                    <c:if test="${actionBean.hmInduk.pegangan eq 'P'}">
                        Pajakan
                    </c:if>
                    <c:if test="${actionBean.hmInduk.pegangan eq 'S'}">
                        Selama - lama
                    </c:if>
                    &nbsp;
                </p>
                <p>
                    <label>Tempoh :</label>
                    <c:if test="${actionBean.hmInduk.tempohPegangan ne null && actionBean.hmInduk.tempohPegangan ne 0 }">
                        ${actionBean.hmInduk.tempohPegangan} Tahun&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hmInduk.tempohPegangan eq null}">
                        -
                    </c:if>
                    <c:if test="${actionBean.hmInduk.tempohPegangan eq 0}">
                        -
                    </c:if>
                </p>
                <p>
                    <label>Tarikh Luput :</label>
                    <c:if test="${actionBean.hmInduk.tarikhLuput eq null}">
                        Tiada
                    </c:if>

                    <fmt:formatDate type="date"
                                    pattern="dd/MM/yyyy"
                                    value="${actionBean.hmInduk.tarikhLuput}"/>&nbsp;
                </p>
                <p>
                    <label for="noPu">No Permohonan Ukur :</label>
                    <c:if test="${actionBean.hmInduk.noPu ne null}">
                        ${actionBean.hmInduk.noPu}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hmInduk.noPu eq null}">
                        - &nbsp;
                    </c:if>
                </p>

                <p>
                    <label >Syarat Nyata :</label>
                    ${actionBean.hakmilik.syaratNyata.kod}&nbsp;                  
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:textarea name="syaratNyata" rows="5" cols="60" id="syarat" readonly="true">${actionBean.hakmilik.syaratNyata.syarat}</s:textarea>
                    </p>

                    <p>
                        <label >Sekatan :</label>
                    ${actionBean.hakmilik.sekatanKepentingan.kod}&nbsp;
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:textarea name="sekatan" rows="5" cols="60" id="sekatan" readonly="true">${actionBean.hakmilik.sekatanKepentingan.sekatan}</s:textarea>
                    </p>
                    <p>
                        <label>No Pelan Piawai :</label>
                    <c:if test="${actionBean.hmInduk.noLitho ne null}">
                        ${actionBean.hmInduk.noLitho}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hmInduk.noLitho eq null}">
                        - &nbsp;
                    </c:if>
                </p>
                <p>
                    <label>No Pelan Diperakui :</label>
                    <c:if test="${actionBean.hmInduk.noPelan ne null}">
                        ${actionBean.hmInduk.noPelan}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hmInduk.noPelan eq null}">
                        - &nbsp;
                    </c:if>
                </p>

                <p>
                    <label >Keluasan  :</label>
                    <s:format formatPattern="#,##0.0000" value=" ${actionBean.hmInduk.luas}"/>&nbsp; ${actionBean.hmInduk.kodUnitLuas.nama}
                </p>
                <%-- <p>
                     <label >Cukai Asal (RM)  :</label>

                    <fmt:formatNumber value="${actionBean.hakmilik.cukaiSebenar}" pattern="0.00"/>&nbsp;
                </p>--%>
                <p>
                    <label >Cukai Tanah Tahunan (RM)  :</label>

                    <fmt:formatNumber value="${actionBean.hmInduk.cukai}" pattern="0.00"/>&nbsp;
                </p>
                <p>
                    <label>No Versi DHKE (Master Title-Landed) :</label>
                    ${actionBean.hmInduk.noVersiDhke}&nbsp;
                </p>
                <p>
                    <label>No Versi DHDE (Master Title-Landed) :</label>
                    ${actionBean.hmInduk.noVersiDhde}&nbsp;
                </p>


                <p>
                    <label>Tarikh Daftar Asal :</label>
                    <c:if test="${actionBean.hmInduk.tarikhDaftarAsal ne ''}">
                        <fmt:formatDate type="date"
                                        pattern="dd/MM/yyyy" value="${actionBean.hmInduk.tarikhDaftarAsal}"/>&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hmInduk.tarikhDaftarAsal eq ''}">
                        - &nbsp;
                    </c:if>
                </p>
                <c:if test="${actionBean.hmInduk.kodStatusHakmilik.kod eq 'B'}" >
                    <p>
                        <label>Tarikh Batal :</label>
                        <%--${actionBean.hakmilik.tarikhBatal}--%>
                        <fmt:formatDate type="date"
                                        pattern="dd/MM/yyyy"
                                        value="${actionBean.hmInduk.tarikhBatal}"/>&nbsp;
                    </p>
                </c:if>
                <p>
                    <label>No Rujukan Fail :</label>
                    ${actionBean.hmInduk.noFail}&nbsp;
                </p>
                <br/>
                <%--<p align="center">
                    <s:button name="pertanyaanHakmilik" id="kembali" value="Kembali" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/>
                </p>--%>
            </fieldset>
        </div>
    </div> 

</s:form>

