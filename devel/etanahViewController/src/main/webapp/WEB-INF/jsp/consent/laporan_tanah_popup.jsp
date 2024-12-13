<%-- 
    Document   : laporan_tanah_popup
    Created on : May 31, 2013, 3:38:26 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MUATNAIK GAMBAR</title>
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
    
    
    function refreshpage(type){
        var url = '${pageContext.request.contextPath}/consent/laporan_tanah_ladang?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function refreshpage(){
        NoPrompt();
        opener.refreshV2('main');        
        self.close();
    }
                
    function uploadForm(pandanganImej) {
        NoPrompt();
        var idLapor = $('#idLapor').val();
        var idHakmilik = $('#idHakmilik').val();
        window.open("${pageContext.request.contextPath}/consent/laporan_tanah_ladang?uploadDoc&pandanganImej="+pandanganImej+"&idLapor="+idLapor+"&idHakmilik="+idHakmilik, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        
    function doSetDokumenHakmilik(){
        var idDokumen = document.getElementById("hmImej").options[document.getElementById("hmImej").selectedIndex].value;
        if(idDokumen != ''){
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
    }

</script>
<body>
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
                // Reset the flag to 
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }
        
    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.consent.LaporanTanahLadangActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <%--<s:hidden name="kodD" id="kodD"/>--%>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.laporanTanah.idLaporan}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>Muatnaik Gambar</legend>
                    <div class="content" align="center">

                        <table class="tablecloth" border="0">

                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Muatnaik Gambar Tanah :
                                </td>
                                <td>
                                    <s:select name="" style="width:300px;" id="hmImej" onchange="doSetDokumenHakmilik();">
                                        <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                        <s:options-collection collection="${actionBean.hakmilikImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                    </s:select>&nbsp;
                                    <s:hidden name="" id="idDokumenH" />
                                    <s:button name="uploadDoc" id="display" value="Ubah Suai" class="btn" onclick="uploadForm('H');"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br/>
                </div>

            </fieldset>
        </div>
        <fieldset class="aras1">
            <table  width="100%" border="0">
                <tr>
                    <td align="center">
                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                    </td>
                </tr>
            </table>   
        </fieldset>
    </s:form>
</body>
