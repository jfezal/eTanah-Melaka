<%-- 
    Document   : laporan_tanahV2batuan
    Created on : Jun 10, 2013, 9:44:39 AM
    Author     : Mohammad Hafifi
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bahan Batuan dan Pindahan</title>
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
    var size = 0 ;
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
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
    <%--alert("j=hihi");--%>
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
    
        function refreshpage(){    
            NoPrompt();
            
            self.close();
            opener.refreshUtilitiLaporanTanah();
        }
        
        function deleteBahanBatuan(){
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                $.post('${pageContext.request.contextPath}/pelupusan/utiliti/laporanTanah?deleteBahanBatuan',{idPermohonan:'${actionBean.idPermohonan}'},
                function(data){
                    alert("Maklumat Berjaya Dihapuskan");
                    self.close();
                }, 'html');
            }
        }
</script>
<body>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
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
            allowPrompt = false;
        }

    </script>
    <s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiLaporanTanahActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="idPermohonan" id="idPermohonan"/>
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.laporanTanah.idLaporan}"/>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Bahan Batuan dan Pindahan</legend>
                <br>
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <td>Jenis</td>
                            <td>
                                <s:select name="kodbahanbatu" id="kodbahanbatu">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodItemPermitBatuan}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Tempat Diambil</td>
                            <td><s:text name="tempatdiambil" style="width:250px"/></td>
                        </tr>
                        <tr>
                            <td>Tempat Dipindah</td>
                            <td><s:text name="tempatdipindah" style="width:250px"/></td>
                        </tr>
                        <tr>
                            <td>Kuantiti</td>
                            <td>
                                <s:text name="kuantitibahanbatu"/>
                                <s:select name="kodkuantitibatuuom" id="kodkuantitibatuuom">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodUOMForBahanBatu}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
        <fieldset class="aras1">
            <table  width="100%" border="0">
                <tr>
                    <td align="center">
                        <s:submit name="simpanBahanBatuanPindahan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                        <c:if test="${actionBean.permohonanBahanBatuan ne null}">
                            <s:button name="padamBahanBatuanPindahan" value="Padam" class="btn" onclick="deleteBahanBatuan();"/>
                        </c:if>                        
                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </s:form>
</body>
