<%-- 
    Document   : laporan_pelanV2DalamKawasan
    Created on : Feb 19, 2013, 3:22:58 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dalam Kawasan</title>
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
            //        alert(type);
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?refreshpage&type='+type;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
    
        function refreshpage(){
            //        alert('aa');
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
        
        function deleteRow(idMohonlaporKws,f,tName)
        {   
            NoPrompt();
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?deleteRow&idKand='+idMohonlaporKws+'&tName='+tName,q,
                function(data){
                    $('#page_div').html(data);
                    
                }, 'html');
                
                self.refreshpage2('dKawasan') ;
            }
        }
        
        function refreshpage2(type){
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        function editKawasan(idMohonLaporKws){
    <%--alert(id);--%>
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            var noPtSementara = $('#noPtSementara').val();
//            alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?showFormPopUp1&idHakmilik="+idHakmilik+"&noPtSementara="+noPtSementara+"&idMohonLaporKws="+idMohonLaporKws, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function addKawasan(){
    <%--alert(id);--%>
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            var noPtSementara = $('#noPtSementara').val();
//            alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?showFormPopUp1&idHakmilik="+idHakmilik+"&noPtSementara="+noPtSementara, "eTanah",
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
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanPelanV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="kodD" id="kodD"/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="noPtSementara" id="noPtSementara"/>
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.permohonanLaporanPelan.idLaporan}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                    <legend>Dalam Kawasan</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <th>Jenis Rizab</th><th>Catatan</th><th>No. Warta</th><th>Tarikh Warta</th><th>No. Pelan Warta</th><th>Tindakan</th>
                            </tr>
                            <c:if test="${!empty actionBean.senaraiLaporanKawasan}">
                                <c:set var="i" value="1" />
                                <tr>
                                    <c:forEach items="${actionBean.senaraiLaporanKawasan}" var="line">

                                        <td>
                                            ${line.kodRizab.nama}
                                        </td>
                                        <td>
                                            ${line.catatan}
                                        </td>
                                        <td>
                                            ${line.noWarta}
                                        </td>
                                        <td>
                                            <s:format value="${line.tarikhWarta}" formatPattern="dd/MM/yyyy"/>
                                        </td>
                                        <td>
                                            ${line.noPelanWarta}
                                        </td>
                                        <td>
                                            <a onclick="editKawasan(${line.idMohonlaporKws})" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            <a onclick="deleteRow(${line.idMohonlaporKws},this.form,'mohonLaporKWS')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a>
                                        </td>
                                    </tr>

                                    <c:set var="i" value="${i+1}" />        
                                </c:forEach>
                            </c:if>
                        </table>
                    </div>
                    <br/>

            </fieldset>
        </div>
        <fieldset class="aras1">
            <table  width="100%" border="0">
                <tr>
                    <td align="center">
                        <s:button name="Tambah" id="save" value="Tambah" class="btn" onclick="addKawasan()"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                    </td>
                </tr>
            </table>   
        </fieldset>
    </s:form>
</body>

