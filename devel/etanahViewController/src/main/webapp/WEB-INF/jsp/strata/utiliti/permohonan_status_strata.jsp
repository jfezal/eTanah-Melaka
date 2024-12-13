<%-- 
    Document   : permohonan_status_strata
    Created on : May 13, 2011, 03:49:04 PM
    Author     : latifah.iskak
--%>

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
        <title>Status Permohonan</title>
    </head>
    <body>
        <script type="text/javascript">
            function dateValidation(id, value) {
                var vsplit = value.split('/');
                var fulldate = vsplit[1] + '/' + vsplit[0] + '/' + vsplit[2];
                var today = new Date();
                var sdate = new Date(fulldate);
                if (sdate > today) {
                    alert("Tarikh yang dimasukkan tidak sesuai.");
                    $('#' + id).val("");
                }
            }
            
            function viewHakmilik(id){
                window.open("${pageContext.request.contextPath}/lelong/status?viewhakmilikDetail&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
            }

            function viewSejarah(id){
                window.open("${pageContext.request.contextPath}/lelong/status?viewSejarah&idPermohonan="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
            }
  
            function doSearch(f,e){       
                var v = $('#idPermohonan').val();
                if(v == ''){
                    alert('Sila masukkan ID Permohonan.');
                    $('#idPermohonan').focus();
                    return;
                }
                f.action = f.action + '?' + e;        
                f.submit();
            }
        </script>
        <s:messages />
        <s:errors />

        <s:form action="/strata/status_permohonan">

            <fieldset class="aras1">
                <legend>Status Permohonan Strata</legend>
                <p>
                    <label>ID Permohonan :</label>
                    <s:text name="permohonan.idPermohonan" size="34" maxlength="20" id="idMohon" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="checkPermohonan" value="Cari" class="btn" onclick=""/>
                    <s:submit name="reset" value="Isi Semula" class="btn" onclick=""/>&nbsp;
                </p>
                <br/><br/>

                <p><label>Carian Mengikut Tarikh Permohonan</label></p>
                <br>
                <center>
                <p>
                    Tarikh Mula :
                    <s:text id="trh_mula" name="trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                   Tarikh Akhir :
                    <s:text id="trh_akhir" name="trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                </center>
                <br/>
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="checkPermohonanByDate" value="Cari" class="btn" onclick=""/>
                    <s:submit name="reset" value="Isi Semula" class="btn" onclick=""/>&nbsp;
                </p>
            </fieldset>
                
            <c:if test="${actionBean.flag}">
                <fieldset class="aras1">
                    <legend>Proses Permohonan</legend>
                    <p>
                        <label for="idPermohonan">ID Permohonan :</label>
                        ${actionBean.permohonan.idPermohonan}
                    </p>
                    <p>
                        <label for="kodUrusan">Kod Urusan/Urusan :</label>
                        ${actionBean.permohonan.kodUrusan.kod} -
                        ${actionBean.permohonan.kodUrusan.nama}
                    </p>

                    <!--<p><label for="pguna">Peringkat :</label>--actionBean.stage == null ? "-" : actionBean.stage-->
                    <!--</p>-->

                    <p>
                        <label for="kodUrusan">Status permohonan :</label>
                        <c:choose>
                            <c:when test="${actionBean.permohonan.status eq 'SL'}">
                               SELESAI 
                               <c:if test="${actionBean.permohonan.keputusan.kod ne null}">
                               ( ${actionBean.permohonan.keputusan.nama} )
                               </c:if>
                            </c:when>
                            <c:otherwise>
                                ${actionBean.namastatus}
<!--                                Belum Selesai-->
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p>
                        <label for="stage">Peringkat :</label>
                        ${actionBean.stage == null ? "-" : actionBean.stage}
                    </p>
                    <br/>
                    <center>
                    <display:table name="${actionBean.permohonan.senaraiFasa}" class="tablecloth" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
                        <%--<display:column property="idAliran" title="Tindakan"/>--%>
                        <display:column title="Tindakan">
                            <c:choose>
                                <c:when test="${line.idAliran eq 'agihtugas'}">Agih Tugasan</c:when>
                                <c:when test="${line.idAliran eq 'agihantugas'}">Agih Tugasan</c:when>
                                <c:when test="${line.idAliran eq 'kemasukan'}">Kemasukan</c:when>
                                <c:when test="${line.idAliran eq 'g_semakkemasukan'}">Semak Kemasukan</c:when>
                                <c:when test="${line.idAliran eq 'semak_kemasukan'}">Semak Kemasukan</c:when>
                                <c:when test="${line.idAliran eq 'g_sedialaporan'}">Sedia Laporan</c:when>
                                <c:when test="${line.idAliran eq 'sedialaporan'}">Sedia Laporan</c:when>
                                <c:when test="${line.idAliran eq 'semaklaporan'}">Semak Laporan</c:when>
                                <c:when test="${line.idAliran eq 'terimapelan'}">Terima Pelan</c:when>
                                <c:when test="${line.idAliran eq 'sediakertasptg'}">Sedia Kertas Pertimbangan PTG</c:when>
                                <c:when test="${line.idAliran eq 'sedia_kertas_pertimbangan'}">Sedia Kertas Pertimbangan PTG</c:when>
                                <c:when test="${line.idAliran eq 'semakkertasptg'}">Semak Kertas Pertimbangan PTG</c:when>
                                <c:when test="${line.idAliran eq 'semakkertasptg2'}">Semak Kertas Pertimbangan PTG</c:when>
                                <c:when test="${line.idAliran eq 'semak_kertas_pertimbangan'}">Semak Kertas Pertimbangan PTG</c:when>
                                <c:when test="${line.idAliran eq 'perakuan'}">Perakuan</c:when>
                                <c:when test="${line.idAliran eq 'keputusan1'}">Keputusan PTG</c:when>
                                <c:when test="${line.idAliran eq 'keputusan'}">Keputusan PTG</c:when>
                                <c:when test="${line.idAliran eq 'g_cetakpelan'}">Cetak Pelan</c:when>
                                <c:when test="${line.idAliran eq 'g_sediasuratlulus'}">Sedia Surat Lulus</c:when>
                                <c:when test="${line.idAliran eq 'terimabayaran'}">Terima Bayaran</c:when>
                                <c:when test="${line.idAliran eq 'terimabayaran2'}">Terima Bayaran</c:when>
                                <c:when test="${line.idAliran eq 'sediasuratperingatan'}">Penyediaan Surat Makluman</c:when>
                                <c:when test="${line.idAliran eq 'g_keputusan2'}">Keputusan Pendaftar</c:when>
                                <c:when test="${line.idAliran eq 'bayaran5F'}">Bayaran Notis 5F</c:when>
                                <c:when test="${line.idAliran eq 'sediasurattolak'}">Penyediaan Surat Tolak</c:when>
                                <c:when test="${line.idAliran eq 'janasurattolak'}">Jana Surat Tolak</c:when>
                                <c:when test="${line.idAliran eq 'signsuratlantikan'}">Surat Lantikan</c:when>
                                <c:when test="${line.idAliran eq 'sediakertassiasatan'}">Sedia Kertas Siasatan</c:when>
                                <c:when test="${line.idAliran eq 'janakertassiasatan'}">Jana Kertas Siasatan</c:when>
                                <c:when test="${line.idAliran eq 'kemasukansediasijil'}">Kemasukan</c:when>
                                <c:when test="${line.idAliran eq 'semaksijil'}">Semak Kemasukan</c:when>
                                <c:when test="${line.idAliran eq 'semaksijiltphgt'}">Semak Sijil</c:when>
                                <c:when test="${line.idAliran eq 'g_semaklaporan'}">Semak Laporan</c:when>
                                <c:when test="${line.idAliran eq 'g_sediakertasptg'}">Sedia Kertas Pertimbangan PTG</c:when>
                                <c:when test="${line.idAliran eq 'sediabayaran'}">Kemasukan Bayaran Kelulusan</c:when>
                                <c:when test="${line.idAliran eq 'sediasuratlulus'}">Penyediaan Surat Makluman</c:when>
                                <c:when test="${line.idAliran eq 'sediasuratbatal'}">Penyediaan Surat Pembatalan</c:when>
                                <c:when test="${line.idAliran eq 'g_semakpelan'}">Semak Pelan dan Borang</c:when>
                                <c:when test="${line.idAliran eq 'g_terimapab'}">Terima Pelan</c:when>
                                <c:when test="${line.idAliran eq 'teriamlaporan'}">Semak Laporan</c:when>
                                <c:when test="${line.idAliran eq 'tandatangansurat'}">Tanda Tangan Surat</c:when>
                                <c:when test="${line.idAliran eq 'sediasuratjupem'}">Sedia Surat Jupem</c:when>
                                <c:when test="${line.idAliran eq 'kemasukan_jupem'}">Sedia Surat Permohonan Jupem</c:when>
                                <c:when test="${line.idAliran eq 'terima_jupem'}">Terima Pelan</c:when>
                                <c:when test="${line.idAliran eq 'cetak_pelan'}">Sedia Surat Lulus</c:when>
                                <c:when test="${line.idAliran eq 'semakwaran'}">Semak Waran</c:when>
                                <c:when test="${line.idAliran eq 'drafkertasptg'}">Sedia Kertas Pertimbangan PTG</c:when>
                                <c:when test="${line.idAliran eq 'syorkertasptg'}">Syor Kertas PTG</c:when>
                                <c:when test="${line.idAliran eq 'sediasuratmakluman'}">Sedia Surat Makluman</c:when>
                                <c:when test="${line.idAliran eq 'semakpermit'}">Semak Permit</c:when>
                                <c:when test="${line.idAliran eq 'sediasurat'}">Sedia Surat Makluman</c:when>
                                <c:when test="${line.idAliran eq 'drafkertasmmk'}">Draf Kertas MMK</c:when>
                                <c:when test="${line.idAliran eq 'semakdrafkertasmmk'}">Semak Draf Kertas Pertimbangan</c:when>
                                <c:when test="${line.idAliran eq 'semakkertasmmk'}">Semak Kertas MMK</c:when>
                                <c:when test="${line.idAliran eq 'kemasukantarikh'}">Kemasukan Tarikh Mesyuarat</c:when>
                                <c:when test="${line.idAliran eq 'keputusanmmk'}">Kemasukan keputusan e-MMKN</c:when>
                                <c:when test="${line.idAliran eq 'sediasijil'}">Penyediaan Sijil</c:when>
                                <c:when test="${line.idAliran eq 'pendaftaran_hakmilik'}">Pendaftaran Hakmilik Strata</c:when>
                                <c:when test="${line.idAliran eq 'surat_tolak'}">Sedia Surat Tolak</c:when>
                                <c:when test="${line.idAliran eq 'jana_sijil_sifus'}">Semak Sijil Formula Unit Syer</c:when>
                                <c:when test="${line.idAliran eq 'jana_surat_maklum'}">Jana Surat Makluman</c:when>
                                <c:when test="${line.idAliran eq 'maklum_tolak'}">Makluman Penolakan</c:when>
                                <c:when test="${line.idAliran eq 'jana_tolak'}">Jana Surat Penolakan</c:when>
                                
                                <c:when test="${line.idAliran eq 'cetakmmk'}">Cetak Kertas MMK</c:when>
                                <c:when test="${line.idAliran eq 'kemasukan_sediasijil'}">Penyediaan Sijil</c:when>
                                <c:when test="${line.idAliran eq 'semakSijil'}">Semak Sijil</c:when>
                                <c:when test="${line.idAliran eq 'semakSijil2'}">Semak Sijil</c:when>
                                <c:otherwise></c:otherwise>
                            </c:choose>
                        </display:column>
                        <display:column property="idPengguna" title="Diproses Oleh"/>
                        <%--<display:column property="infoAudit.dimasukOleh.nama" title="Diproses Oleh"/>
                        <display:column property="infoAudit.dimasukOleh.jawatan" title="Jawatan"/>                    
                        <display:column title="Status">--%>  
                        <%--<c:if test="${line.keputusan.kod ne null}"> ${line.keputusan.kod} - ${line.keputusan.nama}
                        <%--</c:if>--%>
                        <%--</display:column>--%>
                        <display:column property="ulasan" title="Ulasan"/>
                        <%--<display:column title="Nota/Kertas Minit">--%>
                        <%--<c:forEach items="${actionBean.listNota}" var="n">--%>
                        <%--<c:if test="${n.idAliran eq line.idAliran}">-- ${n.nota}
                        <%--</c:if>--%>
                        <%--</c:forEach>--%>
                        <%--</display:column>--%>
                    </display:table>
                </fieldset>
                <br/>
            </c:if>
                <br/>
                <c:if test="${!empty actionBean.senaraiPermohonanSTR}">
                    <fieldset>
                        <display:table name="${actionBean.senaraiPermohonanSTR}" class="tablecloth" id="line">
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column title="Id Permohonan">${line.idPermohonan}</display:column>
                            <display:column title="Urusan">${line.kodUrusan.nama}</display:column>
                            <display:column title="Tarikh Permohonan">${line.infoAudit.tarikhMasuk}</display:column>
                            <display:column title="Status">
                                <c:choose>
                                    <c:when test="${line.status eq 'SL'}">
                                        SELESAI
                                        <c:if test="${actionBean.permohonan.keputusan.kod ne null}">
                                            ( ${actionBean.permohonan.keputusan.nama} )
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        Belum Selesai
                                    </c:otherwise>
                                </c:choose>
                            </display:column>
                        </display:table>
                    </fieldset>
                </c:if>
                </center>
        </s:form>
    </body>
</html>
