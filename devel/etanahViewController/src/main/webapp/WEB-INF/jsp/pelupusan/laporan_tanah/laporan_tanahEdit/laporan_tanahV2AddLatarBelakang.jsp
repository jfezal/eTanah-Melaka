<%--
    Document   :  laporan_tanahV2AddEditLotSmpdn.jsp
    Created on :  March 05, 2012, 11:28:13 AM
    Author     :  Shazwan
--%>




<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carian Latar Belakang Tanah</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
        
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'})
        $('#hakmilik').hide();
        $('#lesen').hide();
        $('#permit').hide();
        $('#manual').hide();
        $('#manual1').hide();
        $('#carian').hide();
        $('#simpanManual').hide();
    });
        
    function refreshpage(){
        NoPrompt();
        opener.refreshV2('main');
        self.close();
    }
        
    function openFrame(type){
        var idHakmilik = $('#idHakmilik').val();
        window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?openFrame&idHakmilik="
            +idHakmilik+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
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
        
    function changeValue(val){
        if(val == 'H'){
            $('#hakmilik').show();
            $('#lesen').hide();
            $('#permit').hide();
            $('#manual').hide();
            $('#manual1').hide();
            $('#carian').show();
            $('#simpanManual').hide();
        }else if(val == 'LP'){
            $('#hakmilik').hide();
            $('#lesen').show();
            $('#permit').hide();
            $('#manual').hide();
            $('#manual1').hide();
            $('#carian').show();
            $('#simpanManual').hide();
        }else if(val == 'P'){
            $('#hakmilik').hide();
            $('#lesen').hide();
            $('#permit').show();
            $('#manual').hide();
            $('#manual1').hide();
            $('#carian').show();
            $('#simpanManual').hide();
        }else{
            $('#hakmilik').hide();
            $('#lesen').hide();
            $('#permit').hide();
            $('#manual').show();
            $('#manual1').show();
            $('#carian').hide();
            $('#simpanManual').show();
        }
    }
    
    function addMohonLaporPohon(){
        NoPrompt();
        var idHakmilik = $('#idHakmilik').val();
        window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?showFormPopUpLatarBelakangTanah&idHakmilik="+idHakmilik+"&idLaporTanah="+${actionBean.idLaporTanah}, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        //window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = true;
        }

    </script>
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahV2PelupusanActionBean" name="frm">
            <fieldset class="aras1">
                <legend>
                    Penambahan Latar Belakang Tanah
                </legend>               
                <s:hidden name="idLaporTanah" value="${actionBean.idLaporTanah}"/>     
                <s:hidden name="idHakmilik" id="idHakmilik"/>
                <table class="tablecloth" border="0" align="center">
                    <tr>
                        <td>
                            Jenis Permohonan :
                        </td>
                        <td>
                            <s:radio name="permohonanLaporanPohon.jenisDipohon" value="H" id="jenisDipohon" onclick="changeValue(this.value)"/>&nbsp;Pemberimilikan&nbsp;
                            <s:radio name="permohonanLaporanPohon.jenisDipohon" value="LP" id="jenisDipohon" onclick="changeValue(this.value)"/>&nbsp;LPS&nbsp;
                            <s:radio name="permohonanLaporanPohon.jenisDipohon" value="P" id="jenisDipohon" onclick="changeValue(this.value)"/>&nbsp;Permit&nbsp;
                            <s:radio name="permohonanLaporanPohon.jenisDipohon" value="M" id="jenisDipohon" onclick="changeValue(this.value)"/>&nbsp;Di Luar Sistem&nbsp;
                        </td>
                    </tr>
                    <tr id="hakmilik">
                        <td>ID Hakmilik</td>
                        <td><s:text name="permohonanLaporanPohon.noRujukan" id="noRujukan" size="20" /></td>
                    </tr>
                    <tr id="lesen">
                        <td>No Lesen</td>
                        <td><s:text name="permohonanLaporanPohon.noRujukan" id="noRujukan" size="12" onkeyup="validateNumber(this,this.value);"/></td>
                    </tr>
                    <tr id="permit">
                        <td>No Permit</td>
                        <td><s:text name="permohonanLaporanPohon.noRujukan" id="noRujukan" size="12" onkeyup="validateNumber(this,this.value);"/></td>
                    </tr>
                    <tr id="manual">
                        <td>No Rujukan / Fail</td>
                        <td><s:text name="noFail" id="noRujukan" size="12"/></td>
                    </tr>
                    <tr id="manual1">
                        <td>Kegunaan</td>
                        <td><s:text name="permohonanLaporanPohon.kegunaan" id="kegunaan" size="20"/></td>
                    </tr>
                    <tr id="carian">
                        <td colspan="2">
                            <s:submit name="carianLatarBelakangTanah" value="Cari" class="btn" onclick="NoPrompt();"/>
                            &nbsp;<s:reset name="IsiSemula" value="Isi Semula" class="btn" onclick="NoPrompt();"/>
                            &nbsp;<s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('lBelakangTanah')"/>
                        </td>
                    </tr>
                    <tr id="simpanManual">
                        <td colspan="2">
                            <s:submit name="simpanLatarBelakangTanah" value="Simpan" class="btn" onclick="NoPrompt();"/>
                            &nbsp;<s:reset name="IsiSemula" value="Isi Semula" class="btn" onclick="NoPrompt();"/>
                            &nbsp;<s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('lBelakangTanah')"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <br>



        <div class="subtitle">
            <c:if test="${tanah}">
                <fieldset class="aras1">
                    <legend>Carian</legend>
                    <table class="tablecloth" border="0" align="center">
                        <tr>
                            <td><c:choose>
                                    <c:when test="${actionBean.permohonanLaporanPohon.jenisDipohon eq 'H'}">
                                        No. Hakmilik
                                    </c:when>
                                    <c:when test="${actionBean.permohonanLaporanPohon.jenisDipohon eq 'LP'}">
                                        No. Lesen
                                    </c:when>
                                    <c:when test="${actionBean.permohonanLaporanPohon.jenisDipohon eq 'P'}">
                                        No. Permit
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>
                            <s:hidden name="permohonanLaporanPohon.jenisDipohon" value="${actionBean.permohonanLaporanPohon.jenisDipohon}"/>
                            </td>
                            <td>${actionBean.permohonanLaporanPohon.noRujukan}
                            <s:hidden name="permohonanLaporanPohon.noRujukan" value="${actionBean.permohonanLaporanPohon.noRujukan}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Nama Pemegang</td>
                            <td>${actionBean.namaPemohon}</td>
                        </tr>
                        <tr>
                            <td>Kegunaan</td>
                            <td>${actionBean.permohonanLaporanPohon.kegunaan}
                            <s:hidden name="permohonanLaporanPohon.kegunaan" value="${actionBean.permohonanLaporanPohon.kegunaan}"/>
                            </td>
                        </tr>
                        <tr>
                            <td><c:choose>
                                    <c:when test="${actionBean.permohonanLaporanPohon.jenisDipohon eq 'H'}">
                                        Tarikh Daftar
                                    </c:when>
                                    <c:when test="${actionBean.permohonanLaporanPohon.jenisDipohon eq 'LP'}">
                                        Tarikh Dikeluarkan
                                    </c:when>
                                    <c:when test="${actionBean.permohonanLaporanPohon.jenisDipohon eq 'P'}">
                                        Tarikh Dikeluarkan
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose></td>
                            <td>
                            ${actionBean.tarikhDaftar}
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <s:submit name="simpanLatarBelakangTanah" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                &nbsp;<s:button name="tutup" value="Carian Semula" class="btn" onclick="addMohonLaporPohon()"/>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </c:if>

        </s:form>
    </div>
</body>


