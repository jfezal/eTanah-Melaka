<%-- 
    Document   : pendepositEditV2
    Created on : Nov 29, 2012, 10:08:13 AM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kemaskini Maklumat Pendeposit</title>
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
        var url = '${pageContext.request.contextPath}/pelupusan/pendepositV2?refreshpage&type='+type;
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
        
    function addPendeposit(){
    <%--alert(id);--%>
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            //            alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pelupusan/pendepositV2?showFormPopUp&idHakmilik="+idHakmilik, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function deleteRowSmpdn(idPihakPendeposit,f,tName)
        {   
            NoPrompt();
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/pendepositV2?deleteRow&idPihakPendeposit='+idPihakPendeposit+'&tName='+tName,q,
                function(data){
                    $('#page_div').html(data);
                    
                }, 'html');
                
                self.refreshpage2('pendeposit') ;
            }
        }
        function editPendeposit(idPihakPendeposit)
        {
            NoPrompt();
            window.open("${pageContext.request.contextPath}/pelupusan/pendepositV2?showFormKemaskiniPendeposit&idPihakPendeposit="+idPihakPendeposit, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        function editBank(idPihakPendeposit)
        {
            NoPrompt();
            window.open("${pageContext.request.contextPath}/pelupusan/pendepositV2?showFormKemaskiniBank&idPihakPendeposit="+idPihakPendeposit, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
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
        
        function uploadForm(pandanganImej,idlaporTnhSmpdn) {
            NoPrompt();
            //            alert(idlaporTnhSmpdn);
            var idLapor = $('#idLapor').val();
            //            alert(idLapor);
            window.open("${pageContext.request.contextPath}/pelupusan/pendepositV2?uploadDoc&pandanganImej="+pandanganImej+"&idLapor="+idLapor+'&idlaporTnhSmpdn='+idlaporTnhSmpdn, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
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
    <s:form beanclass="etanah.view.stripes.pelupusan.common.PendepositV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle" id="main">
            <fieldset class="aras1">
                <div id="maklumatpendeposit">
                    <legend>Maklumat Pendeposit</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                            <th>Nama</th><th>No Pengenalan</th><th>Cara Bayar</th><th>Bank</th><th>No. Akaun</th><th>Tindakan</th>
                            <c:if test="${!empty actionBean.permohonanPihakPendepositList}">
                               
                            <tr>
                                <c:forEach items="${actionBean.permohonanPihakPendepositList}" var="line">

                                    <td>
                                        ${line.pihak.nama}
                                       <%--<s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />--%>
                                    </td>
                                    <td>
                                        ${line.pihak.noPengenalan}
                                        <%--<s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />--%>
                                    </td>
                                    <td>
                                        ${line.caraBayaran} - 
                                        <c:if test="${line.caraBayaran eq 'CT'}">
                                            Cek Tempatan
                                        </c:if>
                                        <c:if test="${line.caraBayaran eq 'EF'}">
                                            Wang Dalam Pindahan
                                        </c:if>
                                        <c:if test="${line.caraBayaran eq 'DB'}">
                                            Deraf Bank
                                        </c:if>
                                       <%--<s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />--%>
                                    </td>
                                    <td>
                                        ${line.bank.nama}
                                        <%--<s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />--%>
                                    </td>
                                    <td>
                                        ${line.noAkaun}
                                        <%--<s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />--%>
                                    </td>
                                     <td>
                                            <a onclick="deleteRowSmpdn(${line.idPermohonanPihakPendeposit},this.form,'mohonPihakPendeposit')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                            <a onclick="editPendeposit(${line.idPermohonanPihakPendeposit})" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini Pendeposit"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            <%--<a onclick="editBank(${line.idPermohonanPihakPendeposit})" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini Bank"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/upload.png'/></a>--%>
                                        </td>
                                </tr>

                                     
                            </c:forEach>
                        </c:if>
                        </table>
                    </div>
                </div>
            </fieldset>
            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <s:button name="Tambah" id="save" value="Tambah" class="btn" onclick="addPendeposit()"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>
        </div>
    </s:form>
</body>