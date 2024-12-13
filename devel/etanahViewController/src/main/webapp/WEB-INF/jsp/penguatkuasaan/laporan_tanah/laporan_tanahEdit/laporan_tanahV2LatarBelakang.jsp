<%--
    Document   :  laporan_tanahV2Sempadan.jsp
    Created on :  Feb 29, 2012, 1:18:13 PM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LATAR BELAKANG</title>
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
       
    }); //END OF READY FUNCTION
         
    function refreshpage2(type){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
        
    function addMohonLaporPohon(){
        NoPrompt();
        var idHakmilik = $('#idHakmilik').val();
        window.open("${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?showFormPopUpLatarBelakangTanah&idHakmilik="+idHakmilik+"&idLaporTanah="+${actionBean.laporanTanah.idLaporan}, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        
    function deleteRowMohonLaporPohon(idKandungan,f,tName)
    {   
        NoPrompt();
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?deleteRow&idKandungan='+idKandungan+'&tName='+tName,q,
            function(data){
                self.refreshpage2('lBelakangTanah') ;
                    
            }, 'html');
          alert("Maklumat telah berjaya dihapuskan");         
            
        }
    }
    function refreshpage(){
        NoPrompt();
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                var idHakmilik = $('#idHakmilik').val();
                opener.refreshV2ManyHakmilik('main',idHakmilik);
        </c:when>
        <c:otherwise>
                opener.refreshV2('main');
        </c:otherwise>
    </c:choose>
            self.close();
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
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.penguatkuasaan.LaporanTanahV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.laporanTanah.idLaporan}"/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle" id="main">
            <fieldset class="aras1">

                <div id="jtek">
                    <legend>Senarai Latar Belakang Tanah</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <th>Bil</th><th>Jenis Dipohon</th><th>No Rujukan / No Fail</th><th>Kegunaan</th><th>Tindakan</th>
                            </tr>
                            <%--JABATAN TEKNIKAL--%>
                            <c:if test="${!empty actionBean.senaraiPermohonanLaporanPohon}">
                                <c:set var="i" value="1" />
                                <tr>
                                    <c:forEach items="${actionBean.senaraiPermohonanLaporanPohon}" var="line">

                                        <td>
                                            ${i}
                                        </td>
                                        <td>
                                            <c:if test="${line.jenisDipohon eq 'H'}">
                                                Pemberimilikan
                                            </c:if>
                                            <c:if test="${line.jenisDipohon eq 'LP'}">
                                                LPS
                                            </c:if>
                                            <c:if test="${line.jenisDipohon eq 'P'}">
                                                Permit
                                            </c:if>
                                            <c:if test="${line.jenisDipohon eq 'R'}">
                                                Rizab
                                            </c:if>
                                            <c:if test="${line.jenisDipohon eq 'M'}">
                                                Manual
                                            </c:if>
                                        </td>
                                        <td>
                                            ${line.noRujukan}
                                        </td>
                                        <td>
                                            ${line.kegunaan}
                                        </td>
                                        <td>
                                            <a onclick="deleteRowMohonLaporPohon(${line.idLaporanPohon},this.form,'mohonLaporPohon')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> 
                                        </td>
                                    </tr>

                                    <c:set var="i" value="${i+1}" />        
                                </c:forEach>
                            </c:if>
                        </table>

                    </div>
                    <br/>
                </div>
            </fieldset>

            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <s:button name="Tambah" id="save" value="Tambah" class="btn" onclick="addMohonLaporPohon()"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>
        </div>
    </s:form>
</body>