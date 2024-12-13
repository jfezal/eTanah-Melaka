<%-- 
    Document   : laporan_maklumaturusan_mis
    Created on : 16 Ogos 2010, 10:43:28 AM
    Author     : wan.fairul
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#maklumat_hakmilik').hide();
        $('#maklumat_keluasan_tanah').hide();
        $('#maklumat_pemilikan').hide();
        $('#statistik_keluasan_tanah').hide();
        $('#laporan_hakmilik').hide();
        $('#laporan_kemajuan').hide();
        $('#laporan_tambahan').hide();
        $('#laporan_baru').hide();
    });

    function changelaporan(value){

        if(value =="0")
        {
            $('#maklumat_hakmilik').hide();
            $('#maklumat_keluasan_tanah').hide();
            $('#maklumat_pemilikan').hide();
            $('#statistik_keluasan_tanah').hide();
            $('#laporan_kemajuan').hide();
            $('#laporan_hakmilik').hide();
            $('#laporan_tambahan').hide();
            $('#laporan_baru').hide();
        }
        if(value =="1")
        {
            $('#maklumat_hakmilik').show();
            $('#maklumat_keluasan_tanah').hide();
            $('#maklumat_pemilikan').hide();
            $('#statistik_keluasan_tanah').hide();
            $('#laporan_kemajuan').hide();
            $('#laporan_hakmilik').hide();
            $('#laporan_tambahan').hide();
            $('#laporan_baru').hide();
        }
        if(value =="2")
        {
            $('#maklumat_hakmilik').hide();
            $('#maklumat_keluasan_tanah').show();
            $('#maklumat_pemilikan').hide();
            $('#statistik_keluasan_tanah').hide();
            $('#laporan_kemajuan').hide();
            $('#laporan_hakmilik').hide();
            $('#laporan_tambahan').hide();
            $('#laporan_baru').hide();
        }
        if(value =="3")
        {
            $('#maklumat_hakmilik').hide();
            $('#maklumat_keluasan_tanah').hide();
            $('#maklumat_pemilikan').show();
            $('#statistik_keluasan_tanah').hide();
            $('#laporan_kemajuan').hide();
            $('#laporan_hakmilik').hide();
            $('#laporan_tambahan').hide();
            $('#laporan_baru').hide();
        }
        if(value =="4")
        {
            $('#maklumat_hakmilik').hide();
            $('#maklumat_keluasan_tanah').hide();
            $('#maklumat_pemilikan').hide();
            $('#statistik_keluasan_tanah').show();
            $('#laporan_kemajuan').hide();
            $('#laporan_hakmilik').hide();
            $('#laporan_tambahan').hide();
            $('#laporan_baru').hide();
        }
        if(value =="5")
        {
            $('#maklumat_hakmilik').hide();
            $('#maklumat_keluasan_tanah').hide();
            $('#maklumat_pemilikan').hide();
            $('#statistik_keluasan_tanah').hide();
            $('#laporan_kemajuan').show();
            $('#laporan_hakmilik').hide();
            $('#laporan_tambahan').hide();
            $('#laporan_baru').hide();
        }
        if(value =="6")
        {
            $('#maklumat_hakmilik').hide();
            $('#maklumat_keluasan_tanah').hide();
            $('#maklumat_pemilikan').hide();
            $('#statistik_keluasan_tanah').hide();
            $('#laporan_kemajuan').hide();
            $('#laporan_hakmilik').show();
            $('#laporan_tambahan').hide();
            $('#laporan_baru').hide();
        }

        if(value =="7")
        {
            $('#maklumat_hakmilik').hide();
            $('#maklumat_keluasan_tanah').hide();
            $('#maklumat_pemilikan').hide();
            $('#statistik_keluasan_tanah').hide();
            $('#laporan_kemajuan').hide();
            $('#laporan_hakmilik').hide();
            $('#laporan_tambahan').show();
            $('#laporan_baru').hide();
        }
        if(value =="8")
        {
            $('#maklumat_hakmilik').hide();
            $('#maklumat_keluasan_tanah').hide();
            $('#maklumat_pemilikan').hide();
            $('#statistik_keluasan_tanah').hide();
            $('#laporan_kemajuan').hide();
            $('#laporan_hakmilik').hide();
            $('#laporan_tambahan').hide();
            $('#laporan_baru').show();
        }    
    }
    function popup(url)
    {
        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=yes';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }
</script>

<div id="laporan">
    <s:form beanclass="etanah.view.stripes.pengambilan.LaporanMaklumatUrusanMis">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Laporan Pendaftaran
                </legend>
                <p style="color:red">
                    *Sila Pilih Senarai Laporan Pendaftaran<br/>
                </p>
                <br>
                <p>
                    <label>Senarai Laporan Seksyen 3 :</label>
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <s:select name="senaraiLaporan"  onchange="javaScript:changelaporan(this.value)">
                            <s:option value="0">--Sila Pilih--</s:option>
                            
                            <s:option value="2">Pengambilan Seksyen 8 dibawah 3(1)(a)</s:option>
                            <s:option value="3">Pengambilan Seksyen 8 dibawah 3(1)(b)</s:option>
                            <s:option value="4">Pengambilan Seksyen 8 dibawah 3(1)(c)</s:option>
                           
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.kodNegeri eq 'n9'}">
                        <s:select name="senaraiLaporan"  onchange="javaScript:changelaporan(this.value)">
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option value="2">Maklumat Keluasan Tanah</s:option>
                            <s:option value="3">Maklumat Pemilikan</s:option>
                            <s:option value="5">Laporan Kemajuan</s:option>
                            <s:option value="6">Laporan Hakmilik</s:option>
                            <s:option value="7">Laporan Tambahan</s:option>
                        </s:select>
                    </c:if>
                </p>
                <br>
            </fieldset>    
        </div>
        <br>
        

                        
        <div class="subtitle" id="maklumat_keluasan_tanah">
            <fieldset class="aras1">

                <legend>
                    Seksyen 3 (a)
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.senaraiKeluasanTanah}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/laporanMaklumat/requestParam?namaReport=${actionBean.senaraiKeluasanTanahRN[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
        <div class="subtitle" id="maklumat_pemilikan">
            <fieldset class="aras1">

                <legend>
                    Syeksen 3 (b)
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.senaraiPemilikan}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/laporanMaklumatUrusanMis/requestParam?namaReport=${actionBean.senaraiPemilikanRN[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>

    </s:form>
</div>
